package limehrm.hibernate.util;

import limehrm.hibernate.model.Leave;
import limehrm.hibernate.model.User;
import limehrm.hibernate.model.Worker;
import limehrm.hibernate.model.Recruitment;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    /** 
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration()
                    .configure()
                   
                    .addAnnotatedClass(Worker.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Leave.class)
                    .addAnnotatedClass(Recruitment.class);
    
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
    
    public static void close() {
        sessionFactory.close();
    }
    
}

