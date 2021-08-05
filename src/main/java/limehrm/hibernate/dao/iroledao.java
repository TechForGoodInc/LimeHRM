import java.util.List;
import models.Role;

/**
 *
 */
public interface IRoleDAO {
    /**
     * list of roles
     * @return 
     */
    public abstract List<Role> get();
    
    /**
     * save or update a role
     * @param role 
     */
    public abstract void saveOrUpdateRole(Role role);
}