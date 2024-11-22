package model.vo;

public class RankVO extends EmpresaVerdeVO{
    private int idRank;
    private String descRank;
    private int posicao;
    private int pontuacao;

    public RankVO() {
        this.idRank = idRank;
        this.posicao = posicao;
    }

    public String getDescRank() {
        return descRank;
    }

    public void setDescRank(String descRank) {
        this.descRank = descRank;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getIdRank() {
        return idRank;
    }

    public void setIdRank(int idRank) {
        this.idRank = idRank;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

}