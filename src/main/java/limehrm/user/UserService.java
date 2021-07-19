package limehrm.user;
import limehrm.hibernate.dao.UserDao;
import limehrm.hibernate.model.User;
import limehrm.util.LoggerUtil;
import java.util.List;
public class UserService {
    private static LoggerUtil logger = new LoggerUtil(UserService.class.getSimpleName());
    
    public static void save(User user) {
        logger.logTrace("Saving user");
        UserDao.saveUser(user);
    }
    public static List<User> getAllUsers() {
        logger.logTrace("Fetching all users");
        return UserDao.getAllUsers();
    }

    public static User findById(String email) {
        logger.logTrace("Fetching user by email");
        return UserDao.getUser(email);
    }
    
    public static void update(User user) {
        logger.logTrace("Updating user record in database");
        UserDao.updateUser(user);
    }
    
    public static void delete (String email) {
        logger.logTrace("Deleting user record from database");
        UserDao.deleteUser(email);
    }
}
