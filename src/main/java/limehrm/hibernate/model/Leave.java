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
@Table(name = "leave", schema = "limehrm")
@TypeDefs ({
    @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
})
public class Leave implements DBItem {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "leave_type")
    private String leaveType;
    
    @Column(name = "fromDate")
    private Date fromDate;
    
    @Column(name = "toDate")
    private Date toDate;
    
    @Column(name = "comment")
    private String comment;
    
    @Enumerated(EnumType.STRING)
    @Type (type = "pgsql_enum")
    @Column(name = "leave_status")
    private LeaveStatus leaveStatus;
    
   
    
    public Leave() {
    }
    
    public Leave(String id) {
        super();
        this.id = id;
    }
    
    public Leave(String id, String leaveType, Date fromDate, Date toDate, String comment, LeaveStatus leaveStatus  ) {
        super();
        
        this.id = id;
        this.leaveType = leaveType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.comment = comment;
        this.leaveStatus = leaveStatus;
    }
    
    
    
    /** 
     * @return String
     */
    public String getId() {
        return id;
    }

    
    /** 
     * @param id
     * @return Leave
     */
    public Leave setId(String id) {
        this.id = id;
        return this;
    }

    
    /** 
     * @return String
     */
    public String getLeaveType(){
        return this.leaveType;
    }

    
    /** 
     * @param leaveType
     * @return Leave
     */
    public Leave setLeaveType(String leaveType){
        this.leaveType = leaveType;
        return this;
    }

    
    /** 
     * @return Date
     */
    public Date getfromDate(){
        return this.fromDate;
    }

    
    /** 
     * @param fromDate
     * @return Leave
     */
    public Leave setFromDate(Date fromDate){
        this.fromDate = fromDate;
        return this;
    }

    
    /** 
     * @return Date
     */
    public Date getToDate(){
        return this.toDate;
    }

    
    /** 
     * @param toDate
     * @return Leave
     */
    public Leave setToDate(Date toDate){
        this.toDate = toDate;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getComment() {
        return this.comment;
    }

    
    /** 
     * @param comment
     * @return Leave
     */
    public Leave SetComment(String comment){
        this.comment = comment;
        return this;
    }
    
    
    /** 
     * @return LeaveStatus
     */
    public LeaveStatus getleaveStatus() {
        return this.leaveStatus;
    }

    
    /** 
     * @param leaveStatus
     * @return Leave
     */
    public Leave SetLeaveStatus(LeaveStatus leaveStatus){
        this.leaveStatus = leaveStatus;
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


