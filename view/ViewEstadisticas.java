package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;


public class ViewEstadisticas {

	private JFrame frame;

	public void iniciar(ArrayList<Double> estadisticas) {
		ViewEstadisticas pantalla = new ViewEstadisticas(estadisticas);
		pantalla.frame.setVisible(true);
	}

	public ViewEstadisticas(ArrayList<Double> estadisticas) {
		initialize(estadisticas);
	}

	private void initialize(ArrayList<Double> estadisticas) {
		iniciarRecursos();

		añadirJlabel("Estadisticas",  160, 10, 350, 20, 14);
		añadirJlabel("Promedio de interes en cada tema:", 61, 88, 238, 14, 12);
		añadirJlabel("Deportes:", 61, 144, 77, 14, 12);
		añadirJlabel("Musica:", 61, 232, 46, 14, 12);
		añadirJlabel("Espectaculo:", 61, 325, 89, 14, 12);
		añadirJlabel("Ciencia:", 61, 412, 46, 14, 12);

		añadirJlabel("" + estadisticas.get(0), 241, 144, 46, 14, 12);
		añadirJlabel("" + estadisticas.get(1), 241, 232, 46, 14, 12);
		añadirJlabel("" + estadisticas.get(2), 241, 325, 46, 14, 12);
		añadirJlabel("" + estadisticas.get(3), 241, 423, 46, 14, 12);

		frame.setLocationRelativeTo(null);
	}

	private void iniciarRecursos() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 192));
		frame.setBounds(0, 0, 428, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnvolver = new JButton("Volver");
		btnvolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewGrupos.setVisible(true);
				frame.dispose();
			}
		});
		btnvolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnvolver.setBounds(155, 604, 89, 23);
		frame.getContentPane().add(btnvolver);
	}

	private void añadirJlabel(String mensaje, int x, int y, int width, int height, int size) {

		JLabel etiqueta = new JLabel(mensaje);
		etiqueta.setFont(new Font("Tahoma", Font.BOLD, size));
		etiqueta.setBounds(x, y, width, height);
		frame.getContentPane().add(etiqueta);
	}
}
