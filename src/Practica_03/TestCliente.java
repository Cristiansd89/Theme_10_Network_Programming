package Practica_03;

public class TestCliente {

	public static void main(String[] args) {
		
		TCPFicheroCliente cliente1 = new TCPFicheroCliente("localhost",8000);
			
		cliente1.comunicacion();
	}

	}

