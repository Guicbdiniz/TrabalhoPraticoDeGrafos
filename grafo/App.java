package grafo;

import java.util.List;

public class App {

    private static String getPath(String nomeArquivo) {
        return "D:\\Documentos\\2020\\PUCMinas\\Grafos\\Exercicio Pratico 1\\testes\\" + nomeArquivo;
    }

    private static void testesUnicursal() {

        Grafo unicursal = new Grafo(
                "D:\\Documentos\\2020\\PUCMinas\\Grafos\\Exercicio Pratico 1\\testes\\unicursal.txt");

        unicursal.imprimeGrafo();

        List<Vertice> vertices = unicursal.pegaTodosOsVerticesDoGrafo();

        System.out.println("1 e 2 são adjacentes: " + unicursal.isAdjacente(new Vertice("1"), new Vertice("2")));
        System.out.println("1 e 3 são adjacentes: " + unicursal.isAdjacente(new Vertice("1"), new Vertice("3")));
        System.out.println("1 e 4 são adjacentes: " + unicursal.isAdjacente(new Vertice("1"), new Vertice("4")));
        System.out.println("1 e 5 são adjacentes: " + unicursal.isAdjacente(new Vertice("1"), new Vertice("5")));
        System.out.println("1 e 6 são adjacentes: " + unicursal.isAdjacente(new Vertice("1"), new Vertice("6")));

        System.out.println("Grau do Vértice 1: " + unicursal.getGrau(new Vertice("1")));
        System.out.println("Grau do Vértice 2: " + unicursal.getGrau(new Vertice("2")));
        System.out.println("Grau do Vértice 3: " + unicursal.getGrau(new Vertice("3")));
        System.out.println("Grau do Vértice 4: " + unicursal.getGrau(new Vertice("4")));
        System.out.println("Grau do Vértice 5: " + unicursal.getGrau(new Vertice("5")));
        System.out.println("Grau do Vértice 6: " + unicursal.getGrau(new Vertice("6")));

        for (Vertice vertice : vertices) {
            System.out.println("Vértice " + vertice.getIdentificador() + " é isolado: " + unicursal.isIsolado(vertice));
            System.out
                    .println("Vértice " + vertice.getIdentificador() + " é pendente: " + unicursal.isPendente(vertice));
        }

        System.out.println("Grafo é nulo: " + unicursal.isNulo());
        System.out.println("Grafo é regular: " + unicursal.isRegular());
        System.out.println("Grafo é completo: " + unicursal.isCompleto());
        System.out.println("Grafo é euleriano: " + unicursal.isEuleriano());
        System.out.println("Grafo é unicursal: " + unicursal.isUnicursal());
        System.out.println("Grafo é conexo: " + unicursal.isConexo());

        Grafo complementar = unicursal.getComplementar();

        complementar.imprimeGrafo();

        Grafo arvorePrim = unicursal.getAGMPrim(new Vertice("1"));

        arvorePrim.imprimeGrafo();

        Grafo arvoreKruskal = unicursal.getAGMKruskal();

        arvoreKruskal.imprimeGrafo();

    }

    private static void testeCompleto() {
        Grafo grafo = new Grafo(App.getPath("completo.txt"));

        grafo.imprimeGrafo();

        List<Vertice> vertices = grafo.pegaTodosOsVerticesDoGrafo();

        System.out.println("1 e 2 são adjacentes: " + grafo.isAdjacente(new Vertice("1"), new Vertice("2")));
        System.out.println("1 e 3 são adjacentes: " + grafo.isAdjacente(new Vertice("1"), new Vertice("3")));
        System.out.println("1 e 4 são adjacentes: " + grafo.isAdjacente(new Vertice("1"), new Vertice("4")));

        System.out.println("Grau do Vértice 1: " + grafo.getGrau(new Vertice("1")));
        System.out.println("Grau do Vértice 2: " + grafo.getGrau(new Vertice("2")));
        System.out.println("Grau do Vértice 3: " + grafo.getGrau(new Vertice("3")));
        System.out.println("Grau do Vértice 4: " + grafo.getGrau(new Vertice("4")));

        for (Vertice vertice : vertices) {
            System.out.println("Vértice " + vertice.getIdentificador() + " é isolado: " + grafo.isIsolado(vertice));
            System.out.println("Vértice " + vertice.getIdentificador() + " é pendente: " + grafo.isPendente(vertice));
        }

        System.out.println("Grafo é nulo: " + grafo.isNulo());
        System.out.println("Grafo é regular: " + grafo.isRegular());
        System.out.println("Grafo é completo: " + grafo.isCompleto());
        System.out.println("Grafo é euleriano: " + grafo.isEuleriano());
        System.out.println("Grafo é unicursal: " + grafo.isUnicursal());
        System.out.println("Grafo é conexo: " + grafo.isConexo());

        Grafo complementar = grafo.getComplementar();

        complementar.imprimeGrafo();

        Grafo arvorePrim = grafo.getAGMPrim(new Vertice("1"));

        arvorePrim.imprimeGrafo();

        Grafo arvoreKruskal = grafo.getAGMKruskal();

        arvoreKruskal.imprimeGrafo();
    }

