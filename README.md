<h1 align="center"> Pix Challange </h1>


![Linguagem usada](https://img.shields.io/badge/JAVA:-17-005100?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-Spring-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-JPA-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-Actuator-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-Validation-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-Swagger-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-Lombok-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-H2-3c0064?style=for-the-badge)


## Descrição do projeto:

<p align="justify">
Aplicativo Web - CRUD demonstrando funcionalidade simulando cadastro de chave PIX e guardando a mesma numa base de dados. Sendo possível cadastrar, buscar, alterar e desativar a mesma.

## Como funciona:

Para atuação com a API é necessário fazer requisições PUT, GET ou POST.
</p>
Dentro do projeto existe uma coleção de chamadas para simplificar a utilização da mesma com nome do arquivo Pix.postman_collection.json 
</p>
ou 
</p>
uma vez inicializada existe documentação gerada através do Swagger-UI na url http://localhost:8080/swagger-ui/index.html#/ .

Abaixo segue listagem das possíveis operações.
~~~
/pix/{id}
/pix/inactive/{id}
/pix
/pix/findByKeyType/{keyType}
/pix/findByKeyInclusionDate/{inclusionDate}
/pix/findById/{id}
/pix/findByAgencyAndAccount/{agency}/{account}
/pix/findByAccountHolderName/{accountHolderName}
~~~
</p>


## Técnicas e tecnologias utilizadas:

- ``InteliJ IDEA;``
- ``Spring Boot;``
- ``Spring JPA;``
- ``Spring Actuator;``
- ``Spring Validation;``
- ``Swagger;``
- ``Lombok;``
- ``H2;``
- ``Gradle;``
