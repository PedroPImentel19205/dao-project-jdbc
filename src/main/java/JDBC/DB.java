package JDBC; // Crie um pacote 'db' para esta classe

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Importe Statement
import java.sql.ResultSet; // Importe ResultSet

import JDBC.ConnectionConfig; // Importe a sua classe de configuração

public class DB {

    private static Connection conn = null; // Variável estática para a conexão

    // --- Método para obter a conexão ---
    public static Connection getConnection() {
        if (conn == null) { // Se a conexão ainda não foi estabelecida
            try {
                // É boa prática carregar o driver explicitamente para versões mais antigas do JDBC.
                // Para JDBC 4.0+ (Java 6+), isso geralmente não é necessário, mas não faz mal.
                // Class.forName("com.mysql.cj.jdbc.Driver"); // Exemplo para MySQL Driver 8.x+

                // Usa as constantes da sua ConnectionConfig
                conn = DriverManager.getConnection(
                        ConnectionConfig.URL,
                        ConnectionConfig.USER,
                        ConnectionConfig.PASSWORD
                );
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            } catch (SQLException e) {
                // Lança uma exceção personalizada ou trata o erro de conexão
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    // --- Método para fechar a conexão ---
    public static void closeConnection() { // Mudamos para não receber Connection como parâmetro
        if (conn != null) { // Só tenta fechar se a conexão existe
            try {
                conn.close();
                conn = null; // Defina a conexão como null após fechar
                System.out.println("Conexão com o banco de dados fechada com sucesso!");
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    // --- Método para fechar Statement ---
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    // --- Método para fechar ResultSet ---
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}