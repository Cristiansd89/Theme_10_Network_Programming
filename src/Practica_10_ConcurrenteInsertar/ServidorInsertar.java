package Practica_10_ConcurrenteInsertar;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorInsertar {

	public static void main(String[] args) {
		
		
		
		
		try {
			int cont = 0;
			
			ServerSocket servidor = new ServerSocket(8000);
			System.out.println("Atendiendo peticiones");
			
			while(true) {
				cont++;
				Socket comunicaCliente = servidor.accept();// acepta conexion con el cliente
				System.out.println("atendiendo la peticion "+cont);
				
				Thread hilo = new ManejadorHilos(comunicaCliente,cont);
				hilo.start();
				
			}
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}



class ManejadorHilos  extends Thread{
	
	Socket comunicaCliente;
	int cont;
	ObjectInputStream entradaServidor;
	ObjectOutputStream salidaServidor;
	int [] arrayInput;
	
	
	int dato;
	int posicion;
	
	
	
	
	public ManejadorHilos (Socket comunicaCliente,int cont) {
		this.comunicaCliente = comunicaCliente;
		this.cont = cont;

	}
	
	public void run() {
		try {
			entradaServidor = new ObjectInputStream(comunicaCliente.getInputStream()); // Flujo de entrada
			
			arrayInput = (int[]) entradaServidor.readObject(); // Lectura del Array
			
			dato = (int) entradaServidor.readObject(); //lectura del datos a insertar
			
			posicion = (int) entradaServidor.readObject(); //Lectura de la poscion donde los vamos  insertar
			
			// int tamMaX = ;
			
			System.out.println(arrayInput.length+ " Valor que tiene el Array de entrada ");
			
			int tamMax = arrayInput.length+1; //damos  un espacio mas a la longitud del primer array 
			int  [] arrayOut = new int [tamMax]; //ampliamos el tamaño del  el nuevo array
			
			for(int i = 0 ; i < arrayInput.length; i++) { //Copiamos el  array de entrada al nuevo array
				
				arrayOut[i] = arrayInput[i]; 
			}
			/*
			Cuando le pasamos al for el tamMax hay que tener encuenta que le pasamos el valor(8) y 
			en el array no existe la posicion 8 si no la 7 por que empieza desde la cero
			*/
			
			System.out.println(tamMax+ " tamaño que le vamos a dar al nuevo array");

			for(int i =tamMax-1; i > posicion ; i--) { 
				arrayOut[i] = arrayOut[i-1]; // Desplazamos uno a uno cada elemento
			}
			arrayOut[posicion] = dato; // Insertamos el dato en la posicion
			
			
			
			
			salidaServidor = new ObjectOutputStream(comunicaCliente.getOutputStream());
			
			salidaServidor.writeObject(arrayOut);
			
			
		} catch (ClassNotFoundException e) {
				System.out.println("Error en la lectura de bytes en el servidor");
			e.printStackTrace();	
		} catch (IOException e) {
			System.out.println("Error en el run");
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
}


