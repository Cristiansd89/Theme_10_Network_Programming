package Practica_16_InterfazChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ClienteChat  {
	
	private static Socket cliente ;
	static String host;
	static int puerto;
	
	public static void main(String[] args) {
		
		Scanner ent = new Scanner(System.in);
		
		InterfazCliente interfaz = new InterfazCliente();
		
		PaqueteEnvio paquete_recibido = new PaqueteEnvio();
		
			try {
				host = paquete_recibido.getIp();
				puerto = paquete_recibido.getPuerto();
				
				
				
				cliente = new Socket(host , puerto);
				ManejadorCliente hiloSalida = new ManejadorCliente(System.in,cliente.getOutputStream());
				ManejadorCliente hilosEntrada = new ManejadorCliente(cliente.getInputStream(), System.out);
				
				hiloSalida.start();
				hilosEntrada.start();
				
			}catch (IOException e) {
				
			}
		}
	
	
}
class ManejadorCliente extends Thread{
	
	private OutputStream salida;
	private InputStream entrada;
	
	
	public ManejadorCliente(InputStream entrada,OutputStream salida){
		
		this.entrada = entrada;
		this.salida = salida;
		
	}
	public void run () {
		 try {
			 
			 PrintStream sal = new PrintStream(salida);
			
			 BufferedReader ent = new BufferedReader(new InputStreamReader(entrada));
			 
			 
			 String linea = ent.readLine();
			 while(linea != null) {
				 sal.println(linea);
				 linea = ent.readLine();
			 }
			
			
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

