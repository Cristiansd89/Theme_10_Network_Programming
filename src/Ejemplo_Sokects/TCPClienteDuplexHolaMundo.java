package Ejemplo_Sokects;

import java.net.*;
import java.io.*;

public class TCPClienteDuplexHolaMundo
{

  public static void main(String[] args)  
  {
     DataInputStream FlujoEntrada;
     DataOutputStream FlujoSalida;


     try 
     {
    	 /*
    	  * Creamos un objeto Tipo Socket y le pasamos el nombre de la maquina y puerto
    	  */
        Socket SocketCliente = new Socket("localhost", 8000); 
        String entrada;

        FlujoEntrada = new DataInputStream(SocketCliente.getInputStream());
        FlujoSalida = new DataOutputStream(SocketCliente.getOutputStream());

        try 
        {
            FlujoSalida.writeUTF("Hola terricola\n");
            
            entrada = FlujoEntrada.readUTF();
            System.out.print(entrada); 
        }
        catch (IOException e) 
        {
             System.out.println("Error en la lectura de datos");
             System.exit(0);
        }
     

        // SocketCliente.close();

     }
     catch (UnknownHostException e) 
     {
            System.out.println("Referencia a host no resuelta");
     }
     catch (IOException e) 
     {
            System.out.println("Error en las comunicaciones");
   }
  }


 }