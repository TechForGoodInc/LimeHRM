import java.util.List;

/**
 *
 */
public interface IRoleService {

    /**
     * Retrieve Roles
     * @return 
     */
    public abstract List getRoles();
    
    /**
     * Create Role
     * @param title 
     */
    public abstract void saveRole(String title);
    
    /**
     * Update Role
     * @param id
     * @param title 
     */
    public abstract void updateRole(int id, String title);
}