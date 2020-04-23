package grafo;

import java.util.ArrayList;
import java.util.List;

/**
 * GrafoDirigido
 */
public class GrafoDirigido extends AbstracaoDoGrafo {

    /**
     * Construtor da classe. Primeiro são pegas todas as arestas do arquivo passado
     * usando a classe LeitorDeGrafoEmArquivo. Depois são pegos todos os vértices do
     * grafo a partir das arestas. Por fim, é criado um objeto da classe
     * ListaDeVerticesAdjacentes para cada vértice do grafo, pegando os dados das
     * arestas. A lista de arestas do grafo é mantida para ser usada posteriormente.
     */
    public GrafoDirigido(String caminhoDoArquivo) {
        this.listaDeAjacencia = new ArrayList<ListaDeVerticesAdjacentes>();

        List<ArestaDirigida> listaDeArestasDoGrafo = LeitorDeGrafoEmArquivo
                .pegaListaDeArestasDirigidasDoArquivoLido(caminhoDoArquivo);
        List<Vertice> todosOsVerticesDoGrafo = AbstracaoDoGrafo
                .pegaTodosOsVerticesDasArestasDirigidas(listaDeArestasDoGrafo);

        for (Vertice vertice : todosOsVerticesDoGrafo) {
            ListaDeVerticesAdjacentes listaDeVerticesAdjacentes = new ListaDeVerticesAdjacentes(vertice);

            for (ArestaDirigida aresta : listaDeArestasDoGrafo) {
                if (aresta.v1.equals(vertice) && aresta.direcaoDaAresta == 1) {
                    listaDeVerticesAdjacentes.AdicionaVerticeAdjacente(aresta.v2);
                } else if (aresta.v2.equals(vertice) && aresta.direcaoDaAresta == -1) {
                    listaDeVerticesAdjacentes.AdicionaVerticeAdjacente(aresta.v1);
                }
            }

            this.listaDeAjacencia.add(listaDeVerticesAdjacentes);
        }
    }

    /**
     * Construtor simples da classe.
     * 
     * @param listaDeAdjacencia - lista de adjacência do grafo.
     */
    public GrafoDirigido(List<ListaDeVerticesAdjacentes> listaDeAdjacencia) {
        this.listaDeAjacencia = listaDeAdjacencia;
    }

    /**
     * Pega quantas vezes o vértice passado aparece na lista de adjacência dos
     * outros vértices.
     * 
     * @param v1 vértice passado.
     * @return quantas vezes o vértice passado aparece na lista de adjacência dos
     *         outros vértices.
     */
    public int getGrauDeEntrada(Vertice v1) {
        int grauDeSaida = 0;

        for (ListaDeVerticesAdjacentes listaDeVerticesAdjacentes : this.listaDeAjacencia) {
            if (!listaDeVerticesAdjacentes.getPrincipal().equals(v1)) {
                for (Vertice verticeAnalisado : listaDeVerticesAdjacentes.getAdjacentes()) {
                    if (verticeAnalisado.equals(v1)) {
                        grauDeSaida++;
                    }
                }
            }
        }

        return grauDeSaida;
    }

    /**
     * Pega tamanho da lista de vértices adjacentes do vértice passado.
     * 
     * @param v1 vértice passado.
     * @return tamanho da lista de vértices adjacentes.
     */
    public int getGrauDeSaida(Vertice v1) {
        ListaDeVerticesAdjacentes listaDoVerticeV1 = this.pegaListaDoVertice(v1);

        return listaDoVerticeV1.pegaGrauDoVerticePrincipal();
    }

    /**
     * Checa se o Grafo dirigido apresenta algum ciclo. Para isso, são feitos ciclos
     * de remoção de folhas do grafo, checando se os vértices acabaram (grafo
     * aciclico) ou se acabaram os vértices folhas (grafo cíclico).
     * 
     * @return se o Grafo apresenta ciclo ou não.
     */
    public boolean hasCiclo() {

        List<Vertice> verticesFolha = this.pegaVerticesFolha();
        List<Vertice> verticesDoGrafo = this.pegaTodosOsVerticesDoGrafo();
        List<ListaDeVerticesAdjacentes> listaDeVerticesAdjacentes = new ArrayList<ListaDeVerticesAdjacentes>(
                this.listaDeAjacencia);

        while (verticesDoGrafo.size() != 0) {

            if (verticesFolha.size() == 0) {
                return true;
            }

            Vertice verticeRemovido = verticesFolha.get(0);
            verticesDoGrafo.remove(verticeRemovido);

            // Remoção de vértice do grafo.
            for (ListaDeVerticesAdjacentes lista : listaDeVerticesAdjacentes) {
                if (lista.getPrincipal().equals(verticeRemovido)) {
                    listaDeVerticesAdjacentes.remove(lista);
                } else {
                    for (Vertice vertice : lista.getAdjacentes()) {
                        if (vertice.equals(verticeRemovido)) {
                            lista.getAdjacentes().remove(verticeRemovido);
                        }
                    }
                }
            }

            verticesFolha = new GrafoDirigido(listaDeVerticesAdjacentes).pegaVerticesFolha();

        }

        return false;
    }

    /**
     * Pega vertices com grau de saida igual a 0 no grafo.
     * 
     * @return vertices folha do grafo.
     */
    private List<Vertice> pegaVerticesFolha() {
        List<Vertice> verticesFolha = new ArrayList<Vertice>();

        for (Vertice vertice : this.pegaTodosOsVerticesDoGrafo()) {
            if (this.getGrauDeSaida(vertice) == 0) {
                verticesFolha.add(vertice);
            }
        }

        return verticesFolha;
    }

}
