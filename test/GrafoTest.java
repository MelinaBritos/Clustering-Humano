package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Grafo;

public class GrafoTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void cantVerticesNegativaTest() {
		Grafo grafo = new Grafo(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(5, 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 5, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(-1, 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(-1, 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest(){
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 2, 0);
	}

	@Test
	public void noExisteAristaTest() {
		Grafo grafo = new Grafo(5);
		assertFalse(grafo.existeArista(0, 1));
	}
	
	@Test
	public void existeAristaTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1, 0);
		assertTrue(grafo.existeArista(0, 1));
	}
	
	@Test
	public void existeAristaOpuestaTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1, 0);
		assertTrue(grafo.existeArista(1, 0));
	}
	
	@Test
	public void agregarAristaDosVecesTest(){
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3, 0);
		grafo.agregarArista(2, 3, 0);
		assertTrue(grafo.existeArista(2, 3));
	}
	
	@Test
	public void eliminarAristaTest(){
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(1, 0, 0);
		
		grafo.eliminarArista(1, 0);
		assertFalse(grafo.existeArista(1, 0));
	}
	
	@Test
	public void aristaOpuestaEliminadaTest(){
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(1, 0, 0);
		grafo.eliminarArista(1, 0);
		assertFalse(grafo.existeArista(0, 1));
	}

	@Test
	public void eliminarAristaInexistenteTest(){
		Grafo grafo = new Grafo(5);
		grafo.eliminarArista(1, 0);
		assertFalse(grafo.existeArista(1, 0));
	}
	
	@Test
	public void eliminarOtrArista() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(1, 0, 0);
		grafo.agregarArista(2, 0, 0);
		grafo.eliminarArista(2, 0);
		assertTrue(grafo.existeArista(1, 0));
	}

}