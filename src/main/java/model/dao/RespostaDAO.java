package model.dao;

import model.dao.connection.ConexaoFactory;
import model.vo.RespostaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RespostaDAO {
    public Connection conexao;

    public RespostaDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public RespostaVO cadastrarResposta(RespostaVO resposta) {
        RespostaVO respostaGerada = null;

        try {
            String sql = "INSERT INTO TB_TWE_RESPOSTAS (RESPOSTA, PONTO) VALUES (?, ?)";
            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, resposta.getResposta());
            stmt.setInt(2, resposta.getPonto());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                respostaGerada = new RespostaVO(rs.getInt(1), resposta.getResposta(), resposta.getPonto());
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao cadastrar a resposta: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return respostaGerada;
    }

    public boolean atualizarResposta(int idResposta, RespostaVO resposta) {
        try {
            String sql = "UPDATE TB_TWE_RESPOSTAS SET RESPOSTA = ?, PONTO = ? WHERE ID_RESPOSTA = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, resposta.getResposta());
            stmt.setInt(2, resposta.getPonto());
            stmt.setInt(3, idResposta);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar a resposta: " + err.getMessage(), err);
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

    public RespostaVO consultarResposta(int idResposta) {
        RespostaVO resposta = null;

        try {
            String sql = "SELECT * FROM TB_TWE_RESPOSTAS WHERE ID_RESPOSTA = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idResposta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resposta = new RespostaVO(
                        rs.getInt("ID_RESPOSTA"),
                        rs.getString("RESPOSTA"),
                        rs.getInt("PONTO")
                );
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar a resposta: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return resposta;
    }

    public void deletarResposta(int idResposta) {
        try {
            String sql = "DELETE FROM TB_TWE_RESPOSTAS WHERE ID_RESPOSTA = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idResposta);
            stmt.executeUpdate();
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar a resposta: " + err.getMessage(), err);
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
