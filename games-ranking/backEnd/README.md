# BackEnd

O projeto foi gerado utilizando o [Spring-boot](https://spring.io/projects/spring-boot)

## Ambiente

É necessário que tenha o [Maven](https://maven.apache.org/) na máquina. 

## Instalando dependências
Para instalar as dependências entrar na raiz do projeto (backEnd) e executar: `mvn install`

## Servidor de desenvolvimento

Na pasta raiz do projeto (backEnd) executar: `mvn spring-boot:run`

OBS: antes de executar criar banco de dados.

Este comando irá iniciar um servidor Tomcat na porta 8080

## Configuração banco de dados

Alterar o arquivo `backEnd/src/main/resources/application.properties` inserindo os dados de acesso ao banco de dados de um usuário com permissões administrativas.

Foi utilizado o banco de dados Mysql

Criar banco de dados: acessar o console do Mysql, e executar: `create database games_ranking;`
