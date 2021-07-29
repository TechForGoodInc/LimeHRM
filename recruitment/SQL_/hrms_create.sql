create table Candidate(
Candidate_ID int AUTO_INCREMENT,
Candidate_Code varchar(4) UNIQUE,
Candidate_Name varchar(30) NOT NULL,
constraint pk_Candidate PRIMARY KEY (Candidate_ID)
);

create table Employee(
Employee_ID int AUTO_INCREMENT,
Employee_Code varchar(4) UNIQUE,
Employee_Name varchar(30) NOT NULL,
Department_ID int,
Manager_ID int,
constraint pk_Employee PRIMARY KEY (Employee_ID)
);

create table Department(
Department_ID int AUTO_INCREMENT,
Department_Code varchar(4) UNIQUE,
Department_Name varchar(30) NOT NULL,
Manager_ID int,
constraint pk_Department PRIMARY KEY (Department_ID)
);

create table Project(
Project_ID int AUTO_INCREMENT,
Project_Code varchar(4) UNIQUE,
Status varchar(10),
Department_ID int,
constraint pk_Project PRIMARY KEY (Project_ID)
);

create table Hiring_Request(
Hiring_Request_ID int AUTO_INCREMENT,
Hiring_Request_Code varchar(4) UNIQUE,
Status varchar(10),
Manager_ID int,
constraint pk_Hiring_Request PRIMARY KEY (Hiring_Request_ID)
);

create table Candidate_Application(
Candidate_Application_ID int AUTO_INCREMENT,
Candidate_Application_Code varchar(4) UNIQUE,
Hiring_Request_ID int,
Candidate_ID int,
constraint pk_Candidate_Application PRIMARY KEY (Candidate_Application_ID)
);
