package Ejemplo_Sokects;


import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;

public class TCPClienteHolaMundo 
{

  public static void main(String[] args) 
  {
         
    DataOutputStream Flujo;
     try 
     {
        Socket SocketCliente = new Socket("localhost", 8000);         
        Flujo = new DataOutputStream(SocketCliente.getOutputStream()); // uoutput escribir
        Flujo.writeUTF("Hola Mundo");

        SocketCliente.close();
     } 
     catch (UnknownHostException e) 
     {
            System.out.println("Referencia a host no resuelta");
     }
     catch (IOException e) 
     {
            System.out.println("Error en las comunicaciones");
     }
     catch (SecurityException e) 
     {
            System.out.println("Comunicacion no permitida por razones de seguridad");
     }
  }

}
