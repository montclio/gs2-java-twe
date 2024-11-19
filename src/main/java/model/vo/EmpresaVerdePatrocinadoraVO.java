package model.vo;

public class EmpresaVerdePatrocinadoraVO {
    private int idEmpresa;
    private String nomeFantasia;
    private int cnpj;
    private double valorPatrocinio;

    public EmpresaVerdePatrocinadoraVO(int idEmpresa, String nomeFantasia, int cnpj, double valorPatrocinio) {
        this.idEmpresa = idEmpresa;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.valorPatrocinio = valorPatrocinio;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public java.lang.String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(java.lang.String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public double getValorPatrocinio() {
        return valorPatrocinio;
    }

    public void setValorPatrocinio(double valorPatrocinio) {
        this.valorPatrocinio = valorPatrocinio;
    }
}