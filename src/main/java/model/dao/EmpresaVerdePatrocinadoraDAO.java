package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.EmpresaVerdePatrocinadoraVO;

public class EmpresaVerdePatrocinadoraDAO extends EmpresaVerdeDAO{
    public Connection conexao;

    public EmpresaVerdePatrocinadoraDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public EmpresaVerdePatrocinadoraVO cadastrarEmpresaVerdePatrocinadora(EmpresaVerdePatrocinadoraVO empresaPatro) {
        EmpresaVerdePatrocinadoraVO empresaGerada = null;

        try {
            String sql = "INSERT INTO tb_twe_emp_parceiras (cnpj_parc, nome_fantasia, posicao, valor_patrocionio)"
                    + " VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, empresaPatro.getCnpj());
            stmt.setString(2, empresaPatro.getNomeFantasia());
            stmt.setInt(3, empresaPatro.getPosicao());
            stmt.setDouble(4, empresaPatro.getValorPatrocinio());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                empresaGerada = new EmpresaVerdePatrocinadoraVO(
                        rs.getInt(1),
                        empresaPatro.getNomeFantasia(),
                        empresaPatro.getCnpj(),
                        empresaPatro.getPosicao(),
                        empresaPatro.getValorPatrocinio()
                );
            } else {
                throw new RuntimeException("Nenhuma chave foi gerada ao cadastrar a empresa.");
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao cadastrar a empresa: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return empresaGerada;
    }

    public boolean atualizarEmpresaVerdePatrocinadora(Integer idEmpresa, EmpresaVerdePatrocinadoraVO empresaPatro) {
        try {
            String sql = "UPDATE tb_twe_emp_parceiras SET  cnpj_parc = ?, nome_fantasia = ?, posicao = ?, valor_patrocionio = ?, "
                    + "WHERE id_empresa_parc = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, empresaPatro.getCnpj());
            stmt.setString(2, empresaPatro.getNomeFantasia());
            stmt.setInt(3, empresaPatro.getPosicao());
            stmt.setDouble(4, empresaPatro.getValorPatrocinio());

            int linhasAfetadas = stmt.executeUpdate();


            return linhasAfetadas > 0;

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar a Empresa: " + err.getMessage(), err);
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

    public EmpresaVerdePatrocinadoraVO consultarEmpresaVerdePatro(int idEmpresa) {
        EmpresaVerdePatrocinadoraVO empresaVerdePatro = null;

        try {
            String sql = "SELECT * FROM tb_twe_emp_parceiras" + " WHERE id_empresa_parc = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idEmpresa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                empresaVerdePatro = new EmpresaVerdePatrocinadoraVO();
                empresaVerdePatro.setIdEmpresa(rs.getInt("id_empresa_parc"));
                empresaVerdePatro.setCnpj(rs.getInt("cnpj_parc"));
                empresaVerdePatro.setNomeFantasia(rs.getString("nome_fantasia"));
                empresaVerdePatro.setPosicao(rs.getInt("posicao"));
                empresaVerdePatro.setValorPatrocinio(rs.getDouble("valor_patrocionio"));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar a Empresa: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return empresaVerdePatro;
    }

    public void deletarEmpresaVerdePatrocinadora(int idEmpresa) {
        try {
            String sql = "DELETE FROM tb_twe_emp_parceiras WHERE id_empresa_parc = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idEmpresa);
            stmt.executeUpdate();

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar a Empresa: " + err.getMessage(), err);
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
