package pro.bit.bitproject.common;

import java.security.MessageDigest;

public class PasswordEncryptUtil {
	
	public static String encryptPassword(String passwordString)throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordString.getBytes());

        byte bytes[] = md.digest();

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
        buffer.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return buffer.toString();
    }
}
