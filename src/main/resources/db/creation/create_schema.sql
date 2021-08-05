drop schema if exists limehrm cascade; -- TODO: Remove this line

create schema limehrm;

/* create custom types */

/* TODO: Fix Later */
-- drop type if exists limehrm.address;
-- create type limehrm.address as (
--     street_name text,
--     apartment_or_unit_number text,
--     city text,
--     state text,
--     zip text,
--     country text
-- );

create type limehrm.sex as enum ('MALE', 'FEMALE', 'OTHER');

create type limehrm.marital_status as enum ('SINGLE', 'MARRIED');

create type limehrm.job_status as enum ('ONGOING', 'TERMINATED', 'COMPLETED');

create type limehrm.leave_status as enum ('APPROVE', 'CANCEL', 'REJECT');

create type limehrm.stage as enum ('APPLICATION_RECIEVED', 'PHONE_SCREENING', 'REFERENCE_CHECK', 'JOB_OFFER', 'HIRED', 'REJECTED', 'IN_PERSON_INTERVIEW');


create table ROLES (ID INTEGER NOT NULL, "NAME" VARCHAR(255), PRIMARY KEY (ID));


create table limehrm.worker (
    id text not null,
    email text
    first_name text,
    last_name text,
    personal_email text,
    home_phone text,
    mobile_phone text,
    home_address jsonb,
    sex limehrm.sex,
    marital_status limehrm.marital_status,
    position_name text,
    team_name text,
    salary bool,
    department text,
    birth_date date,
    start_date date,
    end_date date,
    job_status limehrm.job_status,
    manager_email text
);

alter table limehrm.worker add primary key(id);


create table limehrm.user (
    email text not null,
    password text,
    userId text,
    google_id text,
    microsoft_id text
);

alter table limehrm.user add primary key(email);

create table limehrm.leave (
    email text
    id text not null,
    leave_type text,
    from_date date,
    to_date date,
    comment text,
    leave_status limehrm.leave_status

    
);

create table limehrm.recruitment (
    id text not null,
    vacancy text,
    candidate text,
    email text,
    contact_Number text,
    date_applied date,
    stage limehrm.stage

    
);
