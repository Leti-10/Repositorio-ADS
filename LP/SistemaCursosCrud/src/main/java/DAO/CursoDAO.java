package DAO;

import factory.Conexao;
import modelo.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void inserir(Curso curso) {
        String sql = "INSERT INTO curso (nome, carga_horaria, limite_alunos, ativo) VALUES (?, ?, ?, ?)";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setInt(3, curso.getLimiteAlunos());
            stmt.setInt(4, curso.getAtivo());

            stmt.executeUpdate();
            System.out.println("Curso inserido com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void inserirCurso(Curso curso) {
        inserir(curso);
    }

    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT c.*, COUNT(a.idAluno) AS qtdAlunos " +
                "FROM curso c " +
                "LEFT JOIN aluno a ON c.idCurso = a.id_curso AND a.ativo = 1 " +
                "GROUP BY c.idCurso";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getString("nome"),
                        rs.getInt("carga_horaria"),
                        rs.getInt("limite_alunos"),
                        rs.getInt("ativo"),
                        new ArrayList<>()
                );
                curso.setIdCurso(rs.getInt("idCurso"));
                curso.setQtdAlunos(rs.getInt("qtdAlunos")); // seta a quantidade de alunos
                cursos.add(curso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    public List<Curso> buscarCursos(String filtro, String valor) {
        List<Curso> cursos = new ArrayList<>();

        String coluna = null;
        String comparador = "LIKE";
        String sql;

        switch (filtro) {
            case "ID":
                coluna = "idCurso";
                comparador = "=";
                break;
            case "Nome":
                coluna = "nome";
                valor = "%" + valor + "%";
                break;
            case "Carga":
                coluna = "carga_horaria";
                comparador = "=";
                break;
            case "Limite":
                coluna = "limite_alunos";
                comparador = ">=";
                break;
            case "Status":
                coluna = "ativo";
                comparador = "=";
                valor = valor.equalsIgnoreCase("ativo") ? "1" : "0";
                break;
            case "Qtd.Alunos":
                break;
            default:
                throw new IllegalArgumentException("Filtro inválido.");
        }

        if ("Qtd.Alunos".equals(filtro)) {
            sql = "SELECT c.*, COUNT(a.idAluno) AS qtdAlunos " +
                    "FROM curso c " +
                    "LEFT JOIN aluno a ON c.idCurso = a.id_curso AND a.ativo = 1 " +
                    "GROUP BY c.idCurso " +
                    "HAVING COUNT(a.idAluno) >= ?";
        } else {
            sql = "SELECT * FROM curso WHERE " + coluna + " " + comparador + " ?";
        }

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if ("Qtd.Alunos".equals(filtro)) {
                stmt.setInt(1, Integer.parseInt(valor));
            } else if ("Nome".equals(filtro)) {
                stmt.setString(1, valor);
            } else {
                stmt.setInt(1, Integer.parseInt(valor));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getString("nome"),
                        rs.getInt("carga_horaria"),
                        rs.getInt("limite_alunos"),
                        rs.getInt("ativo"),
                        new ArrayList<>()
                );
                curso.setIdCurso(rs.getInt("idCurso"));

                if ("Qtd.Alunos".equals(filtro)) {
                    curso.setQtdAlunos(rs.getInt("qtdAlunos"));
                } else {
                    curso.setQtdAlunos(0);
                }

                cursos.add(curso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }



    public void alterarCurso(Curso curso) {
        String sql = "UPDATE curso SET nome = ?, carga_horaria = ?, limite_alunos = ?, ativo = ? WHERE idCurso  = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setInt(3, curso.getLimiteAlunos());
            stmt.setInt(4, curso.getAtivo());
            stmt.setInt(5, curso.getIdCurso());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Curso '" + curso.getNome() + "' atualizado com sucesso!");
            } else {
                System.out.println("Nenhum curso foi atualizado. Verifique o ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirCurso(int idCurso) {
        String sql = "DELETE FROM curso WHERE idCurso = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCurso);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Curso excluido com sucesso!");
            } else {
                System.out.println("Curso não encontrado para exclusão");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void desativarCurso(int idCurso) {
        String sql = "UPDATE curso SET ativo = 0 WHERE idCurso = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idCurso);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Curso desativado com sucesso!");
            } else {
                System.out.println("Curso não encontrado para desativação.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reativarCurso(int idCurso) {
        String sql = "UPDATE curso SET ativo = 1 WHERE idCurso = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCurso);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Curso reativado com sucesso!");
            } else {
                System.out.println("Curso não encontrado para reativação.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
