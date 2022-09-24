package Practica_10_ConcurrenteInsertar;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteInsertar {

	public static void main(String[] args) {
			
		Socket cliente;
		
		ObjectOutputStream salidaCliente;
		ObjectInputStream entradaCliente;
		
		int [] arrayOut = {1,2,3,4,6,7,8};
		int [] arrayInput;
		int dato =5;
		int posicion= 4 ;

		
		 try {
			 
			 /*
			  * Vamos a enviar un array al servidor y un dato el servidor nos tendra que devolver 
			  * un array con el dato ya insertado
			  */
			 
			cliente = new Socket("localhost" ,8000);
			
			
			salidaCliente = new ObjectOutputStream(cliente.getOutputStream());
			
			salidaCliente.writeObject(arrayOut);
			salidaCliente.writeObject(dato);
			salidaCliente.writeObject(posicion);
			
			
			entradaCliente = new ObjectInputStream(cliente.getInputStream());

		
			 arrayInput = (int[]) entradaCliente.readObject();
			 
			 
			 for(int i = 0; i< arrayInput.length; i++) {
				 System.out.println(arrayInput[i]);
			 }
		
			
		} catch (ClassNotFoundException e) {
				System.out.println("Error al leer el Array");
				e.printStackTrace();	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
	}

}
