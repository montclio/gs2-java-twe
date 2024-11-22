package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.QuizVO;

public class QuizDAO extends PerguntaDAO{
    public Connection conexao;

    public QuizDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    public QuizVO cadastrarQuiz(QuizVO quiz) {
        QuizVO quizGerado = null;

        try {
            String sql = "INSERT INTO tb_twe_quiz (id_pergunta)"
                    + " VALUES (?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, quiz.getIdPergunta());


            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                quizGerado = new QuizVO();
                quizGerado.setIdPergunta(quiz.getIdPergunta());
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao cadastrar o Quiz: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return quizGerado;
    }

    public boolean atualizarQuiz(int idQuiz, int idPergunta) {
        try {
            String sql = "UPDATE tb_twe_quiz SET id_pergunta = ? WHERE id_quiz = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idPergunta);
            stmt.setInt(2, idQuiz);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar o Quiz: " + err.getMessage(), err);
        }
    }


    public QuizVO consultarQuiz(int idQuiz) {
        QuizVO quiz = null;

        try {
            String sql = "SELECT * FROM tb_twe_quiz" + " WHERE id_quiz = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idQuiz);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                quiz = new QuizVO();
                quiz.setIdPergunta(rs.getInt("id_pergunta"));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar o Quiz: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return quiz;
    }

    public void deletarQuiz(int idQuiz) {
        try {
            String sql = "DELETE FROM tb_twe_quiz WHERE id_quiz = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idQuiz);
            stmt.executeUpdate();

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar o Quiz: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
    }
}
