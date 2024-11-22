package model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PerguntaVO {
    private int idPergunta;
    private String pergunta;

    public PerguntaVO() {
        this.idPergunta = 0;
        this.pergunta = null;
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