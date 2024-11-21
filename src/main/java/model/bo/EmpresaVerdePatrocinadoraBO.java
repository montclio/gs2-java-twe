package model.bo;

import model.dao.EmpresaVerdePatrocinadoraDAO;
import model.dao.UsuarioDAO;
import model.vo.EmpresaVerdePatrocinadoraVO;
import model.vo.UsuarioVO;

import java.sql.SQLException;

public class EmpresaVerdePatrocinadoraBO {
    private EmpresaVerdePatrocinadoraDAO empresaPatroDAO;
    public EmpresaVerdePatrocinadoraBO() throws ClassNotFoundException, SQLException{
        this.empresaPatroDAO = new EmpresaVerdePatrocinadoraDAO();
    }

    public EmpresaVerdePatrocinadoraVO cadastrarEmpresaVerdePatrocinadoraBO(EmpresaVerdePatrocinadoraVO empresaPatro) throws ClassNotFoundException, SQLException {
        return empresaPatroDAO.cadastrarEmpresaVerdePatrocinadora(empresaPatro);
    }

    public EmpresaVerdePatrocinadoraVO consultarEmpresaVerdePatrocinadoraBO(int idEmpresa) throws ClassNotFoundException, SQLException {
        return empresaPatroDAO.consultarEmpresaVerdePatro(idEmpresa);
    }


    public void excluirEmpresaVerdePatrocinadoraBO(int idEmpresa) throws ClassNotFoundException, SQLException {
        empresaPatroDAO.deletarEmpresaVerdePatrocinadora(idEmpresa);
    }

    public boolean atualizarUsuarioBO(Integer idEmpresa,EmpresaVerdePatrocinadoraVO empresaPatro) throws ClassNotFoundException, SQLException{
        return empresaPatroDAO.atualizarEmpresaVerdePatrocinadora(idEmpresa, empresaPatro);
    }

}
