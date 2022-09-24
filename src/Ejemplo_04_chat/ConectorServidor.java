package Ejemplo_04_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConectorServidor {

	private static ServerSocket servidor;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Puerto = 8000;
		ManejadorBroadCast manejadorBroadcast = new ManejadorBroadCast();
		try {
			servidor = new ServerSocket(Puerto);
			System.out.println("Escuchando en " + servidor.getLocalPort());
			
			while(true){
				Socket clienteConectado = servidor.accept();
				System.out.println("Se concecta alguien");
				HiloEscichaHijo escucha = new HiloEscichaHijo(manejadorBroadcast, clienteConectado);
				escucha.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
