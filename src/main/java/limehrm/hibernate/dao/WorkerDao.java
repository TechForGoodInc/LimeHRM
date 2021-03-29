package limehrm.hibernate.dao;

import limehrm.hibernate.model.Worker;
import limehrm.hibernate.util.HibernateUtil;
import limehrm.util.LoggerUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    
        HibernateUtil.close();
    }
    
    public static void updateWorker(Worker worker) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        session.update(worker);
        
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    public static Worker getWorker(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Worker worker = session.get(Worker.class, id);
    
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
    
    public static void deleteWorker(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Worker worker = session.get(Worker.class, id);
        
        session.delete(worker);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    public static Worker getWorkerFromEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    
        session.beginTransaction();
        
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    
        CriteriaQuery<Worker> criteriaQuery = criteriaBuilder.createQuery(Worker.class);
    
        Root<Worker> root = criteriaQuery.from(Worker.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
    
        List<Worker> workers = session.createQuery(criteriaQuery).getResultList();
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    
        return workers.get(0);
    }
}
