package model.bean;

import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.PBEKeySpec;  
import javax.crypto.spec.SecretKeySpec;  
import java.nio.charset.StandardCharsets;  
import java.security.InvalidAlgorithmParameterException;  
import java.security.InvalidKeyException;  
import java.security.NoSuchAlgorithmException;  
import java.security.spec.InvalidKeySpecException;  
import java.security.spec.KeySpec;  
import java.util.Base64;  
import javax.crypto.BadPaddingException;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;  
public class EncryptDecrypt   
{  
	/* Private variable declaration */  
	private static final String SECRET_KEY = "123456789";  
	private static final String SALTVALUE = "abcdefg";
	private static final String PASS = "GROUP8";

	/* Encryption Method */  
	public static String encrypt(String strToEncrypt)   
	{  
		try   
		{  
			strToEncrypt = strToEncrypt.concat(PASS);

			String output = new String();
			for (int i = (strToEncrypt.length() - 1); i >= 0; i--) {
				output += (strToEncrypt.charAt(i));
			}

			strToEncrypt = output;

			/* Declare a byte array. */  
			byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
			IvParameterSpec ivspec = new IvParameterSpec(iv);        
			/* Create factory for secret keys. */  
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
			/* PBEKeySpec class implements KeySpec interface. */  
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
			SecretKey tmp = factory.generateSecret(spec);  
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);  
			/* Retruns encrypted value. */  
			return Base64.getEncoder()  
					.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));  
		}   
		catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)   
		{  
			System.out.println("Error occured during encryption: " + e.toString());  
		}  
		return null;  
	}  

	/* Decryption Method */  
	public static String decrypt(String strToDecrypt)   
	{  
		try   
		{  
			/* Declare a byte array. */  
			byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
			IvParameterSpec ivspec = new IvParameterSpec(iv);  
			/* Create factory for secret keys. */  
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
			/* PBEKeySpec class implements KeySpec interface. */  
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
			SecretKey tmp = factory.generateSecret(spec);  
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");  
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);  
			/* Retruns decrypted value. */  

			String deString = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

			// strToEncrypt = strToEncrypt + PASS;

			String output = new String();
			for (int i = (deString.length() - 1); i >= 0; i--) {
				output += (deString.charAt(i));
			}

			deString = output.replace(PASS,"");

			return deString ;  
		}   
		catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)   
		{  
			System.out.println("Error occured during decryption: " + e.toString());  
		}  
		return null;  
	} 
}