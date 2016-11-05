import java.security.PrivateKey;
import java.security.PublicKey;

public class IntegridadFichero {
	protected Digester dig;
	protected RSACipher cipher;
	
	public IntegridadFichero(){
		dig = new Digester();
		cipher = new RSACipher();
	}
	
	public byte[] ExtractAllData(Paquete p){
		String toret = null;
		for( String t:p.getNombresBloque()){
			if( !t.equals("!ALLHASH:")){
				toret +=  p.getBloque(t).getNombre();
			}
		}
		return toret.getBytes();
	}
	
	public void WriteIntegridad(Paquete p, PrivateKey k){
		byte[] data = this.ExtractAllData(p);
		byte[] hash = dig.digest(data);
		
		byte[] ciphered = cipher.Cifrar(hash, k);
		p.anadirBloque(new Bloque("!ALLHASH:",ciphered));
		PaqueteDAO.escribirPaquete("compostela.bin", p);
	}
	
	public boolean CheckIntegridad(Paquete p, PublicKey k){
		byte[] original = this.ExtractAllData(p);
		
		byte[] tryhash = p.getContenidoBloque("!ALLHASH:");
		byte[] desciphered = cipher.Descifrar(tryhash, k);
		return dig.equalToHash(original, desciphered);
	}
}
