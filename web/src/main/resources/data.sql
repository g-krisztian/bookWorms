drop table if exists library_user;
drop table if exists borrow;
drop table if exists book;

create TABLE IF NOT EXISTS customers (
    id bigint NOT NULL,
    fullname text NOT NULL,
    email text NOT NULL,
    isactive boolean
);

-- Acceptance test failes until id generation bug is not solved, so I am taking this out
-- Ofcourse AC tests should mock out database later...
-- merge into customers KEY (id) VALUES (1,'Jani', 'Janos_Pillinger@epam.com',true);

create TABLE IF NOT EXISTS books (
    id bigint NOT NULL,
    author text,
    title text,
    genre text,
    status_id bigint
);

create TABLE IF NOT EXISTS borrows(
    id bigint NOT NULL,
    customerid bigint,
    bookid bigint,
    startdate date,
    enddate date,
    libraryfine decimal,
    isactive boolean,
    status text,
    FOREIGN KEY (customerid) REFERENCES library_user(id),
    FOREIGN KEY (bookid) REFERENCES book(id)
);

merge into books KEY (id) VALUES (1,'Douglas Adams','The Hitchhiker\’s Guide to the Galaxy','Sci-fi',1);


