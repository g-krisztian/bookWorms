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
        Borrow borrow = new Borrow(customer,
                book,
                LocalDate.now(),
                LocalDate.now(),
                BigDecimal.ZERO,
                "Book not available");
        BookStatus savedStatus = book.getStatus();
        if (savedStatus.isAvailable()) {
            bookEntity.getStatus().setAvailableCopies(bookEntity.getStatus().getAvailableCopies() - 1);
            BorrowEnity borrowEnity = new BorrowEnity(
                    customerEntity,
                    bookEntity,
                    LocalDate.now(),
                    LocalDate.now().plusWeeks(2L),
                    BigDecimal.ZERO,
                    status);
            borrow = borrowTransformer.transform(borrowRepository.save(borrowEnity));
        }
        return borrow;
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
                " Sincerely: Librarian Team", userName, author, title, days, days > 1 ? "s" : "");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Your borrow near to exceed");
        msg.setText(message);
        javaMailSender.send(msg);
    }

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
        BookStatus status = borrow.getBook().getStatus();
        status.setAvailableCopies(status.getAvailableCopies() + 1);
        borrow.setStatus("closed");
        updateStatus(status);
        return borrowTransformer.transform(borrowRepository.save(entity));
    }
}
