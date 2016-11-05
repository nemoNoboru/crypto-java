import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Interfaz {
	Map<String,String> data;
	
	public Interfaz(){
		data = new HashMap<String,String>();
		
		System.out.println("Introduzca los siguientes datos para continuar");
		
		System.out.println("Nombre del peregrino: ");
		data.put("Nombre", System.console().readLine());
		
		System.out.println("DNI: ");
		data.put("DNI", System.console().readLine());
		
		System.out.println("Domicilio: ");
		data.put("Domicilio", System.console().readLine());
		Date t = new Date();
		
		data.put("fecha", t.toString());
		
		System.out.println("Lugar: ");
		data.put("lugar", System.console().readLine());
		
		System.out.println("Motivaciones de peregrinaje: ");
		data.put("motivaciones", System.console().readLine());
	}
	
	public String toString(){
		System.out.println(JSONUtils.map2json(this.data));
		return JSONUtils.map2json(this.data);

	}
}
