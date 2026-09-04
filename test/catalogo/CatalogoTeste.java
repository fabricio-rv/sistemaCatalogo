package catalogo;

import catalogo.modelo.Cd;
import catalogo.modelo.Dvd;
import catalogo.modelo.Livro;
import catalogo.modelo.Revista;
import catalogo.modelo.TipoDvd;
import catalogo.persistencia.PersistenciaCatalogo;
import catalogo.servico.Catalogo;
import catalogo.servico.SessaoCatalogo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CatalogoTeste {
    public static void executar() throws IOException {
        Catalogo catalogo = criarCatalogo();

        Teste.iguais(4, catalogo.todos().size());
        Teste.iguais("Livro Limpo", catalogo.buscarPorId("L1").orElseThrow().getNome());
        Teste.iguais(1, catalogo.listarLivros().size());
        Teste.iguais(1, catalogo.listarCds().size());
        Teste.iguais(1, catalogo.listarDvds().size());
        Teste.iguais(1, catalogo.listarRevistas().size());
        Teste.iguais(1, catalogo.pesquisarLivrosPorEditora("casa").size());
        Teste.iguais(1, catalogo.pesquisarCdsPorGenero("rock").size());
        Teste.iguais(1, catalogo.pesquisarCdsPorFaixa("segunda").size());
        Teste.iguais(1, catalogo.pesquisarDvdsPorTipo(TipoDvd.FILME).size());
        Teste.iguais(1, catalogo.pesquisarDvdsPorDescricao("extras").size());
        Teste.iguais(1, catalogo.pesquisarRevistasPorAssunto("orientada").size());
        Teste.iguais(1, catalogo.pesquisarRevistasPorEditora("tech").size());
        Teste.lancaExcecao(IllegalArgumentException.class,
                () -> catalogo.cadastrar(new Livro("L1", "Outro", LocalDate.now(),
                        List.of("Autor"), "Editora", 2020)));

        PersistenciaFalsa persistencia = new PersistenciaFalsa(catalogo);
        SessaoCatalogo sessao = new SessaoCatalogo(persistencia);
        sessao.iniciar();
        sessao.encerrar();
        Teste.verdadeiro(persistencia.salvou);
        Teste.iguais(4, sessao.getCatalogo().todos().size());
    }

    public static Catalogo criarCatalogo() {
        Catalogo catalogo = new Catalogo();
        LocalDate data = LocalDate.of(2024, 9, 4);
        catalogo.cadastrar(new Livro("L1", "Livro Limpo", data, List.of("Ana"),
                "Casa do Codigo", 2022));
        catalogo.cadastrar(new Cd("C1", "Album", data, List.of("Banda"),
                "Rock", List.of("Primeira", "Segunda")));
        catalogo.cadastrar(new Dvd("D1", "Curso", data, List.of("Equipe"),
                TipoDvd.FILME, "Filme com extras"));
        catalogo.cadastrar(new Revista("R1", "Engenharia", data, List.of("Carlos"),
                2024, 10, "Tech Editora", List.of("Programacao orientada a objetos", "Git")));
        return catalogo;
    }

    private static class PersistenciaFalsa implements PersistenciaCatalogo {
        private final Catalogo catalogo;
        private boolean salvou;

        private PersistenciaFalsa(Catalogo catalogo) {
            this.catalogo = catalogo;
        }

        @Override
        public void salvar(Catalogo catalogo) {
            salvou = true;
        }

        @Override
        public Catalogo carregar() {
            return catalogo;
        }
    }
}