package Ejemplo_04_chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class InterfazCliente {

	private JTextField usuario = null;
	private JFrame frame;
	private JTextField textFieldEscribir;
	private JTextArea textAreaMensaje;
	ConectorCliente cliente = new ConectorCliente(this);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					InterfazCliente window = new InterfazCliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public InterfazCliente() throws UnknownHostException, IOException {
		
		String user = JOptionPane.showInputDialog("Introduce tu nombre de usuario", null).toString();
		cliente.conectar();
		cliente.enviarMensaje(user);
		System.out.println(user);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 459, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldEscribir = new JTextField();
		textFieldEscribir.setBounds(12, 13, 292, 45);
		frame.getContentPane().add(textFieldEscribir);
		textFieldEscribir.setColumns(10);
		
		JButton btnNewButtonEnviar = new JButton("Enviar");
		btnNewButtonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cliente.enviarMensaje(textFieldEscribir.getText());
				textFieldEscribir.setText("");
			}
		});
		btnNewButtonEnviar.setBounds(316, 13, 113, 45);
		frame.getContentPane().add(btnNewButtonEnviar);
		
		textAreaMensaje = new JTextArea("");
		textAreaMensaje.setEditable(false);
		textAreaMensaje.setBounds(12, 64, 417, 291);
		frame.getContentPane().add(textAreaMensaje);
	}
	
	public void llegaMensaje(String mensaje){
		String aux = textAreaMensaje.getText()+"\n";
		aux = aux + mensaje;
		textAreaMensaje.setText(aux);
	}
}
