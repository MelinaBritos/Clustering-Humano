package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import presenter.Presenter;

import javax.swing.JComboBox;

public class View {

	private JFrame frame;
	private JTable personas;
	private int cantGrupos;
	private JComboBox<String> comboBox;
	private JTextField nombre;
	private ButtonGroup interesDeporte, interesMusica, 
	interesEspectaculo, interesCiencia;
	private DefaultTableModel model;
	

	public void iniciar() {
		View pantalla = new View();
		pantalla.frame.setVisible(true);
	}

	public View() {
		initialize();
	}
	
	private void initialize() {			//REFACTORIZADO
		iniciarRecursos();
		añadirJLabels();
		añadirGrupos(interesDeporte, interesMusica, interesEspectaculo, interesCiencia);
		añadirBotones();

	}

	@SuppressWarnings("serial")
	private void iniciarRecursos(){
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 192));
		frame.setBounds(0, 0, 428, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		nombre = new JTextField();
		nombre.setBounds(175, 72, 165, 20);
		frame.getContentPane().add(nombre);
		nombre.setColumns(10);

		interesDeporte = new ButtonGroup();
		interesMusica = new ButtonGroup();
		interesEspectaculo = new ButtonGroup();
		interesCiencia = new ButtonGroup();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 420, 340, 130);
		frame.getContentPane().add(scrollPane);

		
		model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		model.addColumn("Nombre");
		model.addColumn("D");
		model.addColumn("M");
		model.addColumn("E");
		model.addColumn("C");
		
		personas = new JTable();
		scrollPane.setViewportView(personas);
		personas.setModel(model);

		// Centra las filas horizontalmente
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		int columnCount = personas.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			personas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(205, 580, 100, 20);
		frame.getContentPane().add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "2", "3", "4" }));
	}

	private void añadirJLabels() {
		añadirJlabel("Clustering Humano", 140, 10, 350, 20, 14);
		añadirJlabel("Nombre:", 50, 75, 90, 20, 13);
		añadirJlabel("Deporte:", 50, 130, 90, 20, 13);
		añadirJlabel("Musica:", 50, 190, 90, 20, 13);
		añadirJlabel("Espectaculo:", 50, 250, 90, 20, 13);
		añadirJlabel("Ciencia:", 50, 310, 90, 20, 13);
		añadirJlabel("Cantidad de Grupos:", 45, 580, 125, 20, 12);

	}

	private void añadirGrupos(ButtonGroup interesDeportes, ButtonGroup interesMusica, ButtonGroup interesEspectaculo,
			ButtonGroup interesCiencia) {

		for (int i = 1; i <= 5; i++) {
			crearRadioButton(interesDeportes, 175 + (i - 1) * 35, 130, 25, 25, String.valueOf(i));
			crearRadioButton(interesMusica, 175 + (i - 1) * 35, 190, 25, 25, String.valueOf(i));
			crearRadioButton(interesEspectaculo, 175 + (i - 1) * 35, 250, 25, 25, String.valueOf(i));
			crearRadioButton(interesCiencia, 175 + (i - 1) * 35, 310, 25, 25, String.valueOf(i));
		}
	}

	private void añadirJlabel(String mensaje, int x, int y, int width, int height, int size) {

		JLabel etiqueta = new JLabel(mensaje);
		etiqueta.setFont(new Font("Tahoma", Font.BOLD, size));
		etiqueta.setBounds(x, y, width, height);
		frame.getContentPane().add(etiqueta);
	}

	private void crearRadioButton(ButtonGroup interesDeportes, int x, int y, int width, int height, String comando) {
		JRadioButton boton = new JRadioButton("");
		interesDeportes.add(boton);
		boton.setActionCommand(comando);
		boton.setBackground(new Color(255, 128, 192));
		boton.setForeground(new Color(255, 128, 192));
		boton.setBounds(x, y, width, height);
		frame.getContentPane().add(boton);
	}

	private void añadirBotones() {
		crearBoton("Crear Grupos", 130, 615, 145, 23, this::crearGrupos);
		crearBoton("Agregar", 150, 355, 90, 23, this::agregarAlaTabla);
		
	}

	private void agregarAlaTabla(ActionEvent e) {
		if (nombre.getText().length() > 0 && interesDeporte.getSelection() != null
				&& interesMusica.getSelection() != null && interesEspectaculo.getSelection() != null
				&& interesCiencia.getSelection() != null) {
			String nombreUsuario = nombre.getText();
			String deporte = interesDeporte.getSelection().getActionCommand();
			String musica = interesMusica.getSelection().getActionCommand();
			String espectaculo = interesEspectaculo.getSelection().getActionCommand();
			String ciencia = interesCiencia.getSelection().getActionCommand();
			model.addRow(new String[] { nombreUsuario, deporte, musica, espectaculo, ciencia });
			personas.setModel(model);
			nombre.setText("");
			interesDeporte.clearSelection();
			interesMusica.clearSelection();
			interesEspectaculo.clearSelection();
			interesCiencia.clearSelection();
		}
	}

	private void crearGrupos(ActionEvent e) {
		Presenter presenter = new Presenter();
		if (personas.getRowCount() == 0 || personas.getRowCount() == 1) {
			JOptionPane.showMessageDialog(frame, "Debe ingresar al menos dos personas");
		} else {
			cantGrupos = Integer.parseInt((String) comboBox.getSelectedItem());
			HashSet<HashSet<String>> grupos = presenter.empezarAlgoritmo(personas, cantGrupos);
			ArrayList<Double> estadisticas = presenter.devolverEstadisticas();
			ViewGrupos.iniciar(grupos, estadisticas);
			frame.dispose();
		}
	}

	private void crearBoton(String texto, int x, int y, int width, int height, ActionListener listener) {
		JButton boton = new JButton(texto);
		boton.addActionListener(listener);
		boton.setBounds(x, y, width, height);
		frame.getContentPane().add(boton);
	}
	
}
