package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Codificar {

	private final static String TIPO_COFICACION = "SHA-256";
	
	
	
	
	
	public String codificar(String clave) {
		
		String hashValue = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(TIPO_COFICACION);
			
			messageDigest.update(clave.getBytes());
			
			byte[] digestedByte = messageDigest.digest();
			
			hashValue = DatatypeConverter.printHexBinary(digestedByte).toLowerCase();							
			
		} catch (NoSuchAlgorithmException e) {e.printStackTrace();}
		
		
		return hashValue;
	}
	
	
	public boolean comparar(String clave, String pwd)
	{				
		if (codificar(clave).equals(pwd)) return true;	
		return false;
	}		
	
}
