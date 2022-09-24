package Practica_02;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TPCTeletipoCliente  extends TCPCliente{
	
	Scanner ent = new Scanner(System.in);
	DataOutputStream flujoSalida;
	
	public TPCTeletipoCliente(String host, int puerto) {
		super(host, puerto);
		
	}

	/*
	 * Este metodo va implementar todos los mecanismos de  comunicacion con el
	 * otro servidor
	 */
	@Override
	public void comunicacion() { // Metodo abstracto
			
		try {
			/*
			 * 
			 * Pasamos  un objeto tipo con el Con el getOutPutStream
			 */
			flujoSalida = new DataOutputStream(Cliente.getOutputStream()); 
		
			
			String cadena;
			
			boolean salir = false;
			String opcion;
			
			while(!salir) {
				System.out.println("Introduce una cadena ");
				
				cadena = ent.nextLine();
				if(salir = (cadena.equalsIgnoreCase("salir"))) {
					salir = true;
				}
				flujoSalida.writeUTF(cadena);
			}
			Cliente.close();
		
		
		} catch (IOException e) {
            System.out.println("Error de salida");

			e.printStackTrace();
		}
		

		
		
			
	}

}
