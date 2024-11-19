package model.vo;

public class PerguntaVO {
    private int idPergunta;
    private String pergunta;

    public PerguntaVO(int idPergunta, String pergunta) {
        this.idPergunta = idPergunta;
        this.pergunta = pergunta;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public java.lang.String getPergunta() {
        return pergunta;
    }

    public void setPergunta(java.lang.String pergunta) {
        this.pergunta = pergunta;
    }
}