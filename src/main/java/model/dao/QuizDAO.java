package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.QuizVO;

public class QuizDAO {
    public Connection conexao;

    public QuizDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public QuizVO cadastrarQuiz(QuizVO quiz) {
        QuizVO quizGerado = null;

        try {
            String sql = "INSERT INTO TB_TWE_USUARIO (NOME, SOBRENOME, DATA_NASCIMENTO, USUARIO_EMAIL, USUARIO_CEP, SENHA, PONTUACAO_USUARIO, SEXO)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, quiz.getNome());
            stmt.setString(2, quiz.getSobrenome());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                quizGerado = new QuizVO();
                quizGerado.setNome(usuario.getNome());
                quizGerado.setSobrenome(usuario.getSobrenome());
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
}
