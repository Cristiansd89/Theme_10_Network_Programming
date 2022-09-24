package Ejemplo_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEcoHilos_2
	{  
	   public static void main(String[] args )
	   {  
	      try
	      {  
	         int i = 1;
	         ServerSocket s = new ServerSocket(8189);

	         while(true)
	         {  
	            Socket incoming = s.accept( );
	            System.out.println("Petición del Cliente " + i);
	            Thread t = new ThreadedEchoHandler(incoming, i);
	            t.start();
	            i++;
	         }
	      }
	      catch (Exception e)
	      {  
	         e.printStackTrace();
	      }
	   }
	}

	/**
	   This class handles the client input for one server socket
	   connection. 
	*/
	class ThreadedEchoHandler extends Thread
	{ 
	   /**
	      Constructs a handler.
	      @param i the incoming socket
	      @param c the counter for the handlers (used in prompts)
	   */
	   public ThreadedEchoHandler(Socket i, int c)
	   { 
	      incoming = i; counter = c; 
	   }

	   public void run()
	   {  
	      try
	      {  
	         BufferedReader in = new BufferedReader
	            (new InputStreamReader(incoming.getInputStream()));
	         PrintWriter out = new PrintWriter
	            (incoming.getOutputStream(), true /* autoFlush */);

	         out.println( "Hello! Enter BYE to exit." );

	         boolean done = false;
	         while (!done)
	         {  
	            String str = in.readLine();
	            if (str == null)
			 done = true;
	            else
	            {  
	               out.println("Echo (" + counter + "): " + str);

	               if (str.trim().equals("BYE"))
	                  done = true;
	            }
	         }
	         incoming.close();
	      }
	      catch (Exception e)
	      {  
	         e.printStackTrace();
	      }
	   }

	   private Socket incoming;
	   private int counter;
	}
