package br.com.hw.beon.model;

import java.util.Date;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class LogAtividade {

    private long idAtividade;
    private long idUsuario;
    private String atividade;
    private Date data;

    public long getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(long idAtividade) {
        this.idAtividade = idAtividade;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
