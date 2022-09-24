package Practica_11_InsertarOrdenada;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorOrdenador {

	public static void main(String[] args) {
		

		try {
			int i = 0;
			ServerSocket servidor = new ServerSocket(8000); // Creamos Servidor
			System.out.println("Atendiendo peticion");
			
			while(true) {
				i++;
				Socket comunicaCliente = servidor.accept();// Acceptamos la conecion del cliente
				System.out.println("Cliente ( " +i+" )");
				Thread hilo = new ManejadorHilos(comunicaCliente, i);
				hilo.start();
				
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

class ManejadorHilos extends Thread{
	
	Socket comunicaCliente;
	
	ObjectInputStream entradaServidor;
	ObjectOutputStream salidaServidor;
	
	int [] arrayOut;
	int [] arrayInput;
	
	
	int cont;
	
	
	public ManejadorHilos (Socket comunicaCliente,int cont) {
		this.comunicaCliente = comunicaCliente;
		this.cont = cont;
	}
	
	
	
	public void run (){
		
		
		try {
			entradaServidor = new ObjectInputStream(comunicaCliente.getInputStream());
			salidaServidor = new ObjectOutputStream(comunicaCliente.getOutputStream());
			
			 try {
				arrayInput =  (int[]) entradaServidor.readObject();
				int dato = (int) entradaServidor.readObject();
				
				
				int tamMax = arrayInput.length+1;
				System.out.println("Tamanño del  array " +tamMax);
				
				arrayOut = new int [tamMax];
				
				for(int i = 0 ; i < arrayInput.length; i++) { //Copiamos el  array de entrada al nuevo array
					
					arrayOut[i] = arrayInput[i]; 
				}
				
				
				int aux;
				
				
					for(int i=tamMax-1; i > 0; i--) {
						arrayOut[i] = arrayOut[i-1];
						
						if(dato > arrayOut[i]) {
							arrayOut[i] = dato;
							i=0;	
						
					}
					}
					
					
					salidaServidor.writeObject(arrayOut);
					
					
			
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
}
