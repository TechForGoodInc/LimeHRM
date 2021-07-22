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
@Table(name = "recruitment", schema = "limehrm")
@TypeDefs ({
        @TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
})
public class Recruitment implements DBItem {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "vacancy")
    private String vacancy;
    
    @Column(name = "candidate")
    private String candidate;
    
    @Column(name = "personal_email")
    private String personalEmail;
    
    @Column(name = "contact_number")
    private String contactNumber;
    
    @Enumerated(EnumType.STRING)
    @Type (type = "pgsql_enum")
    @Column(name = "stage")
    private Stage stage;
    
    @Column(name = "date_applied")
    private Date dateApplied;
    
    public Recruitment() {
    }
    
    public Recruitment(String id) {
        super();
        this.id = id;
    }
    
    public Recruitment(String id, String vacancy, String candidate, String personalEmail, String contactNumber, Date dateApplied, Stage stage ) {
        super();
        
        this.id = id;
        this.vacancy = vacancy;
        this.candidate = candidate;
        this.personalEmail= personalEmail;
        this.contactNumber = contactNumber;
        this.dateApplied = dateApplied;
        this.stage = stage;

        
    }
    
    
    
    /** 
     * @return String
     */
    public String getId() {
        return id;
    }

    
    /** 
     * @param id
     * @return Recruitment
     */
    public Recruitment setId(String id) {
        this.id = id;
        return this;
    }

    
    /** 
     * @return String
     */
    public String getVacancy(){
        return this.vacancy;
    }

    
    /** 
     * @param Vacancy
     * @return Recruitment
     */
    public Recruitment setVacancy(String Vacancy){
        this.vacancy = Vacancy;
        return this;
    }

    
    /** 
     * @return String
     */
    public String getCandidate(){
        return this.candidate;
    }

    
    /** 
     * @param candidate
     * @return Recruitment
     */
    public Recruitment setFromDate(String candidate){
        this.candidate = candidate;
        return this;
    }

    
    /** 
     * @return String
     */
    public String getPersonalEmail(){
        return this.personalEmail;
    }

    
    /** 
     * @param personalEmail
     * @return Recruitment
     */
    public Recruitment setPersonalEmail(String personalEmail){
        this.personalEmail = personalEmail;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getContactNumber() {
        return this.contactNumber;
    }

    
    /** 
     * @param contactNumber
     * @return Recruitment
     */
    public Recruitment SetContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
        return this;
    }
    
    
    /** 
     * @return Stage
     */
    public Stage getStage() {
        return this.stage;
    }

    
    /** 
     * @param stage
     * @return Recruitment
     */
    public Recruitment SetRecruitmentStatus(Stage stage){
        this.stage = stage;
        return this;
    }

    
    /** 
     * @return Date
     */
    public Date getDateApplied() {
        return this.dateApplied;
    }

    
    /** 
     * @param dateApplied
     * @return Recruitment
     */
    public Recruitment SetDateApplied(Date dateApplied){
        this.dateApplied = dateApplied;
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


