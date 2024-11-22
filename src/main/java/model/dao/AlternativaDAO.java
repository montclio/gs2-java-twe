package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bo.AlternativaBO;
import model.dao.connection.ConexaoFactory;
import model.vo.AlternativaVO;
import model.vo.UsuarioVO;

public class AlternativaDAO extends PerguntaDAO {
    public Connection conexao;

    public AlternativaDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public AlternativaVO cadastrarAlternativa(AlternativaVO alternativa) {
        AlternativaVO alternativaGerada = null;

        try {
            String sql = "INSERT INTO tb_twe_alternativas (id_pergunta, desc_alternativas)"
                    + " VALUES (?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, alternativa.getIdPergunta());
            stmt.setString(2, alternativa.getDescAlternativa());


            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                alternativaGerada = new AlternativaVO();
                alternativaGerada.setDescAlternativa(alternativa.getDescAlternativa());
                alternativaGerada.setIdPergunta(alternativa.getIdPergunta());
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao cadastrar o Alternativa: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return alternativaGerada;
    }

    public boolean atualizarAlternativa(int idAlternativa, AlternativaVO alternativa) {
        try {
            String sql = "UPDATE tb_twe_alternativas SET id_pergunta = ?, desc_alternativas = ?"
                    + "WHERE id_alternativa = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, alternativa.getIdPergunta());
            stmt.setString(2, alternativa.getDescAlternativa());
            stmt.setLong(3, idAlternativa);

            int linhasAfetadas = stmt.executeUpdate();


            return linhasAfetadas > 0;

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar o Alternativa: " + err.getMessage(), err);
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

    public AlternativaVO consultarAlternativa(int idAlternativa) {
        AlternativaVO alternativa = null;

        try {
            String sql = "SELECT * FROM tb_twe_alternativas" + " WHERE id_alternativa = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idAlternativa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                alternativa = new AlternativaVO();
                alternativa.setIdAlternativa(rs.getInt("id_alternativa"));
                alternativa.setIdPergunta(rs.getInt("id_pergunta"));
                alternativa.setDescAlternativa(rs.getString("desc_alternativas"));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar o Alternativa: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return alternativa;
    }


    public void deletarAlternativa(int idAlternativa) {
        try {
            String sql = "DELETE FROM tb_twe_alternativas WHERE id_alternativa = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idAlternativa);
            stmt.executeUpdate();

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar o Alternativa: " + err.getMessage(), err);
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
