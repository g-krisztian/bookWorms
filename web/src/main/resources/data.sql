

CREATE TABLE "library_user" (
    id bigint NOT NULL,
    fullname text NOT NULL,
    email text NOT NULL,
    isactive boolean
);

create table "book" (
    author text,
    title text,
    category text,
    genre text,
    status_id bigint
);

