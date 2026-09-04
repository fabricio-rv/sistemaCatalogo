# Git Flow utilizado

Na atividade anterior, o desenvolvimento foi pensado com branches curtas próximas da `main`. Nesta continuação, organizei o mesmo projeto usando Git Flow.

A branch `main` representa a versão estável. A branch `develop` recebe o trabalho em desenvolvimento. Para cada etapa, criei uma feature a partir da `develop` e enviei a alteração por Pull Request.

## Início do repositório

Comecei publicando o código-base e criando a branch de desenvolvimento:

```powershell
git init
git branch -M main
git add .
git commit -m "importa codigo base da atividade anterior"
git remote add origin https://github.com/fabricio-rv/sistemaCatalogo.git
git push -u origin main
git checkout -b develop
git push -u origin develop
```

## Desenvolvimento das etapas

O processo usado para cada feature foi:

```powershell
git checkout develop
git pull origin develop
git checkout -b feature/nome-da-tarefa
```

Depois da alteração:

```powershell
git status
git add .
git commit -m "descreve a alteracao realizada"
git push -u origin feature/nome-da-tarefa
```

No GitHub, abri um Pull Request da feature para `develop`. Depois do merge, atualizei a branch local:

```powershell
git checkout develop
git pull origin develop
```

Fiz o fluxo manualmente, sem instalar a extensão `git-flow`, o que também era permitido pela atividade.

## Simulação de conflito

Para praticar a resolução de conflitos, criei duas branches a partir da mesma versão da `develop` e alterei a mesma linha do menu em cada uma. Depois de integrar a primeira branch, a segunda ficou em conflito.

Atualizei a segunda branch com:

```powershell
git fetch origin
git merge origin/develop
```

Resolvi manualmente os marcadores de conflito no arquivo, escolhi o texto final, executei os testes e registrei a resolução:

```powershell
git add src/catalogo/Main.java
git commit -m "resolve conflito no titulo do menu"
git push
```

## Release

Quando todas as features estiverem integradas e os testes passarem, a primeira versão será preparada assim:

```powershell
git checkout develop
git pull origin develop
git checkout -b release/1.0.0
git push -u origin release/1.0.0
```

Depois disso, será aberto um Pull Request de `release/1.0.0` para `main`. Após o merge, a versão será identificada com uma tag:

```powershell
git checkout main
git pull origin main
git tag -a v1.0.0 -m "Primeira versao do catalogo"
git push origin v1.0.0
git tag -a v1.0.0 -m "primeira versao do catalogo"
git push origin v1.0.0
```

Dessa forma, a `main` fica com a versão entregue e a `develop` mantém o histórico das etapas de desenvolvimento.