package Practica_14_chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;


public class ServidorChat {
	static LinkedList<Socket> usuarios = new  LinkedList<>();
	
	private static Socket comunicaCliente;

	public static void main(String[] args) {

	
	
	try {
	
		int  cont = 0; // Contador de usuarios conectados
		ServerSocket servidor = new ServerSocket(8000);  // Creamos el serverSocket
		
		
		while(true) {
			System.out.println("Servidor encendido");
			comunicaCliente = servidor.accept(); // Aceptamos la conexion
			System.out.println("Cliente conectado");
			Thread usuario = new ManejadorHilos(cont,comunicaCliente); // Clase Manejador
			usuarios.add(comunicaCliente);
			cont++; // incrementamos el tipo 
			usuario.start();
			
			
			
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	

}/*
	Recorrer el arrayList de Sokect enviando el mensaje por todos los sockets
 */
	public static void difundir (String mensaje) {
		
		for(Socket usuario : usuarios) {
			try {
				BufferedWriter salidaServidor = new BufferedWriter(new OutputStreamWriter(usuario.getOutputStream())); 
				salidaServidor.write(mensaje); // 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Recorre todo el arrayList si coincide con el que  esta conectado lo elimina
	 * de la lista y lo cierra
	 */
	public static void eliminar() {
		
		try {
			for(Socket usuario : usuarios ) {
				if(usuario.equals(comunicaCliente)) {
					usuarios.remove(usuario);
				}
			}
			
			comunicaCliente.close();
			
		}catch(Exception e) {
			
		}
	}


}
class ManejadorHilos extends Thread{
	
	private static Socket comunicaCliente;
	int cont;
	String nick = null;
	
	private static BufferedReader entradaServidor;
	private static BufferedWriter salidaServidor;
	
	
	public ManejadorHilos(int cont,Socket comunicaCliente) {
		this.comunicaCliente = comunicaCliente;
		this.cont = cont;
	}
	
	public void run() {
		
		

		ServidorChat servidor = new ServidorChat();
			
		try {
			
				entradaServidor = new BufferedReader(new InputStreamReader(comunicaCliente.getInputStream()));
								
				boolean salir = false;
				String mensaje = "";
				
		        do {  
		        	
					mensaje = entradaServidor.readLine();
		            servidor.difundir(mensaje);

		               if (mensaje.equals("adios")) {
		            	   ServidorChat.eliminar();
		            	   salir = true; 
		               }
		        
				}while (!salir);
				         comunicaCliente.close();

			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

