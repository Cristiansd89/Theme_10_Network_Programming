package Practica_03;

public class TestServidor {

	public static void main(String[] args) {
		
		TCPFicheroServidor servidor1 = new TCPFicheroServidor(8000);
		servidor1.comunicacion();
		
	}

}
