package model;

import java.util.HashSet;

public class AGM {
	private static Grafo Agm;
	private static HashSet<Integer> verticesMarcados;
	private static int pesoAristaMinima;

	public static Grafo agm(Grafo grafo) {
		if (!grafo.tieneAristas()) {
			throw new IllegalArgumentException("El grafo no tiene aristas");
		} else {
			Agm = new Grafo(grafo.tamano());
			verticesMarcados = new HashSet<Integer>();
			int v1 = 0;
			int v2 = 0;
			pesoAristaMinima = Integer.MAX_VALUE;
			verticesMarcados.add(0);

			for (int i = 0; i < Agm.tamano() - 1; i++) { // cant aristas |v| - 1

				for (int vertice = 0; vertice < grafo.tamano(); vertice++) {
					if (verticesMarcados.contains(vertice)) {
						for (int vecino : grafo.vecinos(vertice)) {
							if (vertice != vecino) {
								if (!verticesMarcados.contains(vecino)
										&& grafo.obtenerPeso(vecino, vertice) < pesoAristaMinima) {
									pesoAristaMinima = grafo.obtenerPeso(vecino, vertice);
									v1 = vecino;
									v2 = vertice;
								}
							}
						}
					}
				}

				verticesMarcados.add(v1);
				Agm.agregarArista(v1, v2, pesoAristaMinima);
				pesoAristaMinima = Integer.MAX_VALUE;
			}
			return Agm;
		}
	}
}
