import java.util.ArrayList;


public class Candidate_profile {
	String candidate_name;
	String candidate_address;
	int candidate_id;
	ArrayList<Hiring_request> candidates;
	
	public String getCandidate_name() {
		return candidate_name;
	}
	public void setCandidate_name(String candidate_name) {
		this.candidate_name = candidate_name;
	}
	public String getCandidate_address() {
		return candidate_address;
	}
	public void setCandidate_address(String candidate_address) {
		this.candidate_address = candidate_address;
	}
	public int getCandidate_id() {
		return candidate_id;
	}
	public void setCandidate_id(int candidate_id) {
		this.candidate_id = candidate_id;
	}
	public ArrayList<Hiring_request> getCandidates() {
		return candidates;
	}
	public void setCandidates(ArrayList<Hiring_request> candidates) {
		this.candidates = candidates;
	}
	
	

}
