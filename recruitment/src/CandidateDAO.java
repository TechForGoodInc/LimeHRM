import java.util.Vector;

public interface CandidateDAO {
	
	public Candidate getCandidateByCode(String code);
	public Vector<Candidate> getAllCandidates();
	public void addCandidate(Candidate candidate);
	public void removeCandidate(String code);

}
