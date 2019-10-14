package com.bookworms.library.service;

import com.bookworms.library.dao.repositories.LibraryDao;
import com.bookworms.library.service.domain.Borrow;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LibraryService {

    @Autowired
    private LibraryDao libraryDao;

    private List<Borrow> pendingBorrows = new ArrayList<>();
    private List<Borrow> activeBorrows = new ArrayList<>();
    private List<Borrow> pendingReturnBorrows = new ArrayList<>();

    public void addPendingBorrow(Borrow borrow) {
        pendingBorrows.add(borrow);
        libraryDao.addPendingBorrow(borrow.getId());
    }

    public void addActiveBorrow(Borrow borrow) {
        activeBorrows.add(borrow);
        libraryDao.addActiveBorrow(borrow.getId());
    }
}
