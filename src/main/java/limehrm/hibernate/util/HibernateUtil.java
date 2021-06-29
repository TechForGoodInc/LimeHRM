package limehrm.hibernate.util;

import limehrm.hibernate.model.User;
import limehrm.hibernate.model.Worker;
import org.hibernate.SessionFactory;
import org.hibernate.boot.cfgxml.spi.LoadedConfig;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration()
                    .configure()
                    .addAnnotatedClass(Worker.class)
                    .addAnnotatedClass(User.class);
    
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();
    
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

