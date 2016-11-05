import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Interfaz {
	Map<String,String> data;
	
	public Interfaz(){
		data = new HashMap<String,String>();
		
		System.out.println("Introduzca los siguientes datos para continuar");
		
		System.out.println("Nombre del albergue: ");
		data.put("nombre", System.console().readLine());
		
		Date t = new Date();
		
		data.put("fecha", t.toString());
		
		System.out.println("Lugar: ");
		data.put("lugar", System.console().readLine());
		
		System.out.println("Incidencias: ");
		data.put("incidencias", System.console().readLine());
	}
	
	public String toString(){
		return JSONUtils.map2json(this.data);
	}
}
