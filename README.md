# ZG-Hero Project: To-Do List (Java Backend) ☕

> Trilha K1-T3: Java | Acelera ZG

Este projeto consiste na implementação de um **MVP (Minimum Viable Product)** de uma aplicação de lista de tarefas (To-Do List) rodando via terminal. O foco principal é o desenvolvimento da lógica de backend utilizando **Java 17**, sem o uso de frameworks externos, aplicando conceitos de Orientação a Objetos e manipulação de estruturas de dados.

## 📋 Sobre o Projeto

O objetivo é criar um sistema robusto para gerenciamento de tarefas que, futuramente, poderá ser integrado a um Frontend. A aplicação funciona através de um menu interativo no console, permitindo a criação, leitura e remoção de tarefas, com um algoritmo inteligente de priorização.

### 🚀 Funcionalidades Principais (MVP)

* **Gerenciamento de Tarefas (CRD):**
    * **Criar:** Adicionar novas tarefas com Nome, Descrição, Data de Término, Prioridade (1-5), Categoria e Status.
    * **Listar:** Visualizar tarefas filtradas por Categoria, Prioridade ou Status.
    * **Deletar:** Remover tarefas concluídas ou canceladas.
* **Ordenação Inteligente:**
    * Implementação de rebalanceamento automático: ao inserir uma nova tarefa, ela é posicionada automaticamente na lista baseada em seu nível de prioridade (5 sendo a mais urgente).
* **Menu Interativo:** Interface via terminal para navegação entre as opções.
---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17 (LTS)
* **Paradigma:** Orientação a Objetos (POO).
* **Armazenamento:** Em memória (Listas/Arrays).
* **IDE:** IntelliJ IDEA.

---

## ▶️ Como Executar o Projeto

### Pré-requisitos
* Ter o [Java JDK 17](https://www.oracle.com/java/technologies/downloads/#java17) instalado.

### Passo a passo

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/ViniciusMoraisAraujo/TODO-java.git
    ```
2.  **Acesse a pasta:**
    ```bash
    cd ./TODO/src/
    ```
3.  **Compile e execute:**
    ```bash
    javac -d bin src/*.java
    java -cp bin Main
    ```


Desenvolvido durante o programa **Acelera ZG**. 🚀