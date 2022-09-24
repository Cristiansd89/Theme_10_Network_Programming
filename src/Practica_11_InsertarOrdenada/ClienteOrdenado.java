package Practica_11_InsertarOrdenada;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteOrdenado {

	public static void main(String[] args) {
		
		ObjectOutputStream salidaCliente;
		ObjectInputStream entradaCliente;
		
		int arrayOut[] = {1,2,3,4,5,7,8,9,10};
		int arrayInput  [];
		int dato = 6;
		
		
		try {
			Socket cliente = new Socket("localhost", 8000);
			
			salidaCliente = new ObjectOutputStream(cliente.getOutputStream());
			
			salidaCliente.writeObject(arrayOut);
			salidaCliente.writeObject(dato);
			
			
			entradaCliente = new ObjectInputStream(cliente.getInputStream());
			
			 try {
				arrayInput = (int[]) entradaCliente.readObject();
				
				
				for (int i = 0; i< arrayInput.length ; i++) {
					System.out.println(arrayInput[i]);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
