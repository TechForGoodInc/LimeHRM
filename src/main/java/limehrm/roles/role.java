import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 *
 */
@Entity
@Table(name = "ROLES")
public class Role {
    @Id 
    @Column(name = "id")
    @SequenceGenerator(name = "seq", sequenceName = "role_seq")    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    
    
    @Column(name = "name")
    private String title;
    
    
    @OneToOne(mappedBy = "role")
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}