package Ejemplo_01;


import java.io.*;
import java.net.*;

/**
   Este programa implementa a multithreaded server que escucha en
   el puerto 8000 y devuelve el eco al cliente.
*/
public class ServidorEcoHilos
{  
   public static void main(String[] args )
   {  
      try
      {  
         int i = 1;
         // Para conectar con el servidor telnet localhost 8000
         ServerSocket s = new ServerSocket(8000);

         //bucle infinito
         while(true)
         {
            System.out.println("En espera de que se conecte un cliente\n");
            Socket comunicaCliente = s.accept( ); //Aceptamos la conexion
            System.out.println("atendiendo la peticion " + i);
            Thread tarea = new ManejadorEcoHilo(comunicaCliente, i);
            tarea.start(); //Llama al metodo run
            i++;
         }
      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());       
         
      }
   }
}

/**
   Esta clase maneja el  cliente que conecta con el server socket
   
*/
class ManejadorEcoHilo extends Thread
{ 
   /**
      Construye un manejador.
      i es el socket conectado
      c es el contador
   */

   private Socket comunicaCliente;
   private int contador;


   public ManejadorEcoHilo(Socket i, int c)
   { 
      comunicaCliente = i;
      contador = c;
   }

   public void run()
   {  
      try
      {  
         BufferedReader in = new BufferedReader(new InputStreamReader(comunicaCliente.getInputStream()));
         PrintWriter out = new PrintWriter(comunicaCliente.getOutputStream(), true /* autoFlush Limpiar un buffer de salida*/);

         out.println( "Hola! Enter adios para salir." );

         boolean seguir = true;
         while (seguir)
         {  
            String str = in.readLine();
            if (str.equalsIgnoreCase("")) {
                seguir = false;

            }
            else
            {  
               out.println("Echo (" + contador + "): " + " "+  str);

               if (str.equals("adios"))
                  seguir = false;
            }
         }
         comunicaCliente.close();
      }
      catch (Exception e)
      {  
         System.out.println(e.getMessage());
      }
   }
}

