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
import com.bookworms.library.service.library.LibraryService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final BookStatusRepository bookStatusRepository;
    private final JavaMailSender javaMailSender;

    public BorrowService(BorrowRepository borrowRepository, CustomerRepository customerRepository, BookRepository bookRepository,
                         JavaMailSender javaMailSender, BookStatusRepository bookStatusRepository) {
        this.borrowRepository = borrowRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.javaMailSender = javaMailSender;
        this.bookStatusRepository = bookStatusRepository;
    }

    @Transactional
    public Borrow createBorrow(Customer customerRequest, Book bookRequest, String status) {
        CustomerEntity customerEntity = customerRepository.getOne(customerRequest.getUserData().getId());
        Customer customer = new Customer(customerEntity);
        BookEntity bookEntity = bookRepository.getOne(bookRequest.getId());
        Book book = new Book(bookEntity);
        Borrow borrow = new Borrow(customer,book,LocalDate.now(),LocalDate.now(),BigDecimal.ZERO,"Book not available");
        BookStatus savedStatus = book.getStatus();
        if (savedStatus.isAvailable()) {
        BorrowEnity borrowEnity = new BorrowEnity(
                customerEntity,
                bookEntity,
                LocalDate.now(),
                LocalDate.now().plusWeeks(2L),
                BigDecimal.ZERO,
                status);
        borrow = new Borrow(borrowRepository.save(borrowEnity));
        }
        return borrow;
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void notifyBorrowers() {
        borrowRepository.findAll()
                .stream()
                .map(Borrow::new)
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
        Book book = new Book(bookRepository.getOne(bookId));
        Customer customer = new Customer(customerRepository.getOne(customerId));
        BookStatus status = book.getStatus();
        status.addSubscriber(customer);

        bookStatusRepository.save(new BookStatusEntity(
                status.getId(),
                status.getOverAllCopies(),
                status.getAvailableCopies(),
                status.getSubscribers().stream()
                        .map(Customer::getUserData)
                        .map(UserData::getId)
                        .map(customerRepository::getOne)
                .collect(Collectors.toList())
                ));
        return new Book(bookRepository.getOne(bookId));
    }
}
