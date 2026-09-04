package catalogo.modelo;

import java.time.LocalDate;
import java.util.List;

public class Revista extends ItemColecionavel {
    private final int anoPublicacao;
    private final int volume;
    private final String editora;
    private final List<String> assuntos;

    public Revista(String id, String nome, LocalDate dataAquisicao, List<String> autores,
                   int anoPublicacao, int volume, String editora, List<String> assuntos) {
        super(id, nome, dataAquisicao, autores);
        if (anoPublicacao <= 0 || volume <= 0) {
            throw new IllegalArgumentException("Ano e volume devem ser positivos");
        }
        this.anoPublicacao = anoPublicacao;
        this.volume = volume;
        this.editora = validarTexto(editora, "Editora");
        this.assuntos = List.copyOf(assuntos);
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getVolume() {
        return volume;
    }

    public String getEditora() {
        return editora;
    }

    public List<String> getAssuntos() {
        return assuntos;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String toString() {
        return super.toString() + " anoPublicacao=" + anoPublicacao + ", volume=" + volume
                + ", editora='" + editora + "', assuntos=" + assuntos;
    }
}