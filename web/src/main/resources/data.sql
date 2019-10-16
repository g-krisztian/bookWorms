CREATE TABLE IF NOT EXISTS library_user (
    id bigint NOT NULL,
    fullname text NOT NULL,
    email text NOT NULL,
    isreturned boolean
);

INSERT INTO library_user VALUES (1,'Jani', 'Janos_Pillinger@epam.com',true);

create TABLE IF NOT EXISTS book (
    id bigint NOT NULL,
    author text,
    title text,
    genre text,
    status_id bigint
);

INSERT INTO book VALUES (1,'Douglas Adams','The Hitchhiker\’s Guide to the Galaxy','Sci-fi',1);

CREATE TABLE IF NOT EXISTS pending_borrows (
    borrowid bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS active_borrows (
    borrowid bigint NOT NULL
);
