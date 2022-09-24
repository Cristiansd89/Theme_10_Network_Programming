package Practica_13_BusquedaSecuencial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSecuencial {
	/*
	 * Esto es un servidor no Concurrente no tiene que tener hilos
	 */

	public static void main(String[] args) {
		
		ServerSocket servidor;
		ObjectInputStream entradaCliente;
		ObjectOutputStream salidaCliente;
		String [] arrayInput;
		int cont = 0;
		boolean encotrada = false;
		String busqueda;
		String cadena;
		
		
		try {
			 servidor = new ServerSocket(8000);
			System.out.println("Atendiendo Peticion");
			Socket comunicaCliente = servidor.accept(); // Aceptamos la conexion

			entradaCliente = new ObjectInputStream(comunicaCliente.getInputStream());
			
			arrayInput= (String[]) entradaCliente.readObject();
			busqueda = (String) entradaCliente.readObject();
				
				
			for (int i= 0; i< arrayInput.length ; i++) {
				cont++;
				if(busqueda.equalsIgnoreCase(arrayInput[i])) {
					encotrada = true;
				}
					
					
				}
				if(encotrada) {
					cadena = "la palabra "+busqueda+ "se encuentra en la posicion " +cont;
				}else {
					cadena = "la palabra" +busqueda+ " no esta en la lista";
				}
				
				salidaCliente = new ObjectOutputStream(comunicaCliente.getOutputStream());

				salidaCliente.writeObject(cadena);	
				
				
				servidor.close();
				
			} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	

