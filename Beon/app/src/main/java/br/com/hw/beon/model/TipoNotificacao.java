package br.com.hw.beon.model;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class TipoNotificacao {

    private int idTipoNotificacao;
    private String tipoNotificacao;
    private boolean ativo;

    public int getIdTipoNotificacao() {
        return idTipoNotificacao;
    }

    public void setIdTipoNotificacao(int idTipoNotificacao) {
        this.idTipoNotificacao = idTipoNotificacao;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
