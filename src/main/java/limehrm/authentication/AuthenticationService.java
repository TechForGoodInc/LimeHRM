//package limehrm.authentication;
//
//import com.sun.mail.util.BASE64EncoderStream;
//import limehrm.exceptions.KeyPairNotGeneratedException;
//import limehrm.exceptions.PasswordNotEncryptedException;
//import limehrm.util.ArrayTools;
//import limehrm.util.JwtUtil;
//import limehrm.util.LoggerUtil;
//import limehrm.util.PasswordUtils;
//import net.bytebuddy.utility.RandomString;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64Encoder;
//import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.GeneralDigest;
//import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.KeyParameter;
//import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.PKCS5S2ParametersGenerator;
//import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.SHA256Digest;
//
//import javax.crypto.*;
//import javax.crypto.spec.PBEKeySpec;
//import javax.xml.bind.DatatypeConverter;
//import java.io.ByteArrayOutputStream;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.StringWriter;
//import java.math.BigInteger;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.security.*;
//import java.security.cert.X509Certificate;
//import java.security.interfaces.RSAKey;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.sql.Array;
//import java.util.*;
//
//public class AuthenticationService {
//    private static HashMap<String, KeyPair> emailToKeyPairMap = new HashMap<>();
//    private static final LoggerUtil logger = new LoggerUtil(AuthenticationService.class.getSimpleName());
//
//    public static AuthenticationResponse getToken(KeyPair applicationKeyPair, String grantType, String authHeader) {
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//        logger.logWarn("askldjfasdklfj");
//
//        if (grantType.equals("password")) {
//            // Byte array for enhanced security (Strings are immutable and we don't want to store passwords in code)
//            byte[] base64Payload = Base64.getDecoder().decode(Objects.requireNonNull(authHeader).substring(6));
//
//            // Convert payload to character array
//            char[] base64PayloadCharArray = new String(base64Payload, StandardCharsets.UTF_8).toCharArray();
//            logger.logWarn(new String(base64PayloadCharArray));
//
//            int colonIndex = ArrayUtils.indexOf(base64PayloadCharArray, ':');
//
//            char[] emailChars = ArrayUtils.subarray(base64PayloadCharArray, 0, colonIndex);
//            char[] encryptedPasswordChars = ArrayUtils.subarray(base64PayloadCharArray, colonIndex + 1, base64PayloadCharArray.length);
//            logger.logWarn("ENCRYPTYED: {}", new String(encryptedPasswordChars));
//            Arrays.fill(base64PayloadCharArray, (char) 0);
//
//            byte[] decryptedPasswordBytes;
//
//            // Decrypt authHeader with private key and store in char array
//            try {
//                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//                KeyPair keyPair = emailToKeyPairMap.get(new String(emailChars));
//
//                if (keyPair == null) {
//                    throw new KeyPairNotGeneratedException();
//                }
//
//                cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
//
//                byte[] byteStr = Base64.getDecoder().decode(ArrayTools.toBytes(encryptedPasswordChars));
//
//                decryptedPasswordBytes = cipher.doFinal(byteStr);
//
//            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
//                throw new RuntimeException("Unable to Decrypt"); // TODO: Fix erros fix'm
//            } catch (BadPaddingException | IllegalBlockSizeException e) { // deal with this later
//                e.printStackTrace();
//                throw new PasswordNotEncryptedException();
//            }
//
//            logger.logWarn("Password: {}", new String(decryptedPasswordBytes));
//
//            String hashedPassword = PasswordUtils.hashPassword(decryptedPasswordBytes, salt.getBytes(StandardCharsets.UTF_8));
//
//            authenticationResponse = new AuthenticationResponse(AuthenticationController.getAuthenticationToken(applicationKeyPair.getPrivate(), new String(emailChars), hashedPassword));
//        }
//
//        return authenticationResponse;
//    }
//
//    public static AuthenticationPublicKeyResponse generatePublicKeyForEmail(String email) {
//
//        KeyPair keyPair = JwtUtil.generateKeyPair();
//        emailToKeyPairMap.put(email, keyPair);
//
//        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
//
//        String base64EncodedKey = new String(Base64.getEncoder().encode(rsaPublicKey.getEncoded()));
//
//        String publicKeyPEM = "-----BEGIN CERTIFICATE-----\n"
//                + base64EncodedKey +
//                "\n-----END CERTIFICATE-----";
//
//        return new AuthenticationPublicKeyResponse(publicKeyPEM);
//    }
//}
