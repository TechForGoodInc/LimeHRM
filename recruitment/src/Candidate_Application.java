
public class Candidate_Application {
	
	String Candidate_Application_Code;
	String Hiring_Request_Code;
	String Candidate_Code;
	
	public Candidate_Application() {}
	public Candidate_Application(String code) { Candidate_Application_Code = code; }
	public String getCandidate_Application_Code() {
		return Candidate_Application_Code;
	}
	public void setCandidate_Application_Code(String candidate_Application_Code) {
		Candidate_Application_Code = candidate_Application_Code;
	}
	public String getHiring_Request_Code() {
		return Hiring_Request_Code;
	}
	public void setHiring_Request_Code(String hiring_Request_Code) {
		Hiring_Request_Code = hiring_Request_Code;
	}
	public String getCandidate_Code() {
		return Candidate_Code;
	}
	public void setCandidate_Code(String candidate_Code) {
		Candidate_Code = candidate_Code;
	}
	
	public void display() {
		System.out.println("Code = " + Candidate_Application_Code);
	}
	
}
