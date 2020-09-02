drop schema if exists limehrm cascade; -- TODO: Remove this line

create schema limehrm;

/* create custom types */

/* TODO: Fix Later */
drop type if exists limehrm.address;
create type limehrm.address as (
    street_name text,
    apartment_or_unit_number text,
    city text,
    state text,
    zip text,
    country text
);

create type limehrm.sex as enum ('male', 'female', 'other');

create type limehrm.marital_status as enum ('single', 'married');

create type limehrm.job_status as enum ('ongoing', 'terminated', 'completed');

create table limehrm.worker (
    id int not null,
    first_name text,
    last_name text,
    personal_email text,
    home_phone text,
    mobile_phone text,
    home_address limehrm.address,
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