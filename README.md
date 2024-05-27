# MSs - Gestão de Pedidos

Composta por quatro módulos principais: Cliente, Produto e Estoque, Pedidos e Logística. Essa abordagem permite uma melhor modularização e escalabilidade, garantindo maior flexibilidade e eficiência operacional.

O **módulo Cliente** é responsável pelo gerenciamento de informações pessoais.

O **módulo Produto e Estoque** Ele controla o estoque, acompanha o fluxo de entrada e saída de produtos. E consta com um fluxo de importação massiva de produtos

O **módulo Pedidos** trata das solicitações e geração de pedidos.

Por fim, o **módulo Logística** este realiza a busca de informações para a entrega do pedido.

## Link do GitHub do projeto

https://github.com/FlaviaMonteiro31/GestaoPedidos


## Start do projeto 


1º - Ter instalado docker ou PostgreSQL na máquina (Nesse exemplo usarei o docker)

 2º - Executar o comando docker:

> docker-compose up -d 

## Tecnologias utilizadas

* Java 17 (Padrão Spring Initializr)

* Spring boot 3.1.0 (Padrão Spring Initializr)

* DevTools (Facilitar setup no ambiente de desenvolvimento dando Restart no servidor a cada modificação feita)

* Lombok (Facilitar criação de métodos acessores e construtores quando necessário)

* Spring Web (Para usar uma API REST)

* Open API (Habilitar Swagger) URL: **/swagger-ui/index.html*

* Spring Data JPA (Para implementar paginação)

* Bean Validation (Para fazer validações de campos na borda mais externa da API, as REQUESTS)

* Spring Batch (Para realizar a carga massiva de produtos)

* Spring Function (Para criar API para consumo)

* Spring Integration (Para integração de APIs baseado em mensagens)

* GIT (Controle de versão do projeto)

* IDE's (Eclipse, Intellij, VS Code)

* Insomnia (Testes da API)

* PostgreSQL(Persistir os dados)

* Docker (Para subir um container com mysql)

## Documentação do Swagger

MS_Clientes: [Swagger_Ms_Clientes.json](https://github.com/FlaviaMonteiro31/GestaoPedidos/blob/main/Ms_Clientes.json)
MS_Produto_Estoque: [Swagger_Ms_Produto_Estoque.json](https://github.com/FlaviaMonteiro31/GestaoPedidos/blob/main/Ms_Produto_Estoque.json)
MS_Pedidos: [Swagger_Ms_Pedidos.json](https://github.com/FlaviaMonteiro31/GestaoPedidos/blob/main/Ms_Pedido.json)
MS_Logistica: [Swagger_Ms_Logistica.json
](https://github.com/FlaviaMonteiro31/GestaoPedidos/blob/main/Ms_Logistica.json)

## Requisições HTTP/Insomnia

Para testes dos micro serviços foram utilizados as requisições do [link](https://github.com/FlaviaMonteiro31/GestaoPedidos/blob/main/MSs_Gest%C3%A3oDePedidos) no Insomnia. 
