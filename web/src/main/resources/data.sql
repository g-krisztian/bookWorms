drop table if exists library_user;
drop table if exists borrow;
drop table if exists book;

CREATE TABLE IF NOT EXISTS customers (
    id bigint NOT NULL,
    full_name text NOT NULL,
    email text NOT NULL,
    is_active boolean
);

merge INTO customers (id,full_name,email,is_active) VALUES (customer_id_seq.nextval,'Jani', 'Janos_Pillinger@epam.com',true);

create TABLE IF NOT EXISTS books (
    id bigint NOT NULL,
    author text,
    title text,
    genre text,
    print_type text,
    status_id bigint
);

MERGE INTO books (id,author,title,genre,print_type,status_id) VALUES (book_id_seq.nextval,'Douglas Adams','The Hitchhiker\’s Guide to the Galaxy','SCIFI','BOOK',1);

create TABLE IF NOT EXISTS borrows(
    id bigint NOT NULL,
    customerid bigint,
    bookid bigint,
    start_date date,
    end_date date,
    library_fine decimal,
    is_active boolean,
    status text,
    FOREIGN KEY (customerid) REFERENCES customers(id),
    FOREIGN KEY (bookid) REFERENCES book(id)
);

