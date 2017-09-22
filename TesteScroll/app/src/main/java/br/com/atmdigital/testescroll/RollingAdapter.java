package br.com.atmdigital.testescroll;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RollingAdapter extends BaseAdapter {

    private List<String> lista;
    private Context context;

    public RollingAdapter(List<String> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rolling, parent, false);
        }

        TextView itemTextView = (TextView) convertView.findViewById(R.id.item);
        String item = (String) getItem(position);
        itemTextView.setText(item);

        return convertView;
    }
}