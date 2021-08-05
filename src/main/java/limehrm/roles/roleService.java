import limehrm.hibernate.dao.RoleDAO;

import java.util.List;

public class RoleService {
    public List getAll(){
        return RoleDAO.get();
    }

    public void save(String title) {
        Role role = new Role();
        role.setTitle(title);
        RoleDAO.saveOrUpdateRole(role);
    }

    public void update(Integer id, String title) {
        Role role = new Role();
        role.setTitle(title);
        role.setId(id);
        RoleDAO.saveOrUpdateRole(role);
    }
}