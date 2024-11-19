package model.vo;

public class EmpresaVerdeVO {
    private int idEmpresa;
    private String nomeFantasia;
    private int cnpj;
    private String fonte;

    public EmpresaVerdeVO(int idEmpresa, String nomeFantasia, int cnpj, String fonte) {
        this.idEmpresa = idEmpresa;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.fonte = fonte;
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

    public java.lang.String getFonte() {
        return fonte;
    }

    public void setFonte(java.lang.String fonte) {
        this.fonte = fonte;
    }
}
