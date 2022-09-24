package Ejemplo_Sokects;


import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TCPServidorHolaMundo 
{

  public static void main(String[] args) 
  {
     InputStream FlujoDeEntrada;
     DataInputStream Flujo;
     try 
     {
        ServerSocket SocketServidor = new ServerSocket(8000); // Clase para manejar el servidor
        Socket ComunicaConCliente = SocketServidor.accept(); // devuelve la conexion asociada del cliente que se ha conectado

        System.out.println("Comunicacion establecida");        
        FlujoDeEntrada = ComunicaConCliente.getInputStream();
        Flujo = new DataInputStream(FlujoDeEntrada);
        System.out.println(Flujo.readUTF());

        ComunicaConCliente.close();
        SocketServidor.close();
     }
     catch (IOException e) 
     {
            System.out.println("Error en las comunicaciones");
            System.exit(0);
     } 
     catch (SecurityException e) 
     {
            System.out.println("Comunicacion no permitida por razones de seguridad");
           System.exit(0);
     }

  }

}
