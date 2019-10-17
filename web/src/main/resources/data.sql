CREATE TABLE IF NOT EXISTS library_user (
    id bigint NOT NULL,
    fullname text NOT NULL,
    email text NOT NULL,
    isactive boolean
);

INSERT INTO library_user VALUES (1,'Jani', 'Janos_Pillinger@epam.com',true);

CREATE TABLE IF NOT EXISTS book (
    id bigint NOT NULL,
    author text,
    title text,
    genre text,
    status_id bigint
);

CREATE TABLE IF NOT EXISTS borrow(
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

INSERT INTO book VALUES (1,'Douglas Adams','The Hitchhiker\’s Guide to the Galaxy','Sci-fi',1);


