package catalogo.persistencia;

import catalogo.modelo.ItemColecionavel;
import catalogo.servico.Catalogo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ArquivoCatalogo implements PersistenciaCatalogo {
    private final Path arquivo;

    public ArquivoCatalogo(Path arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public void salvar(Catalogo catalogo) throws IOException {
        Path pasta = arquivo.getParent();
        if (pasta != null) {
            Files.createDirectories(pasta);
        }
        try (ObjectOutputStream saida = new ObjectOutputStream(Files.newOutputStream(arquivo))) {
            saida.writeObject(catalogo.todos());
        }
    }

    @Override
    public Catalogo carregar() throws IOException {
        if (Files.notExists(arquivo)) {
            return new Catalogo();
        }
        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(arquivo))) {
            Object dados = entrada.readObject();
            if (!(dados instanceof List<?> lista)) {
                throw new IOException("Arquivo de catalogo invalido");
            }
            List<ItemColecionavel> itens = lista.stream()
                    .map(ItemColecionavel.class::cast)
                    .toList();
            return new Catalogo(itens);
        } catch (ClassNotFoundException | ClassCastException erro) {
            throw new IOException("Nao foi possivel ler o catalogo", erro);
        }
    }
}