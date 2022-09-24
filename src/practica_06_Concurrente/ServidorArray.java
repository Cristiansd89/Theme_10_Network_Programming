package practica_06_Concurrente;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;

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
	class ManejadorHilos extends Thread {

		private Socket comunicacionCliente;
		private int contador ;
		
		

		public  ManejadorHilos(Socket comunicacionCliente,int contador) {
			this.comunicacionCliente = comunicacionCliente;
			this.contador = contador;
		
		}
	
		public void run() {
			
			try {
				
				
				ObjectInputStream entradaServidor = new ObjectInputStream(comunicacionCliente.getInputStream()); // Flujo de entrada al servidor
				
				ObjectOutputStream salidaServidor = new ObjectOutputStream(comunicacionCliente.getOutputStream());
				
					
				int array1[] = (int []) entradaServidor.readObject();
				int array2[] = (int []) entradaServidor.readObject();
				
				int[] fusionArray =  new int[array1.length +array2.length];
				
				int i = 0;
				int j = 0;
				int k = 0;
				
				while (i < array1.length && j < array2.length) {
					
					if(array1[i] < array2[j]) {
						fusionArray[k] = array1[i];
						i++;
						k++;
					}else {
						fusionArray[k] = array2[j];
						k++;
						j++;
					}
				
				}
				
				while (i < array1.length) {
					fusionArray[k] = array1[i];
					i++;
					k++;
					
				}
				
				while (j < array2.length) {
					fusionArray[k] = array2[j];
					j++;
					k++;
					
				}
				
				
				salidaServidor.writeObject(fusionArray);
					
					
				comunicacionCliente.close();
				
				
				
			} catch (Exception e) {
				System.out.println("Error en el servidor");
				e.printStackTrace();
			}
		}

		
	}

		
	


		
		
	
		


