# Catálogo de Itens Colecionáveis

Este projeto foi desenvolvido para a disciplina de Gerenciamento de Configuração de Software. A proposta foi continuar o catálogo da atividade anterior, mas desta vez organizando o desenvolvimento com Git Flow.

O trabalho foi feito individualmente. Mesmo assim, usei branches separadas para simular o fluxo que seria usado por uma equipe, com `main`, `develop`, features, Pull Requests e uma branch de release.

## O que o sistema faz

O programa permite cadastrar livros, CDs, DVDs e revistas. Todos os itens possuem identificação, nome, data de aquisição e autores, além dos dados próprios de cada tipo.

Também é possível:

- listar os itens por tipo;
- consultar um item pela identificação;
- pesquisar livros pela editora;
- pesquisar CDs pelo gênero ou por uma faixa;
- pesquisar DVDs pelo tipo ou pela descrição;
- pesquisar revistas pela editora ou por um assunto;
- salvar os dados em arquivo para recuperar depois.

## Estrutura

```text
src/
  catalogo/
    Main.java
    modelo/
    persistencia/
    servico/
test/
  catalogo/
.github/workflows/ci.yml
docs/
```

## Como testar

Usei Java 21. No PowerShell, os comandos para compilar e executar os testes são:

```powershell
Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force -Path out | Out-Null
$fontes = Get-ChildItem src,test -Recurse -Filter *.java | ForEach-Object FullName
javac -d out $fontes
java -cp out catalogo.TodosOsTestes
```

Se estiver tudo certo, o resultado será:

```text
Testes unitarios: OK
Teste de integracao: OK
Todos os testes passaram.
```

## Como executar

Depois de compilar:

```powershell
java -cp out catalogo.Main
```

Os dados ficam salvos em `dados/catalogo.dat` quando a opção `0` é escolhida. Ao iniciar o programa novamente, esse arquivo é carregado.

## Documentação

- [Organização das tarefas](docs/EQUIPE_E_TAREFAS.md)
- [Fluxo de trabalho utilizado](docs/GIT_FLOW.md)