package limehrm.user;

import java.util.Objects;

public class User {
    private String username;
    private int id;
    
    public User() {}
    
    public User(String username, int id) {
        setUsername(username);
        setId(id);
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(id, user.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(username, id);
    }
}