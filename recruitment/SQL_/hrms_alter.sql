alter table Employee
add constraint fk_Department_ID FOREIGN KEY (Department_ID)
REFERENCES Department(Department_ID);

alter table Employee
add constraint fk_Manager_ID FOREIGN KEY (Manager_ID)
REFERENCES Employee(Employee_ID);

alter table Department
add constraint fk_Manager_ID1 FOREIGN KEY (Manager_ID)
REFERENCES Employee(Employee_ID);

alter table Project
add constraint fk_Department_ID1 FOREIGN KEY (Department_ID)
REFERENCES Department(Department_ID);

alter table Hiring_Request
add constraint fk_Manager_ID2 FOREIGN KEY (Manager_ID)
REFERENCES Employee(Employee_ID);

alter table Candidate_Application
add constraint fk_Hiring_Request_ID FOREIGN KEY (Hiring_Request_ID)
REFERENCES Hiring_Request(Hiring_Request_ID);

alter table Candidate_Application
add constraint fk_Candidate_ID FOREIGN KEY (Candidate_ID)
REFERENCES Candidate(Candidate_ID)
ON DELETE CASCADE;
