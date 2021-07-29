import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.sql.*;
import java.util.*;

public class Demo {

	public static DAO_Factory daoFactory;
	private static BufferedReader br;
	public static void main(String[] args) {
	
		try{
			daoFactory = new DAO_Factory();
			
			Scanner in = new Scanner(System.in);
					
			System.out.println("Select type of User: ");
			System.out.println();
			System.out.println("1. Recruitment Manager");
			System.out.println("2. Manager");
			System.out.println("3. Candidate");
			
			int option = in.nextInt();
			
			if (option == 1) {
				
					implementRMUsecases();
				
					}		
			
			else if (option == 2) {
				
				implementManagerusecases();
				
			}
			
			else if (option == 3) {
				
			}
		
		}
		
		catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
		}
	}
	
public static void implementRMUsecases() throws Exception {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("1. View Hiring Requests");
		System.out.println("2. View Candidates");
		System.out.println("3. Recruit Candidate");

		int option = in.nextInt();
		
		if (option == 1) {
			try{
			daoFactory.activateConnection();
			
			Hiring_RequestDAO hrdao = daoFactory.getHiringRequestDAO();
			
			Vector<Hiring_Request> hrs = hrdao.getAllHiringRequests();
			
			for (Hiring_Request temp : hrs)
			{
				temp.display();
			}
			
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
				
		}
		
		else if (option == 2) {
			try{
				daoFactory.activateConnection();
				
				CandidateDAO candidatedao = daoFactory.getCandidateDAO();
				
				Vector<Candidate > candidates = candidatedao.getAllCandidates();
				
				for (Candidate temp1 : candidates)
				{
					temp1.display();
				}
				
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
			}catch(Exception e){
					// End transaction boundary with failure
					daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
					e.printStackTrace();
			}
					
			
			
		}
		
		else if (option == 3) {
			
			
			rm();
		}
	}
	
public static void implementManagerusecases() {
	
	Scanner in = new Scanner(System.in);
	
	System.out.println("1. View Employees");
	System.out.println("2. Add Projects");
	System.out.println("3. View Projects");
	System.out.println("4. Create Hiring Requests");

	int option = in.nextInt();
	
	if (option == 1) {
		try{
		daoFactory.activateConnection();
		
		EmployeeDAO employeedao = daoFactory.getEmployeeDAO();
		
		Vector<Employee> employees = employeedao.getAllEmployees();
		
		for (Employee temp2 : employees)
		{
			temp2.display();
		}
		
		daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
	}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
	}
			
	}
	
	else if (option == 2) {
		try{
			System.out.print("Enter Project Code: ");
			Scanner input = new Scanner(System.in);
			String pro_code = input.next();
			System.out.print("Enter Status: ");
			String status = input.next();
			System.out.print("Enter Department Code: ");
			String dept_code = input.next();
			
			Project p = new Project();
			p.setDepartment_Code(dept_code);
			p.setProject_Code(pro_code);
			p.setStatus(status);
			
			daoFactory.activateConnection();
			
			ProjectDAO projectdao = daoFactory.getProjectDAO();
			
			projectdao.addProject( p );
			
			
			
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
				
		
		
	}
	
	else if (option == 3) {
		try{
			daoFactory.activateConnection();
			
			ProjectDAO projectdao = daoFactory.getProjectDAO();
			
			Vector<Project> projects = projectdao.getAllProjects();
			
			for (Project temp3 : projects)
			{
				temp3.display();
			}
			
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
			
		
	}
}
public static void rm() throws Exception{
	daoFactory.activateConnection();
	br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter Employee details");
			System.out.println("Enter Employee Name: ");
			String name = br.readLine();
			System.out.println("Enter Employee Code: ");
			String code = br.readLine();
			System.out.println("Enter Manager Id: ");
			int mid = Integer.parseInt(br.readLine());
			System.out.println("Enter Department id: ");
			int did = Integer.parseInt(br.readLine());
			Employee emp = new Employee(code, name);
			emp.setDepartment_Code(did);
			emp.setManager_Code(mid);
			try{
				//daoFactory.activateConnection();
				EmployeeDAO sdao = daoFactory.getEmployeeDAO();
				sdao.addEmployee(emp);
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
			}catch(Exception e){
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
			}
}

	
}