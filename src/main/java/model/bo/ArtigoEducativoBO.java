package model.bo;

import model.dao.ArtigoEducativoDAO;
import model.dao.UsuarioDAO;
import model.vo.ArtigoEducativoVO;
import model.vo.UsuarioVO;

import java.sql.SQLException;

public class ArtigoEducativoBO {
    private ArtigoEducativoDAO artigoDAO;
    public ArtigoEducativoBO() throws ClassNotFoundException, SQLException{
        this.artigoDAO = new ArtigoEducativoDAO();
    }

    public ArtigoEducativoVO cadastrarArtigoEducativoBO(ArtigoEducativoVO artigo) throws ClassNotFoundException, SQLException {
        return artigoDAO.cadastrarArtigoEducativo(artigo);
    }

    public ArtigoEducativoVO consultarUArtigoEducativoBO(int idArtigo) throws ClassNotFoundException, SQLException {
        return artigoDAO.consultarArtigoEducativo(idArtigo);
    }


    public void excluirArtigoEducativoBO(int idArtigo) throws ClassNotFoundException, SQLException {
        artigoDAO.deletarArtigoEducativ(idArtigo);
    }

    public boolean atualizarArtigoEducativoBO(int idArtigo,ArtigoEducativoVO artigo) throws ClassNotFoundException, SQLException{
        return artigoDAO.atualizarArtigoEducativo(idArtigo, artigo);
    }

}
