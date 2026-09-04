# Organização das tarefas

O trabalho foi realizado individualmente.

| Aluno | Matrícula | Responsabilidades |
| Fabricio Rassier | 26180324 | Modelagem, implementação, testes, persistência, documentação e Git Flow |

Para não colocar todas as alterações diretamente nas branches principais, dividi o trabalho em etapas menores. O código-base da atividade anterior ficou na `main`, e a continuação foi organizada a partir da `develop`.

## Divisão das etapas

| Branch | Entrega | Origem e destino |
|---|---|---|
| `feature/testes-catalogo` | testes unitários, dublê e teste de integração | `develop` para `develop` |
| `feature/documentacao` | README e registro do trabalho | `develop` para `develop` |
| `feature/integracao-continua` | execução automática dos testes | `develop` para `develop` |
| branches de alteração do menu | simulação e resolução de conflito | `develop` para `develop` |
| `release/1.0.0` | preparação da primeira versão | `develop` para `main` |

Antes de enviar cada branch, compilei o projeto e executei os testes. As alterações foram integradas por Pull Request para deixar registrado no GitHub como o código avançou em cada etapa.

## Evidências do trabalho

- histórico de commits separado por etapa;
- branches `main` e `develop`;
- branches de feature;
- Pull Requests para integração das alterações;
- conflito criado e resolvido em branches diferentes;
- execução dos testes no GitHub Actions;
- branch de release e tag `v1.0.0`.