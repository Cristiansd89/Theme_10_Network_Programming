package Practica_04_02_;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServidorFecha {

	public static void main (String [] args)
	
	{	
		try {
				int contClientes = 0;
				ServerSocket servidor = new ServerSocket (8800);
				System.out.println("Servidor escuchando");
				while(true)
				{
					contClientes++;
					Socket comunicarCliente = servidor.accept();
					System.out.println("Atendiendo la petición"+ contClientes);
					
					Thread tarea = new EnviarFecha (comunicarCliente);
					tarea.start();
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class EnviarFecha extends Thread
{
	
	Socket cliente;	

	
	public EnviarFecha (Socket s)
	{
		cliente = s;
		
	}
	
	public void run ()
	{
		Date fecha = new Date();
		
		try {
			ObjectOutputStream entradaObjeto = new ObjectOutputStream (cliente.getOutputStream());
			entradaObjeto.writeObject(fecha);
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}