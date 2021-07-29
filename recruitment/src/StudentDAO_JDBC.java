import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class StudentDAO_JDBC implements StudentDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public StudentDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	@Override
	public Employee getStudentByKey(String ssn) {
		Student s = new Student();
		Employee e = new Employee();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select employee_name from employee where employee_ssn = '" + ssn+"';";
			ResultSet rs = stmt.executeQuery(sql);
																																																																																																																																																																																			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String name = rs.getString("employee_name");
				e.setEmployee_name(name);
				break;
				// Add exception handling here if more than one row is returned
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		return e;
	}

	@Override
	public void addStudent(Student student) {
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into student(rollno, name) values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setInt(1, student.getRollno());
			preparedStatement.setString(2, student.getName());
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
 
			System.out.println("Student: Roll No " + student.getRollno() 
				+ ", added to the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}
}
