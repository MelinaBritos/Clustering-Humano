package model;

import java.util.Arrays;
import java.util.HashSet;


public class Grafo {
	// Representamos el grafo por su matriz de adyacencia
	private Integer[][] A;

	// La cantidad de vertices esta predeterminada desde el constructor
	public Grafo(int vertices) {
		verificarCantVertices(vertices);
		A = new Integer[vertices][vertices];
	}

	// Agregado de aristas
	public void agregarArista(int i, int j, int peso) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = peso;
		A[j][i] = peso;
	}

	// Eliminacion de aristas
	public void eliminarArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = null;
		A[j][i] = null;
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return (A[i][j] != null);
	}

	// Cantidad de vertices
	public int tamano() {
		return A.length;
	}

	// Vecinos de un vertice
	public HashSet<Integer> vecinos(int i) {
		verificarVertice(i);

		HashSet<Integer> ret = new HashSet<Integer>();
		for (int j = 0; j < this.tamano(); ++j)
			if (i != j) {
				if (this.existeArista(i, j))
					ret.add(j);
			}

		return ret;
	}

	public Integer obtenerPeso(int i, int j) {
		return A[i][j];
	}

	public boolean tieneAristas() {
		boolean ret = false;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if (i != j) {
					ret = ret || A[i][j] != null;
				}
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Integer[] fila : A) {
			sb.append(Arrays.toString(fila));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public boolean equals(Grafo otro) {
		if (tamano() != otro.tamano()) {
			return false;
		}
		boolean ret = true;
		for (int i = 0; i < tamano(); i++) {
			for (int j = 0; j < tamano(); j++) {
				ret = ret && A[i][j] == otro.A[i][j];
			}
		}
		return ret;
	}

	// Verifica que sea un vertice valido
	private void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

		if (i >= A.length)
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}

	private void verificarCantVertices(int vertices) {
		if (vertices < 0)
			throw new IllegalArgumentException("No se puede crear un grafo con vértices menores a 0");
	}

}
