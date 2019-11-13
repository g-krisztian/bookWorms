package com.bookworms.library.service;

import com.bookworms.library.dao.entities.BookEntity;
import com.bookworms.library.dao.entities.BookStatusEntity;
import com.bookworms.library.dao.entities.BorrowEnity;
import com.bookworms.library.dao.entities.CustomerEntity;
import com.bookworms.library.dao.repositories.BookRepository;
import com.bookworms.library.dao.repositories.BookStatusRepository;
import com.bookworms.library.dao.repositories.BorrowRepository;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.bookworms.library.service.transformer.BookTransformer;
import com.bookworms.library.service.transformer.BorrowTransformer;
import com.bookworms.library.service.transformer.CustomerTransformer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final BookStatusRepository bookStatusRepository;
    private final JavaMailSender javaMailSender;
    private final BookTransformer bookTrasformer;
    private final BorrowTransformer borrowTransformer;
    private CustomerTransformer customerTransformer;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository, CustomerRepository customerRepository, BookRepository bookRepository,
                         JavaMailSender javaMailSender, BookStatusRepository bookStatusRepository, BookTransformer bookTrasformer, BorrowTransformer borrowTransformer, CustomerTransformer customerTransformer) {
        this.borrowRepository = borrowRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.javaMailSender = javaMailSender;
        this.bookStatusRepository = bookStatusRepository;
        this.bookTrasformer = bookTrasformer;
        this.borrowTransformer = borrowTransformer;
        this.customerTransformer = customerTransformer;
    }

    @Transactional
    public Borrow createBorrow(Long customerId, Long bookId, String status) {
        CustomerEntity customerEntity = customerRepository.getOne(customerId);
        Customer customer = customerTransformer.transform(customerEntity);
        BookEntity bookEntity = bookRepository.getOne(bookId);
        Book book = bookTrasformer.transform(bookEntity);

        BookStatus savedStatus = book.getStatus();
        Borrow borrow = Borrow.builder()
                .book(book)
                .customer(customer)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(2L))
                .libraryFinePerDay(BigDecimal.ZERO)
                .build();

        if (savedStatus.isAvailable()) {
            savedStatus.createBorrow();
            borrow.setStatus(status);
            borrow.setId(saveBorrow(customerEntity, bookEntity, borrow).getId());
        } else borrow.setStatus("Book not available");
        return borrow;
    }

    private BorrowEnity saveBorrow(CustomerEntity customerEntity, BookEntity bookEntity, Borrow borrow) {
        return borrowRepository.save(new BorrowEnity(
                customerEntity,
                bookEntity,
                borrow.getStartDate(),
                borrow.getEndDate(),
                borrow.getLibraryFinePerDay(),
                borrow.getStatus()));
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void notifyBorrowers() {
        borrowRepository.findAll()
                .stream()
                .map(borrowTransformer::transform)
                .filter(b -> "active".equals(b.getStatus()))
                .filter(borrow -> borrow.getEndDate().minusDays(4).compareTo(LocalDate.now()) < 0)
                .forEach(this::sendNotifyEmail);
    }

    private void sendNotifyEmail(Borrow borrow) {
        String userName = borrow.getCustomer().getUserData().getFullName();
        String email = borrow.getCustomer().getUserData().getEmail();
        String author = borrow.getBook().getAuthor();
        String title = borrow.getBook().getTitle();
        int days = borrow.getEndDate().compareTo(LocalDate.now());
        String message = String.format("Dear %s!\n" +
                " The book you borrowed (%s: %s) going to exceed in %d day%s. \n" +
                " Sincerely: Librarian Team", userName, author, title, days, days == 1 ? "" : "s");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Your borrow near to exceed");
        msg.setText(message);
        javaMailSender.send(msg);
    }

    @Transactional
    public Book subscribe(Long customerId, Long bookId) {
        Book book = bookTrasformer.transform(bookRepository.getOne(bookId));
        Customer customer = customerTransformer.transform(customerRepository.getOne(customerId));
        BookStatus status = book.getStatus();
        status.addSubscriber(customer);
        updateStatus(status);
        return bookTrasformer.transform(bookRepository.getOne(bookId));
    }

    private void updateStatus(BookStatus status) {
        bookStatusRepository.save(new BookStatusEntity(
                status.getId(),
                status.getOverAllCopies(),
                status.getAvailableCopies(),
                status.getSubscribers().parallelStream()
                        .map(Customer::getUserData)
                        .map(UserData::getId)
                        .map(customerRepository::getOne)
                        .collect(Collectors.toList())
        ));
    }

    public List<Borrow> getBorrowByState(String status) {
        return borrowRepository.findAllByStatus(status).stream().map(borrowTransformer::transform).collect(Collectors.toList());
    }

    @Transactional
    public Borrow modifyBorrow(Long borrowId, String status) {
        BorrowEnity entity = borrowRepository.getOne(borrowId);
        entity.setStatus(status);
        return borrowTransformer.transform(borrowRepository.save(entity));
    }

    @Transactional
    public Borrow closeBorrow(Long borrowId) {
        BorrowEnity entity = borrowRepository.getOne(borrowId);
        entity.setStatus("closed");
        Borrow borrow = borrowTransformer.transform(entity);
        BookStatus status = borrow.getBook().getStatus().closeBorrow();
        updateStatus(status);
        return borrowTransformer.transform(borrowRepository.save(entity));
    }

    public List<Borrow> getBorrowsByUserId(Long id) {
        return borrowRepository.findAllByCustomerId(id).stream().map(borrowTransformer::transform).collect(Collectors.toList());
    }

    public void notifySubscribers(Book book) {
        book.getStatus().getSubscribers().forEach(c -> sendNotifyEmail(book, c));
    }

    private void sendNotifyEmail(Book book, Customer customer) {
        String userName = customer.getUserData().getFullName();
        String email = customer.getUserData().getEmail();
        String author = book.getAuthor();
        String title = book.getTitle();
        String message = String.format("Dear %s!\n" +
                " The book that you subscribed (%s: %s) is available. \n" +
                " Sincerely: Librarian Team", userName, author, title);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject(String.format("%s: %s is available now", author, title));
        msg.setText(message);
        javaMailSender.send(msg);
    }
}
