package limehrm.service;

import limehrm.hibernate.dao.UserDao;
import limehrm.hibernate.model.User;
import limehrm.util.LoggerUtil;
import java.util.List;

public class UserService {
    private static LoggerUtil logger = new LoggerUtil(UserService.class.getSimpleName());
    
    
    /** 
     * @param user
     */
    public static void save(User user) {
        logger.logTrace("Saving user");
        UserDao.saveUser(user);
    }
    
    /** 
     * @return List<User>
     */
    public static List<User> getAllUsers() {
        logger.logTrace("Fetching all users");
        return UserDao.getAllUsers();
    }

    
    /** 
     * @param userId
     * @return User
     */
    public static User findById(String userId) {
        logger.logWarn(userId);
        logger.logTrace("Fetching user by email");
        return UserDao.getUser(userId);
    }
    
    
    /** 
     * @param user
     */
    public static void update(User user) {
        logger.logTrace("Updating user record in database");
        UserDao.updateUser(user);
    }
    
    
    /** 
     * @param email
     */
    public static void delete (String email) {
        logger.logTrace("Deleting user record from database");
        UserDao.deleteUser(email);
    }
}
