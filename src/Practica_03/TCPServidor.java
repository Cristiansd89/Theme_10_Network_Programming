package Practica_03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class TCPServidor {
	
	ServerSocket servidor;
	
	public TCPServidor(int puerto) {
		try {
			this.servidor = new ServerSocket(puerto);
		} catch (IOException e) {
			System.out.println("Error en la clase servidor padre");
			e.printStackTrace();
		}
	}
	
	
	public abstract void comunicacion();

}
