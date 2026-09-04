package catalogo.servico;

import catalogo.persistencia.PersistenciaCatalogo;

import java.io.IOException;

public class SessaoCatalogo {
    private final PersistenciaCatalogo persistencia;
    private Catalogo catalogo;

    public SessaoCatalogo(PersistenciaCatalogo persistencia) {
        this.persistencia = persistencia;
        this.catalogo = new Catalogo();
    }

    public void iniciar() throws IOException {
        catalogo = persistencia.carregar();
    }

    public void encerrar() throws IOException {
        persistencia.salvar(catalogo);
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }
}