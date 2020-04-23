package grafo;

public class ArestaDirigida {

    public Vertice v1;
    public Vertice v2;
    public int pesoDaAresta;
    public int direcaoDaAresta;

    public ArestaDirigida(String textoDeInformacoesDaAresta) {
        String[] informacoesDaAresta = textoDeInformacoesDaAresta.replaceAll(" ", "").split(";");
        this.v1 = new Vertice(informacoesDaAresta[0]);
        this.v2 = new Vertice(informacoesDaAresta[1]);
        this.pesoDaAresta = Integer.parseInt(informacoesDaAresta[2]);
        this.direcaoDaAresta = Integer.parseInt(informacoesDaAresta[3]);
    }

}