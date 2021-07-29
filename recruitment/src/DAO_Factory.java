import java.sql.*;
/*
	Methods to be called in the following order:

	1. activateConnection
	2. 	Any number getDAO calls with any number of database transactions
	3. deactivateConnection
*/
public class DAO_Factory{

	public enum TXN_STATUS { COMMIT, ROLLBACK };

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/hrms2";
	static final String USER = "root";
	static final String PASS = "123";
	Connection dbconnection = null;

	// You can add additional DAOs here as needed
	CandidateDAO candidateDAO = null;
	Hiring_RequestDAO hiringRequestDAO = null;
	EmployeeDAO employeeDAO = null;
	ProjectDAO projectDAO = null; 

	boolean activeConnection = false;

	public DAO_Factory()
	{
		dbconnection = null;
		activeConnection = false;
	}

	public void activateConnection() throws Exception
	{
		if( activeConnection == true )
			throw new Exception("Connection already active");

		System.out.println("Connecting to database...");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			dbconnection = DriverManager.getConnection(DB_URL,USER,PASS);
			dbconnection.setAutoCommit(false);
			activeConnection = true;
		} catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public CandidateDAO getCandidateDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( candidateDAO == null )
			candidateDAO = new CandidateDAO_JDBC( dbconnection );

		return candidateDAO;
	}
	
	public Hiring_RequestDAO getHiringRequestDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( hiringRequestDAO == null )
			hiringRequestDAO = new Hiring_RequestDAO_JDBC( dbconnection );

		return hiringRequestDAO;
	}
	
	public EmployeeDAO getEmployeeDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( employeeDAO == null )
			employeeDAO = new EmployeeDAO_JDBC( dbconnection );

		return employeeDAO;
	}
	
	public ProjectDAO getProjectDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( projectDAO == null )
			projectDAO = new ProjectDAO_JDBC( dbconnection );

		return projectDAO;
	}
	
	
	
	public void deactivateConnection( TXN_STATUS txn_status )
	{
		// Okay to keep deactivating an already deactivated connection
		activeConnection = false;
		if( dbconnection != null ){
			try{
				if( txn_status == TXN_STATUS.COMMIT )
					dbconnection.commit();
				else
					dbconnection.rollback();

				dbconnection.close();
				dbconnection = null;

				// Nullify all DAO objects
				candidateDAO = null;
				hiringRequestDAO = null;
				employeeDAO = null;
				projectDAO = null; 
			}
			catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
	}
};
