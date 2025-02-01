package model;

import java.util.ArrayList;

import java.util.HashSet;

import javax.swing.JTable;

public class Clustering {
	private ArrayList<Persona> personas;
	private Grafo grafoPersonas;
	ArrayList<Double> estadisticas;

	public Clustering() {
	}
	
	public HashSet<HashSet<String>> crearGrupos(int cantPersonas, JTable tablaPersonas, int cantGrupos){
		HashSet<HashSet<String>> grupos = new HashSet<HashSet<String>>();
		
		crearPersonas(tablaPersonas, cantPersonas, crearListaPersonas(cantPersonas));		
		Grafo agm = AGM.agm(crearGrafoPersonas(cantPersonas));
		HashSet<HashSet<Integer>> gruposVertices = crearGruposDeVertices(agm, cantGrupos);
		estadisticas = promedioIntereses();
		grupos = crearGruposDePersonas(gruposVertices, cantGrupos);
		
		return grupos;
	}
	
	private void crearPersonas(JTable tablaPersonas, int cantPersonas, ArrayList<Persona> personas) {
		for(int i = 0; i < cantPersonas; i++) {

			String nombre = (String) tablaPersonas.getValueAt(i, 0);
			int deporte = Integer.parseInt((String)tablaPersonas.getValueAt(i, 1));
			int musica = Integer.parseInt((String)tablaPersonas.getValueAt(i, 2));
			int espectaculo = Integer.parseInt((String)tablaPersonas.getValueAt(i, 3));
			int ciencia = Integer.parseInt((String)tablaPersonas.getValueAt(i, 4));

			Persona p= new Persona(nombre, deporte, musica, espectaculo, ciencia);
			personas.add(p);
		}
	}

	public ArrayList<Persona> crearListaPersonas(int cantPersonas) {
		verificarCantPersonasPositivo(cantPersonas);
		return personas = new ArrayList<Persona>(cantPersonas);
	}
	
	public void agregarPersona(Persona persona) {
		personas.add(persona);
	}

	public Grafo crearGrafoPersonas(int cantPersonas) {
		verificarPersonasNoVacio();
		verificarCantPersonasPositivo(cantPersonas);
		verificarCantPersonas(cantPersonas);
		grafoPersonas = new Grafo(cantPersonas);

		for (int i = 0; i < cantPersonas - 1; i++) {
			agregarAristasPersona(i);
		}
		return grafoPersonas;
	}

	private void agregarAristasPersona(int n) {
		for (int i = 0; i < grafoPersonas.tamano(); i++) {
			if (i != n)
				grafoPersonas.agregarArista(n, i, calcularSimilaridad(personas.get(n), personas.get(i)));
		}
	}

	private int calcularSimilaridad(Persona i, Persona j) {
		int d = i.getInteresDeporte() - j.getInteresDeporte();
		int m = i.getInteresMusica() - j.getInteresMusica();
		int e = i.getInteresEspectaculo() - j.getInteresEspectaculo();
		int c = i.getInteresCiencia() - j.getInteresCiencia();
		int suma = Math.abs(d) + Math.abs(m) + Math.abs(e) + Math.abs(c);

		return suma;
	}
	// similaridad(i, j) = |di − dj | + |mi − mj | + |ei − ej | + |ci − cj |.

	/*
	 * a partir de los grupos de vertices creo el grupo de personas obteniendo los
	 * nombres correspondientes a la persona[vertice]
	 */
	public HashSet<HashSet<String>> crearGruposDePersonas(HashSet<HashSet<Integer>> gruposDeVertices, int cantGrupos) {
		verificarCantGrupos(cantGrupos);
		HashSet<HashSet<String>> gruposDePersonas = new HashSet<HashSet<String>>(cantGrupos);

		for (HashSet<Integer> grupo : gruposDeVertices) {
			HashSet<String> g = new HashSet<String>();
			for (Integer number : grupo) {
				g.add(personas.get(number).getNombre());
			}
			gruposDePersonas.add(g);

		}
		return gruposDePersonas;

	}

	public HashSet<HashSet<Integer>> crearGruposDeVertices(Grafo agm, int cantGrupos) {
		verificarCantGrupos(cantGrupos);

		HashSet<HashSet<Integer>> grupos = new HashSet<HashSet<Integer>>(cantGrupos);

		// Elimino las aristas necesarias para crear la cant de grupos pedidos
		for (int i = 0; i < cantGrupos - 1; i++) {
			eliminarAristaMayorPeso(agm);
		}

		agregarGrupos(agm.tamano(), grupos, agm);

		return grupos;
	}

	public ArrayList<Double> promedioIntereses() {
		ArrayList<Double> ret = new ArrayList<Double>(4);
		double deporte = 0.0;
		double musica = 0.0;
		double espectaculo = 0.0;
		double ciencia = 0.0;

		int cant = personas.size();

		for (int i = 0; i < cant; i++) {
			deporte += personas.get(i).getInteresDeporte();
			musica += personas.get(i).getInteresMusica();
			espectaculo += personas.get(i).getInteresEspectaculo();
			ciencia += personas.get(i).getInteresCiencia();
		}

		deporte = Math.round((deporte / cant) * 100.0) / 100.0;
		musica = Math.round((musica / cant) * 100.0) / 100.0;
		espectaculo = Math.round((espectaculo / cant) * 100.0) / 100.0;
		ciencia = Math.round((ciencia / cant) * 100.0) / 100.0;

		ret.add(deporte);
		ret.add(musica);
		ret.add(espectaculo);
		ret.add(ciencia);

		return ret;
	}

	private void eliminarAristaMayorPeso(Grafo agm) {
		int max = 0;
		int v1 = 0;
		int v2 = 0;

		for (int i = 0; i < agm.tamano(); i++) {
			for (int j = 0; j < agm.tamano(); j++) {
				if (i != j) {
					if (agm.obtenerPeso(i, j) != null && agm.obtenerPeso(i, j) > max) {
						max = agm.obtenerPeso(i, j);
						v1 = i;
						v2 = j;
					}
				}
			}
		}

		if (v1 != v2) {
			agm.eliminarArista(v1, v2);
		}
	}

	/*
	 * recorro cada vertice del arbol y si ya pertenece a una componente
	 * conexa(grupo) vuelvo, sino calculo el nuevo grupo y lo agrego
	 */
	
	private void agregarGrupos(int vertices, HashSet<HashSet<Integer>> grupos, Grafo agm) {
		boolean perteneceAGrupo = false;

		for (int j = 0; j < vertices; j++) {
			for (HashSet<Integer> grupo : grupos) {
				if (grupo.contains(j))
					perteneceAGrupo = true;
			}
			if (!perteneceAGrupo)
				grupos.add(BFS.alcanzables(agm, j));

			perteneceAGrupo = false;
		}

	}
	
	public ArrayList<Double> devolverEstadisticas(){
		return estadisticas;
	}

	private void verificarCantPersonasPositivo(int cantPersonas) {
		if (cantPersonas < 1) {
			throw new IllegalArgumentException("La cantidad de personas debe ser mayor a 1");
		}
	}

	private void verificarCantPersonas(int cantPersonas) {
		if (personas.size() != cantPersonas) {
			throw new IllegalArgumentException("La cantidad de vertices y personas debe ser iguales");
		}
	}

	private void verificarCantGrupos(int cantGrupos) {
		if (cantGrupos < 1) {
			throw new IllegalArgumentException("Debe haber por lo menos un grupo");
		}
	}

	private void verificarPersonasNoVacio() {
		if (personas.size() < 1) {
			throw new RuntimeException("No se puede crear el grafo sin personas");

		}
	}

}
