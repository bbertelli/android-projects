package br.com.brunobertelli.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import adapter.RepositoryListAdapter;
import adapter.UserListAdapter;
import br.com.brunobertelli.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Repository;
import model.User;
import network.APIClient;

public class ListUserActivity extends BaseActivity {

    @BindView(R.id.list) RecyclerView recyclerView;
    @BindView(R.id.loading) ProgressBar progressBarLoading;

    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        setNavigateUpButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        showList();

        User user = getIntent().getParcelableExtra("user");
        String listUser = getIntent().getStringExtra("listUser");

        switch (listUser){
            case "showFollowers":
                setWindowTitle(getString(R.string.screen_title_followers));
                if (userListAdapter == null){
                    showLoading();
                    APIClient.getInstance().getFollowers(user.login);
                }else{
                    setupUserRecyclerView();
                }
                break;
            case "showFollowing":
                setWindowTitle(getString(R.string.screen_title_following));
                if (userListAdapter == null){
                    showLoading();
                    APIClient.getInstance().getFollowing(user.login);
                }else{
                    setupUserRecyclerView();
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserListResponse(List<User> users) {
        setupUsersAdapter(users);
    }

    private void showLoading(){
        recyclerView.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    private void showList(){
        progressBarLoading.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void setupUserRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userListAdapter);
    }

    private void setupUsersAdapter(List<User> users) {
        showList();

        userListAdapter = new UserListAdapter(users, this, new UserListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                getUserInfo(user.login);
            }
        });
        recyclerView.setAdapter(userListAdapter);
        setupUserRecyclerView();
        userListAdapter.notifyDataSetChanged();
    }

    private void getUserInfo(String userLogin){
        showLoading();
        APIClient.getInstance().getUser(userLogin);
    }
}
