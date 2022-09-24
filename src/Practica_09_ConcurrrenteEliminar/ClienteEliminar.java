package Practica_09_ConcurrrenteEliminar;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteEliminar {

	public static void main(String[] args) {
		Socket cliente;
		ObjectOutputStream flujoSalida;
		ObjectInputStream flujoEntrada;
		
		int [] arrayOut = {1,2,3,4,5,6,7,8,9};
		int [] arrayInput;
		int posicion = 5;
		
		
		
		try {
			
			cliente = new Socket("localhost" , 8000); // Creando al cliente 
			flujoSalida = new ObjectOutputStream(cliente.getOutputStream()); // abrimos el flujo de salida
			
			
			
			flujoSalida.writeObject(arrayOut); // Envio del Array al servidor
			flujoSalida.writeObject(posicion); // Envio de la posicion que quermos eliminar
			
			
			flujoEntrada = new ObjectInputStream(cliente.getInputStream()); // Flujo de entrada
			
			arrayInput =(int[]) flujoEntrada.readObject(); // Lectura del Array enviado desde el servidor
			
			for(int i = 0; i < arrayInput.length; i++) {
				System.out.println(arrayInput[i]);
			}
			
			
			
			
		}catch(Exception e) {
			System.out.println("Error en el cliente");
		}
		

	}

}
