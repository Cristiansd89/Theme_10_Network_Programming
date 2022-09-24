package Ejemplo_04_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConectorCliente implements Runnable{
	
	public DataOutputStream flujoSalida;
	public DataInputStream flujoEntrada;
	public Socket socket;
	InterfazCliente interfaz;
	
	public ConectorCliente(InterfazCliente interfaz){
		this.interfaz=interfaz;
	}
	public boolean conectar() throws UnknownHostException, IOException{
		String Host = "localHost";
		int Puerto = 8000;
		
		socket = new Socket(Host,Puerto);
		
		flujoSalida = new DataOutputStream(socket.getOutputStream());
		
		flujoEntrada = new DataInputStream(socket.getInputStream());
		arrancarRecepcion();
		return true;
		
	}
	
	public void arrancarRecepcion(){
		Thread hilo = new Thread(this);
		hilo.start();
	}
	
	public void enviarMensaje(String mensaje){
		try {
			flujoSalida.writeUTF(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			try {
				interfaz.llegaMensaje(flujoEntrada.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Fin de la comunicacion");
				break;
			}
		}
	}
	
	public void cerrar(){
		try {
			flujoEntrada.close();
			flujoSalida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
