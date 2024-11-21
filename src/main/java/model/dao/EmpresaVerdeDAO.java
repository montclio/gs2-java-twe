package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.EmpresaVerdeVO;
import model.vo.UsuarioVO;

public class EmpresaVerdeDAO {
    public Connection conexao;

    public EmpresaVerdeDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public EmpresaVerdeVO cadastrarEmpresaVerde(EmpresaVerdeVO empresaVerde){
        EmpresaVerdeVO empresaVerdeGerada = null;

        try {
            String sql = "INSERT INTO TB_TWE_EMPRESA_VERDE(cnpj_verde, nome_fantasia, posicao, fonte)"
                    + "VALUE(?,?,?,?,?)";
            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, empresaVerde.getCnpj());
            stmt.setString(2, empresaVerde.getNomeFantasia());
            stmt.setInt(3, empresaVerde.getPosicao());
            stmt.setString(4, empresaVerde.getFonte());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                empresaVerdeGerada = new EmpresaVerdeVO();
                empresaVerdeGerada.setCnpj(empresaVerde.getCnpj());
                empresaVerdeGerada.setNomeFantasia(empresaVerde.getNomeFantasia());
                empresaVerdeGerada.setPosicao(empresaVerdeGerada.getPosicao());
                empresaVerdeGerada.setFonte(empresaVerde.getFonte());
            }

        }catch (SQLException err){
            throw new RuntimeException("Erro ao cadastrar a sua empresa Verde: " + err.getMessage(), err);
        } finally {
            try{
                if (this.conexao != null) {
                    this.conexao.close();
                }
            }catch (SQLException e){
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return empresaVerdeGerada;
    }

    public boolean atualizarEmpresaVerde(int idEmpresa, EmpresaVerdeVO empresaVerde) {
        try {
            String sql = "UPDATE tb_twe_empresa_verde SET cnpj_verde = ?, nome_fantasia =? , posicao= ?, fonte =? WHERE id_empresa_verde = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, empresaVerde.getCnpj());
            stmt.setString(2, empresaVerde.getNomeFantasia());
            stmt.setInt(3, empresaVerde.getPosicao());
            stmt.setString(4, empresaVerde.getFonte());
            stmt.setLong(5, idEmpresa);

            int linhasAfetadas = stmt.executeUpdate();


            return linhasAfetadas > 0;

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar a sua Empresa: " + err.getMessage(), err);
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


    public EmpresaVerdeVO consultarEmpresaVerde(int idEmpresaVerde) {
        EmpresaVerdeVO empresaVerde = null;

        try {
            String sql = "SELECT * FROM tb_twe_empresa_verde" + " WHERE id_empresa_verde = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idEmpresaVerde);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                empresaVerde = new EmpresaVerdeVO();
                empresaVerde.setCnpj(rs.getInt("id_empresa_verde"));
                empresaVerde.setCnpj(rs.getInt("cnpj_verde"));
                empresaVerde.setNomeFantasia(rs.getString("nome_fantasia"));
                empresaVerde.setPosicao(rs.getInt("posicao"));
                empresaVerde.setFonte(rs.getString("fonte"));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar sua Empresa: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return empresaVerde;
    }


    public void deletarEmpresaVerde(int idEmpresa) {
        try {
            String sql = "DELETE FROM tb_twe_empresa_verde WHERE id_empresa_verde = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idEmpresa);
            stmt.executeUpdate();

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar sua Empresa: " + err.getMessage(), err);
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
