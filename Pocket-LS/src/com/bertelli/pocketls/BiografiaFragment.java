package com.bertelli.pocketls;

import java.util.ArrayList;

import com.bertelli.pocketls.adapter.CustomGridViewAdapter;
import com.bertelli.pocketls.model.FavListItemModel;
import com.startapp.android.publish.StartAppSDK;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class BiografiaFragment extends Fragment {
	
	public BiografiaFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		StartAppSDK.init(getActivity(), "102580822", "208457602", true);
 
        View rootView = inflater.inflate(R.layout.fragment_biografia, container, false);
        
        /*// ADMOB
	    AdView adView = (AdView) rootView.findViewById(R.id.adView_biografia_fragment);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);*/
        
        GridView gridView;
		ArrayList<FavListItemModel> gridArray = new ArrayList<FavListItemModel>();
		CustomGridViewAdapter customGridAdapter;
		
		// set grid view item
		Bitmap op1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bio_ic1);
		Bitmap op2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bio_ic2);
		Bitmap op3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bio_ic3);
		Bitmap op4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bio_ic4);
				
		final String titulo1 = getResources().getString(R.string.bio_op1);
		final String titulo2 = getResources().getString(R.string.bio_op2);
		final String titulo3 = getResources().getString(R.string.bio_op3);
		final String titulo4 = getResources().getString(R.string.bio_op4);
		
		gridArray.add(new FavListItemModel(op1, titulo1));
		gridArray.add(new FavListItemModel(op2, titulo2));
		gridArray.add(new FavListItemModel(op3, titulo3));
		gridArray.add(new FavListItemModel(op4, titulo4));
		
		gridView = (GridView) rootView.findViewById(R.id.bio_gridView);
		customGridAdapter = new CustomGridViewAdapter(getActivity(),
				R.layout.row_albuns_gridview, gridArray);
		gridView.setAdapter(customGridAdapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				Integer p = position;
				
				if (p.equals(0)) {
					Intent intent = new Intent(getActivity().getBaseContext(), BiografiaActivity.class);
	    			intent.putExtra("bio", "1");
	    		    getActivity().startActivity(intent);
				}
				if (p.equals(1)) {
					Intent intent = new Intent(getActivity().getBaseContext(), BiografiaActivity.class);
		  			intent.putExtra("bio", "2");
		  		    getActivity().startActivity(intent);
				}
				if (p.equals(2)) {
					Intent intent = new Intent(getActivity().getBaseContext(), BiografiaActivity.class);
		  			intent.putExtra("bio", "3");
		  		    getActivity().startActivity(intent);
				}
				if (p.equals(3)) {
					Intent intent = new Intent(getActivity().getBaseContext(), BiografiaActivity.class);
		  			intent.putExtra("bio", "4");
		  		    getActivity().startActivity(intent);
				}
			}

		});
         
        return rootView;
    }
}
