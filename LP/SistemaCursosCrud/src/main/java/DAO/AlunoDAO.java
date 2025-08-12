package DAO;

import factory.Conexao;
import modelo.Aluno;
import modelo.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    /*Argumento se o CPF já foi cadastrado*/

    public boolean cpfExiste(String cpf) {
        String sql = "select * from aluno where cpf = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*argumento se o CPF é válido pela regras governamentais*/

    public static boolean isCPFValido(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11)
            return false;

        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 >= 10)
                digito1 = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 >= 10)
                digito2 = 0;

            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                    digito2 == Character.getNumericValue(cpf.charAt(10));

        } catch (Exception e) {
            return false;
        }
    }
 
    
    /*Verifica se o curso está ativo no banco de dados*/
    public boolean cursoAtivo(int idCurso) {
        String sql = "SELECT ativo FROM curso WHERE idCurso = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ativo") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void inserirAluno(Aluno aluno) {
        if (!cursoAtivo(aluno.getCurso().getIdCurso())) {
            System.out.println("Não é possível adicionar aluno: curso está inativo.");
            return;
        }
        String sql = "insert into aluno(nome, cpf, email, data_nascimento, ativo, id_curso, telefone) VALUES (?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setDate(4, Date.valueOf(aluno.getData_Nascimento()));
            stmt.setInt(5, aluno.getAtivo());
            stmt.setInt(6, aluno.getCurso().getIdCurso());
            stmt.setString(7, aluno.getTelefone());

            stmt.executeUpdate();
            System.out.println("Aluno adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT "
                + "a.idAluno, a.nome AS aluno_nome, a.cpf, a.telefone, a.email, a.data_nascimento, a.ativo AS aluno_ativo, "
                + "c.idCurso, c.nome AS curso_nome, c.carga_horaria, c.limite_alunos, c.ativo AS curso_ativo "
                + "FROM aluno a "
                + "LEFT JOIN curso c ON a.id_Curso = c.idCurso ";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso curso = null;
                int idCurso = rs.getInt("idCurso");
                if (!rs.wasNull()) {
                    curso = new Curso(
                            rs.getString("curso_nome"),
                            rs.getInt("carga_horaria"),
                            rs.getInt("limite_alunos"),
                            rs.getInt("curso_ativo"),
                            new ArrayList<>()
                    );
                    curso.setIdCurso(idCurso);
                }

                String cpfRaw = rs.getString("cpf");
                String cpfLimpo = cpfRaw != null ? cpfRaw.replaceAll("\\D", "") : "";

                if (cpfLimpo.length() != 11) {
                    System.err.println("CPF inválido encontrado no banco (idAluno=" + rs.getInt("idAluno") + "): " + cpfRaw);
                    continue;
                }

                Aluno aluno = new Aluno(
                        rs.getString("aluno_nome"),
                        cpfLimpo,
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getInt("aluno_ativo"),
                        curso
                );

                aluno.setIdAluno(rs.getInt("idAluno"));
                aluno.setAtivo(rs.getInt("aluno_ativo"));
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> buscarAlunos(String campo, String valor) {
        List<Aluno> alunos = new ArrayList<>();

        String coluna;
        boolean bool = true;
        String sql;

        switch (campo) {
            case "ID":
                coluna = "a.idAluno";
                bool = false;
                break;
            case "Nome":
                coluna = "a.nome";
                break;
            case "CPF":
                coluna = "a.cpf";
                break;
            case "Status":
                coluna = "a.ativo";
                bool = false;
                break;
            case "Curso":
                coluna = "c.nome";
                break;
            default:
                throw new IllegalArgumentException("Filtro inválido!");
        }

        sql = "SELECT a.idAluno, a.nome AS aluno_nome, a.cpf, a.telefone, a.email, a.data_nascimento, a.ativo AS aluno_ativo, "
                + "c.idCurso, c.nome AS curso_nome, c.carga_horaria, c.limite_alunos, c.ativo AS curso_ativo "
                + "FROM aluno a "
                + "LEFT JOIN curso c ON a.id_Curso = c.idCurso "
                + "WHERE ";

        if (bool) {
            sql += coluna + " LIKE ?";
        } else {
            sql += coluna + " = ?";
        }

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (campo.equals("ID")) {
                stmt.setInt(1, Integer.parseInt(valor));
            } else if (campo.equals("Status")) {
                int status = valor.equalsIgnoreCase("Ativo") ? 1 : 0;
                stmt.setInt(1, status);
            } else {
                stmt.setString(1, "%" + valor + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getString("curso_nome"),
                        rs.getInt("carga_horaria"),
                        rs.getInt("limite_alunos"),
                        rs.getInt("curso_ativo"),
                        new ArrayList<>()
                );
                curso.setIdCurso(rs.getInt("idCurso"));

                String cpfRaw = rs.getString("cpf");
                String cpfLimpo = cpfRaw != null ? cpfRaw.replaceAll("\\D", "") : "";

                if (cpfLimpo.length() != 11) {
                    System.err.println("CPF inválido encontrado no banco (idAluno=" + rs.getInt("idAluno") + "): " + cpfRaw);
                    continue;
                }

                Aluno aluno = new Aluno(
                        rs.getString("aluno_nome"),
                        cpfLimpo,
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getInt("aluno_ativo"),
                        curso
                );
                aluno.setIdAluno(rs.getInt("idAluno"));
                aluno.setAtivo(rs.getInt("aluno_ativo"));
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public void alterarAluno(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ?, data_nascimento = ?, ativo = ?, id_curso = ? WHERE idAluno = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setDate(4, Date.valueOf(aluno.getData_Nascimento()));
            stmt.setInt(5, aluno.getAtivo());
            stmt.setInt(6, aluno.getCurso().getIdCurso());
            stmt.setInt(7, aluno.getIdAluno());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Aluno atualizado com sucesso!");
            } else {
                System.out.println("Nenhum aluno foi atualizado. Verifique o ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirAluno(int idAluno) {
        String sql = "DELETE FROM aluno WHERE idAluno = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Aluno excluído com sucesso!");
            } else {
                System.out.println("Aluno não encontrado para exclusão.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}