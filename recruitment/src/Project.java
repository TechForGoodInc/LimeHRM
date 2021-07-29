public class Project {
	
	String Project_Code;
	String Status;
	String Department_Code;
	
	public Project() { Status = "ongoing"; }
	public  Project(String code) {  Project_Code = code; Status = "ongoing"; }
	
	public String getProject_Code() {
		return Project_Code;
	}
	public void setProject_Code(String project_Code) {
		Project_Code = project_Code;
	}
	public String getDepartment_Code() {
		return Department_Code;
	}
	public void setDepartment_Code(String department_Code) {
		Department_Code = department_Code;
	}
	
	public String getStatus() {
		return Status;
	}
	
	public void setStatus(String status) {
		Status = status;
	}
	
	public void display() {
		System.out.println("Code = " + Project_Code);
	}

}