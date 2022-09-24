package Practica_05_ServidorConcurrente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorArray {

	public static void main(String[] args) {
		
		
		try {
			
			int i = 1;
			ServerSocket servidor = new ServerSocket(8000);
			Socket comunicacionCliente = servidor.accept(); //Aceptamos la conexion
			System.out.println("atediendo la peticion " +i);
			// falta crear el hilo de la clase tipo ManejadorEcoHilo
			Thread hilo = new ManejadorHilos(comunicacionCliente, i);
			hilo.start();
			i++;
			
			
		}catch(Exception e ) {
			System.out.println("Error en el servidor");
		}
	}

	
}
class ManejadorHilos extends Thread {
	
	
	private Socket comunicacioncliente;
	private int contador ;
	
	public ManejadorHilos(Socket comunicacioncliente, int contador) {
		this.comunicacioncliente = comunicacioncliente;
		this.contador = contador;
		
	}
	public void run() {
		try {
			
			
			ObjectInputStream entradaServidor = new ObjectInputStream(comunicacioncliente.getInputStream()); // Flujo de entrada de datos  del servidor
			int aux = 0 ;
			
			int arrayServidor[] =(int []) entradaServidor.readObject(); // Lectura  del Array 
			
			
			boolean hayIntercmabio;
			do {
				 hayIntercmabio = false;
				
				for(int i = 0; i <arrayServidor.length -1; i ++) {
					
					if(arrayServidor[i]> arrayServidor[i+1]){
						
						aux = arrayServidor[i];
						arrayServidor[i] = arrayServidor[i+1];
						arrayServidor[i+1] = aux;
						
						hayIntercmabio = true;
					}
				
				}
			}while(hayIntercmabio);
			
			ObjectOutputStream salidaServidor = new ObjectOutputStream (comunicacioncliente.getOutputStream());
			
			salidaServidor.writeObject(arrayServidor);
		}catch (Exception e) {
			// TODO: handle exception
		}
	
		
		
		
		
	}
}
