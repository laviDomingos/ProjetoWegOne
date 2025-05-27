package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://nozomi.proxy.rlwy.net:51315/db_WegOne";
    private static final String USUARIO = "root";
    private static final String SENHA = "JKByMgrCgMMVSvufYhcRbtzYDhqpxqQL";

    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        }
    }
}
