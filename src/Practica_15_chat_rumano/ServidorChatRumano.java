package Practica_15_chat_rumano;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServidorChatRumano {

	
	public static LinkedList<Socket> listaES = new LinkedList<Socket>();
	private static Socket comCliente;
	private static int contClientes = 0;
	
	public static void main(String[] args) {
		
		try {
			
			
			PrintStream pantalla = new PrintStream(System.out);
			ServerSocket servidor = new ServerSocket(8000);
			System.out.println("servidor eschuchando");
			
			while (true) {
				
				comCliente = servidor.accept();
				pantalla.println("nuevo cliente conectado");
				listaES.add(comCliente);
				contClientes++;
				
				Thread tarea = new ManejadorPeticionChat(comCliente);
				tarea.start();
				
				
						}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void difundir(String nick, String mensaje) {
		for(Socket usuario: listaES) {
			try {
				PrintWriter salidaServer = new PrintWriter(usuario.getOutputStream(),true);
				salidaServer.println(nick+": "+mensaje);
				
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void eliminar(String nick,Socket cliente) throws IOException {
		
		listaES.remove(cliente);
		contClientes--;
		
		cliente.close();
				
	}
	

}

class ManejadorPeticionChat extends Thread{

	private Socket Cliente;
	String nick = null;
	PrintStream pantalla = new PrintStream(System.out);
	String mensaje = null;
	String linea = null;
	private final String flag = "adios";
	
	public ManejadorPeticionChat(Socket comCliente) {
		this.Cliente = comCliente;
	}
	
	public void run() {
		
		//ServidorChat servidor = new ServidorChat();
		
		try {
			
			
			BufferedReader entradaServer = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));
			PrintWriter salidaServer = new PrintWriter(Cliente.getOutputStream(),true);// true es autoflush
				
			salidaServer.println("Bienvenido! teclea (adios) para abandonar");
			salidaServer.println("introduce tu nick");
			
			nick = entradaServer.readLine();
			
			ServidorChatRumano.difundir(nick," se ha unido al chat");
			linea = entradaServer.readLine();
			
			while (!linea.equalsIgnoreCase(flag)) {
				
				ServidorChatRumano.difundir(nick,linea);
				linea = entradaServer.readLine();
			}
			ServidorChatRumano.difundir(nick,"ha abandonado el chat");
			ServidorChatRumano.eliminar(nick, Cliente);
			//comCliente.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
			
		
	}

}












