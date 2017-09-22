package br.com.brunobertelli.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.HttpURLConnection;

import br.com.brunobertelli.R;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.User;
import network.APIClient;
import okhttp3.Response;
import retrofit2.http.HTTP;
import utils.DialogUtils;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.et_username) EditText editTextUserName;
    @BindView(R.id.loading) ProgressBar progressBarLoading;
    @BindView(R.id.btn_search) Button buttonSearch;
    @BindString(R.string.attention) String titleDialogError;
    @BindString(R.string.user_not_found_msg) String userNotFoundError;
    @BindString(R.string.generic_server_error) String serverError;
    @BindString(R.string.required_field) String requiredFieldError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        hideActionBar();
        hideKeyboard();
        setEditTextSearchButton(editTextUserName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextUserName.setText("");
        showSearch();
    }

    public void doSearch(View v){
        showLoading();

        if (TextUtils.isEmpty(editTextUserName.getText())){
            showSearch();
            editTextUserName.setError(requiredFieldError, getDrawable(android.R.drawable.ic_dialog_info));
        }else{
            APIClient.getInstance().getUser(editTextUserName.getText().toString());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAPIClientResponse(User user) {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("user", user);
        startActivity(i);
    }

    @Override
    protected boolean handleError(retrofit2.Response response) {
        showError(response.code());
        showSearch();
        editTextUserName.setText("");
        return true;
    }

    private void showLoading(){
        buttonSearch.setVisibility(View.GONE);
        editTextUserName.setEnabled(false);
        editTextUserName.setClickable(false);
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    private void showSearch(){
        progressBarLoading.setVisibility(View.GONE);
        editTextUserName.setEnabled(true);
        editTextUserName.setClickable(true);
        buttonSearch.setVisibility(View.VISIBLE);
    }

    private void showError(int errorCode){
        switch (errorCode){
            case HttpURLConnection.HTTP_NOT_FOUND:
                DialogUtils.showSimpleAlertDialog(titleDialogError, userNotFoundError, this, null);
                break;
            default:
                DialogUtils.showSimpleAlertDialog(titleDialogError, serverError, this, null);
        }
    }

    private void setEditTextSearchButton(EditText view){
        view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    doSearch(buttonSearch);
                    return true;
                }
                return false;
            }
        });
    }
}
