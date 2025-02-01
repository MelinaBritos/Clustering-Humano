package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import model.AGM;
import model.Clustering;
import model.Grafo;
import model.Persona;

public class AplicacionTest {

	@Test(expected = IllegalArgumentException.class)
	public void crearListaPersonasNegativas() {
		Clustering app = new Clustering();
		app.crearListaPersonas(-2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void crearGrafoPersonasMayorAListaPersonasTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(3);
		app.crearListaPersonas(3);
		app.agregarPersona(new Persona("1", 1, 1, 1, 1));
		app.agregarPersona(new Persona("2", 2, 2, 2, 2));
		app.agregarPersona(new Persona("3", 3, 3, 3, 3));
		app.crearGrafoPersonas(5);

	}

	@Test(expected = IllegalArgumentException.class)
	public void crearGruposDeVerticesCantMenorAUnoTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(2);
		app.agregarPersona(new Persona("juan", 1, 5, 1, 1));
		app.agregarPersona(new Persona("pepe", 1, 3, 2, 5));
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(2));
		app.crearGruposDeVertices(grafoPersonas, 0);
	}

	@Test(expected = RuntimeException.class)
	public void crearGruposDeVerticesSinPersonasTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(5);
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(5));
		app.crearGruposDeVertices(grafoPersonas, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void crearGruposDeVerticesCantidadesDistintasTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(2);
		app.agregarPersona(new Persona("juan", 1, 5, 1, 1));
		app.agregarPersona(new Persona("pepe", 1, 3, 2, 5));
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(3));
		app.crearGruposDeVertices(grafoPersonas, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearGruposDePersonasCantMenorAUnoTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(2);
		app.agregarPersona(new Persona("juan", 1, 5, 1, 1));
		app.agregarPersona(new Persona("pepe", 1, 3, 2, 5));
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(2));
		HashSet<HashSet<Integer>> grupos = app.crearGruposDeVertices(grafoPersonas, 0);
		app.crearGruposDePersonas(grupos, 0);
	}

	@Test(expected = RuntimeException.class)
	public void crearGruposDePersonasSinPersonasTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(5);
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(5));
		HashSet<HashSet<Integer>> grupos = app.crearGruposDeVertices(grafoPersonas, 2);
		app.crearGruposDePersonas(grupos, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void crearGruposDePersonasCantidadesDistintasTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(2);
		app.agregarPersona(new Persona("juan", 1, 5, 1, 1));
		app.agregarPersona(new Persona("pepe", 1, 3, 2, 5));
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(3));
		HashSet<HashSet<Integer>> grupos = app.crearGruposDeVertices(grafoPersonas, 2);
		app.crearGruposDePersonas(grupos, 0);
	}
	
	@Test
	public void crearListaPersonasTest() {
		Clustering app = new Clustering();
		ArrayList<Persona> personas = app.crearListaPersonas(3);
		ArrayList<Persona> esperado = new ArrayList<Persona>(3);
		assertEquals(esperado, personas);

	}

	@Test
	public void agregarPersonaTest() {
		Clustering app = new Clustering();
		ArrayList<Persona> personas = app.crearListaPersonas(3);
		Persona persona = new Persona("Persona", 1, 2, 3, 4);
		assertTrue(personas.size() == 0);
		app.agregarPersona(persona);
		assertTrue(personas.size() == 1);
	}
	
	@Test
	public void indiceDeSimilaridadTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(3);
		app.agregarPersona(new Persona("1", 1, 1, 1, 1));
		app.agregarPersona(new Persona("2", 2, 2, 2, 2));
		app.agregarPersona(new Persona("3", 3, 3, 3, 3));
		
		Grafo esperado = new Grafo(3);
		esperado.agregarArista(0, 1, 4);
		esperado.agregarArista(0, 2, 8);
		esperado.agregarArista(1, 2, 4);

		assertTrue(esperado.equals(app.crearGrafoPersonas(3)));
	}
	
	@Test
	public void crearGruposDeVerticesTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(3);
		app.agregarPersona(new Persona("juan", 1, 5, 1, 1));
		app.agregarPersona(new Persona("pepe", 1, 3, 2, 5));
		app.agregarPersona(new Persona("gustavo", 5, 5, 5, 5));
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(3));
		HashSet<HashSet<Integer>> grupos = app.crearGruposDeVertices(AGM.agm(grafoPersonas), 2);

		HashSet<HashSet<Integer>> esperado = new HashSet<HashSet<Integer>>();
		HashSet<Integer> grupo1 = new HashSet<Integer>();
		grupo1.add(0);
		grupo1.add(1);
		HashSet<Integer> grupo2 = new HashSet<Integer>();
		grupo2.add(2);
		esperado.add(grupo1);
		esperado.add(grupo2);

		assertEquals(esperado, grupos);
	}

	@Test
	public void crearGruposDePersonasTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(3);
		app.agregarPersona(new Persona("juan", 1, 5, 1, 1));
		app.agregarPersona(new Persona("pepe", 1, 3, 2, 5));
		app.agregarPersona(new Persona("gustavo", 5, 5, 5, 5));
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(3));
		HashSet<HashSet<Integer>> gruposV = app.crearGruposDeVertices(grafoPersonas, 2);

		HashSet<HashSet<String>> grupos = app.crearGruposDePersonas(gruposV, 2);

		HashSet<HashSet<String>> esperado = new HashSet<HashSet<String>>();
		HashSet<String> grupo1 = new HashSet<String>();
		grupo1.add("juan");
		grupo1.add("pepe");
		HashSet<String> grupo2 = new HashSet<String>();
		grupo2.add("gustavo");
		esperado.add(grupo1);
		esperado.add(grupo2);

		assertEquals(esperado, grupos);
	}

	@Test
	public void promedioInteresesGrupoTest() {
		Clustering app = new Clustering();
		app.crearListaPersonas(3);
		app.agregarPersona(new Persona("juan", 1, 5, 1, 1));
		app.agregarPersona(new Persona("pepe", 1, 3, 2, 5));
		app.agregarPersona(new Persona("guss", 5, 5, 5, 5));
		Grafo grafoPersonas = AGM.agm(app.crearGrafoPersonas(3));
		app.crearGruposDeVertices(AGM.agm(grafoPersonas), 2);
		ArrayList<Double> esperado = new ArrayList<Double>();
		esperado.add(2.33);
		esperado.add(4.33);
		esperado.add(2.67);
		esperado.add(3.67);

		assertEquals(esperado, app.promedioIntereses());
	}

}