package Practica_13_BusquedaSecuencial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteBusqueda {

	public static void main(String[] args) {

			Socket cliente;
			ObjectInputStream entradaCliente;
			ObjectOutputStream salidaCliente;
			String[] cadenas = {"Gonzalo", "George", "Mirna", "Maria", "Alex" , "Paulo"};
			String busqueda = "Mirna";
			
			
			try {
				
				cliente = new Socket("localhost" ,8000);
				
				
				salidaCliente = new ObjectOutputStream(cliente.getOutputStream());
				salidaCliente.writeObject(cadenas);
				salidaCliente.writeObject(busqueda);
				
				
				entradaCliente = new ObjectInputStream(cliente.getInputStream());
				
				 
				String cadena = (String) entradaCliente.readObject();
				
				System.out.println(cadena);
				
				
				
				cliente.close();
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
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
