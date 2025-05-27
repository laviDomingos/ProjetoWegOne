package org.example.db;

public class TestaConexao {
    public static void main(String[] args) {
        try (var conn = Conexao.getConexao()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexão com banco de dados OK!");
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
