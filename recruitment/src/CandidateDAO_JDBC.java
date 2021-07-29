import java.sql.*;
import java.util.Vector;

public class CandidateDAO_JDBC implements CandidateDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public CandidateDAO_JDBC(Connection dbconn){
		
		dbConnection = dbconn;
	
	}

	@Override
	public Candidate getCandidateByCode(String code) {
		Candidate c = new Candidate();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Candidate_Code, Candidate_Name from Candidate where Candidate_Code = \"" + code + "\";";
			ResultSet candidate = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(candidate.next()){
			
				String candidate_code  = candidate.getString("Candidate_Code");
				String candidate_name = candidate.getString("Candidate_Name");
				c.setCandidate_Code(candidate_code);
				c.setCandidate_Name(candidate_name);
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		return c;
	}

	@Override
	public void addCandidate(Candidate candidate) {
		
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into Candidate(Candidate_Code, Candidate_Name) values (?,?);";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, candidate.getCandidate_Code());
			preparedStatement.setString(2, candidate.getCandidate_Name());
 
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
	public void removeCandidate(String code) { 
		
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "delete from Candidate where Candidate_code = \"" + code + "\";";
	
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
	
public Vector<Candidate> getAllCandidates() {
		
		Vector<Candidate> candidate = new Vector<Candidate>();
		Vector<String> codes = new Vector<String>();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Candidate_Code from Candidate;";
			ResultSet d = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(d.next()){
			
				String t  = d.getString("Candidate_Code");
				codes.add(t);
			}
			
			for (String s : codes) {
					
				Candidate temp1 = getCandidateByCode(s);
					candidate.add(temp1);
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	return candidate;
	}
}
