package model.vo;

public class DistintivoVO extends UsuarioVO{
    private int idDistintivo;
    private String nomeDistintivo;

    public DistintivoVO() {
        this.idDistintivo = idDistintivo;
        this.nomeDistintivo = nomeDistintivo;
    }

    public int getIdDistintivo() {
        return idDistintivo;
    }

    public void setIdDistintivo(int idDistintivo) {
        this.idDistintivo = idDistintivo;
    }

    public java.lang.String getNomeDistintivo() {
        return nomeDistintivo;
    }

    public void setNomeDistintivo(java.lang.String nomeDistintivo) {
        this.nomeDistintivo = nomeDistintivo;
    }

    public int atribuirDistintivo(){
            return 1;
    }
}