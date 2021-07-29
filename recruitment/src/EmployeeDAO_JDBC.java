import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class EmployeeDAO_JDBC implements EmployeeDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public EmployeeDAO_JDBC(Connection dbconn){
		
		dbConnection = dbconn;
	
	}

	@Override
	public Employee getEmployeeByCode(String code) {
		Employee e = new Employee();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Employee_Code, Employee_Name from Employee where Employee_Code = \"" + code + "\";";
			ResultSet employee = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(employee.next()){
			
				String employee_code  = employee.getString("Employee_Code");
				String employee_name = employee.getString("Employee_Name");
				e.setEmployee_Code(employee_code);
				e.setEmployee_Name(employee_name);
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Department_Code from Employee, Department where Employee_Code = \"" + code + "\" and Employee.Department_ID = Department.Department_ID;";
			ResultSet deptCode = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(deptCode.next()){
			
				int dept_code  = deptCode.getInt("Department_Code");
				e.setDepartment_Code(dept_code);
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		try{
			stmt = dbConnection.createStatement();
			sql = "select e2.Employee_Code from Employee e1, Employee e2 where e1.Employee_Code = \"" + code + "\" and e1.Manager_ID = e2.Employee_ID;";
			ResultSet mgrCode = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(mgrCode.next()){
			
				int mgr_code  = mgrCode.getInt("e2.Employee_Code");
				e.setManager_Code(mgr_code);
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return e;
	}

	@Override
public void addEmployee(Employee employee) {
		
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into Employee(Employee_Code, Employee_Name, Department_ID, Manager_ID) values (?,?,?,?);";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, employee.getEmployee_Code());
			preparedStatement.setString(2, employee.getEmployee_Name());
			preparedStatement.setInt(3, employee.getDepartment_Code());
			preparedStatement.setInt(4, employee.getManager_Code());

			preparedStatement.executeUpdate();
			
		} 
		
		catch (SQLException e) {
 			
			System.out.println(e.getMessage());
 		
		}

		try {
			
			if (preparedStatement != null) {
			
				preparedStatement.close();
			
			}
			
		} 
		
		catch (SQLException e) {
 		
			System.out.println(e.getMessage());
 		
		}
	
	}
public Vector<Employee> getAllEmployees() {
		
		Vector<Employee> employee = new Vector<Employee>();
		Vector<String> codes = new Vector<String>();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Employee_Code from Employee;";
			ResultSet d = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(d.next()){
			
				String t  = d.getString("Employee_Code");
				codes.add(t);
			}
			
			for (String s : codes) {
					
				Employee temp1 = getEmployeeByCode(s);
					employee.add(temp1);
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	return employee;
	}

}