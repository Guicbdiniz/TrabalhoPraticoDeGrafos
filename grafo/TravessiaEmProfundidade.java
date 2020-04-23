package grafo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grafo.AbstracaoDoGrafo.CorDoVertice;

/**
 * TravessiaEmProfundidade
 */
public class TravessiaEmProfundidade {

    private List<ListaDeVerticesAdjacentes> listaDeVerticesAdjacentes;
    private List<Vertice> vertices;
    private Map<Vertice, CorDoVertice> coresDosVertices;
    private Map<Vertice, Integer> temposDeDescobertaDosVertices;
    private Map<Vertice, Integer> temposDeTerminoDosVertices;

    /**
     * Construtor da classe
     * 
     * @param listaDeVerticesAdjacentes lista de adjacência do grafo trabalhado.
     */
    public TravessiaEmProfundidade(List<ListaDeVerticesAdjacentes> listaDeVerticesAdjacentes) {
        this.listaDeVerticesAdjacentes = listaDeVerticesAdjacentes;
        this.vertices = this.pegaListaDeTodosOsVertices();
        this.inicializaCoresETempos();
    }

    // MÉTODOS PÚBLICOS

    /**
     * Checa se grafo trabalhado é conexo. Ao atravessar todo o grafo a partir de um
     * primeiro vértice, é checado se existe algum ainda em branco.
     * 
     * @return true se o grafo for conexo e false se não.
     */
    public boolean grafoEConexo() {

        int tempo = 0;
        Vertice primeiroVerticeChecado = this.vertices.get(0);

        this.visita(primeiroVerticeChecado, tempo);

        for (Vertice vertice : this.vertices) {
            if (this.coresDosVertices.get(vertice) == CorDoVertice.BRANCO) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checa quantos componentes o grafo tem. Atravessa-se o grafo a partir de um
     * vértice e guarda-se quantas vezes o loop de procurar um vértice em branco
     * rodou.
     * 
     * @return número de componentes do Grafo.
     */
    public int pegaNumeroDeComponentesDoGrafo() {

        int tempo = 0;
        int numeroDeComponentes = 1;
        Vertice primeiroVerticeChecado = this.vertices.get(0);

        this.visita(primeiroVerticeChecado, tempo);

        for (Vertice vertice : this.vertices) {
            if (this.coresDosVertices.get(vertice) == CorDoVertice.BRANCO) {
                numeroDeComponentes++;
                this.visita(vertice, tempo);
            }
        }

        return numeroDeComponentes;
    }

    // MÉTODOS PRIVADOS

    /**
     * Coleta uma lista com todos os vértices a partir da lista de adjacência.
     * 
     * @return uma lista com todos os vértices do grafo trabalhado.
     */
    private List<Vertice> pegaListaDeTodosOsVertices() {
        return this.listaDeVerticesAdjacentes.stream().map(lista -> lista.getPrincipal()).collect(Collectors.toList());
    }

    /**
     * Inicializa o mapa de cores (conecta cada vértice com sua cor atual), o mapa
     * de tempos de descobertas (conecta cada vértice com o tempo em que foi
     * descoberto) e o mapa de tempos de término (conecta cada vértice com o tempo
     * em que foi totalmente analizado).
     */
    private void inicializaCoresETempos() {
        int numeroDeVertices = this.vertices.size();

        this.coresDosVertices = new HashMap<Vertice, CorDoVertice>(numeroDeVertices);
        this.temposDeDescobertaDosVertices = new HashMap<Vertice, Integer>(numeroDeVertices);
        this.temposDeTerminoDosVertices = new HashMap<Vertice, Integer>(numeroDeVertices);

        for (Vertice vertice : vertices) {
            coresDosVertices.put(vertice, CorDoVertice.BRANCO);
        }
    }

    /**
     * Visita o vértice, chamando-se recursivamente para cada vértice adjacente a
     * este que ainda não foi visitado.
     * 
     * @param verticeVisitado vértice para ser visitado.
     * @param tempo           tempo atual.
     */
    private void visita(Vertice verticeVisitado, int tempo) {
        this.coresDosVertices.put(verticeVisitado, CorDoVertice.AZUL);
        this.temposDeDescobertaDosVertices.put(verticeVisitado, ++tempo);

        List<Vertice> verticesAdjacentesAoVerticeVisitado = this.pegaVerticesAdjacentesAoVertice(verticeVisitado);

        for (Vertice vertice : verticesAdjacentesAoVerticeVisitado) {
            if (this.coresDosVertices.get(vertice) == CorDoVertice.BRANCO) {
                visita(vertice, tempo);
            }
        }

        this.coresDosVertices.put(verticeVisitado, CorDoVertice.VERMELHO);
        this.temposDeTerminoDosVertices.put(verticeVisitado, ++tempo);
    }

    /**
     * Pega uma lista com todos os vértices no grafo atual adjacentes ao passado.
     * 
     * @param verticeVisitado vértice atualmente visitado.
     * @return lista com todos os vértices adjacentes.
     */
    private List<Vertice> pegaVerticesAdjacentesAoVertice(Vertice verticeVisitado) {
        int indexDoVertice = this.vertices.indexOf(verticeVisitado);

        return this.listaDeVerticesAdjacentes.get(indexDoVertice).getAdjacentes();
    }

}