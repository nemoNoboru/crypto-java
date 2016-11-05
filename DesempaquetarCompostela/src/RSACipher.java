import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class RSACipher {
	private Cipher cifrador;
	
	public RSACipher(){
		Security.addProvider(new BouncyCastleProvider()); // Cargar el provider BC
		try {
			cifrador = Cipher.getInstance("RSA", "BC");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Hace uso del provider BC
	}
	
	public byte[] Cifrar(byte[] msg, Key key){
		try {
			cifrador.init(Cipher.ENCRYPT_MODE,key);
			return cifrador.doFinal(msg);
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
			System.out.println("Clave no valida... Abortando");
			System.exit(1);
		} catch (IllegalBlockSizeException e) {
			System.out.println("Tamano de bloque ilegal... Abortando");
			System.exit(1);
		} catch (BadPaddingException e) {
			System.out.println("Mal padding... Abortando");
			System.exit(1);
		}
		return null;
	}
	
	public byte[] Descifrar(byte[] msg, Key key){
		try {
			cifrador.init(Cipher.DECRYPT_MODE, key);
			return cifrador.doFinal(msg);
		} catch (InvalidKeyException e1) {
			System.out.println("Clave no valida... Abortando");
			System.exit(1);
		} catch (IllegalBlockSizeException e) {
			System.out.println("Tamano de bloque ilegal... Abortando");
			System.exit(1);
		} catch (BadPaddingException e) {
			System.out.println("Mal padding... Abortando");
			System.exit(1);
		}
		return null;
	}
}
