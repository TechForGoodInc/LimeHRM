package limehrm.hibernate.dao;

import limehrm.hibernate.model.Recruitment;
import limehrm.hibernate.util.HibernateUtil;
import limehrm.util.LoggerUtil;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.UUID;

public class RecruitmentDao {
    private static LoggerUtil logger = new LoggerUtil(RecruitmentDao.class.getSimpleName());
    
    public static void saveRecruitment(Recruitment recruitment) {
        if (recruitment.getRecruitmentId() == null) {
            String uuid = UUID.randomUUID().toString().toUpperCase().substring(0, 7)
                    .replace("0", "Z").replace("o", "Y");
    
            recruitment.setRecruitmentId(uuid);
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
    
    public static Recruitment getRecruitment(String RecruitmentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    
        session.beginTransaction();
    
        Recruitment recruitment = session.get(Recruitment.class, RecruitmentId);
    
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
    
}
