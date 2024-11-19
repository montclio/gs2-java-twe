package model.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoFactory {

    public Connection conexao() throws SQLException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");


        } catch (Exception err) {
            err.printStackTrace();
        }

        return DriverManager.getConnection
                ("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                        "rm557541", "190800");
    }

}
