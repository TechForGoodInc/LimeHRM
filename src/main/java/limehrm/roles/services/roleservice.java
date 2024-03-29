import limehrm.hibernate.dao.RoleDAO;
import limehrm.hibernate.dao.IRoleDAO;
import java.util.List;

/**
 *
 *
 */
public class RoleService implements IRoleService{

    IRoleDAO rd = new RoleDAO();
    /**
     * List Roles
     * @return 
     */
    public  List getRoles() {
        List<Role> roles = rd.get();
        return roles;
    }

    /**
     * Save Role
     * @param title 
     */
    public  void saveRole(String title) {
        Role role = new Role();
        role.setTitle(title);
        rd.saveOrUpdateRole(role);
    }

    /**
     * Update the entire Role Object (PUT)
     * @param id
     * @param title 
     */
    public  void updateRole(int id, String title) {
        Role role = new Role();
        role.setTitle(title);
        role.setId(id);
        rd.saveOrUpdateRole(role);
    }
}