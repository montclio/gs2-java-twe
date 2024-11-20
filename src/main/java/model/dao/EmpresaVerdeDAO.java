package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.EmpresaVerdeVO;

public class EmpresaVerdeDAO {
    public Connection conexao;

    public EmpresaVerdeDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public EmpresaVerdeVO cadastrarEmpresaVerde(EmpresaVerdeVO empresaVerde){
        EmpresaVerdeVO empresaVerdeGerada = null;

        try {
            String sql = "INSERT INTO TB_TWE_EMPRESA_VERDE(cnpj_verde, nome_fantasia, posicao, fonte) VALUE(?,?,?,?,?)";
        }
    }
}
