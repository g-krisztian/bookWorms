package com.bookworms.library.service.library;

import com.bookworms.library.dao.repositories.BorrowRepository;
import com.bookworms.library.service.domain.Borrow;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class LibraryService {

    private final BorrowRepository borrowRepository;

    @Autowired
    public LibraryService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    private List<Borrow> pendingBorrows = new ArrayList<>();
    private List<Borrow> activeBorrows = new ArrayList<>();
    private List<Borrow> pendingReturnBorrows = new ArrayList<>();

    public void addPendingBorrow(Borrow borrow) {
        pendingBorrows.add(borrow);
    }

    public void addActiveBorrow(Borrow borrow) {
        activeBorrows.add(borrow);
    }
}
