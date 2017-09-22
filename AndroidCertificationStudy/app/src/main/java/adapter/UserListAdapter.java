package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.brunobertelli.R;
import model.User;

/**
 * Created by bruno.bertelli on 06/04/2017.
 */

public class UserListAdapter extends RecyclerView.Adapter{

    private List<User> users;
    private Context context;
    private OnItemClickListener listener;

    public UserListAdapter(List<User> users, Context context, OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        final User user = users.get(position);

        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onItemClick(user);
            }
        });

        Picasso.with(context).load(user.avatar).fit().into(userViewHolder.avatar);

        if (user.name == null || user.name.isEmpty())
            userViewHolder.name.setVisibility(View.INVISIBLE);
        else
            userViewHolder.name.setText(user.name);

        userViewHolder.login.setText(user.login);

        if (user.company == null || user.company.isEmpty())
            userViewHolder.company.setVisibility(View.INVISIBLE);
        else
            userViewHolder.company.setText(user.company);

        if (user.location == null || user.location.isEmpty())
            userViewHolder.location.setVisibility(View.INVISIBLE);
        else
            userViewHolder.location.setText(user.location);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }
}