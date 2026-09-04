package catalogo;

public class TodosOsTestes {
    public static void main(String[] args) throws Exception {
        CatalogoTeste.executar();
        System.out.println("Testes unitarios: OK");
        PersistenciaTeste.executar();
        System.out.println("Teste de integracao: OK");
        System.out.println("Todos os testes passaram.");
    }
}