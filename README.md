# ApiAttornatus

## Descrição

Api para gerenciamento de Pessoas:

  Seus **EndPoints** são:

    * Criar uma pessoa
    * Editar uma pessoa
    * Consultar uma pessoa
    * Listar pessoas
    * Criar endereço para pessoa
    * Listar endereços da pessoa
    * Poder informar qual endereço é o principal da pessoa  

## Projeto

Java : 19
Spring Boot : 3.0.2

## Dependências:
  
  * Spring Web
  * Spring Data JPA
  * Validation
  * H2 Database

## Premissas

A pessoa cadastrada pode ter vários endereços, mas somente **um** pode ser o PRINCIPAL. Quando alterar algum endereço SECUNDÁRIO
para PRINCIPAL, onde já existe um PRINCIPAL, ele automaticamente altera o antigo PRINCIPAL para SECUNDÁRIO e deixa apenas um como PRINCIPAL.

-------------------------------------

## Requests

Durante o projeto foi utilizado o postman para verificar as requisições. Segue abaixo a foto do Workspace criado:

 <img src="https://github.com/caioandre182/ApiAttornatus/blob/main/postman_api.jpg" width="600" height="400">

