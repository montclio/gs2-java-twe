package model.vo;

public class EmpresaVerdePatrocinadoraVO extends EmpresaVerdeVO {

    private double valorPatrocinio;

    public EmpresaVerdePatrocinadoraVO() {
        super();
    }

    public EmpresaVerdePatrocinadoraVO(int idEmpresa, String nomeFantasia, int cnpj, int posicao, double valorPatrocinio) {
        super();
        this.setIdEmpresa(idEmpresa);
        this.setNomeFantasia(nomeFantasia);
        this.setCnpj(cnpj);
        this.setPosicao(posicao);
        this.valorPatrocinio = valorPatrocinio;
    }


    public double getValorPatrocinio() {
        return valorPatrocinio;
    }

    public void setValorPatrocinio(double valorPatrocinio) {
        this.valorPatrocinio = valorPatrocinio;
    }
}