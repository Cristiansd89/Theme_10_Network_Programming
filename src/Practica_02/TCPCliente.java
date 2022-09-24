package Practica_02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class TCPCliente {

	Socket Cliente; 
	
	
	public TCPCliente (String host, int puerto) {
		try {
			this.Cliente = new Socket(host, puerto); // al contrustor le pasamos parametros para pasarle posteriormente en el test los valores
			
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
