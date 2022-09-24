package Practica_03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPFicheroServidor extends TCPServidor {
	
	
	FileOutputStream escrituraFich;
	
	DataInputStream flujoEntrada;
	
	int	 bytesLectura; // Es un entero por que va leyendo Bytes
	
	 final int tamBuffer = 256;
	
	byte array_bits[] = new byte [tamBuffer]; // Cantidad de bytes que vamos almacenar
	
	
	public TCPFicheroServidor(int puerto) {
		super(puerto);
		Socket comuninicacionCliente = new Socket();
	}



	@Override
	public void comunicacion() {
		
		try {
			
			
			Socket comunicacionCliente = servidor.accept(); // Aceptamos la conexion con el cliente
			
			escrituraFich = new FileOutputStream("/home/cristian/Escritorio/GrabarTexto2.txt");
			
			flujoEntrada = new DataInputStream(comunicacionCliente.getInputStream());
			
			do {
				bytesLectura = flujoEntrada.read(array_bits); // Leemos en bloque de bytes el fichero
				escrituraFich.write(array_bits, 0,array_bits.length); // Escribe los datos el array (Para enviar siempre hay que escribir
				System.out.println(escrituraFich);
				
			}while(bytesLectura == array_bits.length); // Mientras los bytes leidos se han iguales a la longitud del Array
			// Al ser diferente se veria que es la ultima vuelta
				
						
				
			escrituraFich.close();
			flujoEntrada.close();

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //
		
	}

}
