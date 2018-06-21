# BackEnd

O projeto foi gerado utilizando o [Spring-boot](https://spring.io/projects/spring-boot)

## Ambiente

É necessário que tenha o [Maven](https://maven.apache.org/) na máquina. 

## Instalando dependências
Para instalar as dependências entrar na raiz do projeto (backEnd) e executar: `mvn install`

## Servidor de desenvolvimento

Na pasta raiz do projeto (backEnd) executar: `mvn spring-boot:run`

Este comando irá iniciar um servidor Tomcat na porta 8080

## Configuração banco de dados

Foi utilizado o banco de dados Mysql
Alterar o arquivo `backEnd/src/main/resources/application.properties` inserindo os dados de acesso ao banco de dados
