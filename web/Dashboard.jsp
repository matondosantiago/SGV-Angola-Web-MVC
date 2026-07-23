<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - SGV Angola</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/estilo.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Sistema de Gestão de Vistos - Angola</h1>
            <div class="user-info">
                Bem-vindo, <%= usuario.getNome() %> | <a href="logout">Sair</a>
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
        <main class="app-background">
            <div class="home-content">
                <img src="<%= request.getContextPath() %>/img/imagemBrasao.jpg" alt="Brasão de Angola" class="brasao-logo">
                <h2>República de Angola</h2>
                <h3>Ministério das Relações Exteriores</h3>
                <p>Sistema de Gestão de Pedidos de Visto</p>
            </div>
        </main>
        <footer>
            <p>&copy; 2026 - SGV Angola</p>
        </footer>
    </div>
</body>
</html>