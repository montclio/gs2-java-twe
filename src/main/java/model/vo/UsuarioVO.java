package model.vo;

import java.util.Date;

public class UsuarioVO {
    private int idUsuario;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String usuarioEmail;
    private int usuarioCep;
    private String senha;
    private int pontuacaoUsuario;
    private String sexo;

    public UsuarioVO() {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.usuarioEmail = usuarioEmail;
        this.usuarioCep = usuarioCep;
        this.senha = senha;
        this.pontuacaoUsuario = pontuacaoUsuario;
        this.sexo = sexo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public int getUsuarioCep() {
        return usuarioCep;
    }

    public void setUsuarioCep(int usuarioCep) {
        this.usuarioCep = usuarioCep;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontuacaoUsuario() {
        return pontuacaoUsuario;
    }

    public void setPontuacaoUsuario(int pontuacaoUsuario) {
        this.pontuacaoUsuario = pontuacaoUsuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
