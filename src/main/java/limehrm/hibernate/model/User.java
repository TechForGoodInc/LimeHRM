package limehrm.hibernate.model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import limehrm.util.DBItem;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table (name = "user", schema = "limehrm")
public class User implements DBItem {
    
    @Id
    @Column (name = "email")
    private String email;
    
    @Column (name = "password")
    private String password;

    @Column (name = "id")
    private String id;

    @Column (name = "google_id")
    private String googleId;

    @Column (name = "microsoft_id")
    private String microsoftId;
    
    public User(String email) {
        this.email = email;
    }
    
    public User(String email, String password, String id, String googleId, String microsoftId) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.googleId = googleId;
        this.microsoftId = microsoftId;
    }
    
    public User() {
        
    }
    
    public String getEmail() {
        return email;
    }
    
    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    
    public String getPassword() {
        return password;
    }
    
    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public String getId() {
        return id;
    }
    
    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getMicrosoftId() {
        return microsoftId;
    }

    public void setMicrosoftId(String microsoftId) {
        this.microsoftId = microsoftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getId(), user.getId()) && Objects.equals(getGoogleId(), user.getGoogleId()) && Objects.equals(getMicrosoftId(), user.getMicrosoftId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getId(), getGoogleId(), getMicrosoftId());
    }

    @Override
    public Object getPrimaryKey() {
        return email;
    }
}
