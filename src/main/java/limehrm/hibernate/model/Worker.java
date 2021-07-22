package limehrm.hibernate.model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import limehrm.util.DBItem;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "worker", schema = "limehrm")
@TypeDefs ({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
})
public class Worker implements DBItem {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "personal_email")
    private String personalEmail;
    
    @Column(name = "home_phone")
    private String homePhone;
    
    @Column(name = "mobile_phone")
    private String mobilePhone;
    
    @Type (type = "jsonb")
    @Column(name = "home_address", columnDefinition = "jsonb")
    private Address homeAddress;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "sex")
    private Sex sex;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;
    
    @Column(name = "position_name")
    private String positionName;
    
    @Column(name = "team_name")
    private String teamName;
    
    @Column(name = "salary")
    private boolean salary;
    
    @Column(name = "department") 
    private String department;
    
    @Column(name = "birth_date")
    private Date birthDate;
    
    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "end_date")
    private Date endDate;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "job_status")
    private JobStatus jobStatus;
    
    @Column(name = "manager_email")
    private String managerEmail;
    
    public Worker() {
    }
    
    public Worker(String id) {
        super();
        this.id = id;
    }
    
    public Worker(String id, String firstName, String lastName, String personalEmail, String homePhone, String mobilePhone, Address homeAddress, Sex sex, MaritalStatus maritalStatus, String positionName, String teamName, boolean salary, String department, Date birthDate, Date startDate, Date endDate, JobStatus jobStatus, String managerEmail) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalEmail = personalEmail;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.homeAddress = homeAddress;
        this.sex = sex;
        this.maritalStatus = maritalStatus;
        this.positionName = positionName;
        this.teamName = teamName;
        this.salary = salary;
        this.department = department;
        this.birthDate = birthDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobStatus = jobStatus;
        this.managerEmail = managerEmail;
    }
    
    
    
    /** 
     * @return String
     */
    public String getId() {
        return id;
    }
    
    
    /** 
     * @param id
     * @return Worker
     */
    public Worker setId(String id) {
        this.id = id;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }
    
    
    /** 
     * @param firstName
     * @return Worker
     */
    public Worker setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getLastName() {
        return lastName;
    }
    
    
    /** 
     * @param lastName
     * @return Worker
     */
    public Worker setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getPersonalEmail() {
        return personalEmail;
    }
    
    
    /** 
     * @param personalEmail
     * @return Worker
     */
    public Worker setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getHomePhone() {
        return homePhone;
    }
    
    
    /** 
     * @param homePhone
     * @return Worker
     */
    public Worker setHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getMobilePhone() {
        return mobilePhone;
    }
    
    
    /** 
     * @param mobilePhone
     * @return Worker
     */
    public Worker setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }
    
    
    /** 
     * @return Address
     */
    public Address getHomeAddress() {
        return homeAddress;
    }
    
    
    /** 
     * @param homeAddress
     * @return Worker
     */
    public Worker setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }
    
    
    /** 
     * @return Sex
     */
    public Sex getSex() {
        return sex;
    }
    
    
    /** 
     * @param sex
     * @return Worker
     */
    public Worker setSex(Sex sex) {
        this.sex = sex;
        return this;
    }
    
    
    /** 
     * @return MaritalStatus
     */
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }
    
    
    /** 
     * @param maritalStatus
     * @return Worker
     */
    public Worker setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getPositionName() {
        return positionName;
    }
    
    
    /** 
     * @param positionName
     * @return Worker
     */
    public Worker setPositionName(String positionName) {
        this.positionName = positionName;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getTeamName() {
        return teamName;
    }
    
    
    /** 
     * @param teamName
     * @return Worker
     */
    public Worker setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }
    
    
    /** 
     * @return boolean
     */
    public boolean isSalary() {
        return salary;
    }
    
    
    /** 
     * @param salary
     * @return Worker
     */
    public Worker setSalary(boolean salary) {
        this.salary = salary;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getDepartment() {
        return department;
    }
    
    
    /** 
     * @param department
     * @return Worker
     */
    public Worker setDepartment(String department) {
        this.department = department;
        return this;
    }
    
    
    /** 
     * @return Date
     */
    public Date getBirthDate() {
        return birthDate;
    }
    
    
    /** 
     * @param birthDate
     * @return Worker
     */
    public Worker setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
    
    
    /** 
     * @return Date
     */
    public Date getStartDate() {
        return startDate;
    }
    
    
    /** 
     * @param startDate
     * @return Worker
     */
    public Worker setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    
    
    /** 
     * @return Date
     */
    public Date getEndDate() {
        return endDate;
    }
    
    
    /** 
     * @param endDate
     * @return Worker
     */
    public Worker setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
    
    
    /** 
     * @return JobStatus
     */
    public JobStatus getJobStatus() {
        return jobStatus;
    }
    
    
    /** 
     * @param jobStatus
     * @return Worker
     */
    public Worker setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getManagerEmail() {
        return managerEmail;
    }
    
    
    /** 
     * @param managerEmail
     * @return Worker
     */
    public Worker setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
        return this;
    }
    
    
    /** 
     * @return Object
     */
    @Override
    public Object getPrimaryKey() {
        return this.id;
    }
}
