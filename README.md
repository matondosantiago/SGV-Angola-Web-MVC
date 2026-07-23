# SGV Angola - Sistema de Gestão de Vistos (Versão Web)

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-Servlets%20%2F%20JSP-blue.svg)](https://jakarta.ee/)
[![Database](https://img.shields.io/badge/Database-MySQL%20%2F%20PostgreSQL-blue.svg)](#-configuração-da-base-de-dados)
[![Architecture](https://img.shields.io/badge/Architecture-MVC%20%2B%20DAO-green.svg)](#-arquitetura-do-sistema)

O **SGV Angola** é uma aplicação web desenvolvida para simular e automatizar o processo de submissão, consulta e acompanhamento estatístico de pedidos de visto do Ministério das Relações Exteriores de Angola.

## 🔗 Outras Implementações
Este projeto é a evolução em arquitetura web do sistema original. Se pretendes visualizar a versão Desktop com persistência de baixo nível em ficheiros de acesso aleatório, aceda a:
* 🖥️ **Versão Desktop (JavaFX + RandomAccessFile):** [SGV-Angola-Desktop-JavaFX](https://github.com/matondosantiago/SGV-Angola-Desktop-JavaFX)

## 🎯 Funcionalidades Principais

* **Autenticação e Sessão:** Controlo de acessos via Login e Gestão de Sessões (`HttpSession`).
* **Registo de Solicitações:** Formulário dinâmico para registo de novos pedidos de visto com validações completas.
* **Consulta de Pedidos:** Listagem e pesquisa de vistos registados com suporte a caracteres UTF-8.
* **Painel Estatístico:** Gráficos/Tabelas com contagem dinâmica por tipo de visto ordenada de forma descendente.

## 🏗️ Arquitetura do Sistema

O projeto adota o padrão arquitetural **MVC (Model-View-Controller)** combinado com o padrão **DAO (Data Access Object)** para garantir uma separação clara de responsabilidades:

* **Model (`Model/`, `DAO/`):** Classes de domínio (`SolicitacaoVisto`, `Usuario`) e gestão da camada de dados (`SolicitacaoVistoDAO`).
* **View (`web/`):** Páginas dinâmicas construídas em **JSP (JavaServer Pages)** com estilização CSS customizada.
* **Controller (`Controller/`):** **Servlets** Java encarregados de processar as requisições HTTP (`doGet`/`doPost`) e gerir os desvios de navegação.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Tecnologias Web:** Servlets (Jakarta EE), JSP, HTML5, CSS3, JavaScript
* **Servidor Web:** Apache Tomcat 9/10
* **Base de Dados:** MySQL 8.0
* **IDE Recomendada:** Apache NetBeans IDE 17+ / IntelliJ IDEA

## ⚙️ Configuração da Base de Dados

1. Certifica-te de que o teu servidor de base de dados (MySQL) está em execução.
2. Cria a base de dados chamada `visto_angola`.
3. Executa o script SQL para criar as tabelas e inserir o utilizador administrador padrão:

```sql
-- Tabela de Utilizadores
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Solicitações de Visto
CREATE TABLE solicitacoes_visto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    tipo_visto VARCHAR(50) NOT NULL,
    nome_completo VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    naturalidade VARCHAR(100) NOT NULL,
    nacionalidade_atual VARCHAR(50) NOT NULL,
    estado_civil VARCHAR(20) NOT NULL,
    numero_passaporte VARCHAR(30) NOT NULL UNIQUE,
    emitido_em VARCHAR(50) NOT NULL,
    emitido_aos DATE NOT NULL,
    validade_ate DATE NOT NULL,
    residencia_atual VARCHAR(200) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    motivo_viagem VARCHAR(100) NOT NULL,
    endereco_angola VARCHAR(200) NOT NULL,
    data_solicitacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_solicitacoes_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Inserir utilizador administrador padrão
INSERT INTO usuarios (username, password, nome, email)
VALUES ('admin', '123456', 'Administrador', 'admin@vistoangola.com');

```

## 🚀 Como Executar o Projeto

1. **Clonar o Repositório:**
```bash
git clone [https://github.com/matondosantiago/SGV-Angola-Web-MVC.git](https://github.com/matondosantiago/SGV-Angola-Web-MVC.git)

```

2. **Configurar a Conexão JDBC:**
* Abre o ficheiro `src/java/Util/DatabaseConnection.java`.
* Ajusta as variáveis `URL`, `USER` e `PASSWORD` de acordo com o teu ambiente local.


3. **Deploy no Servidor:**
* Abre o projeto na tua IDE (NetBeans/IntelliJ).
* Associa o servidor **Apache Tomcat**.
* Executa o projeto (`Run Project` ou `Shift + F6`).

4. **Aceder no Navegador:**
* Abre `http://localhost:8080/VistoAngolaMVC/login`
* **Credenciais de Acesso:**
* **Utilizador:** `admin`
* **Palavra-passe:** `123456`

---

## 👤 Autor

Desenvolvido por **Matondo Santiago**.

* 🐙 **GitHub:** [@matondosantiago](https://www.google.com/search?q=https://github.com/matondosantiago)
* 📧 **Contacto:** matondosantiago@gmail.com

```

```
