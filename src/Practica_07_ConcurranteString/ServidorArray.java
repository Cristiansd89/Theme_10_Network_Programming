package Practica_07_ConcurranteString;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorArray {

	public static void main(String[] args) {
	try {
			
			
			int i = 1;
			ServerSocket servidor = new ServerSocket(8000);
			System.out.println("Atendiendo peticiones");
			
			while(true) {
				Socket comunicacionCliente = servidor.accept(); //Aceptamos la conexion
				System.out.println("atediendo la peticion " +i);
				// falta crear el hilo de la clase tipo ManejadorEcoHilo
				Thread hilo = new ManejadorHilos(comunicacionCliente,i);
				hilo.start();
				i++;
			}
			
			
			
		}catch(Exception e ) {
			System.out.println("Error en el servidor");
		}
	}
}

class ManejadorHilos extends Thread{
	Socket comunicacionCliente;
	int cont ;
	
	
	public ManejadorHilos(Socket comunicacionCliente,int cont) {
		this.comunicacionCliente = comunicacionCliente;
		this.cont = cont;
	}
	
	
	
	public void run() {
		int cont = 0;
		try {
			
			DataInputStream flujoEntrada = new DataInputStream(comunicacionCliente.getInputStream());  // Flujo para leer Datos primitivos
			PrintWriter pantalla = new PrintWriter(comunicacionCliente.getOutputStream()); // abro flujo para escribir por pantallla
			
			String fraseEntrada = flujoEntrada.readUTF();
			String palabraEntrante = flujoEntrada.readUTF();
			
		
			/*
			 * // Cada vez que se encuentre un espacio en blanco lo añadira a una posicion 
			 * el siiguiente  grupo de caracteres lo añadira al array
			 */
			String[] arrayCadenas = fraseEntrada.split(" "); // Este metodo separa en un array por el metodo que le marquemos
			
			
			for(int i =0 ; i< arrayCadenas.length; i++) {
				
				if(arrayCadenas[i].equals(palabraEntrante)) {
					cont++;
				}
			}
			
			if(cont > 0) {
				System.out.println("La palabra " +palabraEntrante+ "Esta " +cont+ " veces");

			}else {
				System.out.println(palabraEntrante+ "No se encuentra en el texto");
			}
			
			
			
		comunicacionCliente.close();

							
		}
		catch (Exception e) {
			System.out.println("Error en la clase Manejador");
		}
		
	}
	
	
}
