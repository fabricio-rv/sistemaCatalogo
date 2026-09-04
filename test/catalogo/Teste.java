package catalogo;

public class Teste {
    public static void iguais(Object esperado, Object recebido) {
        if (!esperado.equals(recebido)) {
            throw new AssertionError("Esperado: " + esperado + ", recebido: " + recebido);
        }
    }

    public static void verdadeiro(boolean condicao) {
        if (!condicao) {
            throw new AssertionError("A condicao deveria ser verdadeira");
        }
    }

    public static void lancaExcecao(Class<? extends Throwable> tipo, Runnable acao) {
        try {
            acao.run();
        } catch (Throwable erro) {
            if (tipo.isInstance(erro)) {
                return;
            }
            throw new AssertionError("Excecao diferente da esperada", erro);
        }
        throw new AssertionError("A excecao esperada nao foi lancada");
    }
}