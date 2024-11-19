package model.vo;

public class QuizVO {
    private int idPergunta;
    private int pontuacao;

    public QuizVO(int idPergunta, int pontuacao) {
        this.idPergunta = idPergunta;
        this.pontuacao = pontuacao;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
