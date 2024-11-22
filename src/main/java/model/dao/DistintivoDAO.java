package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.DistintivoVO;



public class DistintivoDAO {
    public Connection conexao;

    public DistintivoDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public DistintivoVO cadastrarDistintivo(DistintivoVO distintivo) {
        DistintivoVO distintivoGerado = null;

        try {
            String sql = "INSERT INTO tb_twe_distintivo (id_usuario, desc_distintivo)"
                    + " VALUES (?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, distintivo.getIdUsuario());
            stmt.setString(2, distintivo.getNomeDistintivo());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                distintivoGerado = new DistintivoVO();
                distintivoGerado.setNome(distintivo.getNome());
                distintivoGerado.setSobrenome(distintivo.getSobrenome());

            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao cadastrar o Distintivo: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return distintivoGerado;
    }

    public boolean atualizarDistintivo(int idDistintivo, DistintivoVO distintivo) {
        try {
            String sql = "UPDATE tb_twe_distintivo SET id_usuario = ?, desc_distintivo = ? "
                    + "WHERE id_distintivo = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, distintivo.getIdUsuario());
            stmt.setString(2, distintivo.getNomeDistintivo());
            stmt.setInt(3, idDistintivo);

            int linhasAfetadas = stmt.executeUpdate();


            return linhasAfetadas > 0;

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar o Distintivo: " + err.getMessage(), err);
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

    public DistintivoVO consultarAlternativa(int idDistintivo) {
        DistintivoVO distintivo = null;

        try {
            String sql = "SELECT * FROM tb_twe_distintivo" + " WHERE id_distintivo = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idDistintivo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                distintivo = new DistintivoVO();
                distintivo.setIdDistintivo(rs.getInt("id_distintivo"));
                distintivo.setIdUsuario(rs.getInt("id_usuario"));
                distintivo.setNomeDistintivo(rs.getString("desc_distintivo"));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar o Distintivo: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return distintivo;
    }

    public void deletarDistintivo(int idDistinto) {
        try {
            String sql = "DELETE FROM tb_twe_distintivo WHERE id_distintivo = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idDistinto);
            stmt.executeUpdate();

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar o Distintivo: " + err.getMessage(), err);
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
