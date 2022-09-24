package Practica_03;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class TCPCliente { // Siempre hay que marcar la clase como abstracta
	
	Socket cliente;

	public TCPCliente(String host, int puerto) {
		
		try {
			this.cliente = new Socket(host, puerto);
		} catch (UnknownHostException e) {
			System.out.println("Error en la  clase padre Cliente");
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public abstract  void comunicacion();
}
