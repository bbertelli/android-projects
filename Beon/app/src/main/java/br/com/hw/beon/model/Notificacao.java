package br.com.hw.beon.model;

import java.util.Date;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class Notificacao {

    private long idNotificacao;
    private long idUsuarioOrigem;
    private long idUsuarioDestino;
    private long idTipoNotificacao;
    private String Notificacao;
    private Date data;

    public long getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(long idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public long getIdUsuarioOrigem() {
        return idUsuarioOrigem;
    }

    public void setIdUsuarioOrigem(long idUsuarioOrigem) {
        this.idUsuarioOrigem = idUsuarioOrigem;
    }

    public long getIdUsuarioDestino() {
        return idUsuarioDestino;
    }

    public void setIdUsuarioDestino(long idUsuarioDestino) {
        this.idUsuarioDestino = idUsuarioDestino;
    }

    public long getIdTipoNotificacao() {
        return idTipoNotificacao;
    }

    public void setIdTipoNotificacao(long idTipoNotificacao) {
        this.idTipoNotificacao = idTipoNotificacao;
    }

    public String getNotificacao() {
        return Notificacao;
    }

    public void setNotificacao(String notificacao) {
        Notificacao = notificacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
