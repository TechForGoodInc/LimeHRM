alter table Employee
drop FOREIGN KEY fk_Department_ID;

alter table Employee
drop FOREIGN KEY fk_Manager_ID;

alter table Department
drop FOREIGN KEY fk_Manager_ID1;

alter table Project
drop FOREIGN KEY fk_Department_ID1;

alter table Hiring_Request
drop FOREIGN KEY fk_Manager_ID2;

alter table Candidate_Application
drop FOREIGN KEY fk_Hiring_Request_ID;

alter table Candidate_Application
drop FOREIGN KEY fk_Candidate_ID;

drop table Candidate;
drop table Employee;
drop table Department;
drop table Project;
drop table Hiring_Request;
drop table Candidate_Application;
