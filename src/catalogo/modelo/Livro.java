package catalogo.modelo;

import java.time.LocalDate;
import java.util.List;

public class Livro extends ItemColecionavel {
    private final String editora;
    private final int anoPublicacao;

    public Livro(String id, String nome, LocalDate dataAquisicao, List<String> autores,
                 String editora, int anoPublicacao) {
        super(id, nome, dataAquisicao, autores);
        this.editora = validarTexto(editora, "Editora");
        this.anoPublicacao = validarAno(anoPublicacao);
    }

    private int validarAno(int ano) {
        if (ano <= 0) {
            throw new IllegalArgumentException("Ano de publicacao invalido");
        }
        return ano;
    }

    public String getEditora() {
        return editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public String toString() {
        return super.toString() + " editora='" + editora + "', anoPublicacao=" + anoPublicacao;
    }
}