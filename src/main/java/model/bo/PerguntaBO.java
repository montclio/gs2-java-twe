package model.bo;

import model.dao.PerguntaDAO;
import model.vo.PerguntaVO;

import java.sql.Connection;
import java.sql.SQLException;

public class PerguntaBO {
    private PerguntaDAO perguntaDAO;

    public PerguntaBO() throws ClassNotFoundException, SQLException{
        this.perguntaDAO = new PerguntaDAO();
    }

    public PerguntaVO cadastrarPerguntaBO(PerguntaVO pergunta) throws SQLException {
        return perguntaDAO.cadastrarPergunta(pergunta);
    }

    public PerguntaVO consultarPerguntaBO(int idPergunta) throws SQLException {
        return perguntaDAO.consultarPergunta(idPergunta);
    }

    public boolean atualizarPerguntaBO(PerguntaVO pergunta) throws SQLException {
        return perguntaDAO.atualizarPergunta(pergunta);
    }

    public boolean excluirPerguntaBO(int idPergunta) throws SQLException {
        return perguntaDAO.deletarPergunta(idPergunta);
    }
}
