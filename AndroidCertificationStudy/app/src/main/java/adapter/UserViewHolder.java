package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.brunobertelli.R;

/**
 * Created by bruno.bertelli on 06/04/2017.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    final ImageView avatar;
    final TextView name;
    final TextView login;
    final TextView company;
    final TextView location;

    public UserViewHolder(View view) {
        super(view);
        avatar = (ImageView) view.findViewById(R.id.avatar_user);
        name = (TextView) view.findViewById(R.id.name_user);
        login = (TextView) view.findViewById(R.id.login_user);
        company = (TextView) view.findViewById(R.id.company_user);
        location = (TextView) view.findViewById(R.id.location_user);
    }
}
