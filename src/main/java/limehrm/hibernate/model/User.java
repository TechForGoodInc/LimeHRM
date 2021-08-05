package limehrm.hibernate.model;

import limehrm.util.DBItem;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table (name = "user", schema = "limehrm")
public class User implements DBItem {

    @Id
    @Column (name = "user_id")
    private String userId;

    @Column (name = "email")
    private String email;
    
    @Column (name = "password")
    private String password;

    @Column (name = "google_id")
    private String googleId;

    @Column (name = "microsoft_id")
    private String microsoftId;
    
    public User(String email) {
        this.email = email;
    }
    
    public User(String userId, String email, String password, String googleId, String microsoftId) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.googleId = googleId;
        this.microsoftId = microsoftId;
    }
    
    public User() {
        
    }
    
    
    /** 
     * @return String
     */
    public String getEmail() {
        return email;
    }
    
    
    /** 
     * @param email
     * @return User
     */
    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getPassword() {
        return password;
    }
    
    
    /** 
     * @param password
     * @return User
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getUserId() {
        return userId;
    }
    
    
    /** 
     * @param id
     * @return User
     */
    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    
    /** 
     * @return String
     */
    public String getGoogleId() {
        return googleId;
    }

    
    /** 
     * @param googleId
     */
    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    
    /** 
     * @return String
     */
    public String getMicrosoftId() {
        return microsoftId;
    }

    
    /** 
     * @param microsoftId
     */
    public void setMicrosoftId(String microsoftId) {
        this.microsoftId = microsoftId;
    }

    
    /** 
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getGoogleId(), user.getGoogleId()) && Objects.equals(getMicrosoftId(), user.getMicrosoftId());
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getUserId(), getGoogleId(), getMicrosoftId());
    }

    
    /** 
     * @return Object
     */
    @Override
    public Object getPrimaryKey() {
        return email;
    }
}
