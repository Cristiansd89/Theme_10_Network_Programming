package Practica_15_chat_02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

import Practica_16_InterfazChat.PaqueteEnvio;


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
			System.out.print("Cliente conectado");
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
				PrintWriter salidaServidor = new PrintWriter(usuario.getOutputStream(), true /* autoFlush Limpiar un buffer de salida*/);
				salidaServidor.println(mensaje); // 
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
	public static void eliminar(Socket cliente) {
		
		try {
			
			usuarios.remove(cliente);
			cliente.close();
			
		}catch(Exception e) {
			
		}
	}


}
class ManejadorHilos extends Thread{
	
	private static Socket comunicaCliente;
	int cont;
	String nick,ip,mensaje;
	PaqueteEnvio paquete_recibido;
	private final String bandera = "adios";
	
	
	
	
	public ManejadorHilos(int cont,Socket comunicaCliente) {
		this.comunicaCliente = comunicaCliente;
		this.cont = cont;
	}
	
	public void run() {
		
		PaqueteEnvio datos_recibidos = new PaqueteEnvio();

		//ServidorChat servidor = new ServidorChat();
		
		try {
			
			ObjectInputStream entradaServidor = new ObjectInputStream(comunicaCliente.getInputStream());
			
			boolean salir = false;
			
			
	        do {  
	        
				paquete_recibido = (PaqueteEnvio) entradaServidor.readObject();
					
				nick =paquete_recibido.getNick();	
				mensaje = paquete_recibido.getMensaje();
				
	             
	            	
	            String difusion =  nick + " : " + " "+  mensaje;
	            ServidorChat.difundir(difusion);

	            if (mensaje.equalsIgnoreCase(bandera)) {
	            	   ServidorChat.difundir( nick +":" + " Se ha marchado");
	            	   salir = true; 
	            }
	            	  
	            
	         }while (salir);
	        
	        ServidorChat.eliminar(comunicaCliente);
	        comunicaCliente.close();

			
			
	    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

