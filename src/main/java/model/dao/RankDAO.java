package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.RankVO;
import model.vo.UsuarioVO;


public class RankDAO {
    public Connection conexao;
    public RankDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public RankVO cadastrarRank(RankVO rank) {
        RankVO rankGerado = null;

        try {
            String sql = "INSERT INTO tb_twe_ranking (desc_ranking, posicao)"
                    + " VALUES (?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, rank.getDescRank());
            stmt.setInt(2, rank.getPosicao());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                rankGerado = new RankVO();
                rankGerado.setDescRank(rank.getDescRank());
                rankGerado.setPosicao(rank.getPosicao());
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao cadastrar o Rank: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return rankGerado;
    }

    public boolean atualizarRank(int idRank, RankVO rank) {
        try {
            String sql = "UPDATE tb_twe_ranking SET desc_ranking = ?, posicao = ?"
                    + "WHERE id_ranking = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, rank.getDescRank());
            stmt.setInt(2, rank.getPosicao());
            stmt.setInt(3, idRank);

            int linhasAfetadas = stmt.executeUpdate();


            return linhasAfetadas > 0;

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar o Rank: " + err.getMessage(), err);
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

    public RankVO consultarRank(int idRank) {
        RankVO rank = null;

        try {
            String sql = "SELECT * FROM tb_twe_ranking" + " WHERE id_ranking = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idRank);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rank = new RankVO();
                rank.setIdRank(rs.getInt("id_ranking"));
                rank.setDescRank(rs.getString("desc_ranking"));
                rank.setPosicao(rs.getInt("posicao"));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar o Rank: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return rank;
    }

    public void deletarRank(int idRank) {
        try {
            String sql = "DELETE FROM tb_twe_ranking WHERE id_ranking = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idRank);
            stmt.executeUpdate();

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar o Rank: " + err.getMessage(), err);
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
