package model.vo;

import java.util.Date;
import java.time.LocalDateTime;
public class SessaoVO {
    private int idSessao;
    private Date dataSessao;
    private LocalDateTime tempoInativo;

    public SessaoVO(int idSessao, Date dataSessao, LocalDateTime tempoInativo) {
        this.idSessao = idSessao;
        this.dataSessao = dataSessao;
        this.tempoInativo = tempoInativo;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public Date getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(Date dataSessao) {
        this.dataSessao = dataSessao;
    }

    public LocalDateTime getTempoInativo() {
        return tempoInativo;
    }

    public void setTempoInativo(LocalDateTime tempoInativo) {
        this.tempoInativo = tempoInativo;
    }
}