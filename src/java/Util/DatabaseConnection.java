package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // ALTERAR PARA POSTGRESQL
    /*private static final String URL = "jdbc:postgresql://localhost:5432/visto_angola";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";  // A senha que você definiu*/
    
    // 1. ALTERADO PARA MYSQL (Porta padrão 3306 e timezone configurada para evitar erros)
    private static final String URL = "jdbc:mysql://localhost:3306/visto_angola?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root"; // Utilizador padrão do MySQL (ou o teu)
    private static final String PASSWORD = "1234"; // A tua senha do MySQL

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexão com BD estabelecida!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver da BD não encontrado: " + e.getMessage());
            throw new SQLException("Driver da BD não encontrado", e);
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao BD: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("✅ Conexão fechada.");
            } catch (SQLException e) {
                System.err.println("❌ Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}