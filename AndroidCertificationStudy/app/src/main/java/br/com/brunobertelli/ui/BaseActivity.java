package br.com.brunobertelli.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventBus.getDefault().register(this);
  }

  @Override
  protected void onDestroy() {
    EventBus.getDefault().unregister(this);
    super.onDestroy();
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  public void setNavigateUpButton(){
    if (getSupportActionBar() != null) {
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  public void setWindowTitle(String title){
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(title);
    }
  }

  public void hideActionBar(){
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }
  }

  public void hideKeyboard(){
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onRequestError(retrofit2.Response response) {
    if (handleError(response))
      return;
    Log.e("BaseActivity", "onRequestError: " + response.code() + response.body());
  }

  protected boolean handleError(retrofit2.Response response) {
    return false;
  }
}
