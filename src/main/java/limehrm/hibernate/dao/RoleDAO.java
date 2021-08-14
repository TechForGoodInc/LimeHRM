import limehrm.hibernate.dao.IRoleDAO;
import java.util.List;
import models.Role;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 */
public class RoleDAO implements IRoleDAO{

    /**
     * Get Role List
     * @return 
     */
    public  List<Role> get() {
        List<Role> roleList = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Role");
            roleList = query.list();
           
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return roleList;
    }
    
    /**
     * Save or Update Role
     * @param role 
     */
    public  void saveOrUpdateRole(Role role){
        Transaction transaction = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(role);
            transaction.commit();
        } catch(HibernateException e){
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally{
            session.close();
        }
    }
}