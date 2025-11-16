# Sistema de Controle de Estoque – Backend

## Universidade do Sul de Santa Catarina – UNISUL  
Unidade Curricular: Sistemas Distribuídos e Mobile – Turma A  
Professor: Osmar de Oliveira Braz Júnior  

---

## Descrição do Projeto
Backend do Sistema de Controle de Estoque distribuído, desenvolvido em **Java** com **RMI**, responsável por gerenciar produtos, categorias e movimentações no estoque, além de gerar relatórios.

🔗 **Frontend do Projeto:** https://github.com/bnsant/Sistemas_Distribuidos_Frontend_A3

---

## Tecnologias Utilizadas
- Java  
- NetBeans  
- RMI  
- MySQL  
- Git/GitHub  

---

## Funcionalidades
- CRUD de produtos  
- CRUD de categorias  
- Registro de entradas e saídas  
- Relatórios:
  - Lista de preços  
  - Balanço físico/financeiro  
  - Produtos abaixo do mínimo  
  - Produtos por categoria  
  - Produtos com maior movimentação  

---

## Requisitos Funcionais
- RF01: Permitir cadastrar, consultar, atualizar e excluir produtos.  
- RF02: Permitir cadastrar, consultar, atualizar e excluir categorias.  
- RF03: Registrar movimentações de entrada e saída de produtos.  
- RF04: Gerar relatórios diversos sobre o estoque.  
- RF05: Realizar comunicação entre cliente e servidor via RMI.  
- RF06: Validar dados antes de inserir ou atualizar no sistema.  

---

## Requisitos Não Funcionais
- RNF01: O sistema deve utilizar arquitetura distribuída baseada em RMI.  
- RNF02: O servidor deve garantir disponibilidade para múltiplas requisições.  
- RNF03: O banco de dados deve manter integridade das informações.  
- RNF04: O código deve ser organizado em camadas (DAO, modelo, servidor).  
- RNF05: A comunicação deve ser segura e estável durante o uso.  
- RNF06: As operações devem possuir tempo de resposta adequado sem travamentos.  

---

## Arquitetura do Sistema
- **Servidor:** processa dados, conecta ao MySQL e expõe serviços via RMI.  
- **Cliente:** consome os serviços e apresenta informações ao usuário.  

---

## Equipe
| Nome completo | RA | GitHub |
|----------------|----------------|----------------|
| Roger Porton Kuntze | 10725118527 | rogerpk |
| Bernardo Mendonça Santiago de Lima | 10725116225 | bnsant |
| Gustavo Abrahão de Melo Carvalho | 1072511496 | gustavoabrahao |
| Kayky de Souza Lautert | 1072511581 | Lautert7 |
