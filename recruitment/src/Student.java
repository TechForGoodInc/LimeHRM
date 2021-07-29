import java.lang.*;

public class Student{
	int rollno;
	String name;
	public Student() { }
	public Student (String s, int r){ rollno = r; name = s; }
	public String getName() { return name; }
	public void setName(String s){ name = s; }
	public int getRollno() { return rollno; }
	public void setRollno(int s){ rollno = s; }
	public void print(){ System.out.print("Rollno=" + rollno); System.out.println("; Name=" + name);}
};

