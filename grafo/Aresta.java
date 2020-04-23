package grafo;

public class Aresta {

    public Vertice v1;
    public Vertice v2;
    public int pesoDaAresta;

    public Aresta(String textoDeInformacoesDaAresta) {
        String[] informacoesDaAresta = textoDeInformacoesDaAresta.replaceAll(" ", "").split(";");
        this.v1 = new Vertice(informacoesDaAresta[0]);
        this.v2 = new Vertice(informacoesDaAresta[1]);
        this.pesoDaAresta = Integer.parseInt(informacoesDaAresta[2]);
    }

}