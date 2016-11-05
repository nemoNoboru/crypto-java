import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class AESCipher {
	
	private SecretKey key;
	private Cipher cipher;
	
	public void setKey(SecretKey k){
		key = k;
	}
	
	public void setKey(byte[] k){
		key = new SecretKeySpec(k,"AES");
	}
	public void newKey(){
		KeyGenerator KeyGen = null;
		try {
			KeyGen = KeyGenerator.getInstance("AES");
			KeyGen.init(256);
			key = KeyGen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error, algorithm not exists");
			System.exit(1);
		}
	}
	
	public SecretKey getKey(){
		return key;
	}
	
	public byte[] cifrar(byte[] msg){
		try {
			cipher.init(Cipher.ENCRYPT_MODE,key);
			return cipher.doFinal(msg);
		} catch (InvalidKeyException e) {
			System.out.println("Clave no valida... Abortando");
			System.exit(1);
		} catch (IllegalBlockSizeException e) {
			System.out.println("Tamano de bloque ilegal... Abortando");
			System.exit(1);;
		} catch (BadPaddingException e) {
			System.out.println("padding ilegal... Abortando");
			System.exit(1);
		}
		return null;
	}
	
	public byte[] descifrar(byte[] msg){
		try {
			cipher.init(Cipher.DECRYPT_MODE,key);
			return cipher.doFinal(msg);
		} catch (InvalidKeyException e) {
			System.out.println("Clave no valida... Abortando");
			System.exit(1);
		} catch (IllegalBlockSizeException e) {
			System.out.println("Tamano de bloque ilegal... Abortando");
			System.exit(1);;
		} catch (BadPaddingException e) {
			System.out.println("padding ilegal... Abortando");
			System.exit(1);
		}
		return null;
	}
	
	public AESCipher(){
		Security.addProvider(new BouncyCastleProvider()); // Cargar el provider BC
		try {
			cipher = Cipher.getInstance("AES", "BC");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | NoSuchProviderException e) {
			System.out.println("Error encontrado...Abortando");
			System.exit(1);
		}
	}

}
