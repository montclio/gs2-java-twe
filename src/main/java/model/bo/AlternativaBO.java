package model.bo;

import model.dao.AlternativaDAO;
import model.vo.AlternativaVO;

import java.sql.SQLException;

public class AlternativaBO {
    private AlternativaDAO alternativaDAO;
    public AlternativaBO() throws ClassNotFoundException, SQLException {
        this.alternativaDAO = new AlternativaDAO();
    }

    public AlternativaVO cadastrarAlternativaBO(AlternativaVO alternativa) throws ClassNotFoundException, SQLException {
        return alternativaDAO.cadastrarAlternativa(alternativa);
    }

    public AlternativaVO consultarAlternativaBO(int idAlternativa) throws ClassNotFoundException, SQLException {
        return alternativaDAO.consultarAlternativa(idAlternativa);
    }


    public void excluirAlternativaBO(int idAlternativa) throws ClassNotFoundException, SQLException {
        alternativaDAO.deletarAlternativa(idAlternativa);
    }

    public boolean atualizarAlternativaBO(int idAlternativa,AlternativaVO usario) throws ClassNotFoundException, SQLException{
        return alternativaDAO.atualizarAlternativa(idAlternativa, usario);
    }
}
