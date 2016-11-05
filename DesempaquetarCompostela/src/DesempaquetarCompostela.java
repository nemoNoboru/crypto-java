import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;


public class DesempaquetarCompostela {

	public static void main(String[] args) {
		KeyProvider provider = new KeyProvider();
		PrivateKey prOficina = provider.getPrivateKey(args[1]);
		
		PublicKey puPeregrino = provider.getPublicKey(args[2]);
		
		CompostelaReader reader = new CompostelaReader(args[0]);
		
		reader.readKey(prOficina);
		
		System.out.println("Integridad del mensaje...");
		
		System.out.println(reader.checkIntegridad(puPeregrino));
		System.out.println("Integridad datos del usuario");
		
		System.out.println(reader.checkUserData(puPeregrino));
		
		showMap(reader.readUserData());
		
		Map<String, PublicKey> albergues = new HashMap<String, PublicKey>(); 
		int numAlbergues = Integer.parseInt(args[3]);
		for(int i = 0; i < numAlbergues ;i++){
			albergues.put(args[i+4], provider.getPublicKey(args[i+4]));
			for(String alberguename:albergues.keySet()){
				System.out.println("Integridad del sello del albergue "+alberguename+"...");
				System.out.println(reader.checkAlbergue(alberguename, albergues.get(alberguename)));
				showMap(reader.readAlbergue(alberguename));
			}
		}

	}
	
	public static void showMap(String string){
		Map<String,String> data = JSONUtils.json2map(string);
		for(String t:data.keySet()){
			System.out.println(t);
			System.out.println(data.get(t));
		}
		System.out.println();
		System.out.println();
	}

}
