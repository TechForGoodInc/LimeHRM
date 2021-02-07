package limehrm.hibernate.dao;

import limehrm.hibernate.model.Worker;
import limehrm.hibernate.util.HibernateUtil;
import limehrm.util.LoggerUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.UUID;

public class WorkerDao {
    private static LoggerUtil logger = new LoggerUtil(WorkerDao.class.getSimpleName());
    
    public static void saveWorker(Worker worker) {
        if (worker.getId() == null) {
            String uuid = UUID.randomUUID().toString().toUpperCase().substring(0, 7)
                    .replace("0", "Z").replace("o", "Y");
    
            worker.setId(uuid);
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        session.save(worker);
    
        session.getTransaction().commit();
    
        session.close();
    }
    
    public static void updateWorker(Worker worker) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        session.update(worker);
        
        session.getTransaction().commit();
        
        session.close();
    }
    
    public static Worker getWorker(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Worker worker = session.get(Worker.class, id);
    
        session.getTransaction().commit();
    
        session.close();
        
        return worker;
    }
    
    public static List<Worker> getAllWorkers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        CriteriaQuery<Worker> criteria = session.getCriteriaBuilder().createQuery(Worker.class);
    
        criteria.from(Worker.class);
        
        List<Worker> workers = session.createQuery(criteria).getResultList();
        
        session.getTransaction().commit();
    
        session.close();
        
        return workers;
    }
    
    public static void deleteWorker(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Worker worker = session.get(Worker.class, id);
        
        session.delete(worker);
    
        session.getTransaction().commit();
    
        session.close();
    }
}
