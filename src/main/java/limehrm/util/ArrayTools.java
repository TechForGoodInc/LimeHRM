package limehrm.util;

//import limehrm.authentication.AuthenticationService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ArrayTools {
    private static final LoggerUtil logger = new LoggerUtil(ArrayTools.class.getSimpleName());
    
    
    /** 
     * @param array1
     * @param array2
     * @return byte[]
     */
    public static byte[] addAll(final byte[] array1, byte[] array2) {
        byte[] joinedArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        
        return joinedArray;
    }
    

/** 
 * @return byte[]
 */
//    public static char[] toChars(byte[] byteArray, Charset charset) {
//        char[] charArray;
//
//        try {
//            InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(byteArray), charset);
//            charArray = new char[byteArray.length];
//            inputStreamReader.read(charArray);
//            logger.logWarn(Integer.toString(byteArray.length >> 1));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return charArray;
////        
//        char[] buffer = new char[byteArray.length >> 1];
//        
//        for(int i = 0; i < buffer.length; i++) {
//            int bpos = i << 1;
//            char c = (char)(((byteArray[bpos]&0x00FF)<<8) + (byteArray[bpos+1]&0x00FF));
//            buffer[i] = c;
//        }
//        
//        return buffer;
//    }
    
    public static byte[] toBytes(char[] charArray) {
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(CharBuffer.wrap(charArray));
        
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        
        // Clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); 
        
        return bytes;
    }
}
