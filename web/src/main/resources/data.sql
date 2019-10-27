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
merge INTO customers (id,full_name,email,is_active) VALUES (customer_id_seq.nextval,'G Krisztian', 'gulyas.krisztian1o9@gmail.com',true);

create TABLE if not exists bookstatus(
    id bigint NOT NULL,
    available_copies bigint,
    over_all_copies bigint
);

MERGE INTO bookstatus (id,available_copies,over_all_copies) VALUES (book_status_id_seq.nextval,1,1);


create TABLE IF NOT EXISTS books (
    id bigint NOT NULL,
    author text,
    title text,
    genre text,
    print_type text,
    status_id bigint,
    FOREIGN KEY (status_id) REFERENCES bookstatus(id)
);

MERGE INTO books (id,author,title,genre,print_type,status_id) VALUES (book_id_seq.nextval,'Douglas Adams','The Hitchhikers Guide to the Galaxy','SCIFI','BOOK',book_status_id_seq.currval);

create TABLE IF NOT EXISTS borrows(
    id bigint NOT NULL,
    customerid bigint,
    bookid bigint,
    start_date date,
    end_date date,
    library_fine decimal,
    status text,
    FOREIGN KEY (customerid) REFERENCES customers(id),
    FOREIGN KEY (bookid) REFERENCES book(id)
);

