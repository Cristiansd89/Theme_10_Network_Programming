package Practica_15_chat_02;

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
		
		
		
			try {
				System.out.println("Introduce IP o Localhost :");
				host = ent.next();
				
				System.out.println("Numero de puerto :");
				puerto = ent.nextInt();
				
				
				
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

