package grafo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LeitorDeGrafoEmArquivo {

    /**
     * Construtor da Classe.
     * 
     * @param caminhoDoArquivo - caminho do arquivo que será lido.
     */
    private LeitorDeGrafoEmArquivo() {

    }

    /**
     * Pega linhas do arquivo texto.
     * 
     * @param caminhoDoArquivo - caminho do arquivo lido.
     * @return lista com cada linha lida no arquivo.
     */
    private static List<String> pegaLinhasDoArquivo(String caminhoDoArquivo) {
        List<String> linhasDoArquivo = null;

        try {
            linhasDoArquivo = Files.readAllLines(Paths.get(caminhoDoArquivo));
            linhasDoArquivo.remove(0);
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo de texto.");
            System.out.println(e.getMessage());
        }

        return linhasDoArquivo;
    }

    /**
     * Pega lista de arestas do grafo não dirigido lido em arquivo.
     * 
     * @param caminhoDoArquivo - caminho do arquivo lido.
     * @return - lista de arestas lidas em arquivo.
     */
    public static List<Aresta> pegaListaDeArestasDoArquivoLido(String caminhoDoArquivo) {

        List<String> linhasDoArquivo = LeitorDeGrafoEmArquivo.pegaLinhasDoArquivo(caminhoDoArquivo);

        return linhasDoArquivo.stream().map(linha -> new Aresta(linha)).collect(Collectors.toList());
    }

    /**
     * Pega lista de arestas do grafo dirigido lido em arquivo.
     * 
     * @param caminhoDoArquivo - caminho do arquivo lido.
     * @return - lista de arestas dirigidas lidas em arquivo.
     */
    public static List<ArestaDirigida> pegaListaDeArestasDirigidasDoArquivoLido(String caminhoDoArquivo) {

        List<String> linhasDoArquivo = LeitorDeGrafoEmArquivo.pegaLinhasDoArquivo(caminhoDoArquivo);

        return linhasDoArquivo.stream().map(linha -> new ArestaDirigida(linha)).collect(Collectors.toList());
    }

}