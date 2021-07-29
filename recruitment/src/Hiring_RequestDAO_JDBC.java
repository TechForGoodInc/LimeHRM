import java.sql.*;
import java.util.Vector;

public class Hiring_RequestDAO_JDBC implements Hiring_RequestDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public Hiring_RequestDAO_JDBC(Connection dbconn){
		
		dbConnection = dbconn;
	
	}

	@Override
	public Hiring_Request getHiringRequestByCode(String code) {
	
		Hiring_Request hr = new Hiring_Request();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Hiring_Request_Code, Status from Hiring_Request where Hiring_Request_Code = \"" + code + "\";";
			ResultSet hiring_request = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(hiring_request.next()){
			
				String hiring_request_code  = hiring_request.getString("Hiring_Request_Code");
				String status = hiring_request.getString("Status");
				hr.setHiring_Request_Code(hiring_request_code);
				hr.setStatus(status);
				
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Employee_Code from Hiring_Request, Employee where Hiring_Request_Code = \"" + code + "\" and Employee.Employee_ID = Hiring_Request.Employee_ID;";
			ResultSet mgr_code = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(mgr_code.next()){
			
				String mgrCode = mgr_code.getString("Employee_Code");
				hr.setManager_Code(mgrCode);
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		return hr;
	}

	@Override
	public void addHiringRequest(Hiring_Request hr) {
		
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into Hiring_Request(Hiring_Request_Code, Status, Manager_Code) values (?,?,?);";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, hr.getHiring_Request_Code());
			preparedStatement.setString(2, hr.getStatus());
			preparedStatement.setString(3, hr.getManager_Code());
			 
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
	public void changeStatus(String code){ 
		
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "update Hiring_Request set Status = \"hired\" where Hiring_Request_Code = \"" + code + "\";";
	
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
	
	public Vector<Hiring_Request> getAllHiringRequests() {
		
		Vector<Hiring_Request> hr = new Vector<Hiring_Request>();
		Vector<String> codes = new Vector<String>();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Hiring_Request_Code from Hiring_Request;";
			ResultSet c = stmt.executeQuery(sql);
																																																																																																																																																																																			
			while(c.next()){
			
				String t  = c.getString("Hiring_Request_Code");
				codes.add(t);
			}
			
			for (String s : codes) {
					
					Hiring_Request temp = getHiringRequestByCode(s);
					hr.add(temp);
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	return hr;
	}
}