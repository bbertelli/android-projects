package br.com.hw.beon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.hw.beon.R;
import br.com.hw.beon.holder.LogAtividadeViewHolder;
import br.com.hw.beon.model.LogAtividade;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class LogAtividadeAdapter extends RecyclerView.Adapter<LogAtividadeViewHolder> {
    private Context context;
    private ArrayList<LogAtividade> itens;

    public LogAtividadeAdapter(Context context, ArrayList<LogAtividade> itens) {
        this.context = context;
        this.itens = itens;
    }

    @Override
    public LogAtividadeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_lista_log_atividade, parent, false);
        return new LogAtividadeViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(LogAtividadeViewHolder holder, int position) {
        LogAtividade logAtividade = itens.get(position);
        //TODO: buscar a imagem do usuario
        holder.imageViewFotoUser.setBackground(context.getDrawable(R.drawable.img_user_small));
        //TODO: buscar o nome do usuario pelo id
        holder.textViewUser.setText(String.valueOf(logAtividade.getIdUsuario()));
        //TODO: converter a data
        holder.textViewDate.setText(String.valueOf(logAtividade.getData().getTime()));
        holder.textViewAtividadeUser.setText(logAtividade.getAtividade());
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
