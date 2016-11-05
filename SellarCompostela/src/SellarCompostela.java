import java.security.PrivateKey;


public class SellarCompostela {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IntegridadFichero in = new IntegridadFichero();
		
		KeyProvider provider = new KeyProvider();
		PrivateKey prAlberge = provider.getPrivateKey(args[1]);
		PrivateKey prPeregrino = provider.getPrivateKey(args[2]);
		
		Sellador sellador = new Sellador();
		
		Paquete paq = PaqueteDAO.leerPaquete(args[0]);
		
		Interfaz i = new Interfaz();
		
		byte[] firma = sellador.hashAndSign(i.toString(), prAlberge);
		
		paq.anadirBloque(new Bloque("!"+ i.data.get("nombre")  +":",i.toString().getBytes()));
		paq.anadirBloque(new Bloque("!"+ i.data.get("nombre") +"Firma:",firma));
		
		in.WriteIntegridad(paq, prPeregrino);
		PaqueteDAO.escribirPaquete(args[0], paq);
	}
}
