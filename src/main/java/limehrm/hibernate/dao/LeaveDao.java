package limehrm.hibernate.dao;

import limehrm.hibernate.model.Leave;
import limehrm.hibernate.util.HibernateUtil;
import limehrm.util.LoggerUtil;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.UUID;

public class LeaveDao {
    private static LoggerUtil logger = new LoggerUtil(LeaveDao.class.getSimpleName());
    
    
    /** 
     * @param leave
     */
    public static void saveLeave(Leave leave) {
        if (leave.getLeaveId() == null) {
            String uuid = UUID.randomUUID().toString().toUpperCase().substring(0, 7)
                    .replace("0", "Z").replace("o", "Y");
    
            leave.setLeaveId(uuid);
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        session.save(leave);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    
    /** 
     * @param leave
     */
    public static void updateLeave(Leave leave) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        session.update(leave);
        
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    
    /** 
     * @param id
     * @return Leave
     */
    public static Leave getLeave(String leaveId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Leave leave = session.get(Leave.class, leaveId);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
        
        return leave;
    }
    
    
    /** 
     * @return List<Leave>
     */
    public static List<Leave> getAllLeaves() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    
        session.beginTransaction();
    
        CriteriaQuery<Leave> criteria = session.getCriteriaBuilder().createQuery(Leave.class);
    
        criteria.from(Leave.class);
        
        List<Leave> leave = session.createQuery(criteria).getResultList();
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
        
        return leave;
    }
    
    
    /** 
     * @param id
     */
    public static void deleteLeave(String leaveId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
                                                                                                
        Leave leave = session.get(Leave.class, leaveId);
        
        session.delete(leave);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    // public static Leave getLeaveFromEmail(String email) {
    //     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    
    //     session.beginTransaction();
        
    //     CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    
    //     CriteriaQuery<Leave> criteriaQuery = criteriaBuilder.createQuery(Leave.class);
    
    //     Root<Leave> root = criteriaQuery.from(Leave.class);
    //     criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
    
    //     List<Leave> leave = session.createQuery(criteriaQuery).getResultList();
    
    //     session.getTransaction().commit();
    
    //     HibernateUtil.close();
    
    //     return leave.get(0);
    // }
}
