package catalogo;

import catalogo.persistencia.ArquivoCatalogo;
import catalogo.servico.Catalogo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersistenciaTeste {
    public static void executar() throws IOException {
        Path pasta = Files.createTempDirectory("catalogo-teste-");
        Path arquivo = pasta.resolve("catalogo.dat");
        ArquivoCatalogo persistencia = new ArquivoCatalogo(arquivo);

        persistencia.salvar(CatalogoTeste.criarCatalogo());
        Catalogo carregado = persistencia.carregar();

        Teste.verdadeiro(Files.exists(arquivo));
        Teste.iguais(4, carregado.todos().size());
        Teste.iguais("Engenharia", carregado.buscarPorId("R1").orElseThrow().getNome());

        Files.deleteIfExists(arquivo);
        Files.deleteIfExists(pasta);
    }
}