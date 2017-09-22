package br.com.hw.beon.model;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class Link {

    private int idLink;
    private int idTipoLink;
    private int idRede;
    private boolean ativo;

    public int getIdLink() {
        return idLink;
    }

    public void setIdLink(int idLink) {
        this.idLink = idLink;
    }

    public int getIdTipoLink() {
        return idTipoLink;
    }

    public void setIdTipoLink(int idTipoLink) {
        this.idTipoLink = idTipoLink;
    }

    public int getIdRede() {
        return idRede;
    }

    public void setIdRede(int idRede) {
        this.idRede = idRede;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
