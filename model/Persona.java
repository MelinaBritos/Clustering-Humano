package model;

public class Persona {

	private String nombre;
	private int interesDeporte, interesMusica, interesEspectaculo, interesCiencia;

	public Persona(String nombre, int deporte, int musica, int espectaculo, int ciencia) {
		
		if(nombre == null) {
			throw new IllegalArgumentException("El nombre no puede ser null.");
		}
		this.nombre = nombre;
		
		verificarNumerosValidos(deporte, musica, espectaculo, ciencia);
		interesDeporte = deporte;
		interesMusica = musica;
		interesEspectaculo = espectaculo;
		interesCiencia = ciencia;
	}

	private void verificarNumerosValidos(int deporte, int musica, int espectaculo, int ciencia) {

		int[] array = { deporte, musica, espectaculo, ciencia };

		for (Integer interesValido : array)
			if (interesValido <= 0 || interesValido > 5) {
				throw new IllegalArgumentException("el interes deberia ser mayor a 1 y menor a 5" + interesValido);
			}
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getInteresDeporte() {
		return interesDeporte;
	}
	
	public void setInteresDeporte(int interesDeporte) {
		this.interesDeporte = interesDeporte;
	}
	
	public int getInteresMusica() {
		return interesMusica;
	}
	
	public void setInteresMusica(int interesMusica) {
		this.interesMusica = interesMusica;
	}
	
	public int getInteresEspectaculo() {
		return interesEspectaculo;
	}
	
	public void setInteresEspectaculo(int interesEspectaculo) {
		this.interesEspectaculo = interesEspectaculo;
	}
	

	public int getInteresCiencia() {
		return interesCiencia;
	}
	
	public void setInteresCiencia(int interesCiencia) {
		this.interesCiencia = interesCiencia;
	}

	
}
