package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo extends AbstracaoDoGrafo {

    private List<Aresta> arestasDoGrafo;

    /**
     * Construtor simples da classe.
     */
    public Grafo() {
        this.listaDeAjacencia = new ArrayList<ListaDeVerticesAdjacentes>();
        this.arestasDoGrafo = new ArrayList<Aresta>();
    }

    /**
     * Construtor da classe. Todos os vértices do grafo são pegos a partir das
     * arestas. Depois é criado um objeto da classe ListaDeVerticesAdjacentes para
     * cada vértice do grafo, pegando os dados das arestas. A lista de arestas do
     * grafo é mantida para ser usada posteriormente.
     */
    public Grafo(List<Aresta> listaDeArestasDoGrafo) {
        this.listaDeAjacencia = new ArrayList<ListaDeVerticesAdjacentes>();

        List<Vertice> todosOsVerticesDoGrafo = AbstracaoDoGrafo.pegaTodosOsVerticesDasArestas(listaDeArestasDoGrafo);

        for (Vertice vertice : todosOsVerticesDoGrafo) {
            ListaDeVerticesAdjacentes listaDeVerticesAdjacentes = new ListaDeVerticesAdjacentes(vertice);

            for (Aresta aresta : listaDeArestasDoGrafo) {
                if (aresta.v1.equals(vertice)) {
                    listaDeVerticesAdjacentes.AdicionaVerticeAdjacente(aresta.v2);
                } else if (aresta.v2.equals(vertice)) {
                    listaDeVerticesAdjacentes.AdicionaVerticeAdjacente(aresta.v1);
                }
            }

            this.listaDeAjacencia.add(listaDeVerticesAdjacentes);
        }

        this.arestasDoGrafo = listaDeArestasDoGrafo;
    }

    /**
     * Construtor da classe. Primeiro são pegas todas as arestas do arquivo passado
     * usando a classe LeitorDeGrafoEmArquivo. Depois são pegos todos os vértices do
     * grafo a partir das arestas. Por fim, é criado um objeto da classe
     * ListaDeVerticesAdjacentes para cada vértice do grafo, pegando os dados das
     * arestas. A lista de arestas do grafo é mantida para ser usada posteriormente.
     */
    public Grafo(String caminhoDoArquivo) {
        this.listaDeAjacencia = new ArrayList<ListaDeVerticesAdjacentes>();

        List<Aresta> listaDeArestasDoGrafo = LeitorDeGrafoEmArquivo.pegaListaDeArestasDoArquivoLido(caminhoDoArquivo);
        List<Vertice> todosOsVerticesDoGrafo = AbstracaoDoGrafo.pegaTodosOsVerticesDasArestas(listaDeArestasDoGrafo);

        for (Vertice vertice : todosOsVerticesDoGrafo) {
            ListaDeVerticesAdjacentes listaDeVerticesAdjacentes = new ListaDeVerticesAdjacentes(vertice);

            for (Aresta aresta : listaDeArestasDoGrafo) {
                if (aresta.v1.equals(vertice)) {
                    listaDeVerticesAdjacentes.AdicionaVerticeAdjacente(aresta.v2);
                } else if (aresta.v2.equals(vertice)) {
                    listaDeVerticesAdjacentes.AdicionaVerticeAdjacente(aresta.v1);
                }
            }

            this.listaDeAjacencia.add(listaDeVerticesAdjacentes);
        }

        this.arestasDoGrafo = listaDeArestasDoGrafo;
    }

    /**
     * Percorre a lista de vértices adjacentes do vértice v1 para checar se v2 está
     * presente.
     * 
     * @param v1 primeiro vértice passado.
     * @param v2 segundo vértice passado.
     * @return true se v1 é adjacente a v2 e false se não.
     */
    public boolean isAdjacente(Vertice v1, Vertice v2) {
        ListaDeVerticesAdjacentes listaDoVerticeV1 = this.pegaListaDoVertice(v1);

        return listaDoVerticeV1 != null && listaDoVerticeV1.getAdjacentes().contains(v2);

    }

    /**
     * Pega tamanho da lista de vértices adjacentes do vértice passado.
     * 
     * @param v1 vértice passado.
     * @return tamanho da lista de vértices adjacentes.
     */
    public int getGrau(Vertice v1) {
        ListaDeVerticesAdjacentes listaDoVerticeV1 = this.pegaListaDoVertice(v1);

        if (listaDoVerticeV1 != null) {
            return listaDoVerticeV1.pegaGrauDoVerticePrincipal();
        } else {
            return 0;
        }
    }

    /**
     * Checa na lista de vértices adjacentes do vértice passado se existe algum
     * vértice.
     *
     * ATENÇÃO: POR EU NÃO CONSEGUIR ENCONTRAR UMA DEFINIÇÃO FORMAL, VÉRTICES COM
     * LOOP NÃO SÃO CONSIDERADOS ISOLADOS.
     * 
     * @param v1 vértice passado.
     * @return true se o grau do vértice passado é 0 e false se não.
     */
    public boolean isIsolado(Vertice v1) {
        return this.getGrau(v1) == 0;
    }

    /**
     * Checa se vértice passado é 'vértice folha'. Ou seja, seu grau é 1.
     * 
     * @param v1 vértice passado.
     * @return true se o grau do vértice passado é 1 e false se não.
     */
    public boolean isPendente(Vertice v1) {
        return this.getGrau(v1) == 1;
    }

    /**
     * Checa se grafo apresenta todos os seus vértices com grau igual.
     */
    public boolean isRegular() {

        int grauDoPrimeiroVertice = this.listaDeAjacencia.get(0).pegaGrauDoVerticePrincipal();

        for (int index = 1; index < this.listaDeAjacencia.size(); index++) {
            if (grauDoPrimeiroVertice != listaDeAjacencia.get(index).pegaGrauDoVerticePrincipal()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checa se todos os vértices do grafo são isolados (ou seja, apresentam grau
     * 0).
     * 
     * @return true se todos os vértices do grafo tem grau 0 e false se não.
     */
    public boolean isNulo() {

        for (ListaDeVerticesAdjacentes lista : this.listaDeAjacencia) {
            if (lista.pegaGrauDoVerticePrincipal() != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checa se todos os vértices do grafo apresentam apenas um vértice ligando a
     * cada um dos outros vértices do grafo.
     * 
     * Para cada vértice do grafo, cria-se uma lista com todos os outros vértices.
     * Depois, checa-se se a lista de adjacentes a aquele vértice contêm todos os
     * vértices da lista criada.
     * 
     * @return true se o grafo for completo e false se não.
     */
    public boolean isCompleto() {
        List<Vertice> todosOsVerticesDoGrafo = this.pegaTodosOsVerticesDoGrafo();

        for (ListaDeVerticesAdjacentes lista : this.listaDeAjacencia) {
            Vertice verticePrincipal = lista.getPrincipal();

            List<Vertice> todosOsVerticesDaListaMenosOPrincipal = Grafo
                    .pegaTodosOsVerticesDaListaMenosOPrincipal(todosOsVerticesDoGrafo, verticePrincipal);

            for (Vertice vertice : todosOsVerticesDaListaMenosOPrincipal) {
                if (!lista.getAdjacentes().contains(vertice)) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * Checa se grafo é conexo (ou seja, é possível caminhar de qualquer vértice
     * para outro vértice).
     * 
     * Para isso, é feita uma travessia em profundidade partindo de um único
     * vértice. Se sobrarem vértices em branco, o vértice não é conexo.
     * 
     * @return true se o vértice for conexo e false se não.
     */
    public boolean isConexo() {
        return new TravessiaEmProfundidade(this.listaDeAjacencia).grafoEConexo();
    }

    /**
     * Checa se o grafo é Euleriano (ou seja, todos os seus vértices apresentam grau
     * par e ele é conexo)
     * 
     * @return
     */
    public boolean isEuleriano() {
        return this.isConexo() && this.todosOsVerticesDoGrafoTemGrauPar();
    }

    /**
     * Checa se o grafo é Unicursal (ou seja, apresenta exatamente 2 vértices de
     * grau ímpar, o resto apresenta grau par)
     * 
     * @return
     */
    public boolean isUnicursal() {
        return this.numeroDeVerticesComGrauImpar() == 2;
    }

    /**
     * Pega grafo complementar do grafo.
     * 
     * A ideia é passar por todos os vértices do grafo. Se ele não estiver ligado a
     * algum, essa aresta é adicionada ao grafo retornado.
     * 
     * @return Um objeto Grafo com os vértices necessários para que o Grafo atual
     *         seja completo.
     */
    public Grafo getComplementar() {

        Grafo grafoComplementar = new Grafo();
        grafoComplementar.listaDeAjacencia = new ArrayList<ListaDeVerticesAdjacentes>();

        List<Vertice> vertices = this.pegaTodosOsVerticesDoGrafo();

        for (ListaDeVerticesAdjacentes listaDeAdjacentesPrincipal : this.listaDeAjacencia) {
            Vertice verticeAtual = listaDeAdjacentesPrincipal.getPrincipal();
            List<Vertice> todosOsVerticesDaListaMenosOAtual = Grafo.pegaTodosOsVerticesDaListaMenosOPrincipal(vertices,
                    verticeAtual);
            ListaDeVerticesAdjacentes listaDeAjacentesAtual = new ListaDeVerticesAdjacentes(verticeAtual);

            for (Vertice verticeAdjacente : todosOsVerticesDaListaMenosOAtual) {
                if (!listaDeAdjacentesPrincipal.getAdjacentes().contains(verticeAdjacente)) {
                    listaDeAjacentesAtual.getAdjacentes().add(verticeAdjacente);
                }
            }

            grafoComplementar.listaDeAjacencia.add(listaDeAjacentesAtual);
        }

        return grafoComplementar;
    }

    /**
     * Pega árvore geradora mínima a partir do uso do algoritmo de Prim começando do
     * vértice passado.
     * 
     * A ideia é ir pegando as arestas incidentes ao vértice incluído a partir de
     * uma fila de prioridades
     * 
     * 
     * A "fila" utilizada é o dicionário de custos que relaciona um vértice com o
     * seu peso a ser adicionado (que é nada mais que o peso da aresta que conecta
     * aquele vértice ao último vértice adicionado).
     * 
     * Uma lista de "borda" é utilizada para incluir aqueles vértices que podem ser
     * adicionados ao incluídos.
     * 
     * Depois de pegar todas as arestas que conectam os vértices incluídos, um grafo
     * é criado a partir delas e é retornado.
     * 
     * @param v1 - vértice de início do algoritmo.
     * @return árvore geradora mínima do grafo.
     */
    public Grafo getAGMPrim(Vertice v1) {

        List<Vertice> borda = new ArrayList<Vertice>();
        List<Vertice> incluidos = new ArrayList<Vertice>();
        List<Vertice> todosOsVertices = this.pegaTodosOsVerticesDoGrafo();
        Map<Vertice, Integer> custos = new HashMap<Vertice, Integer>();
        List<Aresta> arestasIncluidas = new ArrayList<Aresta>();

        for (Vertice vertice : todosOsVertices) {
            custos.put(vertice, Integer.MAX_VALUE); // MAX_VALUE é considerado infinito.
        }

        borda.add(v1);
        custos.put(v1, 0);

        while (borda.size() > 0) {
            Vertice verticeDeMenorCusto = Grafo.pegaVerticeDeMenorCusto(borda, custos);

            incluidos.add(verticeDeMenorCusto);
            borda.remove(verticeDeMenorCusto);

            for (Vertice vertice : todosOsVertices) {
                if (!incluidos.contains(vertice)) {
                    if (Grafo.existeArestaConectandoVertices(this.arestasDoGrafo, vertice, verticeDeMenorCusto)) {

                        Aresta arestaConectora = Grafo.pegaArestaQueConectaOsVertices(this.arestasDoGrafo, vertice,
                                verticeDeMenorCusto);

                        if (custos.get(vertice).intValue() > arestaConectora.pesoDaAresta) {
                            custos.put(vertice, arestaConectora.pesoDaAresta);
                            borda.add(vertice);
                            arestasIncluidas.add(arestaConectora);
                        }
                    }
                }
            }
        }

        return new Grafo(arestasIncluidas);
    }

    /**
     * Pega vértice dentro da lista passada que apresenta o menor custo.
     * 
     * @param vertices - lista de vértices comparados.
     * @param custos   - dicionário de relação vertice - custo.
     * @return vértice de menor custo dentro da lista.
     */
    private static Vertice pegaVerticeDeMenorCusto(List<Vertice> vertices, Map<Vertice, Integer> custos) {
        Vertice verticeDeMenorCusto = vertices.get(0);

        for (Vertice vertice : vertices) {
            if (custos.get(vertice) < custos.get(verticeDeMenorCusto)) {
                verticeDeMenorCusto = vertice;
            }
        }

        return verticeDeMenorCusto;
    }

    /**
     * Checa se existe pelo menos uma aresta na lista de arestas passadas que
     * conecta V1 a V2.
     * 
     * @param arestas  - lista de arestas passadas.
     * @param primeiro - primeiro vértice checado.
     * @param segundo  - segundo vértice checado.
     * @return se existe pelo menos uma aresta conectando v1 a v2.
     */
    private static boolean existeArestaConectandoVertices(List<Aresta> arestas, Vertice primeiro, Vertice segundo) {

        for (Aresta aresta : arestas) {
            if (aresta.v1.equals(primeiro) && aresta.v2.equals(segundo)) {
                return true;
            }
            if (aresta.v2.equals(primeiro) && aresta.v1.equals(segundo)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Pesquisa na lista de arestas pela aresta que conecta os dois vértices
     * passados.
     * 
     * @param arestas  - arestas do grafo.
     * @param primeiro - primeiro vértice passado.
     * @param segundo  - segundo vértice passado.
     * @return aresta encontrada.
     */
    private static Aresta pegaArestaQueConectaOsVertices(List<Aresta> arestas, Vertice primeiro, Vertice segundo) {
        Aresta arestaProcurada = null;

        for (Aresta aresta : arestas) {
            if (aresta.v1.equals(primeiro) && aresta.v2.equals(segundo)) {
                arestaProcurada = aresta;
                break;
            }
            if (aresta.v2.equals(primeiro) && aresta.v1.equals(segundo)) {
                arestaProcurada = aresta;
                break;
            }
        }

        return arestaProcurada;
    }

    /**
     * Pega a árvore geradora mínima a partir do uso do algoritmo de Kruskal.
     * 
     * A ideia é pegar as arestas do grafo, ordená-las e ir adicionando uma por uma
     * na árvore (apenas se suas arestas não estejam já conectadas na árvore) até
     * que o número de arestas seja o número de vértices do grafo - 1.
     * 
     * @return - a árvore geradora mínima do grafo.
     */
    public Grafo getAGMKruskal() {

        List<Aresta> arestasOrdenadas = new ArrayList<Aresta>(this.arestasDoGrafo);
        arestasOrdenadas.sort((Aresta a1, Aresta a2) -> a1.pesoDaAresta - a2.pesoDaAresta);

        List<Aresta> arestasDaArvoreGeradora = new ArrayList<Aresta>();
        int contadorDeArestas = 0;

        while (arestasDaArvoreGeradora.size() < this.listaDeAjacencia.size() - 1) {
            Aresta adicionada = arestasOrdenadas.get(contadorDeArestas);

            if (Grafo.existeCaminhoEntreVerticesDaAresta(arestasDaArvoreGeradora, adicionada)) {
                arestasDaArvoreGeradora.add(adicionada);
            }

            contadorDeArestas++;
        }

        return new Grafo(arestasDaArvoreGeradora);
    }

    /**
     * Método estático para descobrir se já existe um caminho entre os vértices de
     * uma aresta investigada entre outras arestas de um grafo.
     * 
     * Para isso, primeiro são colocados valores chefes para cada árvore dentro das
     * arestas do grafo. Depois, é checado se os vértices da aresta investigada já
     * estão dentro das arestas do grafo. Se sim, por fim, é checado se o chefe dos
     * vértices da aresta investigada são iguais ou não.
     * 
     * ATENÇÃO: esse algoritmo é extremamente custoso. Infelizmente não encontrei
     * alguma maneira de não ser.
     * 
     */
    private static boolean existeCaminhoEntreVerticesDaAresta(List<Aresta> arestasDoGrafo, Aresta investigada) {

        List<Vertice> todosOsVertices = AbstracaoDoGrafo.pegaTodosOsVerticesDasArestas(arestasDoGrafo);
        Map<Vertice, Integer> chefeDaArvore = new HashMap<Vertice, Integer>();

        int contadorDeVertice = 0;
        for (Vertice vertice : todosOsVertices) {
            chefeDaArvore.put(vertice, contadorDeVertice);
            contadorDeVertice++;
        }

        for (Aresta aresta : arestasDoGrafo) {

            int chefeVertice1 = chefeDaArvore.get(aresta.v1);
            int chefeVertice2 = chefeDaArvore.get(aresta.v2);

            if (chefeVertice1 != chefeVertice2) {
                for (Vertice vertice : todosOsVertices) {
                    if (chefeDaArvore.get(vertice).intValue() == chefeVertice1) {
                        chefeDaArvore.put(vertice, chefeVertice2);
                    }
                }
            }
        }

        return chefeDaArvore.containsKey(investigada.v1) && chefeDaArvore.containsKey(investigada.v2)
                && (chefeDaArvore.get(investigada.v1).intValue() != chefeDaArvore.get(investigada.v2).intValue());
    }

    /**
     * Pega número de vértices de corte do grafo. Ou seja, pega o número de vértices
     * que, se removidos, provoca um aumento no número de componentes conexos.
     * 
     * Para cada vértice do grafo, é checado o número de componentes (a partir de
     * uma travessia em profundidade) originais do grafo e o número de componentes
     * depois de sua remoção.
     * 
     * @return número de vértices de corte do grafo.
     */
    public int getCutVertices() {

        int numeroDeVerticesDeCorte = 0;
        int numeroDeComponenteGrafoOriginal = new TravessiaEmProfundidade(this.listaDeAjacencia)
                .pegaNumeroDeComponentesDoGrafo();

        for (Vertice vertice : this.pegaTodosOsVerticesDoGrafo()) {
            List<ListaDeVerticesAdjacentes> listaDeAdjacenciaPosRemocao = this
                    .pegaListaDeAdjacenciaDepoisDaRemocaoDeVertice(this.listaDeAjacencia, vertice);

            int numeroDeComponentesPosRemocao = new TravessiaEmProfundidade(listaDeAdjacenciaPosRemocao)
                    .pegaNumeroDeComponentesDoGrafo();

            if (numeroDeComponentesPosRemocao > numeroDeComponenteGrafoOriginal) {
                numeroDeVerticesDeCorte++;
            }
        }

        return numeroDeVerticesDeCorte;
    }

    /**
     * Cria uma nova lista de adjacência após a remoção de um vértice. Primeiro
     * remove-se a lista do vértice e depois remove-se referências a seus
     * adjacentes.
     * 
     * @param listaDeAdjacencia - lista original.
     * @param removido          - vértice removido.
     * @return lista de adjacência depois de ter vértice removido.
     */
    private List<ListaDeVerticesAdjacentes> pegaListaDeAdjacenciaDepoisDaRemocaoDeVertice(
            List<ListaDeVerticesAdjacentes> listaDeAdjacencia, Vertice removido) {

        List<ListaDeVerticesAdjacentes> novaListaDeAdjacencia = new ArrayList<ListaDeVerticesAdjacentes>(
                listaDeAdjacencia);

        for (ListaDeVerticesAdjacentes listaDeAdjacentes : novaListaDeAdjacencia) {
            if (listaDeAdjacentes.getPrincipal().equals(removido)) {
                novaListaDeAdjacencia.remove(listaDeAdjacentes);
            } else {
                for (Vertice adjacente : listaDeAdjacentes.getAdjacentes()) {
                    if (adjacente.equals(removido)) {
                        listaDeAdjacentes.getAdjacentes().remove(adjacente);
                    }
                }
            }
        }

        return novaListaDeAdjacencia;
    }

}