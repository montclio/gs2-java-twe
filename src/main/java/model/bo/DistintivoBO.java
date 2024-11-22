package model.bo;

import model.dao.DistintivoDAO;
import model.vo.DistintivoVO;

import java.sql.SQLException;

public class DistintivoBO {

    private DistintivoDAO distintivoDAO;
    public DistintivoBO() throws ClassNotFoundException, SQLException {
        this.distintivoDAO = new DistintivoDAO();
    }

    public DistintivoVO cadastrarDistintivoBO(DistintivoVO distintivo) throws ClassNotFoundException, SQLException {
        return distintivoDAO.cadastrarDistintivo(distintivo);
    }

    public DistintivoVO consultarDistintivoBO(int idDistintivo) throws ClassNotFoundException, SQLException {
        return distintivoDAO.consultarAlternativa(idDistintivo);
    }


    public void excluirDistintivoBO(int idDistintivo) throws ClassNotFoundException, SQLException {
        distintivoDAO.deletarDistintivo(idDistintivo);
    }

    public boolean atualizarDistintivoBO(int idDistintivo,DistintivoVO distintivo) throws ClassNotFoundException, SQLException{
        return distintivoDAO.atualizarDistintivo(idDistintivo, distintivo);
    }
    
}
