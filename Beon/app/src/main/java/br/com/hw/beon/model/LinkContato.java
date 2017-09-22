package br.com.hw.beon.model;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class LinkContato {

    private long idLinkContato;
    private long idContato;
    private long idLink;

    public long getIdLinkContato() {
        return idLinkContato;
    }

    public void setIdLinkContato(long idLinkContato) {
        this.idLinkContato = idLinkContato;
    }

    public long getIdContato() {
        return idContato;
    }

    public void setIdContato(long idContato) {
        this.idContato = idContato;
    }

    public long getIdLink() {
        return idLink;
    }

    public void setIdLink(long idLink) {
        this.idLink = idLink;
    }
}
