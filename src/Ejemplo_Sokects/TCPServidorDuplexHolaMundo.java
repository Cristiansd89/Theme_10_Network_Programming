package Ejemplo_Sokects;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public abstract class TCPServidorDuplexHolaMundo {

  public static void main(String[] args)  {
     DataInputStream FlujoEntrada;
     DataOutputStream FlujoSalida;

     try 
     {
        ServerSocket SocketServidor = new ServerSocket(8000);
        Socket ComunicaConCliente = SocketServidor.accept();
        System.out.println("Comunicacion establecida");
        String entrada;

        FlujoEntrada = new DataInputStream(ComunicaConCliente.getInputStream());
        FlujoSalida = new DataOutputStream(ComunicaConCliente.getOutputStream());


        try 
        {
            entrada = FlujoEntrada.readUTF();
            System.out.println(entrada);
            FlujoSalida.writeUTF("Hola humano\n");
        } 
        catch (IOException e) 
        {
            System.out.println("Error de entrada salilda");
            System.exit(0);
        }             
    

       ComunicaConCliente.close();
       SocketServidor.close();

     } 
     catch (IOException e) 
     {
            System.out.println("Error en las comunicaciones");
            System.exit(0);
     } 

  }

 
}
