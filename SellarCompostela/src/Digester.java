import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;


public class Digester {
	private MessageDigest digester;
	
	public Digester(){
		Security.addProvider(new BouncyCastleProvider()); // Cargar el provider BC
		try {
			digester = MessageDigest.getInstance("SHA-256","BC");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			System.out.println("No se ha encontrado sha256... Abortando");
			System.exit(1);
		}
	}
	
	public byte[] digest(byte[] input){
		return digester.digest(input);
	}
	
	public boolean equalToHash(byte[] a, byte[] hash){
		return Arrays.areEqual(digester.digest(a), hash);
	}
	
}
