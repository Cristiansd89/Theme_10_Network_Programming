package Practica_08_ConcurrenteBusquedaBinaria;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteBusqueda {

	public static void main(String[] args) {
		
		Socket cliente;
		ObjectOutputStream salidaCliente;
		ObjectInputStream entradaCliente;
		String cadena;
		
		int [ ] ArrayOrdenado = {1,2,3,4,9,12,15,25,27,32,34,36,40,41,42};
		int busqueda = 36;
		
		try {
			
			
			
			
			
			cliente = new Socket("localhost", 8000); //Creamos el Socket
			
			salidaCliente = new ObjectOutputStream(cliente.getOutputStream()); //Abrimos flujo de salida
			
			salidaCliente.writeObject(ArrayOrdenado); // enviamos el Array 
			salidaCliente.writeObject(busqueda); //enviamos el valor para buscar
			
			entradaCliente = new ObjectInputStream(cliente.getInputStream());
			
			cadena=  (String) entradaCliente.readObject();
			 
			  
			  cliente.close();
				 
			  
			  System.out.println(cadena);
			 
			
			
		} catch (UnknownHostException e) {
			System.out.println("Cliente primer catch");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Cliente segundo catch");
			e.printStackTrace();
		}
		
		
		
		
	}

}
