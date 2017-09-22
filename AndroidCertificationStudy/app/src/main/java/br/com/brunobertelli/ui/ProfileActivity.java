package br.com.brunobertelli.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.brunobertelli.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.User;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.user_avatar) ImageView imageViewAvatar;
    @BindView(R.id.name) TextView textViewName;
    @BindView(R.id.username) TextView textViewUsername;
    @BindView(R.id.company_user) TextView textViewCompanyUser;
    @BindView(R.id.location_user) TextView textViewLocationUser;
    @BindView(R.id.repositories_counter) TextView repositoriesCounter;
    @BindView(R.id.followers_counter) TextView followersCounter;
    @BindView(R.id.following_counter) TextView followingCounter;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setUserInformation();
        setNavigateUpButton();
        setWindowTitle(getString(R.string.screen_title_profile));
    }

    private void setUserInformation(){
        user = getIntent().getParcelableExtra("user");

        Picasso.with(this).load(user.avatar).fit().into(imageViewAvatar);

        if (user.name == null || user.name.isEmpty()){
            textViewName.setVisibility(View.GONE);
        }else {
            textViewName.setText(user.name);
        }

        textViewUsername.setText(user.login);

        if (user.company == null || user.company.isEmpty()){
            textViewCompanyUser.setVisibility(View.GONE);
        }else {
            textViewCompanyUser.setText(user.company);
        }

        if (user.location == null || user.location.isEmpty()){
            textViewLocationUser.setVisibility(View.GONE);
        }else {
            textViewLocationUser.setText(user.location);
        }

        repositoriesCounter.setText(user.repositories);
        followersCounter.setText(user.followers);
        followingCounter.setText(user.following);
    }

    public void showRepositories(View v){
        Intent i = new Intent(this, ListRepositoryActivity.class);
        i.putExtra("user", user);
        startActivity(i);
    }

    public void showFollowers(View v){
        Intent i = new Intent(this, ListUserActivity.class);
        i.putExtra("user", user);
        i.putExtra("listUser", "showFollowers");
        startActivity(i);
    }

    public void showFollowing(View v){
        Intent i = new Intent(this, ListUserActivity.class);
        i.putExtra("user", user);
        i.putExtra("listUser", "showFollowing");
        startActivity(i);
    }
}
