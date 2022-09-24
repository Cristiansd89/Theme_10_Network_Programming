package Practica_08_ConcurrenteBusquedaBinaria;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorBusquedaBinaria {

	public static void main(String[] args) {

		try {
			/*
			 * Conexion del servidor esto siempre es igual
			 */
			int i = 0;
			ServerSocket servidor = new ServerSocket(8000); // Creamos servidor
			System.out.println("Atendiendo peticiones");
			
			while(true) {
				i++;
				Socket comunicaCliente = servidor.accept();// Aceptamos servidor
				System.out.println("atendiendo la peticion "+i);
				
				Thread hilo = new ManejadorHilos(comunicaCliente,i);
				hilo.start();
				
			}
			
			
		} catch (IOException e) {
			System.out.println("Error en el servidor ");
		}
	}

}///Servidor


class ManejadorHilos extends Thread {
	
	/*
	 * Atributos
	 */
	Socket comunicacionCliente;
	ObjectInputStream entradaServidor;
	ObjectOutputStream salidaServidor;
	

	int [] arrayOrdenado;
	int cont;
	int busqueda;
	String cadena;
	
	
	
	
	/*
	 * Metodo constructor
	 */
	public ManejadorHilos(Socket comunicacionCliente,int cont) {
		this.comunicacionCliente = comunicacionCliente;
		this.cont= cont;
	}
	
	public void run() {
		
		
		try {
			
			entradaServidor = new ObjectInputStream(comunicacionCliente.getInputStream()); // Flujo de entrada al servidor 
			arrayOrdenado	= (int[]) entradaServidor.readObject(); //Metodo que introduce los datos dentro del Array
			busqueda = (int) entradaServidor.readObject(); //Variable que vamos a buscar
			int inicio =0;
			int fin = arrayOrdenado.length-1;
			int medio;
			
				do {
					medio = (inicio +fin )/2; // Mitad del Array
					
					if(arrayOrdenado[medio]<busqueda) { // si el numero que buscamos es mayor al dato de la mitad del array 
						inicio = medio - 1; // la posicion de inicio pasara a ser la la posicion del medio
					}else {
						fin = medio + 1; // si no la posicion del final pasara a ser la posicion del medio
					}
					
					
				}while(inicio <= fin && busqueda != arrayOrdenado[medio]);
				cadena ="El numero "  +busqueda+ " Se encuentra en la posicion " +medio;
			
			salidaServidor =  new ObjectOutputStream(comunicacionCliente.getOutputStream());
			salidaServidor.writeObject(cadena);
			
		} catch (IOException e) {
			
			System.out.println("Error en el Hilo de informacion");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("Error en la clase de lectura (readObject");		
			}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
}
