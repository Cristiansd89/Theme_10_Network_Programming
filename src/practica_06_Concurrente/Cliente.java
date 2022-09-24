package practica_06_Concurrente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	
	

	public static void main(String[] args) {
		
		
		int array1 [] = {1,2,5,8,9};
	
		int array2 [] = {3,5,7,10,12};
		
		
		
		
		try {
			Socket cliente = new Socket("localhost", 8000);
			
			ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream()); // Flujo de salida hacia el servidor
			
			ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());// Flujo de entrada 
			
			
			flujoSalida.writeObject(array1);
			flujoSalida.writeObject(array2);
			
			
			
			 try {
				int fusionarray[]= (int[]) (flujoEntrada.readObject());
				
				
				
				for(int i = 0; i < fusionarray.length ; i++) {
					
					System.out.println(fusionarray[i]);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			 cliente.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
