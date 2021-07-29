import java.util.*;

public class RecruitmentManagerUsecases {
	
	public void implementUsecases() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("1. View Hiring Requests");
		System.out.println("2. View Candidates");
		System.out.println("3. Recruit Candidate");

		int option = in.nextInt();
		
		if (option == 1) {
			
			Hiring_RequestDAO hrdao = daoFagetHiring_RequestDAO();
				
		}
		
		else if (option == 2) {
			
			CandidateDAO candidatedao = getCandidateDAO();
			
			
		}
		
		else if (option == 3) {
			
			CandidateDAO candidatedao = getCandidateDAO();
			
		}
	}
	
	

}
