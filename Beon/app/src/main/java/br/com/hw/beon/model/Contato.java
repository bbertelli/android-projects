package br.com.hw.beon.model;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class Contato {

    private long idContato;
    private long idUsuario;
    private long idLinkContato;

    public long getIdContato() {
        return idContato;
    }

    public void setIdContato(long idContato) {
        this.idContato = idContato;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdLinkContato() {
        return idLinkContato;
    }

    public void setIdLinkContato(long idLinkContato) {
        this.idLinkContato = idLinkContato;
    }
}
