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


}
