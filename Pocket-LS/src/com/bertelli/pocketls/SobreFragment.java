package com.bertelli.pocketls;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SobreFragment extends Fragment {
	
	public SobreFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_sobre, container, false);
        
        /*// ADMOB
        AdView adView = (AdView) rootView.findViewById(R.id.adView_sobre_fragment);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);*/
         
        return rootView;
    }
}
