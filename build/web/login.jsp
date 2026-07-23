<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - SGV Angola</title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/estilo.css">
</head>
<body>
    <div class="login-container">
        <div class="login-card">
            <h1>SGV - ANGOLA</h1>
            <h2>Sistema de Gestão de Vistos</h2>
            
            <% if (request.getAttribute("erro") != null) { %>
                <div class="alert alert-danger"><%= request.getAttribute("erro") %></div>
            <% } %>
            
            <form action="login" method="post">
                <div class="form-group">
                    <label for="username">Utilizador</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Palavra-passe</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="btn-primary">ACESSAR SISTEMA</button>
            </form>
        </div>
    </div>
</body>
</html>