import java.util.Vector;

public interface EmployeeDAO {
	
	public Employee getEmployeeByCode(String code);
	public Vector<Employee> getAllEmployees();
	public void addEmployee(Employee employee);

}
