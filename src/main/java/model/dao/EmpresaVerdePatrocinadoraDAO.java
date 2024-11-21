package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.EmpresaVerdePatrocinadoraVO;

public class EmpresaVerdePatrocinadoraDAO {
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


}
