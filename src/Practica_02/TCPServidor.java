package Practica_02;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class TCPServidor {
	
	ServerSocket servidor;

	
	public TCPServidor(int puerto) {
		
		try {
			this.servidor = new ServerSocket(puerto);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public abstract void comunicacion();

}
