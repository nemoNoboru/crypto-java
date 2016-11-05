import java.security.PrivateKey;
import java.security.PublicKey;


public class CompostelaReader {
	private Paquete paq;
	private AESCipher cipher;
	private RSACipher asimCipher;
	private Digester digester;
	
	public CompostelaReader(String filename){
		cipher = new AESCipher();
		asimCipher = new RSACipher();
		paq = PaqueteDAO.leerPaquete(filename);
		digester = new Digester();
	}
	
	public void readKey(PrivateKey key){
		byte[] desc = asimCipher.Descifrar(paq.getBloque("!KEY:").getContenido(), key);
		cipher.setKey(desc);
	}
	
	public String readUserData(){
		byte[] desc = cipher.descifrar(paq.getBloque("!USER:").getContenido());
		return new String(desc);
	}
	
	public boolean checkUserData(PublicKey k){
		byte[] original = paq.getBloque("!USER:").getContenido();
		byte[] cifrado = paq.getBloque("!USERHASH:").getContenido();
		cifrado = asimCipher.Descifrar(cifrado,k);
		return digester.equalToHash(original, cifrado);
	}
	
	public String readAlbergue(String albergue){
		return new String(paq.getBloque("!"+ albergue  +":").getContenido());
	}
	
	public boolean checkAlbergue(String albergue, PublicKey k){
		byte[] original = paq.getBloque("!"+ albergue  +":").getContenido();
		byte[] cifrado = paq.getBloque("!"+ albergue  +"Firma:").getContenido();
		cifrado = asimCipher.Descifrar(cifrado, k);	
		return digester.equalToHash(original, cifrado);
	}
	
	public boolean checkIntegridad(PublicKey k){
		IntegridadFichero in = new IntegridadFichero();
		return in.CheckIntegridad(paq, k);
	}
}
