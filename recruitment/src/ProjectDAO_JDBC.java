import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ProjectDAO_JDBC implements ProjectDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public ProjectDAO_JDBC(Connection dbconn){
		
		dbConnection = dbconn;
	
	}

	@Override
	public Project getProjectByCode(String code) {
	
		Project p = new Project();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Project_Code, Status from Project where Project_Code = \"" + code + "\";";
			ResultSet project = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(project.next()){
			
				String project_code  = project.getString("Project_Code");
				String status = project.getString("Status");
				p.setProject_Code(project_code);
				p.setStatus(status);
				
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Department_Code from Project, Department where Project_Code = \"" + code + "\" and Department.Department_ID = Project.Department_ID;";
			ResultSet dept_code = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(dept_code.next()){
			
				String deptCode = dept_code.getString("Department_Code");
				p.setDepartment_Code(deptCode);
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		return p;
	}

	@Override
	public void addProject(Project p) {
		
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into Project(Project_Code, Status, Department_Code) values (?,?,?);";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, p.getProject_Code());
			preparedStatement.setString(2, p.getStatus());
			preparedStatement.setString(3, p.getDepartment_Code());
			 
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
	
	@Override
	public void changeStatus(String code) { 
		
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "update Project set Status = \"completed\" where Project_Code = \"" + code + "\";";
	
		try {
			
			preparedStatement = dbConnection.prepareStatement(sql);
	
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
public Vector<Project> getAllProjects() {
		
		Vector<Project> project= new Vector<Project>();
		Vector<String> codes = new Vector<String>();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Project_Code from Project;";
			ResultSet d = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(d.next()){
			
				String t  = d.getString("Project_Code");
				codes.add(t);
			}
			
			for (String s : codes) {
					
				Project temp1 = getProjectByCode(s);
				project.add(temp1);
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	return project;
	}
}