    private static void testeNulo() {
        Grafo grafo = new Grafo(App.getPath("nulo.txt"));

        grafo.imprimeGrafo();

        List<Vertice> vertices = grafo.pegaTodosOsVerticesDoGrafo();

        for (Vertice vertice : vertices) {
            System.out.println("Vértice " + vertice.getIdentificador() + " é isolado: " + grafo.isIsolado(vertice));
            System.out.println("Vértice " + vertice.getIdentificador() + " é pendente: " + grafo.isPendente(vertice));
        }

        System.out.println("Grafo é nulo: " + grafo.isNulo());
        System.out.println("Grafo é regular: " + grafo.isRegular());
        System.out.println("Grafo é completo: " + grafo.isCompleto());
        System.out.println("Grafo é euleriano: " + grafo.isEuleriano());
        System.out.println("Grafo é unicursal: " + grafo.isUnicursal());
        System.out.println("Grafo é conexo: " + grafo.isConexo());

        Grafo complementar = grafo.getComplementar();

        complementar.imprimeGrafo();

        Grafo arvorePrim = grafo.getAGMPrim(new Vertice("1"));

        arvorePrim.imprimeGrafo();

        Grafo arvoreKruskal = grafo.getAGMKruskal();

        arvoreKruskal.imprimeGrafo();
    }

    private static void testePendente() {
        Grafo grafo = new Grafo(App.getPath("pendente.txt"));

        grafo.imprimeGrafo();

        List<Vertice> vertices = grafo.pegaTodosOsVerticesDoGrafo();

        System.out.println("1 e 2 são adjacentes: " + grafo.isAdjacente(new Vertice("1"), new Vertice("2")));
        System.out.println("1 e 3 são adjacentes: " + grafo.isAdjacente(new Vertice("1"), new Vertice("3")));

        System.out.println("Grau do Vértice 1: " + grafo.getGrau(new Vertice("1")));
        System.out.println("Grau do Vértice 2: " + grafo.getGrau(new Vertice("2")));
        System.out.println("Grau do Vértice 3: " + grafo.getGrau(new Vertice("3")));

        for (Vertice vertice : vertices) {
            System.out.println("Vértice " + vertice.getIdentificador() + " é isolado: " + grafo.isIsolado(vertice));
            System.out.println("Vértice " + vertice.getIdentificador() + " é pendente: " + grafo.isPendente(vertice));
        }

        System.out.println("Grafo é nulo: " + grafo.isNulo());
        System.out.println("Grafo é regular: " + grafo.isRegular());
        System.out.println("Grafo é completo: " + grafo.isCompleto());
        System.out.println("Grafo é euleriano: " + grafo.isEuleriano());
        System.out.println("Grafo é unicursal: " + grafo.isUnicursal());
        System.out.println("Grafo é conexo: " + grafo.isConexo());

        Grafo complementar = grafo.getComplementar();

        complementar.imprimeGrafo();

        Grafo arvorePrim = grafo.getAGMPrim(new Vertice("1"));

        arvorePrim.imprimeGrafo();

        Grafo arvoreKruskal = grafo.getAGMKruskal();

        arvoreKruskal.imprimeGrafo();
    }

    private static void testeRegular() {
        Grafo grafo = new Grafo(App.getPath("regular.txt"));

        grafo.imprimeGrafo();

        List<Vertice> vertices = grafo.pegaTodosOsVerticesDoGrafo();

        System.out.println("1 e 2 são adjacentes: " + grafo.isAdjacente(new Vertice("1"), new Vertice("2")));
        System.out.println("1 e 3 são adjacentes: " + grafo.isAdjacente(new Vertice("1"), new Vertice("3")));
        System.out.println("1 e 4 são adjacentes: " + grafo.isAdjacente(new Vertice("1"), new Vertice("4")));

        System.out.println("Grau do Vértice 1: " + grafo.getGrau(new Vertice("1")));
        System.out.println("Grau do Vértice 2: " + grafo.getGrau(new Vertice("2")));
        System.out.println("Grau do Vértice 3: " + grafo.getGrau(new Vertice("3")));
        System.out.println("Grau do Vértice 4: " + grafo.getGrau(new Vertice("4")));

        for (Vertice vertice : vertices) {
            System.out.println("Vértice " + vertice.getIdentificador() + " é isolado: " + grafo.isIsolado(vertice));
            System.out.println("Vértice " + vertice.getIdentificador() + " é pendente: " + grafo.isPendente(vertice));
        }

        System.out.println("Grafo é nulo: " + grafo.isNulo());
        System.out.println("Grafo é regular: " + grafo.isRegular());
        System.out.println("Grafo é completo: " + grafo.isCompleto());
        System.out.println("Grafo é euleriano: " + grafo.isEuleriano());
        System.out.println("Grafo é unicursal: " + grafo.isUnicursal());
        System.out.println("Grafo é conexo: " + grafo.isConexo());

        Grafo complementar = grafo.getComplementar();

        complementar.imprimeGrafo();

        Grafo arvorePrim = grafo.getAGMPrim(new Vertice("1"));

        arvorePrim.imprimeGrafo();

        Grafo arvoreKruskal = grafo.getAGMKruskal();

        arvoreKruskal.imprimeGrafo();
    }

    private static void testeCiclico() {

        GrafoDirigido grafo = new GrafoDirigido(App.getPath("ciclico.txt"));

        System.out.println(grafo.hasCiclo());
    }

    private static void testeAciclico() {

        GrafoDirigido grafo = new GrafoDirigido(App.getPath("aciclico.txt"));

        System.out.println(grafo.hasCiclo());
    }

    public static void main(String[] args) {

        try {
            App.testesUnicursal();
        } catch (Exception e) {

        }
        try {
            App.testeCompleto();
        } catch (Exception e) {
        }

        try {
            App.testeNulo();
        } catch (Exception e) {
        }
        try {
            App.testePendente();
        } catch (Exception e) {

        }

        try {
            App.testeRegular();
        } catch (Exception e) {

        }

        App.testeAciclico();
        App.testeCiclico();
    }

}