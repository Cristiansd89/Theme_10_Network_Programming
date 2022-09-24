package Practica_09_ConcurrrenteEliminar;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorEliminar {

	public static void main(String[] args) {
		
		
		
		try {
			int cont = 0;
			ServerSocket servidor = new ServerSocket(8000);
			System.out.println("Atendiendo peticiones");
			
			while(true) {
				cont++;
				Socket comunicaCliente = servidor.accept();// Aceptamos servidor
				System.out.println("atendiendo la peticion "+cont);
				
				Thread hilo = new ManejadorHilos(comunicaCliente,cont);
				hilo.start();
				
			}
			
	
			
		} catch (IOException e) {
			System.out.println("Error en el servidor");
			e.printStackTrace();
		}
		
	}

}
class ManejadorHilos extends Thread {
	
	Socket comunicaCliente;
	
	ObjectInputStream entradaServidor;
	ObjectOutputStream salidaServidor;
	
	int [] arrayInput;
	int [] arrayOut;
	int cont;
	int posicion;
	
	public ManejadorHilos (Socket comunicaCliente,int cont) {
		this.comunicaCliente = comunicaCliente;
		this.cont = cont;
	}
	
	
	public void run() {
		
		try {
			
			entradaServidor = new ObjectInputStream(comunicaCliente.getInputStream()); //Flujo de entrada al servidor
			
			arrayInput =(int[]) entradaServidor.readObject();  // Lectura del Array
			posicion = (int)entradaServidor.readObject();//lectura de la posicion
			
			int tamMax = arrayInput.length;
			
			/*
			 * Se lee desde la posicion que queremos eliminar hacia delante
			 */
			for(int i = posicion; i< tamMax-1; i++) { 
				arrayInput[i] = arrayInput[i+1]; // Pasamos hacia atras  el dato contenido
				
			}
			tamMax--;		//Reducimos el tamaño y se lo pasamos al nuevo array
			arrayOut = new int [tamMax];
			
			for(int i = 0;i < tamMax;i++) {
				arrayOut[i] = arrayInput[i]; // copiamos toda la informacion en el nuevo array
			}
			
			
			
			salidaServidor = new ObjectOutputStream(comunicaCliente.getOutputStream()); // flujo de salida
			
			salidaServidor.writeObject(arrayOut); // // envio del array al cliente de nuevo

			
			
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
}
