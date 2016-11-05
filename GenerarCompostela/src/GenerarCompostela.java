import java.security.*;
import java.security.spec.*;
import java.util.Arrays;

import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

// Necesario para usar el provider Bouncy Castle (BC)
//    Para compilar incluir el fichero JAR en el classpath


// TODO clase interfaz
public class GenerarCompostela {
	public static void main(String[] args) throws Exception {
		/*if (args.length != 1) {
			mensajeAyuda();
			System.exit(1);
		}*/
		
		KeyProvider keyprovider = new KeyProvider();
		CompostelaBuilder cbld = new CompostelaBuilder(args[0]+".bin");
		
		// se leen las claves
		PublicKey OUKey = keyprovider.getPublicKey(args[1]);
		
		PrivateKey PRKey = keyprovider.getPrivateKey(args[2]);
		// contenido del paquete
		
		
		
		// se crea un paquete
		
		cbld.pushKey(OUKey);
		Interfaz i = new Interfaz();
		cbld.pushDataUsuario(i.toString(), PRKey);
		cbld.write(PRKey);
	}

	public static void mensajeAyuda() {
		System.out.println("Generador de pares de clave RSA de 512 bits");
		System.out.println("\tSintaxis:   java GenerarClaves prefijo");
		System.out.println();
	}
}
