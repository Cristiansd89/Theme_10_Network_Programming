package Practica_14_chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ClienteChat  {
	
	private static Socket cliente ;
	
	public static void main(String[] args) {
		Scanner ent = new Scanner(System.in);
		
		
		
		try {
			System.out.println(" Se ha conectado al chat");
			cliente = new Socket("localhost" , 8000);
			Thread hiloCliente = new ManejadorCliente(cliente);
			
			System.out.println( "Bienvenido! Enter (adios) para salir." );
			
			hiloCliente.start();
			
			
			
		}catch (IOException e) {
			
		}
	}
	public  static String pedirMensaje() {
		Scanner ent = new Scanner (System.in);
		//System.out.println("Mensaje :");
		String mensaje = ent.next();
		return mensaje;
	}
	
}

class ManejadorCliente extends Thread{
	
	String mensaje ="";
	Socket cliente;
	
	boolean salir = false;
	
	private static BufferedReader entradaCliente;
	private static BufferedWriter salidaCliente;
	private static PrintWriter pantalla;
	
	
	public ManejadorCliente (Socket cliente) {
		this.cliente=cliente;
		
	}
	
	public void run () {
		 try {
			System.out.println("Introduzca un nick :");
			String nick = pedirMensaje();
				
			 
			 do {  
				 salidaCliente = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream())); //Salida de datos del cliente

				 entradaCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				 pantalla = new PrintWriter(cliente.getOutputStream());
				 System.out.println("Mensaje :");
				 mensaje =  pedirMensaje();
			
	              if (mensaje.equals("adios")){
	            	   salidaCliente.write(nick +" :" +mensaje);
	            	   salir = true;    
	              }
	              salidaCliente.write(mensaje);
	              
	              String msgRecibido = entradaCliente.readLine();
	              pantalla.print(msgRecibido);

	            	  
	            
	         }while (!salir);
	         cliente.close();

			 
			 
			 
			 
			 
			 
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  static String pedirMensaje() {
		Scanner ent = new Scanner (System.in);
		
		String mensaje = ent.next();
		return mensaje;
	}
	
}

