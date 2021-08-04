package limehrm.hibernate.dao;

import limehrm.hibernate.model.Worker;
import limehrm.hibernate.util.HibernateUtil;
import limehrm.util.LoggerUtil;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class WorkerDao {
    private static LoggerUtil logger = new LoggerUtil(WorkerDao.class.getSimpleName());
    
    public static void saveWorker(Worker worker) {
        if (worker.getWorkerId() == null) {
            String uuid = UUID.randomUUID().toString().toUpperCase().substring(0, 7)
                    .replace("0", "Z").replace("o", "Y");
    
            worker.setWorkerId(uuid);
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        session.save(worker);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    public static void updateWorker(Worker worker) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        session.update(worker);
        
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    public static Worker getWorker(String workerId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Worker worker = session.get(Worker.class, workerId);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
        
        return worker;
    }
    
    public static List<Worker> getAllWorkers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    
        session.beginTransaction();
    
        CriteriaQuery<Worker> criteria = session.getCriteriaBuilder().createQuery(Worker.class);
    
        criteria.from(Worker.class);
        
        List<Worker> workers = session.createQuery(criteria).getResultList();
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
        
        return workers;
    }
    
    public static void deleteWorker(String workerId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Worker worker = session.get(Worker.class, workerId);
        
        session.delete(worker);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }

    
    public static Worker getLeave(String leaveId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       
        session.beginTransaction();
        
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    
        CriteriaQuery<Worker> criteriaQuery = criteriaBuilder.createQuery(Worker.class);
        
        Root<Worker> root = criteriaQuery.from(Worker.class);
        root.join("leave", JoinType.RIGHT);
        
        criteriaQuery.select(criteriaBuilder.construct(Worker.class, root.get("leave_id"), root.get("leave").get("leave_id")));
    
        List<Worker> workers = session.createQuery(criteriaQuery).getResultList();
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    
        return workers.get(0);
    }
}
