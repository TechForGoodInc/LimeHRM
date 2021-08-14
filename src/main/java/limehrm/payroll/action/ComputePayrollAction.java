import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ComputePayrollAction extends ActionSupport implements TaxPercentage, ModelDriven<EmployeeBean> {
	
	//has-a relationship
		private EmployeeBean employee = new EmployeeBean();
		
		public String execute(){
			
			
			employee.setBasicPay((employee.getHours() <= 40)
					? employee.getHours() * employee.getPayRate()
					: (40 * employee.getPayRate()));
			
			employee.setOverTimePay((employee.getHours() > 40)
					? (employee.getHours() - 40) * employee.getPayRate() * 1.5
					: 0);
			
			employee.setGrossPay(employee.getBasicPay() + employee.getOverTimePay());
				
				if (employee.getGrossPay() < 10000){
					employee.setWithHoldingTax(employee.getGrossPay() * GPLT10K);
				}
				else if (employee.getGrossPay() < 18000){
					employee.setWithHoldingTax(employee.getGrossPay() * GPLT18K);
				}
				else if (employee.getGrossPay() < 22000){
					employee.setWithHoldingTax(employee.getGrossPay() * GPLT22K);
				}
				else if (employee.getGrossPay() < 28000){
					employee.setWithHoldingTax(employee.getGrossPay() * GPLT28K);
				}
				else if (employee.getGrossPay() < 35000){
					employee.setWithHoldingTax(employee.getGrossPay() * GPLT35K);
				}
				else if (employee.getGrossPay() >= 35000){
					employee.setWithHoldingTax(employee.getGrossPay() * GPGTE35K);
				}
				
				employee.setNetPay(employee.getGrossPay() - employee.getWithHoldingTax());
				
				System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
				System.out.println("Hours Worked: " + employee.getHours());
				System.out.println("Payrate: Php" + employee.getPayRate());
				System.out.println("Basic Payment: Php " + employee.getBasicPay());
				System.out.println("OverTime Payment: Php " + employee.getOverTimePay());
				System.out.println("Gross Payment: Php" + employee.getGrossPay());
				System.out.println("Withholding Tax: Php" + employee.getWithHoldingTax());
				System.out.println("Net Payment: Php" + employee.getNetPay());
			
				SessionFactory sessionFactory = 
						new Configuration().configure().buildSessionFactory();
					
					Session session = sessionFactory.openSession();
					org.hibernate.Transaction tx = session.beginTransaction();
					
					try{
						session.save(employee);
						tx.commit();
					} catch(HibernateException he){
						tx.rollback();
					}
				
				return SUCCESS;
			}		
		
	
	@Override
	public void validate() {
		//Validation for the first name
		if(employee.getFirstName().trim().length() == 0) {
			addFieldError("firstName", "Missing entry for first name.");
		}
		
		//Validation for the last name
		if(employee.getLastName().trim().length() == 0) {
			addFieldError("lastName", "Missing entry for last name.");
		}
		
		//Validation for the hours worked
		if(employee.getHours() < 0) {
			addFieldError("hours", "Hours must not be nagative.");
		}
		
		//Validation for the pay rate
		if(employee.getPayRate() < 0) {
			addFieldError("payRate", "Pay rate must not be negative.");
		}
	}

	@Override
	public EmployeeBean getModel() {
		
		return employee;
	}
}