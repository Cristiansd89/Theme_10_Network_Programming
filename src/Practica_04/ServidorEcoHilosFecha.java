package Practica_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;

/*
 * Esta clase es el servidor 
 */

public class ServidorEcoHilosFecha {

	
	public static void main(String[] args) {
		
		
		
		try {
			
			int i = 1;
			ServerSocket servidor = new ServerSocket(8000); // Objeto servidor con el puerto
			
			
			
			
			while(true) {
				System.out.println("En espera de que se conecte un Cliente\n");
				Socket comunicaCliente = servidor.accept(); // aceptamos la conexion 
				System.out.println("atediendo la peticion " +i);
				// falta crear el hilo de la clase tipo ManejadorEcoHilo
				Thread hilo = new ManejadorEcoHilos(comunicaCliente, i);
				hilo.start();
				i++;
				
				
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

/*
 * Esta clase maneja el  cliente que conecta con el server socket
 */

class ManejadorEcoHilos extends Thread{
	
	private Socket comunicaCliente;
	private int contador ;
	
	SimpleDateFormat formatoDia = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	
	
	/*
	 * Metodo Constructor de la clase Manejador
	 */
	
	public ManejadorEcoHilos(Socket comunicaCliente , int contador) {
		this.comunicaCliente = comunicaCliente;
		this.contador = contador;
	
	}
	
	public void run() {
		
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(comunicaCliente.getInputStream()));
			PrintWriter salida = new PrintWriter(comunicaCliente.getOutputStream(), true  /* autoFlush Limpiar un buffer de salida*/);
			
			salida.println("'exit  ' para salir\n  ");
			salida.println("\n ");
			
			
			
			
			boolean salir = false;
			
			while(!salir) {
				
				salida.println( "Cliente  ("+this.contador+") "+LocalDate.now().getDayOfMonth()+"  " +LocalDate.now().getMonth()+" "+LocalDate.now().getYear());
				salida.println( "Hora " +LocalTime.now().getHour()+" : " +LocalTime.now().getMinute()+" : "+LocalTime.now().getSecond());

				
				
				String cadena = entrada.readLine();
				if(cadena.equalsIgnoreCase("exit")) {
					salir = true;
					
				}
				
			
			}
			salida.println("Cerrando el servidor");	
			comunicaCliente.close();
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	
}



