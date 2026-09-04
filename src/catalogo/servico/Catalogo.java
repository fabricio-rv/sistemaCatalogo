package catalogo.servico;

import catalogo.modelo.Cd;
import catalogo.modelo.Dvd;
import catalogo.modelo.ItemColecionavel;
import catalogo.modelo.Livro;
import catalogo.modelo.Revista;
import catalogo.modelo.TipoDvd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

public class Catalogo {
    private final List<ItemColecionavel> itens;

    public Catalogo() {
        this(new ArrayList<>());
    }

    public Catalogo(List<ItemColecionavel> itens) {
        this.itens = new ArrayList<>(itens);
    }

    public void cadastrar(ItemColecionavel item) {
        if (buscarPorId(item.getId()).isPresent()) {
            throw new IllegalArgumentException("Ja existe um item com a identificacao " + item.getId());
        }
        itens.add(item);
    }

    public Optional<ItemColecionavel> buscarPorId(String id) {
        return itens.stream().filter(item -> item.getId().equalsIgnoreCase(id)).findFirst();
    }

    public List<Livro> listarLivros() {
        return listarPorTipo(Livro.class);
    }

    public List<Cd> listarCds() {
        return listarPorTipo(Cd.class);
    }

    public List<Dvd> listarDvds() {
        return listarPorTipo(Dvd.class);
    }

    public List<Revista> listarRevistas() {
        return listarPorTipo(Revista.class);
    }

    public List<Livro> pesquisarLivrosPorEditora(String editora) {
        return filtrar(listarLivros(), livro -> contem(livro.getEditora(), editora));
    }

    public List<Cd> pesquisarCdsPorGenero(String genero) {
        return filtrar(listarCds(), cd -> contem(cd.getGeneroMusical(), genero));
    }

    public List<Cd> pesquisarCdsPorFaixa(String faixa) {
        return filtrar(listarCds(), cd -> cd.getFaixas().stream().anyMatch(nome -> contem(nome, faixa)));
    }

    public List<Dvd> pesquisarDvdsPorTipo(TipoDvd tipo) {
        return filtrar(listarDvds(), dvd -> dvd.getTipoDvd() == tipo);
    }

    public List<Dvd> pesquisarDvdsPorDescricao(String texto) {
        return filtrar(listarDvds(), dvd -> contem(dvd.getDescricao(), texto));
    }

    public List<Revista> pesquisarRevistasPorAssunto(String assunto) {
        return filtrar(listarRevistas(), revista -> revista.getAssuntos().stream()
                .anyMatch(item -> contem(item, assunto)));
    }

    public List<Revista> pesquisarRevistasPorEditora(String editora) {
        return filtrar(listarRevistas(), revista -> contem(revista.getEditora(), editora));
    }

    public List<ItemColecionavel> todos() {
        return List.copyOf(itens);
    }

    private <T extends ItemColecionavel> List<T> listarPorTipo(Class<T> tipo) {
        return itens.stream().filter(tipo::isInstance).map(tipo::cast).toList();
    }

    private <T> List<T> filtrar(List<T> origem, Predicate<T> filtro) {
        return origem.stream().filter(filtro).toList();
    }

    private boolean contem(String texto, String busca) {
        return texto.toLowerCase(Locale.ROOT).contains(busca.toLowerCase(Locale.ROOT));
    }
}