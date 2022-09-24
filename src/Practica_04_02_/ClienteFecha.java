package Practica_04_02_;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteFecha {
	
	public static void main (String [] args)
	{
		try {
			
			Socket cliente = new Socket ("localhost", 8800);
			ObjectInputStream entradaObjeto = new ObjectInputStream (cliente.getInputStream());
			System.out.println(entradaObjeto.readObject());
			cliente.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}