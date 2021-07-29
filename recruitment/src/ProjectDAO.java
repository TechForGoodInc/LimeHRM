import java.util.Vector;


public interface ProjectDAO {
	
	public Project getProjectByCode(String code);
	public Vector<Project> getAllProjects();
	public void addProject(Project p);
	public void changeStatus(String code);
	

}
