package br.com.hw.beon.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

import br.com.hw.beon.R;
import br.com.hw.beon.adapter.LogAtividadeAdapter;
import br.com.hw.beon.model.LogAtividade;

/**
 * Created by bruno.bertelli on 30/06/2016.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewListaLogAtividade;
    private ArrayList<LogAtividade> atividades;
    private LogAtividadeAdapter logAtividadeAdapter;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        simularDadosLogAtividade();
        logAtividadeAdapter = new LogAtividadeAdapter(getActivity(), atividades);
        recyclerViewListaLogAtividade = (RecyclerView) rootView.findViewById(R.id.lista_log_atividade);
        recyclerViewListaLogAtividade.setAdapter(logAtividadeAdapter);
        recyclerViewListaLogAtividade.setItemAnimator(new DefaultItemAnimator());
        recyclerViewListaLogAtividade.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    private void simularDadosLogAtividade(){

        atividades = new ArrayList<LogAtividade>();
        LogAtividade logAtividade1 = new LogAtividade();
        logAtividade1.setIdUsuario(001L);
        logAtividade1.setAtividade("começou a seguir você.");
        logAtividade1.setData(new Date());

        LogAtividade logAtividade2 = new LogAtividade();
        logAtividade2.setIdUsuario(002L);
        logAtividade2.setAtividade("Seu amigo Jonas do Facebook está no be-on como jonasgodines23.");
        logAtividade2.setData(new Date());

        atividades.add(logAtividade1);
        atividades.add(logAtividade2);
    }
}
