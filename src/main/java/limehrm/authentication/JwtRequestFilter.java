package limehrm.authentication;

import io.jsonwebtoken.ExpiredJwtException;
import limehrm.exceptions.InvalidCredentialsException;
import limehrm.hibernate.dao.UserDao;
import limehrm.hibernate.model.User;
import limehrm.util.AuthUtil;
import limehrm.util.JwtUtil;

import java.security.PublicKey;

public class JwtRequestFilter {
    
    /** 
     * @param jwtToken
     * @param publicKey
     * @return User
     */
    public static User parseJwtToken(String jwtToken, PublicKey publicKey) {
        try {
             return(JwtUtil.parseToken(jwtToken, publicKey));
        } catch (IllegalArgumentException e) {
            // Todo; create exception (unable to get JWT token)
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            // Todo: create expired JWT exception!
            e.printStackTrace();
        }
        // Todo: fix this; add exceptions to above
        return null;
    }

    
    /** 
     * @param user
     * @return Boolean
     */
    public static Boolean validateUser(User user) {
        // check if emails are the same & not expired
        if (user.getEmail() == null) {
            throw new InvalidCredentialsException();
        }

        User databaseFetchedUser = UserDao.getUser(user.getEmail());

        if (databaseFetchedUser.getEmail() != null && databaseFetchedUser.getEmail().equals(user.getEmail())) {
            return true; // Todo: does it auto check if expired?
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
