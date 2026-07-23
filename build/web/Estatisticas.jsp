<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map, Model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("login");
        return;
    }
    Map<String, Integer> estatisticas = (Map<String, Integer>) request.getAttribute("estatisticas");
    Integer total = (Integer) request.getAttribute("total");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Estatísticas - SGV Angola</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/estilo.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Estatísticas de Pedidos</h1>
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
            <h2>Quantidade de Solicitações por Tipo de Visto</h2>
            
            <% if (request.getAttribute("erro") != null) { %>
                <div class="alert alert-danger"><%= request.getAttribute("erro") %></div>
            <% } %>
            
            <% if (estatisticas != null && !estatisticas.isEmpty()) { %>
                <table class="table-stats">
                    <thead>
                        <tr>
                            <th>Tipo de Visto</th>
                            <th>Quantidade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Map.Entry<String, Integer> entry : estatisticas.entrySet()) { %>
                            <tr>
                                <td><%= entry.getKey() %></td>
                                <td><%= entry.getValue() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th><strong>Total</strong></th>
                            <th><strong><%= total %></strong></th>
                        </tr>
                    </tfoot>
                </table>
            <% } else { %>
                <p>Nenhum pedido registado.</p>
            <% } %>
        </main>
        <footer>
            <p>&copy; 2026 - SGV Angola</p>
        </footer>
    </div>
</body>
</html>