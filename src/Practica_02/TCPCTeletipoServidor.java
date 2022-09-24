package Practica_02;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPCTeletipoServidor extends TCPServidor {
	
	DataInputStream FlujoEntrada;
	
	public TCPCTeletipoServidor (int puerto) { // Si le pasamos parametros al constructor  tambien los recibira la clase padre
		super(puerto);
	}

	/*
	 * 
	 * Este metodo abstracto establece la comunicacion entre un  servidor y otro
	 */
	@Override
	public void comunicacion() {
		
		try {
			Socket ComunicacionCliente = servidor.accept(); // Acepta la conexion del cliente
			System.out.println("Comunicacion establecida");
			String entrada;
			boolean salir = false;
			
			
			FlujoEntrada = new DataInputStream(ComunicacionCliente.getInputStream());
			
			while(!salir) {
				entrada = FlujoEntrada.readUTF();
				if(salir = ("salir".equalsIgnoreCase(entrada))) {
					salir = true;
				}
				System.out.println(entrada);
			}
			ComunicacionCliente.close();
			servidor.close();

			
		} catch (IOException e) {
			 System.out.println("Cerrada la conexion con el cliente");
	         System.exit(0);
		}
		
	}
	
	
	

}
