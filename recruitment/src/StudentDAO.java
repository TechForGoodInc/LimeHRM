import java.lang.*;
import java.util.List;

public interface StudentDAO {
	public Employee getStudentByKey(String ssn);
	public void addStudent(Student student);
	// public void updateStudent(Student student);
	// public void deleteStudent(Student student);
	
}

