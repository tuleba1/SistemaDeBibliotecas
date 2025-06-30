# 👤 Módulo Cliente

Este módulo representa a funcionalidade de cadastro e gerenciamento de **clientes** no sistema de biblioteca. Ele faz parte da branch `feature/cliente` e tem como foco o armazenamento e manipulação de dados dos usuários da biblioteca.

## ✅ Funcionalidades

- Cadastro de clientes
- Atualização de informações
- Remoção de clientes
- Consulta de dados
- Armazenamento dos clientes em **memória**, utilizando `ArrayList`

## 🧠 Estrutura da Classe

A classe `Cliente` possui os seguintes atributos:

- `String nome`
- `int idade`
- `String cpf`
- `int id`

## 💾 Armazenamento

Todos os clientes são armazenados temporariamente em uma lista do tipo `ArrayList<Cliente>`, permitindo simulações e testes em memória, sem necessidade de banco de dados.


## 🔧 Possíveis Melhorias
Persistência de Dados: Atualmente, os dados são armazenados apenas em memória. Em um ambiente de produção, seria necessário integrar com um banco de dados (SQL ou NoSQL) para persistir as informações dos clientes.
Interface de Usuário: Desenvolver uma interface gráfica (GUI) ou uma interface de linha de comando mais interativa para facilitar a interação do usuário.
Tratamento de Exceções: Adicionar um tratamento de erro mais robusto para cenários como IDs não encontrados ou dados inválidos.
Validações: Implementar validações para os campos do cliente (ex: formato de CPF, idade mínima).
