package factory;

import java.sql.Connection;

public class testeConexao {
    public static void main(String[] args) {
        try {
            Connection conn = Conexao.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
