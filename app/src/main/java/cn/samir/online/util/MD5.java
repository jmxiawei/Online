package cn.samir.online.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Wongxming E-mail: Wongxming@eoemobile.com
 * @version 1.0
 */
public final class MD5 {
    private static final String ALGORITHM = "MD5";

    private static char sHexDigits[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    private static MessageDigest sDigest;

    static {
        try {
            sDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
//            throw new UnsupportedDigestAlgorithmException(ALGORITHM, e);
        }
    }

    private MD5() {
    }


    public static String encode(String source) {
        byte[] btyes = source.getBytes();
        byte[] encodedBytes = sDigest.digest(btyes);

        return String.valueOf(encodedBytes).toLowerCase();
    }


    public static String md5s(String plainText) {
        String str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

//	public static void main(String[] args) {
//		// MD5 ("") = d41d8cd98f00b204e9800998ecf8427e
//		System.out.println(MD5.encode(""));
//		// MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
//		System.out.println(MD5.encode("a"));
//		// MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72
//		System.out.println(MD5.encode("abc"));
//	}
}
