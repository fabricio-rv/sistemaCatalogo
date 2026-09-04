package catalogo;

import catalogo.modelo.Cd;
import catalogo.modelo.Dvd;
import catalogo.modelo.ItemColecionavel;
import catalogo.modelo.Livro;
import catalogo.modelo.Revista;
import catalogo.modelo.TipoDvd;
import catalogo.persistencia.ArquivoCatalogo;
import catalogo.servico.Catalogo;
import catalogo.servico.SessaoCatalogo;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner entrada = new Scanner(System.in);
    private final SessaoCatalogo sessao = new SessaoCatalogo(
            new ArquivoCatalogo(Path.of("dados", "catalogo.dat")));

    public static void main(String[] args) {
        new Main().executar();
    }

    private void executar() {
        try {
            sessao.iniciar();
            int opcao;
            do {
                mostrarMenu();
                opcao = lerInteiro("Opcao: ");
                executarOpcao(opcao);
            } while (opcao != 0);
            sessao.encerrar();
            System.out.println("Catalogo salvo.");
        } catch (IOException erro) {
            System.out.println("Erro ao acessar os dados: " + erro.getMessage());
        }
    }

    private void mostrarMenu() {
        System.out.println("\nCATALOGO DE COLECIONAVEIS");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Cadastrar CD");
        System.out.println("3 - Cadastrar DVD");
        System.out.println("4 - Cadastrar revista");
        System.out.println("5 - Listar por tipo");
        System.out.println("6 - Consultar por identificacao");
        System.out.println("7 - Pesquisar dados especificos");
        System.out.println("0 - Salvar e sair");
    }

    private void executarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarCd();
                case 3 -> cadastrarDvd();
                case 4 -> cadastrarRevista();
                case 5 -> listarPorTipo();
                case 6 -> consultarPorId();
                case 7 -> pesquisar();
                case 0 -> { }
                default -> System.out.println("Opcao invalida.");
            }
        } catch (IllegalArgumentException erro) {
            System.out.println("Nao foi possivel concluir: " + erro.getMessage());
        }
    }

    private void cadastrarLivro() {
        DadosComuns dados = lerDadosComuns();
        String editora = lerTexto("Editora: ");
        int ano = lerInteiro("Ano de publicacao: ");
        catalogo().cadastrar(new Livro(dados.id(), dados.nome(), dados.dataAquisicao(),
                dados.autores(), editora, ano));
        System.out.println("Livro cadastrado.");
    }

    private void cadastrarCd() {
        DadosComuns dados = lerDadosComuns();
        String genero = lerTexto("Genero musical: ");
        List<String> faixas = lerLista("Faixas separadas por virgula: ");
        catalogo().cadastrar(new Cd(dados.id(), dados.nome(), dados.dataAquisicao(),
                dados.autores(), genero, faixas));
        System.out.println("CD cadastrado.");
    }

    private void cadastrarDvd() {
        DadosComuns dados = lerDadosComuns();
        TipoDvd tipo = lerTipoDvd();
        String descricao = lerTexto("Descricao: ");
        catalogo().cadastrar(new Dvd(dados.id(), dados.nome(), dados.dataAquisicao(),
                dados.autores(), tipo, descricao));
        System.out.println("DVD cadastrado.");
    }

    private void cadastrarRevista() {
        DadosComuns dados = lerDadosComuns();
        int ano = lerInteiro("Ano de publicacao: ");
        int volume = lerInteiro("Volume: ");
        String editora = lerTexto("Editora: ");
        List<String> assuntos = lerLista("Assuntos separados por virgula: ");
        catalogo().cadastrar(new Revista(dados.id(), dados.nome(), dados.dataAquisicao(),
                dados.autores(), ano, volume, editora, assuntos));
        System.out.println("Revista cadastrada.");
    }

    private DadosComuns lerDadosComuns() {
        String id = lerTexto("Identificacao: ");
        String nome = lerTexto("Nome: ");
        LocalDate data = lerData("Data de aquisicao (AAAA-MM-DD): ");
        List<String> autores = lerLista("Autores separados por virgula: ");
        return new DadosComuns(id, nome, data, autores);
    }

    private void listarPorTipo() {
        System.out.println("1 - Livros | 2 - CDs | 3 - DVDs | 4 - Revistas");
        List<? extends ItemColecionavel> itens = switch (lerInteiro("Tipo: ")) {
            case 1 -> catalogo().listarLivros();
            case 2 -> catalogo().listarCds();
            case 3 -> catalogo().listarDvds();
            case 4 -> catalogo().listarRevistas();
            default -> List.of();
        };
        imprimir(itens);
    }

    private void consultarPorId() {
        String id = lerTexto("Identificacao: ");
        catalogo().buscarPorId(id)
                .ifPresentOrElse(System.out::println, () -> System.out.println("Item nao encontrado."));
    }

    private void pesquisar() {
        System.out.println("1 - Livro por editora");
        System.out.println("2 - CD por genero");
        System.out.println("3 - CD por faixa");
        System.out.println("4 - DVD por tipo");
        System.out.println("5 - DVD por descricao");
        System.out.println("6 - Revista por assunto");
        System.out.println("7 - Revista por editora");
        int opcao = lerInteiro("Pesquisa: ");
        List<? extends ItemColecionavel> resultado = switch (opcao) {
            case 1 -> catalogo().pesquisarLivrosPorEditora(lerTexto("Editora: "));
            case 2 -> catalogo().pesquisarCdsPorGenero(lerTexto("Genero: "));
            case 3 -> catalogo().pesquisarCdsPorFaixa(lerTexto("Faixa: "));
            case 4 -> catalogo().pesquisarDvdsPorTipo(lerTipoDvd());
            case 5 -> catalogo().pesquisarDvdsPorDescricao(lerTexto("Descricao: "));
            case 6 -> catalogo().pesquisarRevistasPorAssunto(lerTexto("Assunto: "));
            case 7 -> catalogo().pesquisarRevistasPorEditora(lerTexto("Editora: "));
            default -> List.of();
        };
        imprimir(resultado);
    }

    private void imprimir(List<? extends ItemColecionavel> itens) {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item encontrado.");
            return;
        }
        itens.forEach(System.out::println);
    }

    private TipoDvd lerTipoDvd() {
        return TipoDvd.valueOf(lerTexto("Tipo (MUSICAL, FILME ou DADOS): ").toUpperCase());
    }

    private LocalDate lerData(String mensagem) {
        while (true) {
            try {
                return LocalDate.parse(lerTexto(mensagem));
            } catch (DateTimeParseException erro) {
                System.out.println("Data invalida.");
            }
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException erro) {
                System.out.println("Digite um numero inteiro.");
            }
        }
    }

    private List<String> lerLista(String mensagem) {
        return Arrays.stream(lerTexto(mensagem).split(","))
                .map(String::trim)
                .filter(valor -> !valor.isEmpty())
                .toList();
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine().trim();
    }

    private Catalogo catalogo() {
        return sessao.getCatalogo();
    }

    private record DadosComuns(String id, String nome, LocalDate dataAquisicao, List<String> autores) { }
}