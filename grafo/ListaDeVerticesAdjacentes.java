package grafo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação de uma célula de uma lista de adjacência.
 */
public class ListaDeVerticesAdjacentes {
    private Vertice principal;
    private List<Vertice> adjacentes;

    public ListaDeVerticesAdjacentes(Vertice principal) {
        this.principal = principal;
        this.adjacentes = new ArrayList<Vertice>();
    }

    public ListaDeVerticesAdjacentes(Vertice princiapal, List<Vertice> verticesAdjacentes) {
        this.principal = princiapal;
        this.adjacentes = verticesAdjacentes;
    }

    /**
     * Adiciona vértice adjacente à lista.
     * 
     * @param adjacente objeto Vértice a ser adicionado.
     */
    public void AdicionaVerticeAdjacente(Vertice adjacente) {
        this.adjacentes.add(adjacente);
    }

    /**
     * @return the principal
     */
    public Vertice getPrincipal() {
        return principal;
    }

    /**
     * @return the adjacentes
     */
    public List<Vertice> getAdjacentes() {
        return adjacentes;
    }

    /**
     * Pega número de vértices na lista de adjacentes (somando 2 se for loop).
     * 
     * @return grau do vértice principal.
     */
    public int pegaGrauDoVerticePrincipal() {
        int grau = 0;
        for (Vertice v : this.adjacentes) {
            if (v.equals(this.principal)) {
                grau += 2;
            } else {
                grau++;
            }
        }

        return grau;
    }

    /**
     * Pega a representação da lista de vértices adjacentes em String.
     */
    @Override
    public String toString() {

        String verticesAdjacentes = "";

        for (Vertice vertice : this.adjacentes) {
            verticesAdjacentes += vertice + " ";
        }

        return "Vértice: " + this.principal + "\n\tAdjacentes: " + verticesAdjacentes;
    }
}