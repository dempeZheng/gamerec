package cn.mob.gamerec.util;

import com.lamfire.code.AES;
import com.lamfire.code.MD5;

import java.io.UnsupportedEncodingException;

/**
 * @Description 加密工具类
 * @author hewt
 * @date 2014-4-18上午10:19:49
 * @version 1.0
 * @JDK 1.6
 */
public class EncryptUtil {
	private static byte[] key = null;
	private static AES aes = null;

	static {
		try {
			key = MD5.digest("sharerec.aes.key".getBytes("UTF-8"));
			aes = new AES(key);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: TODO
	 * byte 转16 进制
	 * @param b
	 */
	public static void printHexString( byte[] b) {    
		   for (int i = 0; i < b.length; i++) {   
		     String hex = Integer.toHexString(b[i] & 0xFF);   
		     if (hex.length() == 1) {   
		       hex = '0' + hex;   
		     }   
		     System.out.print(hex);   
		   }   
		  
		} 

	/**
	 * 默认加密方法
	 * 
	 * @param datas
	 * @return
	 */
	public static byte[] encode(byte[] datas) {
		return aes.encode(datas);
	}

	/**
	 * 默认解密方法
	 * 
	 * @param datas
	 * @return
	 */
	public static byte[] decode(byte[] datas) {
		return aes.decode(datas);
	}

	/**
	 * 加密方法，key由我们传进去
	 * 
	 * @param datas
	 * @param key
	 * @return
	 */
	public static byte[] encode(byte[] datas, String key) {
		try {
			return new AES(MD5.digest(key.getBytes("UTF-8"))).encode(datas);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 解密方法，key由我们传进去
	 * 
	 * @param datas
	 * @param key
	 * @return
	 */
	public static byte[] decode(byte[] datas, String key) {
		try {
			return new AES(MD5.digest(key.getBytes("UTF-8"))).decode(datas);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

}