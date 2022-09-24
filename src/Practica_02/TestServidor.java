package Practica_02;

public class TestServidor {

	public static void main(String[] args) {
		
		TCPCTeletipoServidor servidor1 = new TCPCTeletipoServidor(8000);
		
		servidor1.comunicacion();
	}

}
