package model.dao;

import model.dao.connection.ConexaoFactory;
import model.vo.PerguntaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerguntaDAO {
    private Connection conexao;

    public PerguntaDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public PerguntaVO cadastrarPergunta(PerguntaVO pergunta) throws SQLException {
        String sql = "INSERT INTO TB_TWE_PERGUNTAS (pergunta) VALUES (?)";
        try (PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pergunta.getPergunta());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pergunta.setIdPergunta(rs.getInt(1));
                }
            }
        }
        return pergunta;
    }


    public PerguntaVO consultarPergunta(int idPergunta) throws SQLException {
        String sql = "SELECT id_pergunta, pergunta FROM TB_TWE_PERGUNTAS WHERE id_pergunta = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPergunta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Instanciar o objeto sem usar o construtor com parâmetros
                    PerguntaVO pergunta = new PerguntaVO();
                    pergunta.setIdPergunta(rs.getInt("id_pergunta"));
                    pergunta.setPergunta(rs.getString("pergunta"));
                    return pergunta;
                }
            }
        }
        return null;
    }


    public boolean atualizarPergunta(PerguntaVO pergunta) throws SQLException {
        String sql = "UPDATE TB_TWE_PERGUNTAS SET pergunta = ? WHERE id_pergunta = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pergunta.getPergunta());
            stmt.setLong(2, pergunta.getIdPergunta());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }

    public boolean deletarPergunta(int idPergunta) throws SQLException {
        String sql = "DELETE FROM TB_TWE_PERGUNTAS WHERE id_pergunta = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPergunta);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }
}
