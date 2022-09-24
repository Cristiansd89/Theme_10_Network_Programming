package Practica_03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TCPFicheroCliente extends TCPCliente {
	

	FileInputStream lecturaFich =null ; // para leer el archivo
	
	DataOutputStream FlujoSalida; // Para enviar al servidor
	int	 bytesLectura; // Es un entero por que va leyendo Bytes
	final int tamBuffer = 256;
	
	byte array_bits[] = new byte [tamBuffer]; // Cantidad de bytes que vamos almacenar
	
	public TCPFicheroCliente(String host, int puerto) {
		super(host, puerto);
		
		
	}
	
	
	public void comunicacion() {
		
		try {
				int i = 0;
				
				lecturaFich = new FileInputStream("/home/cristian/Escritorio/GrabarTexto.txt"); // Le estamos pasando la clase padre 
				try {
					FlujoSalida = new DataOutputStream(cliente.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				boolean fin = false;
				
				try {
					
					do {
						
						bytesLectura = lecturaFich.read(array_bits);  //Lee el array y te dice la cantidad de Bytes que ha leido
						FlujoSalida.write(array_bits,0,bytesLectura); // ES cribimos el array en el flujo
					}while(bytesLectura == tamBuffer);
					
						
						
						
					
					 // de donde copia el contenido????
					lecturaFich.close();
					FlujoSalida.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
