

CREATE TABLE IF NOT EXISTS library_user (
    id bigint NOT NULL,
    fullname text NOT NULL,
    email text NOT NULL,
    isreturned boolean
);

INSERT INTO library_user VALUES (1,'Jani', 'Janos_Pillinger@epam.com',true);

create TABLE IF NOT EXISTS book (
    author text,
    title text,
    category text,
    genre text,
    status_id bigint
);

