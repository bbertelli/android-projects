package br.com.brunobertelli.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class ListRepositoryActivity extends BaseActivity {

    @BindView(R.id.list) RecyclerView recyclerView;
    @BindView(R.id.loading) ProgressBar progressBarLoading;

    private RepositoryListAdapter repositoryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        setNavigateUpButton();
        setWindowTitle(getString(R.string.screen_title_repositories));
    }

    @Override
    protected void onResume() {
        super.onResume();

        User user = getIntent().getParcelableExtra("user");

        if (repositoryListAdapter == null){
            showLoading();
            APIClient.getInstance().getUserRepositories(user.login);
        }else
            setupRepositoryRecyclerView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRepositoriesResponse(List<Repository> repositories) {
        setupRepositoriesAdapter(repositories);
    }

    private void showLoading(){
        recyclerView.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    private void showList(){
        progressBarLoading.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void setupRepositoryRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(repositoryListAdapter);
    }

    private void setupRepositoriesAdapter(List<Repository> repositories) {
        showList();

        repositoryListAdapter = new RepositoryListAdapter(repositories, this);
        recyclerView.setAdapter(repositoryListAdapter);
        setupRepositoryRecyclerView();
        repositoryListAdapter.notifyDataSetChanged();
    }
}
