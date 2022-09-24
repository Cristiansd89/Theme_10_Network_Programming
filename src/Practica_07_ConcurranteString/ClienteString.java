package Practica_07_ConcurranteString;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Scanner;

public class ClienteString {

	public static void main(String[] args) {
		
		Scanner ent = new Scanner(System.in);
		/*
		 * 
		 * Coge una frase se la mandamos al servidor y el servidor tiene que buscar una palabra 
		 *  y contarla 
		 */
		
		String frase;
		String palabra;
		
		
		
		
		System.out.println("Que Palabra queieres buscar :");
		palabra = ent.next();
		
		 frase ="hola hola hola camion jamon sandia melon hola francisco hola";
		try {
			Socket Cliente = new Socket("localhost", 8000);
			
			DataOutputStream salidaCliente = new DataOutputStream(Cliente.getOutputStream()); // flujo salida datos primitivos
			
			
			salidaCliente.writeUTF(frase); // Frase que enviamos
			salidaCliente.writeUTF(palabra); // Palabra que eniamos
			

			
		Cliente.close();
			

			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error en la clase Cliente");
		}

	}

}
