import java.util.ArrayList;


public class Supervisor {
	public String getMgr_ssn() {
		return mgr_ssn;
	}
	public void setMgr_ssn(String mgr_ssn) {
		this.mgr_ssn = mgr_ssn;
	}
	public String getMgr_name() {
		return mgr_name;
	}
	public void setMgr_name(String mgr_name) {
		this.mgr_name = mgr_name;
	}
	public String getMgr_address() {
		return mgr_address;
	}
	public void setMgr_address(String mgr_address) {
		this.mgr_address = mgr_address;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public ArrayList<Hiring_request> getHiring_request_id() {
		return hiring_request_id;
	}
	public void setHiring_request_id(ArrayList<Hiring_request> hiring_request_id) {
		this.hiring_request_id = hiring_request_id;
	}
	String mgr_ssn;
	String mgr_name;
	String mgr_address;
	Project project;
	ArrayList<Hiring_request> hiring_request_id;

}
