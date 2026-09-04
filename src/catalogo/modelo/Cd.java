package catalogo.modelo;

import java.time.LocalDate;
import java.util.List;

public class Cd extends ItemColecionavel {
    private final String generoMusical;
    private final List<String> faixas;

    public Cd(String id, String nome, LocalDate dataAquisicao, List<String> autores,
              String generoMusical, List<String> faixas) {
        super(id, nome, dataAquisicao, autores);
        this.generoMusical = validarTexto(generoMusical, "Genero musical");
        this.faixas = List.copyOf(faixas);
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public List<String> getFaixas() {
        return faixas;
    }

    @Override
    public String getTipo() {
        return "CD";
    }

    @Override
    public String toString() {
        return super.toString() + " genero='" + generoMusical + "', faixas=" + faixas;
    }
}