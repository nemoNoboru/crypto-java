import java.security.PrivateKey;
import java.security.PublicKey;


public class Sellador {
	private RSACipher cipher;
	private Digester digester;
	
	public Sellador(){
		cipher = new RSACipher();
		digester = new Digester();
	}
	
	public byte[] hashAndSign(String msg, PrivateKey k){
		byte[] hash = digester.digest(msg.getBytes());
		return cipher.Cifrar(hash, k);
	}
	
	public boolean checkHashAndSign(String message, byte[] cipherhash, PublicKey k){
		byte [] hash = cipher.Descifrar(cipherhash, k);
		return digester.equalToHash(message.getBytes(), hash);
	}
}
