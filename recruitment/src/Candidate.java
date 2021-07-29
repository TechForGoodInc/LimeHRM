public class Candidate {
	
	String Candidate_Code;
	String Candidate_Name;
	
	public Candidate() { }
	public Candidate(String code, String name) { Candidate_Code = code; Candidate_Name = name; }
	
	public String getCandidate_Code() { return Candidate_Code; }
	public void setCandidate_Code(String candidate_Code) { Candidate_Code = candidate_Code; }
	
	public String getCandidate_Name() {	return Candidate_Name; }
	public void setCandidate_Name(String candidate_Name) { Candidate_Name = candidate_Name; }

	public void display() { System.out.println("Code = " + Candidate_Code); System.out.println("Name = " + Candidate_Name); }

}
