package Ejemplo_04_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloEscichaHijo extends Thread{
	ManejadorBroadCast manejadorBroadcast;
	Socket clienteConectado;
	DataInputStream flujoEntrada;
	
	public HiloEscichaHijo(){
		
	}
	
	public HiloEscichaHijo(ManejadorBroadCast manejadorBroadcast, Socket clienteConectado){
		this.manejadorBroadcast=manejadorBroadcast;
		this.clienteConectado=clienteConectado;
	}
	
	
	public void run(){
		String nombre = null;
		try {
			flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
			nombre = flujoEntrada.readUTF();
			manejadorBroadcast.añadirFlujoSalida(clienteConectado, nombre);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			try {
				manejadorBroadcast.broadcast("<<"+nombre+">>" + flujoEntrada.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				manejadorBroadcast.broadcast("Se desconecta el cliente : " + nombre );
				manejadorBroadcast.quitarFlujoSalida(nombre);
				break;
			}
		}
	}

}
