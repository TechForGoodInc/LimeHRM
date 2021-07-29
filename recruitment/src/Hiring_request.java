import java.util.ArrayList;


public class Hiring_request {
	int hiring_request_id;
	ArrayList<Candidate_profile> operates_on;
	ArrayList<Recruitment_manager> mgr_ssn;
	

	public int getHiring_request_id() {
		return hiring_request_id;
	}
	public void setHiring_request_id(int h){ 
		hiring_request_id = h;
	}
	public ArrayList<Candidate_profile> getOperates_on() {
		return operates_on;
	}
	public void setOperates_on(ArrayList<Candidate_profile> operates_on) {
		this.operates_on = operates_on;
	}
	public ArrayList<Recruitment_manager> getMgr_ssn() {
		return mgr_ssn;
	}
	public void setMgr_ssn(ArrayList<Recruitment_manager> mgr_ssn) {
		this.mgr_ssn = mgr_ssn;
	}
	
}
