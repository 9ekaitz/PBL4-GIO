package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Codificar {

	private final static String TIPO_COFICACION = "SHA-256";
	
	
	
	
	
	public String codificar(String clave) {
		
		String hashValue = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(TIPO_COFICACION);
			
			messageDigest.update(clave.getBytes());
			
			byte[] digestedByte = messageDigest.digest();
			
			hashValue = bytesToHex(digestedByte).toLowerCase();
			
		} catch (NoSuchAlgorithmException e) {e.printStackTrace();}
		
		
		return hashValue;
	}
	
	
	public boolean comparar(String clave, String pwd)
	{				
		if (codificar(clave).equals(pwd)) return true;	
		return false;
	}	
	
	
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	
}
