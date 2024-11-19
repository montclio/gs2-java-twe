package model.vo;

public class RespostaVO {
    private int idResposta;
    private String resposta;
    private int ponto;

    public RespostaVO(int idResposta, String resposta, int ponto) {
        this.idResposta = idResposta;
        this.resposta = resposta;
        this.ponto = ponto;
    }

    public int getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }

    public java.lang.String getResposta() {
        return resposta;
    }

    public void setResposta(java.lang.String resposta) {
        this.resposta = resposta;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }
}