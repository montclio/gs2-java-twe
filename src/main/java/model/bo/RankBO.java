package model.bo;

import model.dao.RankDAO;
import model.vo.RankVO;

import java.sql.SQLException;

public class RankBO {
    private RankDAO rankDAO;
    public RankBO() throws ClassNotFoundException, SQLException {
        this.rankDAO = new RankDAO();
    }

    public RankVO cadastrarRankBO(RankVO rank) throws ClassNotFoundException, SQLException {
        return rankDAO.cadastrarRank(rank);
    }

    public RankVO consultarRankBO(int idRank) throws ClassNotFoundException, SQLException {
        return rankDAO.consultarRank(idRank);
    }


    public void excluirRankBO(int idRank) throws ClassNotFoundException, SQLException {
        rankDAO.deletarRank(idRank);
    }

    public boolean atualizarRankBO(int idRank,RankVO rank) throws ClassNotFoundException, SQLException{
        return rankDAO.atualizarRank(idRank, rank);
    }

}
