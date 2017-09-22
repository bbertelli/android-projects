package br.com.atmdigital.testescroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jkb.rollinglayout.RollingLayout;
import com.jkb.rollinglayout.RollingLayoutAction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RollingLayoutAction.OnRollingChangedListener,
        RollingLayoutAction.OnRollingItemClickListener {

    private RollingAdapter rollingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollingAdapter = new RollingAdapter(criarListaTeste(), this);
        initDownUp();
    }

    private ArrayList<String> criarListaTeste(){
        ArrayList<String> listaItens = new ArrayList<>();
        for( int i = 0; i < 20; i++ )
        {
            listaItens.add("Item Exemplo " + i);
        }

        return listaItens;
    }

    private void initDownUp() {
        RollingLayout rollingDownUp = (RollingLayout) findViewById(R.id.rollingDownUp);
        rollingDownUp.setAdapter(rollingAdapter);
        rollingDownUp.startRolling();

        rollingDownUp.addOnRollingChangedListener(this);
        rollingDownUp.setOnRollingItemClickListener(this);
    }

    @Override
    public void onRollingChanged(View rollingLayout, int currentPosition, int sumPosition) {

    }

    @Override
    public void onRollingItemClick(View view, ViewGroup parent, int position) {

    }
}
