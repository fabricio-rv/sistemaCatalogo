package catalogo.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Dvd extends ItemColecionavel {
    private final TipoDvd tipoDvd;
    private final String descricao;

    public Dvd(String id, String nome, LocalDate dataAquisicao, List<String> autores,
               TipoDvd tipoDvd, String descricao) {
        super(id, nome, dataAquisicao, autores);
        this.tipoDvd = Objects.requireNonNull(tipoDvd, "Tipo do DVD obrigatorio");
        this.descricao = validarTexto(descricao, "Descricao");
    }

    public TipoDvd getTipoDvd() {
        return tipoDvd;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getTipo() {
        return "DVD";
    }

    @Override
    public String toString() {
        return super.toString() + " tipoDvd=" + tipoDvd + ", descricao='" + descricao + "'";
    }
}