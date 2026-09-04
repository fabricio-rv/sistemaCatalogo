package catalogo.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public abstract class ItemColecionavel implements Serializable {
    private final String id;
    private final String nome;
    private final LocalDate dataAquisicao;
    private final List<String> autores;

    protected ItemColecionavel(String id, String nome, LocalDate dataAquisicao, List<String> autores) {
        this.id = validarTexto(id, "Identificacao");
        this.nome = validarTexto(nome, "Nome");
        this.dataAquisicao = Objects.requireNonNull(dataAquisicao, "Data de aquisicao obrigatoria");
        this.autores = List.copyOf(Objects.requireNonNull(autores, "Lista de autores obrigatoria"));
    }

    protected static String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " obrigatorio");
        }
        return valor.trim();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public List<String> getAutores() {
        return autores;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "%s{id='%s', nome='%s', dataAquisicao=%s, autores=%s}"
                .formatted(getTipo(), id, nome, dataAquisicao, autores);
    }
}