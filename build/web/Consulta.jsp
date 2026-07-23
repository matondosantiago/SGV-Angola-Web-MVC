<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="Model.SolicitacaoVisto, java.util.List, Model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("login");
        return;
    }
    List<SolicitacaoVisto> solicitacoes = (List<SolicitacaoVisto>) request.getAttribute("solicitacoes");
    Integer quantidade = (Integer) request.getAttribute("quantidade");
%>
<!DOCTYPE html>
<html lang="pt">
<head>  
    <meta charset="UTF-8">
    <title>Consulta - SGV Angola</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/estilo.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Consulta de Pedidos</h1>
            <div class="user-info">
                <%= usuario.getNome() %> | <a href="logout">Sair</a>
            </div>
        </header>
        <nav>
            <ul>
                <li><a href="dashboard">Início</a></li>
                <li><a href="cadastrar">Novo Pedido</a></li>
                <li><a href="consultar">Consultar</a></li>
                <li><a href="estatisticas">Estatísticas</a></li>
            </ul>
        </nav>
        <main>
            <% if (request.getAttribute("erro") != null) { %>
                <div class="alert alert-danger"><%= request.getAttribute("erro") %></div>
            <% } %>
            
            <div class="search-box">
                <form action="consultar" method="get">
                    <input type="text" name="termo" placeholder="Buscar por ID ou Nome">
                    <button type="submit" class="btn-primary">BUSCAR</button>
                    <a href="consultar" class="btn-secondary">ATUALIZAR</a>
                </form>
            </div>
            
            <% if (quantidade != null && quantidade > 0) { %>
                <p><strong>Total: <%= quantidade %> registro(s)</strong></p>
                <table class="table-visto">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Passaporte</th>
                            <th>Tipo</th>
                            <th>Data</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (SolicitacaoVisto s : solicitacoes) { %>
                            <tr>
                                <td><%= s.getId() %></td>
                                <td><%= s.getNomeCompleto() %></td>
                                <td><%= s.getNumeroPassaporte() %></td>
                                <td><%= s.getTipoVisto() %></td>
                                <td><%= s.getDataSolicitacao() %></td>
                                <td>
                                    <a href="editar/<%= s.getId() %>" class="btn-edit">Editar</a>
                                    <a href="excluir?id=<%= s.getId() %>" class="btn-delete" 
                                       onclick="return confirm('Tem certeza?')">Excluir</a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <p>Nenhum registo encontrado.</p>
            <% } %>
        </main>
        <footer>
            <p>&copy; 2026 - SGV Angola</p>
        </footer>
    </div>
</body>
</html>