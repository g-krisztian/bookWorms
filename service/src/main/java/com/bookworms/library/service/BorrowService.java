package com.bookworms.library.service;

import com.bookworms.library.dao.entities.BorrowEnity;
import com.bookworms.library.dao.repositories.BookRepository;
import com.bookworms.library.dao.repositories.BorrowRepository;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.library.LibraryService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final JavaMailSender javaMailSender;

    public BorrowService(BorrowRepository borrowRepository, CustomerRepository customerRepository, BookRepository bookRepository,
                         JavaMailSender javaMailSender, LibraryService libraryService) {
        this.borrowRepository = borrowRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public Borrow createBorrow(Customer customer, Book book, String status) {
        BorrowEnity borrowEnity = new BorrowEnity(customerRepository.getOne(customer.getUserData().getId()),
                bookRepository.getOne(book.getId()),
                LocalDate.now(),
                LocalDate.now().plusWeeks(2L),
                BigDecimal.ZERO,
                status);
        BorrowEnity saved = borrowRepository.save(borrowEnity);
        return new Borrow(saved);
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
}
