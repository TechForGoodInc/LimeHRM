package edu.ust.ics.payroll.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name="EmpleyadoSweldo")
public class EmployeeBean {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO) //primary key only with annotation id
	private int id;
	
	
	//input variables
		private int hours;
		private double payRate;
		private String lastName;
		private String firstName;
		
		//computed variables
		private double basicPay;
		private double overTimePay;
		private double grossPay;
		private double withHoldingTax;
		private double netPay;
		
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		

		public int getHours() {
			return hours;
		}
		public void setHours(int hours) {
			System.out.println("setHours() method called.");
			this.hours = hours;
		}
		public double getPayRate() {
			return payRate;
		}
		public void setPayRate(double payRate) {
			System.out.println("setPayRate() method called.");
			this.payRate = payRate;
		}
		public double getBasicPay() {
			return basicPay;
		}
		public void setBasicPay(double basicPay) {
			this.basicPay = basicPay;
		}
		public double getOverTimePay() {
			return overTimePay;
		}
		public void setOverTimePay(double overTimePay) {
			this.overTimePay = overTimePay;
		}
		public double getGrossPay() {
			return grossPay;
		}
		public void setGrossPay(double grossPay) {
			this.grossPay = grossPay;
		}
		public double getWithHoldingTax() {
			return withHoldingTax;
		}
		public void setWithHoldingTax(double withHoldingTax) {
			this.withHoldingTax = withHoldingTax;
		}
		public double getNetPay() {
			return netPay;
		}
		public void setNetPay(double netPay) {
			this.netPay = netPay;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
}