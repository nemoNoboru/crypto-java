import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class KeyProvider {
	private KeyFactory keyFactoryRSA = null;
	
	public KeyProvider(){
		Security.addProvider(new BouncyCastleProvider()); // Cargar el provider BC
		try {
			keyFactoryRSA = KeyFactory.getInstance("RSA", "BC");
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("Provider no encontrado... Abortando");
			System.exit(1);
		} catch (NoSuchProviderException e1) {
			System.out.println("Algoritmo no encontrado... Abortando");
			System.exit(1);
		}
	}
	
	public PublicKey getPublicKey(String filename){
		
		File ficheroClavePublica = new File(filename + ".publica"); 
		int tamanoFicheroClavePublica = (int) ficheroClavePublica.length();
		byte[] bufferPub = new byte[tamanoFicheroClavePublica];
		
		try {
			
			FileInputStream in = new FileInputStream(ficheroClavePublica);
			in.read(bufferPub, 0, tamanoFicheroClavePublica);
			in.close();
			X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(bufferPub);
			return keyFactoryRSA.generatePublic(clavePublicaSpec);
			
		} catch (IOException e) {
			System.out.println("Fichero no encontrado... Abortando");
			System.exit(1);
		} catch (InvalidKeySpecException e) {
			System.out.println("Fichero no valido... Abortando");
			System.exit(1);
		}
		return null;
	}
	
	public PrivateKey getPrivateKey(String filename){
		
		File ficheroClavePrivada = new File(filename + ".privada"); 
		int tamanoFicheroClavePrivada = (int) ficheroClavePrivada.length();
		byte[] bufferPriv = new byte[tamanoFicheroClavePrivada];
		
		try {
			
			FileInputStream in = new FileInputStream(ficheroClavePrivada);
			in.read(bufferPriv, 0, tamanoFicheroClavePrivada);
			in.close();
			PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
			return keyFactoryRSA.generatePrivate(clavePrivadaSpec);
			
		} catch (IOException e) {
			System.out.println("Fichero no encontrado... Abortando");
			System.exit(1);
		} catch (InvalidKeySpecException e) {
			System.out.println("Fichero no valido... Abortando");
			System.exit(1);
		}
		return null;
	}
}
