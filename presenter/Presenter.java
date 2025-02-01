package presenter;

import model.Clustering;


import java.util.ArrayList;
import java.util.HashSet;


import javax.swing.JTable;



public class Presenter {

	Clustering clustering= new Clustering();


	public HashSet<HashSet<String>> empezarAlgoritmo(JTable tablaPersonas, int cantGrupos) {
		int cantPersonas=  tablaPersonas.getRowCount();		
		HashSet<HashSet<String>> grupos= clustering.crearGrupos(cantPersonas, tablaPersonas, cantGrupos);

		return grupos;
	}

	public ArrayList<Double> devolverEstadisticas(){
		return clustering.devolverEstadisticas(); 
	}

}
