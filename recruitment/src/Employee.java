
public class Employee {
	String Employee_Code;
	String Employee_Name;
	int Department_Code;
	int Manager_Code;
	
	public Employee() { }
	public Employee(String code, String name) { Employee_Code = code; Employee_Name = name; }
	
	public String getEmployee_Code() {
		return Employee_Code;
	}
	public void setEmployee_Code(String employee_Code) {
		Employee_Code = employee_Code;
	}
	public String getEmployee_Name() {
		return Employee_Name;
	}
	public void setEmployee_Name(String employee_Name) {
		Employee_Name = employee_Name;
	}
	public int getDepartment_Code() {
		return Department_Code;
	}
	public void setDepartment_Code(int department_Code) {
		Department_Code = department_Code;
	}
	public int getManager_Code() {
		return Manager_Code;
	}
	public void setManager_Code(int manager_Code) {
		Manager_Code = manager_Code;
	}
	
	public void display() {
			System.out.println("Code = " + Employee_Code);
			System.out.println("Name = " + Employee_Name);
	}

}