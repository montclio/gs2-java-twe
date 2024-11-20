package model.bo;

import model.dao.RespostaDAO;
import model.vo.RespostaVO;

import java.sql.SQLException;

public class RespostaBO {
    private RespostaDAO respostaDAO;

    public RespostaBO() throws ClassNotFoundException, SQLException {
        this.respostaDAO = new RespostaDAO();
    }

    public RespostaVO cadastrarRespostaBO(RespostaVO resposta) throws ClassNotFoundException, SQLException {
        return respostaDAO.cadastrarResposta(resposta);
    }

    public RespostaVO consultarRespostaBO(int idResposta) throws ClassNotFoundException, SQLException {
        return respostaDAO.consultarResposta(idResposta);
    }

    public void excluirRespostaBO(int idResposta) throws ClassNotFoundException, SQLException {
        respostaDAO.deletarResposta(idResposta);
    }

    public boolean atualizarRespostaBO(int idResposta, RespostaVO resposta) throws ClassNotFoundException, SQLException {
        return respostaDAO.atualizarResposta(idResposta, resposta);
    }
}
