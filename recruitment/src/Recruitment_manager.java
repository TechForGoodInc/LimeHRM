import java.util.ArrayList;


public class Recruitment_manager {
	String rec_ssn;
	String rec_name;
	String rec_address;
	ArrayList<Hiring_request> hires;
	

	public String getRec_ssn() {
		return rec_ssn;
	}
	public void setRec_ssn(String rec_ssn) {
		this.rec_ssn = rec_ssn;
	}
	public String getRec_name() {
		return rec_name;
	}
	public void setRec_name(String rec_name) {
		this.rec_name = rec_name;
	}
	public String getRec_address() {
		return rec_address;
	}
	public void setRec_address(String rec_address) {
		this.rec_address = rec_address;
	}
	public ArrayList<Hiring_request> getHires() {
		return hires;
	}
	public void setHires(ArrayList<Hiring_request> hires) {
		this.hires = hires;
	}
	
}
