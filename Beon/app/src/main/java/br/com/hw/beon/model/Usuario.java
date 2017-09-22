package br.com.hw.beon.model;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class Usuario {

    private long idUsuario;
    private String nomeUsuario;
    private String nome;
    private boolean perfilPrivado;

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPerfilPrivado() {
        return perfilPrivado;
    }

    public void setPerfilPrivado(boolean perfilPrivado) {
        this.perfilPrivado = perfilPrivado;
    }
}
