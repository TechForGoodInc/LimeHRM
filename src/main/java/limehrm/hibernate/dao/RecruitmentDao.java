package limehrm.hibernate.dao;

import limehrm.hibernate.model.Recruitment;
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

public class RecruitmentDao {
    private static LoggerUtil logger = new LoggerUtil(RecruitmentDao.class.getSimpleName());
    
    public static void saveRecruitment(Recruitment recruitment) {
        if (recruitment.getId() == null) {
            String uuid = UUID.randomUUID().toString().toUpperCase().substring(0, 7)
                    .replace("0", "Z").replace("o", "Y");
    
            recruitment.setId(uuid);
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        session.save(recruitment);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    public static void updateRecruitment(Recruitment recruitment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        session.update(recruitment);
        
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    public static Recruitment getRecruitment(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Recruitment recruitment = session.get(Recruitment.class, id);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
        
        return recruitment;
    }
    
    public static List<Recruitment> getAllRecruitments() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    
        session.beginTransaction();
    
        CriteriaQuery<Recruitment> criteria = session.getCriteriaBuilder().createQuery(Recruitment.class);
    
        criteria.from(Recruitment.class);
        
        List<Recruitment> recruitment = session.createQuery(criteria).getResultList();
        
        session.getTransaction().commit();
        
        HibernateUtil.close();
        
        return recruitment;
    }
    
    public static void deleteRecruitment(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
                                                                                                
        Recruitment recruitment = session.get(Recruitment.class, id);
        
        session.delete(recruitment);
    
        session.getTransaction().commit();
    
        HibernateUtil.close();
    }
    
    // public static Recruitment getRecruitmentFromEmail(String email) {
    //     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    
    //     session.beginTransaction();
        
    //     CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    
    //     CriteriaQuery<Recruitment> criteriaQuery = criteriaBuilder.createQuery(Recruitment.class);
    
    //     Root<Recruitment> root = criteriaQuery.from(Recruitment.class);
    //     criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
    
    //     List<Recruitment> recruitment = session.createQuery(criteriaQuery).getResultList();
    
    //     session.getTransaction().commit();
    
    //     HibernateUtil.close();
    
    //     return recruitment.get(0);
    // }
}
