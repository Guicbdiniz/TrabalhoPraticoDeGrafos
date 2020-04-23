package grafo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Grafo
 */
public abstract class AbstracaoDoGrafo {

    protected List<ListaDeVerticesAdjacentes> listaDeAjacencia;

    /**
     * Enumeração para representar a cor dos vértices em algorítmos de travessia.
     */
    protected enum CorDoVertice {
        BRANCO, AZUL, VERMELHO;
    }

    /**
     * Percorre a lista de adjacencia para pegar a lista de vertices adjacentes do
     * vértice passado.
     * 
     * @param v1 Vértice principal da lista procurada.
     * @return Lista de vértices que tem o vértice procurado como principal ou null
     *         se não for encontrado.
     */
    protected ListaDeVerticesAdjacentes pegaListaDoVertice(Vertice v1) {
        for (ListaDeVerticesAdjacentes lista : this.listaDeAjacencia) {
            if (lista.getPrincipal().equals(v1)) {
                return lista;
            }
        }

        return null;
    }

    /**
     * Cria uma lista simples com todos os vértices do grafo.
     * 
     * @return uma lista simples com todos os vértices do grafo.
     */
    protected List<Vertice> pegaTodosOsVerticesDoGrafo() {
        return this.listaDeAjacencia.stream().map(lista -> lista.getPrincipal()).collect(Collectors.toList());
    }

    /**
     * Exclui um vértice da lista de vértices.
     * 
     * @param lista     lista de vértices original.
     * @param principal vértice que será excluído.
     * @return lista após vértice excluído.
     */
    protected static List<Vertice> pegaTodosOsVerticesDaListaMenosOPrincipal(List<Vertice> lista, Vertice principal) {
        return lista.stream().filter(vertice -> !vertice.equals(principal)).collect(Collectors.toList());
    }

    /**
     * Checa se todos os vértices do grafo tem grau par.
     * 
     * @return true se todos os vértices do grafo tem grau par e false se não.
     */
    protected boolean todosOsVerticesDoGrafoTemGrauPar() {
        for (ListaDeVerticesAdjacentes lista : this.listaDeAjacencia) {
            if (lista.pegaGrauDoVerticePrincipal() % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Pega número de vértices com grau impar do grafo.
     * 
     * @return número de vértices com grau ímpar no grafo.
     */
    protected int numeroDeVerticesComGrauImpar() {
        int numeroDeVerticesComGrauImpar = 0;

        for (ListaDeVerticesAdjacentes lista : this.listaDeAjacencia) {
            if (lista.pegaGrauDoVerticePrincipal() % 2 != 0) {
                numeroDeVerticesComGrauImpar++;
            }
        }

        return numeroDeVerticesComGrauImpar;
    }

    /**
     * Pega todas os vértices da lista de arestas.
     * 
     * @param arestas - arestas do grafo.
     * @return conjunto de vértices das arestas passadas.
     */
    protected static List<Vertice> pegaTodosOsVerticesDasArestas(List<Aresta> arestas) {
        List<Vertice> vertices = new ArrayList<Vertice>();

        for (Aresta aresta : arestas) {

            if (!vertices.contains(aresta.v1)) {
                vertices.add(aresta.v1);
            }
            if (!vertices.contains(aresta.v2)) {
                vertices.add(aresta.v2);
            }
        }
        return vertices;
    }

    /**
     * Pega todos os vértices da lista de arestas dirigidas.
     * 
     * @param arestasDirigidas - arestasDirigidas do grafo.
     * @return conjunto de vértices das arestas dirigidas passadas.
     */
    protected static List<Vertice> pegaTodosOsVerticesDasArestasDirigidas(List<ArestaDirigida> arestasDirigidas) {
        List<Vertice> vertices = new ArrayList<Vertice>();

        for (ArestaDirigida arestaDirigida : arestasDirigidas) {

            if (!vertices.contains(arestaDirigida.v1)) {
                vertices.add(arestaDirigida.v1);
            }
            if (!vertices.contains(arestaDirigida.v2)) {
                vertices.add(arestaDirigida.v2);
            }
        }

        return vertices;
    }

    /**
     * Imprime a lista de adjacência do grafo.
     */
    public void imprimeGrafo() {
        this.listaDeAjacencia.forEach(lista -> {
            System.out.println(lista + "\n");
        });
    }
}