package Practica_16_InterfazChat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InterfazCliente {
	
	
	/*
	 * Declaramos los componentes
	 * 
	 * Siempre se añade el contenedor a la ventana
	 */
	private JFrame ventana; //Declaramos la ventana
	private Container contenedor; //Declaramos el contenedor
	
	
	
	private JPanel panelNort;
	private JPanel panelCent;
	private JPanel panelSur;
	
	private JTextField areaIp;  // Area de Ip o Locahost
	private JTextField areaNick; //area del nick
	private JTextField fieldPuerto;
	private JTextArea areaMensajes; // Area donde se comparten los mensajes
	private JTextArea areaEnvio;   // Area desde se envian los mensajes
	
	
	private JLabel etiqueIp;
	private JLabel etiqueNick;
	private JLabel etiquetaHost;
	
	private JButton enviar; // boton que envia el mensaje
	
	
	
	/*
	 * Este metodo crea los elementos  que van aparecer en la ventana
	 */
	public void crearElementos() {
		
		ventana = new JFrame("CHAT"); //Crear Ventana
		contenedor = new Container(); // Crear Contenedor
		
		panelNort = new JPanel(); // Crear Paneles
		panelCent = new JPanel();
		panelSur = new JPanel();
		
		
		areaIp = new JTextField(8);  // Crear Areas de texto
		areaNick = new JTextField(8);
		fieldPuerto = new JTextField(8);
		areaMensajes = new JTextArea(20,100);
		areaEnvio = new JTextArea(2,30);
		
		
		etiqueIp = new JLabel();
		etiqueNick = new JLabel();
		etiquetaHost = new JLabel();
		
		enviar = new JButton("Enviar");
		
		
		
		ventana.setVisible(true); // siempre tiene que estar esto que no se te olvide
		
		
	}
	
	
	
	
	public void añadirElementos() {
		contenedor = ventana.getContentPane();// Te da la referencia del contenedor
		ventana.setSize(500,400); // Tamaño de la ventana
		
		
		etiqueIp.setText("Ip o localhost");
		etiqueNick.setText("Nick ");
		etiquetaHost.setText("Puerto");
		
		panelNort.add(etiqueIp);
		panelNort.add(areaIp);
		panelNort.add(etiqueNick);
		panelNort.add(areaNick);
		panelNort.add(etiquetaHost);
		panelNort.add(fieldPuerto);
		
		panelCent.add(areaMensajes);
		
		panelSur.add(areaEnvio);
		
		EnviaTexto evento = new EnviaTexto();  // Creo un objeto escuchador
		
		enviar.addActionListener(evento); // Le paso al boton el escuchador
		panelSur.add(enviar);
		
		
		
		contenedor.add(panelNort, BorderLayout.NORTH);
		contenedor.add(panelCent, BorderLayout.CENTER);
		contenedor.add(panelSur,BorderLayout.SOUTH);
		
		
		
	}
	
	
	
	
	public InterfazCliente() {
		crearElementos();
		añadirElementos();
	}
	
	

	public static void main(String[] args) {
			new InterfazCliente();
	}
	
	
	class EnviaTexto implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Socket cliente = new Socket("localhost" ,8000);
				
				PaqueteEnvio datos = new PaqueteEnvio();  // Creamos un objeto de la clase paquete
				
				datos.setNick(areaNick.getText()); /// Introducimos dentro del nick  la informacion que hay en el TextField
				datos.setIp(areaIp.getText()); // Informacion del textField
				datos.setMensaje(areaEnvio.getText());
				
				int puerto = Integer.parseInt(fieldPuerto.getText());  // Transformamos en un integer el dato i
				datos.setPuerto(puerto);
				
				ObjectOutputStream envio_paquete= new ObjectOutputStream(cliente.getOutputStream());
				envio_paquete.writeObject(datos);
				
				cliente.close();
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}	
		}
		
	}

	
	
	
	
	

}







