package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.AGM;
import model.Grafo;

public class AGMTest {

	@Test(expected = IllegalArgumentException.class)
	public void AGMGrafoSinAristas() {
		AGM.agm(new Grafo(5));
		// AGM de un grafo sin aristas genera error de loops
	}

	@Test(expected = IllegalArgumentException.class)
	public void AMGGrafoSinVerticesMinimos() {
		AGM.agm(new Grafo(1));
		// Verificar si no es vacío antes de ejecutar
	}
	
	@Test
	public void AGMGrafoValido() {
		Grafo grafo = new Grafo(3);
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 2);
		grafo.agregarArista(1, 2, 3);
		Grafo agm = AGM.agm(grafo);
		
		Grafo esperado = new Grafo(3);
		esperado.agregarArista(0, 1, 1);
		esperado.agregarArista(0, 2, 2);
		
		assertTrue(esperado.equals(agm));
	}

}
