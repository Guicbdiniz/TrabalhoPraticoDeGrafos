package grafo;

/**
 * Vertice do grafo. Seu único parâmetro é um identificador único.
 */
public class Vertice {
    private String identificador;

    /**
     * Construtor da classe.
     * 
     * @param identificador Identificador para vértice.
     */
    public Vertice(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the identificador.
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Checa se obj é igual a este, comparando seus identificadores.
     * 
     * @param obj objeto a ser comparado.
     * @return true se obj é um Vertice a apresenta o mesmo identificador que este e
     *         false se não.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vertice && ((Vertice) obj).identificador.equals(this.identificador);
    }

    /**
     * Retorna representação em String do grafo.
     */
    @Override
    public String toString() {
        return this.identificador;
    }
}