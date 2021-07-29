insert into Candidate(Candidate_Name, Candidate_Code, Candidate_ID) 
VALUES ('John','C1000', 1);

insert into Candidate(Candidate_Name, Candidate_Code, Candidate_ID) 
VALUES ('SushmitHa','C2000', 2);

insert into Candidate(Candidate_Name, Candidate_Code, Candidate_ID)  
VALUES ('Harika','C3000', 3);

insert into Candidate(Candidate_Name, Candidate_Code, Candidate_ID) 
VALUES ('Lukshya','C4000', 4);





insert into Employee(Employee_Name, Employee_Code, Department_ID, Manager_ID, Employee_ID) 
VALUES ('Gia', 'E06', NULL, NULL, 5);

insert into Employee(Employee_Name, Employee_Code, Department_ID, Manager_ID, Employee_ID) 
VALUES ('Hima', 'E07', NULL, NULL, 6);

insert into Employee(Employee_Name, Employee_Code, Department_ID, Manager_ID, Employee_ID) 
VALUES ('Isha', 'E08', NULL, NULL, 7);

insert into Employee(Employee_Name, Employee_Code, Department_ID, Manager_ID, Employee_ID)  
VALUES ('Joseph','E09', NULL, NULL, 8);

insert into Employee(Employee_Name, Employee_Code, Department_ID, Manager_ID, Employee_ID) 
VALUES ('Alice','E10', NULL, NULL, 9);


insert into Employee(Employee_Name, Employee_Code, Department_ID, Manager_ID, Employee_ID) 
VALUES ('Bob','E01', NULL, NULL, 10);

insert into Employee(Employee_Name, Employee_Code, Department_ID, Manager_ID, Employee_ID)   
VALUES ('Cavin','E02', NULL, NULL, 11);


insert into Department(Department_Name, Department_ID, Department_Code, Manager_ID) 
VALUES ('Marketing',300, 'A21',NULL);

insert into Department(Department_Name, Department_ID, Department_Code, Manager_ID) 
VALUES ('Development',301, 'A22' ,NULL);

insert into Department(Department_Name, Department_ID, Department_Code, Manager_ID) 
VALUES ('Design',302, 'A23' , NULL);

insert into Department(Department_Name, Department_ID, Department_Code, Manager_ID)  
VALUES ('Testing',303, 'A24' ,NULL);


insert into Project(Project_ID, Project_Code, Status, Department_ID) 
VALUES (21, 'P21','pending' , NULL);

insert into Project(Project_ID, Project_Code, Status, Department_ID) 
VALUES (22, 'P22','pending' , NULL);

insert into Project(Project_ID, Project_Code, Status, Department_ID) 
VALUES (23, 'P23','pending' , NULL);

insert into Project(Project_ID, Project_Code, Status, Department_ID) 
VALUES (24, 'P24','pending' , NULL);


insert into Hiring_Request(Hiring_Request_ID,Hiring_Request_Code,Status,Manager_ID ) 
VALUES (50,'H50','pending',NULL);

insert into Hiring_Request(Hiring_Request_ID,Hiring_Request_Code,Status,Manager_ID ) 
VALUES (51,'H51','pending',NULL);

insert into Hiring_Request(Hiring_Request_ID,Hiring_Request_Code,Status,Manager_ID ) 
VALUES (52,'H52','pending',NULL);

insert into Hiring_Request(Hiring_Request_ID,Hiring_Request_Code,Status,Manager_ID ) 
VALUES (53,'H53','pending',NULL);


insert into Candidate_Application(Candidate_Application_ID, Candidate_ID, Hiring_Request_ID, Candidate_Application_Code) 
VALUES (500, NULL, NULL, 'D30');


insert into Candidate_Application(Candidate_Application_ID, Candidate_ID, Hiring_Request_ID, Candidate_Application_Code) 
VALUES (501, NULL, NULL, 'D31');


insert into Candidate_Application(Candidate_Application_ID, Candidate_ID, Hiring_Request_ID, Candidate_Application_Code)  
VALUES (502, NULL, NULL, 'D32');


insert into Candidate_Application(Candidate_Application_ID, Candidate_ID, Hiring_Request_ID, Candidate_Application_Code) 
VALUES (503, NULL, NULL, 'D33');


update Employee
set Department_ID = 300, Manager_ID = 5 
where Employee_ID  = 6 ;

update Employee
set Department_ID = 301, Manager_ID = 7 
where Employee_ID  = 8 ;

update Employee
set Department_ID = 302, Manager_ID = 9 
where Employee_ID  = 10 ;


update Employee
set Department_ID = 300, Manager_ID = 5 
where Employee_ID  = 11 ;


update Project
set department_ID = 300
where project_id = 21;

update Project
set department_ID = 300
where project_id = 22;

update Project
set department_ID = 301
where project_id = 23;

update Project
set department_ID = 302
where project_id = 24;


update Hiring_Request
set Manager_ID = 5
where Hiring_Request_ID = 50;

update Hiring_Request
set Manager_ID = 5
where Hiring_Request_ID = 51;

update Hiring_Request
set Manager_ID = 7
where Hiring_Request_ID = 52;

update Hiring_Request
set Manager_ID = 9
where Hiring_Request_ID = 53;


update Candidate_Application
set Candidate_ID = 1,Hiring_Request_ID = 50
where Candidate_Application_ID = 500;

update Candidate_Application
set Candidate_ID = 2,Hiring_Request_ID = 51
where Candidate_Application_ID = 501;

update Candidate_Application
set Candidate_ID = 3,Hiring_Request_ID = 52
where Candidate_Application_ID = 502;

update Candidate_Application
set Candidate_ID = 4,Hiring_Request_ID = 53
where Candidate_Application_ID = 503;

update Department
set Manager_ID = 5
where Department_ID = 300;

update Department
set Manager_ID = 7
where Department_ID = 301;

update Department
set Manager_ID = 9
where Department_ID = 302;



























