# Sistema de Controle de Estoque – Backend

## Universidade do Sul de Santa Catarina – UNISUL  
Unidade Curricular: Sistemas Distribuídos e Mobile – Turma A  
Professor: Osmar de Oliveira Braz Júnior  

---

## Descrição do Projeto

Este repositório contém o **backend** do Sistema de Controle de Estoque distribuído, desenvolvido em **Java** utilizando **RMI (Remote Method Invocation)** para a comunicação entre cliente e servidor.  
O servidor é responsável por fornecer os serviços de cadastro, atualização, exclusão, consulta e geração de relatórios relacionados aos produtos, categorias e movimentações do estoque.

---

## Tecnologias Utilizadas

- Java  
- NetBeans  
- RMI (Remote Method Invocation)  
- MySQL  
- Git e GitHub  

---

## Arquitetura do Sistema

O sistema é composto por duas camadas principais:

- **Servidor (Backend):** fornece os serviços e realiza o processamento das informações, além de se comunicar com o banco de dados MySQL.  
- **Cliente (Frontend):** consome os serviços do servidor via RMI e exibe as informações ao usuário.

---

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de produtos  
- Cadastro, consulta, atualização e exclusão de categorias  
- Movimentações de entrada e saída de produtos  
- Relatórios:
  - Lista de preços  
  - Balanço físico e financeiro  
  - Produtos abaixo da quantidade mínima  
  - Quantidade de produtos por categoria  
  - Produtos com maior entrada e saída  

---

## Integrantes da Equipe

| Nome completo | RA | Usuário GitHub |
|----------------|----------------|----------------|
| Roger Porton Kuntze | 10725118527 | rogerpk |
| Bernardo Mendonça Santiago de Lima | 10725116225 | bnsant |
| Gustavo Abrahão de Melo Carvalho | 1072511496 | gustavoabrahao |
| Kayky de Souza Lautert | 1072511581 | Lautert7 |
