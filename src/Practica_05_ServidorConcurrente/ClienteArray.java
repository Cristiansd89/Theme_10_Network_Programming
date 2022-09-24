package Practica_05_ServidorConcurrente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteArray {
	

	public static void main(String[] args) {
		
		Socket cliente; 
		int arrayEnteros [] = {5,3,1,7,2,9};
		
		
		
		try {
			cliente = new Socket("localhost", 8000);
			
			
		
			ObjectOutputStream salidaCliente = new ObjectOutputStream(cliente.getOutputStream()); //Flujo de salida del cliente			
			
			salidaCliente.writeObject(arrayEnteros); // Enviamos el Array
			
			
			ObjectInputStream entradacliente = new ObjectInputStream(cliente.getInputStream()); //Flujo de entrada al cliente
			
			int arrayOrdenado [] = (int []) entradacliente.readObject(); // Lectura el Array enviado por el servidor
			
			for(int i = 0; i < arrayOrdenado.length; i++) { // Lo Leemos y los mostramos por
				System.out.println(arrayOrdenado[i]);
			}
			
			
			
			salidaCliente.close();
			entradacliente.close();
		}catch(Exception e) {
			System.out.println("Error en el cliente");
		}
		

	}
	
	
	

}
