import java.security.PrivateKey;
import java.security.PublicKey;


public class CompostelaBuilder {

	private Paquete paq;
	private String name;
	private AESCipher cipher;
	private RSACipher asimCipher;
	private Digester digester; 
	private IntegridadFichero integridad;
	
	public CompostelaBuilder(String namePaquete){
		name = namePaquete;
		paq = new Paquete();
		cipher = new AESCipher();
		cipher.newKey();
		asimCipher = new RSACipher();
		digester = new Digester();
		integridad = new IntegridadFichero();
	}
	
	public void pushKey(PublicKey k){
		byte[] cifrada = asimCipher.Cifrar(cipher.getKey().getEncoded(), k);
		paq.anadirBloque(new Bloque("!KEY:",cifrada));
	}
	
	public void pushDataUsuario(String json, PrivateKey key_usuario){
		byte[] dataCifrada = cipher.cifrar(json.getBytes());
		byte[] digest = digester.digest(dataCifrada);
		byte[] cipherDigest = asimCipher.Cifrar(digest, key_usuario);
		
		paq.anadirBloque(new Bloque("!USERHASH:", cipherDigest));
		paq.anadirBloque(new Bloque("!USER:", dataCifrada));
	}
	
	public void write(PrivateKey k){
		integridad.WriteIntegridad(paq, k);
		PaqueteDAO.escribirPaquete(name, paq);
	}
}
