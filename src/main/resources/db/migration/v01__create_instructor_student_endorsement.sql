CREATE DATABASE  IF NOT EXISTS `endorsement-tracking`;

create table instructor (
        id serial PRIMARY KEY NOT NULL,
        name varchar(50) NULL,
        email_address varchar(100) UNIQUE
);

create table student (
        id serial PRIMARY KEY NOT NULL,
        name varchar(50) NULL,
);

create table endorsement (
        id serial PRIMARY KEY NOT NULL,
        sign_date date NOT NULL,
        text varchar(200) NOT NULL,
        instructor_id serial FOREIGN KEY,
        student_id serial FOREIGN KEY,
);