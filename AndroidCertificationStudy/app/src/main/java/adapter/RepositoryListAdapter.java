package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.brunobertelli.R;
import model.Repository;

/**
 * Created by bruno.bertelli on 06/04/2017.
 */

public class RepositoryListAdapter extends RecyclerView.Adapter{

    private List<Repository> repositories;
    private Context context;

    public RepositoryListAdapter(List<Repository> repositories, Context context){
        this.repositories = repositories;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_repositories, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RepositoryViewHolder repositoryViewHolder = (RepositoryViewHolder) holder;
        Repository repository = repositories.get(position);
        repositoryViewHolder.name.setText(repository.name);
        repositoryViewHolder.description.setText(repository.description);
        repositoryViewHolder.language.setText(repository.language);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        repositoryViewHolder.updatedAt.setText(formatter.format(repository.updatedAt));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
}