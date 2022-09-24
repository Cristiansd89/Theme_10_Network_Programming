package Practica_15_chat_rumano;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChatRumano {
	
	
	static String host;
	static int puerto;
	

	public static void main(String[] args) {
		
		new ClienteChatRumano();
		conectarseA();
		System.out.println("estoy desconectado");
		
	}

	public static void conectarseA() {
		
		Scanner s = new Scanner(System.in);
		System.out.println("introduce el nombre del host");
		host = s.next();
		System.out.println("introduce el puerto");
		puerto = s.nextInt();
		
		try {
			
			Socket comCliente = new Socket(host,puerto);
			Hilo hiloSalida = new Hilo(System.in,comCliente.getOutputStream());
			Hilo hiloEntrada = new Hilo(comCliente.getInputStream(),System.out);
			
			hiloSalida.start();
			hiloEntrada.start();
			
			//comCliente.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

class Hilo extends Thread{
	
	private OutputStream salida;
	private InputStream entrada;

	public Hilo (InputStream entrada,OutputStream salida) {
		
		this.entrada = entrada;
		this.salida = salida;
		
	}
	
	public void run() {
		
		PrintStream sal = new PrintStream(salida);
		BufferedReader ent = new BufferedReader(new InputStreamReader(entrada));
		
		try {
			
			String linea = ent.readLine();
			while (linea != null) {
				sal.println(linea);
				linea = ent.readLine();
			}
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
