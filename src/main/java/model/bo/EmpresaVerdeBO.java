package model.bo;

import model.dao.EmpresaVerdeDAO;
import model.vo.EmpresaVerdeVO;

import java.sql.SQLException;

public class EmpresaVerdeBO {
    private EmpresaVerdeDAO empresaVerdeDao;
    public EmpresaVerdeBO() throws ClassNotFoundException, SQLException{
        this.empresaVerdeDao = new EmpresaVerdeDAO();
    }

    public EmpresaVerdeVO cadastrarEmpresaVerdeBO(EmpresaVerdeVO empresaVerde) throws ClassNotFoundException, SQLException {
        return empresaVerdeDao.cadastrarEmpresaVerde(empresaVerde);
    }

    public EmpresaVerdeVO consultarEmpresaVerdeBO(int idEmpresa) throws ClassNotFoundException, SQLException {
        return empresaVerdeDao.consultarEmpresaVerde(idEmpresa);
    }

    public void excluirEmpresaVerdeBO(int idEmpresa) throws ClassNotFoundException, SQLException {
        empresaVerdeDao.deletarEmpresaVerde(idEmpresa);
    }

    public boolean atualizarEmpresaVerdBO(int idEmpresa,EmpresaVerdeVO empresaVerde) throws ClassNotFoundException, SQLException{
        return empresaVerdeDao.atualizarEmpresaVerde(idEmpresa, empresaVerde);
    }

}
