package Ejemplo_04_chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ManejadorBroadCast {

	ArrayList<DataOutputStream> flujosSalida;
	ArrayList<String> nombresClientes;
	
	public ManejadorBroadCast(){
		flujosSalida = new ArrayList<>();
		nombresClientes = new ArrayList<>();
	}
	
	public synchronized void añadirFlujoSalida(Socket socketCliente, String nombre){
		try {
			DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
			
			flujosSalida.add(flujoSalida);
			nombresClientes.add(nombre);
			broadcast("Se concecta: " + nombre);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void quitarFlujoSalida(String nombre){
		for (int i = 0; i < nombresClientes.size(); i++) {
			if(nombresClientes.get(i).equals(nombre)){
				nombresClientes.remove(i);
				try {
					flujosSalida.get(i).close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flujosSalida.remove(i);
			}
		}
	}
	
	public synchronized void broadcast(String mensaje){
		for (int i = 0; i < flujosSalida.size(); i++) {
			try {
				flujosSalida.get(i).writeUTF(mensaje);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
