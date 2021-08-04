package limehrm.hibernate.dao;

import limehrm.hibernate.model.User;
import limehrm.hibernate.util.HibernateUtil;
import limehrm.util.LoggerUtil;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDao {
    private static LoggerUtil logger = new LoggerUtil(WorkerDao.class.getSimpleName());
    
    
    /** 
     * @param user
     */
    public static void saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        session.save(user);
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
    }
    
    
    /** 
     * @param user
     */
    public static void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        session.update(user);
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
    }
    
    
    /** 
     * @param userId
     * @return User
     */
    public static User getUser(String userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        logger.logWarn(userId);
        session.beginTransaction();
        
        User user = session.get(User.class, userId);
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
        
        return user;
    }


    public static User getUserbyuserId(String userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        logger.logWarn(userId);
        session.beginTransaction();
        
        User user = session.get(User.class, userId);
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
        
        return user;
    }
    
    /** 
     * @return List<User>
     */
    public static List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        CriteriaQuery<User> criteria = session.getCriteriaBuilder().createQuery(User.class);
        
        criteria.from(User.class);
        
        List<User> user = session.createQuery(criteria).getResultList();
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
        
        return user;
    }
    
    
    /** 
     * @param email
     */
    public static void deleteUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        User user = session.get(User.class, email);
        
        session.delete(user);
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
    }
    
}
