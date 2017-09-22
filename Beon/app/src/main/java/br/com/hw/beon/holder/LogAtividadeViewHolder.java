package br.com.hw.beon.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.hw.beon.R;

/**
 * Created by bruno.bertelli on 15/07/2016.
 */
public class LogAtividadeViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    public ImageView imageViewFotoUser;
    public TextView textViewDate;
    public TextView textViewUser;
    public TextView textViewAtividadeUser;

    public LogAtividadeViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        imageViewFotoUser = (ImageView) itemView.findViewById(R.id.img_conta_lista_log_atividade);
        textViewAtividadeUser = (TextView) itemView.findViewById(R.id.atividade_lista_log_atividade);
        textViewDate = (TextView) itemView.findViewById(R.id.date_lista_log_atividade);
        textViewUser = (TextView) itemView.findViewById(R.id.username_lista_log_atividade);
    }
}
