import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Candidate_ApplicationDAO_JDBC implements Candidate_ApplicationDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public Candidate_ApplicationDAO_JDBC(Connection dbconn){
		
		dbConnection = dbconn;
	
	}

	@Override
	public Candidate_Application getCandidate_ApplicationByCode(String code) {
	
		Candidate_Application ca = new Candidate_Application();
		String sql;
		Statement stmt = null;
	
		ca.setCandidate_Application_Code(code);
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Candidate_Code from Candidate_Application, Candidate where Candidate_Application_Code = \"" + code + "\" and Candidate.Candidate_ID = Candidate_Application.Candidate_ID;";
			ResultSet can_code = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(can_code.next()){
			
				String canCode = can_code.getString("Candidate_Code");
				ca.setCandidate_Code(canCode);
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
			sql = "select Hiring_Request_Code from Candidate_Application, Hiring_Request where Candidate_Application_Code = \"" + code + "\" and Hiring_Request.Hiring_Request_ID = Candidate_Application.Hiring_Request_ID;";
			ResultSet hr_code = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(hr_code.next()){
			
				String hrCode = hr_code.getString("Hiring_Request_Code");
				ca.setHiring_Request_Code(hrCode);
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ca;
	}

	@Override
	public void addCandidate_Application(Candidate_Application cappl) {
		
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into Candidate_Application(Candidate_Application_Code, Hiring_Request_Code, Candidate_Code) values (?,?,?);";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, cappl.getCandidate_Application_Code());
			preparedStatement.setString(2, cappl.getHiring_Request_Code());
			preparedStatement.setString(3, cappl.getCandidate_Code());
			 
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
	
}