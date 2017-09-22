package br.com.hw.beon.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.hw.beon.R;

/**
 * Created by bruno.bertelli on 30/06/2016.
 */
public class NotificationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }
}
