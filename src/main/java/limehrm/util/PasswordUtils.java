package limehrm.util;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.GeneralDigest;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.KeyParameter;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.PKCS5S2ParametersGenerator;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.SHA256Digest;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtils {
    public static String hashPassword(byte[] password, byte[] salt) {
        GeneralDigest algorithm = new SHA256Digest();
        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(algorithm);
        generator.init(password, salt, 12);
    
        return new String(((KeyParameter) generator.generateDerivedParameters(256)).getKey());
    }
    
    public static byte[] generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
