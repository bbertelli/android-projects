package br.com.hw.beon.activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import br.com.hw.beon.R;
import br.com.hw.beon.fragment.ContactsFragment;
import br.com.hw.beon.fragment.GlobalFragment;
import br.com.hw.beon.fragment.HomeFragment;
import br.com.hw.beon.fragment.NotificationFragment;
import br.com.hw.beon.fragment.PerfilFragment;

public class MainActivity extends AppCompatActivity {
    private ImageButton buttonHome;
    private ImageButton buttonGlobal;
    private ImageButton buttonContacts;
    private ImageButton buttonNotification;
    private ImageButton buttonPerfil;
    private RelativeLayout layoutButtonHome;
    private RelativeLayout layoutButtonGlobal;
    private RelativeLayout layoutButtonContacts;
    private RelativeLayout layoutButtonNotification;
    private RelativeLayout layoutButtonPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponents();
        setComponents();
        displaySelectedPage(0, true);
    }

    private void getComponents(){
        layoutButtonHome = (RelativeLayout) findViewById(R.id.layout_btn_home);
        layoutButtonGlobal = (RelativeLayout) findViewById(R.id.layout_btn_global);
        layoutButtonContacts = (RelativeLayout) findViewById(R.id.layout_btn_contacts);
        layoutButtonNotification = (RelativeLayout) findViewById(R.id.layout_btn_notification);
        layoutButtonPerfil = (RelativeLayout) findViewById(R.id.layout_btn_perfil);
        buttonHome = (ImageButton) findViewById(R.id.btn_home);
        buttonGlobal = (ImageButton) findViewById(R.id.btn_global);
        buttonContacts = (ImageButton) findViewById(R.id.btn_contacts);
        buttonNotification = (ImageButton) findViewById(R.id.btn_notification);
        buttonPerfil = (ImageButton) findViewById(R.id.btn_perfil);
    }

    private void setComponents(){
        setToolbarBottom();
    }

    private void displaySelectedPage(int position, boolean showDefault) {
        Fragment fragment = null;

        if (showDefault){
            cleanPageSelection();
            buttonHome.setBackground(getDrawable(R.drawable.ic_home_selected));
            fragment = new HomeFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new GlobalFragment();
                    break;
                case 2:
                    fragment = new ContactsFragment();
                    break;
                case 3:
                    fragment = new NotificationFragment();
                    break;
                case 4:
                    fragment = new PerfilFragment();
                    break;
                default:
                    break;
            }

            if (fragment != null) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            } else {
                Toast.makeText(MainActivity.this, "Erro ao exibir tela", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setToolbarBottom(){
        setButtonHome();
        setButtonGlobal();
        setButtonContacts();
        setButtonNotification();
        setButtonPerfil();
    }

    private void setButtonHome(){
        buttonHome.setOnClickListener(onHomeClickListener());
        layoutButtonHome.setOnClickListener(onHomeClickListener());
    }

    private void setButtonGlobal(){
        buttonGlobal.setOnClickListener(onGlobalClickListener());
        layoutButtonGlobal.setOnClickListener(onGlobalClickListener());
    }

    private void setButtonContacts(){
        buttonContacts.setOnClickListener(onContactsClickListener());
        layoutButtonContacts.setOnClickListener(onContactsClickListener());
    }

    private void setButtonNotification(){
        buttonNotification.setOnClickListener(onNotificationClickListener());
        layoutButtonNotification.setOnClickListener(onNotificationClickListener());
    }

    private void setButtonPerfil(){
        buttonPerfil.setOnClickListener(onPerfilClickListener());
        layoutButtonPerfil.setOnClickListener(onPerfilClickListener());
    }

    private View.OnClickListener onHomeClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanPageSelection();
                buttonHome.setBackground(getDrawable(R.drawable.ic_home_selected));
                displaySelectedPage(0, false);
            }
        };
    }

    private View.OnClickListener onGlobalClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanPageSelection();
                buttonGlobal.setBackground(getDrawable(R.drawable.ic_search_selected));
                displaySelectedPage(1, false);
            }
        };
    }

    private View.OnClickListener onContactsClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanPageSelection();
                buttonContacts.setBackground(getDrawable(R.drawable.ic_contacts_selected));
                displaySelectedPage(2, false);
            }
        };
    }

    private View.OnClickListener onNotificationClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanPageSelection();
                buttonNotification.setBackground(getDrawable(R.drawable.ic_notification_selected));
                displaySelectedPage(3, false);
            }
        };
    }

    private View.OnClickListener onPerfilClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanPageSelection();
                buttonPerfil.setBackground(getDrawable(R.drawable.ic_perfil_selected));
                displaySelectedPage(4, false);
            }
        };
    }

    private void cleanPageSelection(){
        buttonHome.setBackground(getDrawable(R.drawable.ic_home_unselected));
        buttonGlobal.setBackground(getDrawable(R.drawable.ic_search_unselected));
        buttonContacts.setBackground(getDrawable(R.drawable.ic_contacts_unselected));
        buttonNotification.setBackground(getDrawable(R.drawable.ic_notification_unselected));
        buttonPerfil.setBackground(getDrawable(R.drawable.ic_perfil_unselected));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
