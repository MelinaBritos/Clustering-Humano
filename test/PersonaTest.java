package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Persona;

public class PersonaTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void asignarInteresDExcedidoTest() {
		Persona p = new Persona("Persona", 6, 1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void asignarInteresDNegativoTest() {
		Persona p = new Persona("Persona", -1, 1, 1, 1);
	}

	@Test
	public void crearPersonaValidaTest() {
		Persona p = new Persona("Persona", 1, 2, 3, 4);
		assertTrue(p.getInteresDeporte() == 1 && p.getInteresMusica() == 2 &&
				p.getInteresEspectaculo() == 3 && p.getInteresCiencia() == 4);
	}
	
}
