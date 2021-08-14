package limehrm.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Helper;
import limehrm.exceptions.InvalidCredentialsException;
import limehrm.hibernate.dao.UserDao;
import limehrm.hibernate.model.User;

public class AuthUtil {
    
    /** 
     * @param password
     * @return String
     */
    public static String hashPassword(char[] password) {
        // Create Argon2 instance
        Argon2 argon2 = Argon2Factory.create();
//        System.out.println(Argon2Helper.findIterations(argon2, 100, 65536, 1));

        try
        {
            // TODO: verify parameters
            String hash = argon2.hash(5, 65531, 1, password);

            return hash;
        } finally {
            argon2.wipeArray(password);
        }
    }

    
    /** 
     * @param password
     * @param hash
     * @return boolean
     */
    public static boolean verifyPassword(char[] password, String hash) {
        Argon2 argon2 = Argon2Factory.create();
        String hashPassword = argon2.hash(5, 65531, 1, password);
        return argon2.verify(hashPassword, password);
    }

}
