package br.com.hw.beon.model;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class TipoLink {

    private int idTipoLink;
    private String tipo;
    private boolean ativo;

    public int getIdTipoLink() {
        return idTipoLink;
    }

    public void setIdTipoLink(int idTipoLink) {
        this.idTipoLink = idTipoLink;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
