package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

import java.sql.SQLException;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;
    public UsuarioBO() throws ClassNotFoundException, SQLException{
    this.usuarioDAO = new UsuarioDAO();
    }

    public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuario) throws ClassNotFoundException, SQLException {
        return usuarioDAO.cadastrarUsuario(usuario);
    }

    public UsuarioVO consultarUsuarioBO(int idUsuario) throws ClassNotFoundException, SQLException {
        return usuarioDAO.consultarUsuario(idUsuario);
    }


    public void excluirUsuarioBO(int idUsuario) throws ClassNotFoundException, SQLException {
        usuarioDAO.deletarUsuario(idUsuario);
    }

    public UsuarioVO atualizarUsuarioBO(UsuarioVO usario) throws ClassNotFoundException, SQLException{
         return usuarioDAO.atualizarUsuario(usario);
    }

}
