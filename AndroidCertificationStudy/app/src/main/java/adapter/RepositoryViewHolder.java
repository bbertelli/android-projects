package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.brunobertelli.R;

/**
 * Created by bruno.bertelli on 06/04/2017.
 */

public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    final TextView name;
    final TextView description;
    final TextView language;
    final TextView updatedAt;

    public RepositoryViewHolder(View view) {
        super(view);
        name = (TextView) view.findViewById(R.id.name_repo);
        description = (TextView) view.findViewById(R.id.desc_repo);
        language = (TextView) view.findViewById(R.id.lang_repo);
        updatedAt = (TextView) view.findViewById(R.id.updated_repo);
    }
}
