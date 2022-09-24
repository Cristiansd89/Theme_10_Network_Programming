package Practica_02;

public class TestCliente {

	public static void main(String[] args) {
		
		TPCTeletipoCliente cliente1 = new TPCTeletipoCliente("localhost", 8000);
		
		cliente1.comunicacion();
	}

}
