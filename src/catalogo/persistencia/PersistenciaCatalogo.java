package catalogo.persistencia;

import catalogo.servico.Catalogo;

import java.io.IOException;

public interface PersistenciaCatalogo {
    void salvar(Catalogo catalogo) throws IOException;

    Catalogo carregar() throws IOException;
}