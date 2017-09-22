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

public class VideografiaFragment extends Fragment {
	
	public VideografiaFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		StartAppSDK.init(getActivity(), "102580822", "208457602", true);
		
        View rootView = inflater.inflate(R.layout.fragment_videografia, container, false);
        
        /*// ADMOB
	    AdView adView = (AdView) rootView.findViewById(R.id.adView_videografia_fragment);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);*/
        
        GridView gridView;
		ArrayList<FavListItemModel> gridArray = new ArrayList<FavListItemModel>();
		CustomGridViewAdapter customGridAdapter;
		
		// set grid view item
		Bitmap op1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd9);
		Bitmap op2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd10);
		Bitmap op3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd11);
		Bitmap op4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd12);
		Bitmap op5 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd13);
		Bitmap op6 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd14);
		Bitmap op7 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd16);
				
		final String titulo1 = getResources().getString(R.string.vid_op1);
		final String titulo2 = getResources().getString(R.string.vid_op2);
		final String titulo3 = getResources().getString(R.string.vid_op3);
		final String titulo4 = getResources().getString(R.string.vid_op4);
		final String titulo5 = getResources().getString(R.string.vid_op5);
		final String titulo6 = getResources().getString(R.string.vid_op6);
		final String titulo7 = getResources().getString(R.string.vid_op7);
		
		gridArray.add(new FavListItemModel(op1, titulo1));
		gridArray.add(new FavListItemModel(op2, titulo2));
		gridArray.add(new FavListItemModel(op3, titulo3));
		gridArray.add(new FavListItemModel(op4, titulo4));
		gridArray.add(new FavListItemModel(op5, titulo5));
		gridArray.add(new FavListItemModel(op6, titulo6));
		gridArray.add(new FavListItemModel(op7, titulo7));
		
		gridView = (GridView) rootView.findViewById(R.id.video_gridView);
		customGridAdapter = new CustomGridViewAdapter(getActivity(),
				R.layout.row_albuns_gridview, gridArray);
		gridView.setAdapter(customGridAdapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				Integer p = position;
				
				if (p.equals(0)) {
					Intent intent = new Intent(getActivity().getBaseContext(), ListaMusicasActivity.class);
	    		    intent.putExtra("idAlbum", "9");
	    		    intent.putExtra("tituloAlbum", getResources().getString(R.string.cd9));
	    		  getActivity().startActivity(intent);
				}
				if (p.equals(1)) {
					Intent intent = new Intent(getActivity().getBaseContext(), ListaMusicasActivity.class);
		  		    intent.putExtra("idAlbum", "10");
		  		  intent.putExtra("tituloAlbum", getResources().getString(R.string.cd10));
		  		  getActivity().startActivity(intent);
				}
				if (p.equals(2)) {
					Intent intent = new Intent(getActivity().getBaseContext(), ListaMusicasActivity.class);
		  		    intent.putExtra("idAlbum", "11");
		  		  intent.putExtra("tituloAlbum", getResources().getString(R.string.cd11));
		  		  getActivity().startActivity(intent);
				}
				if (p.equals(3)) {
					Intent intent = new Intent(getActivity().getBaseContext(), ListaMusicasActivity.class);
		  		    intent.putExtra("idAlbum", "12");
		  		  intent.putExtra("tituloAlbum", getResources().getString(R.string.cd12));
		  		  getActivity().startActivity(intent);
				}
				if (p.equals(4)) {
					Intent intent = new Intent(getActivity().getBaseContext(), ListaMusicasActivity.class);
		  		    intent.putExtra("idAlbum", "13");
		  		  intent.putExtra("tituloAlbum", getResources().getString(R.string.cd13));
		  		  getActivity().startActivity(intent);
				}
				if (p.equals(5)) {
					Intent intent = new Intent(getActivity().getBaseContext(), ListaMusicasActivity.class);
		  		    intent.putExtra("idAlbum", "14");
		  		  intent.putExtra("tituloAlbum", getResources().getString(R.string.cd14));
		  		  getActivity().startActivity(intent);
				}
				if (p.equals(6)) {
					Intent intent = new Intent(getActivity().getBaseContext(), ListaMusicasActivity.class);
		  		    intent.putExtra("idAlbum", "16");
		  		  intent.putExtra("tituloAlbum", getResources().getString(R.string.cd16));
		  		  getActivity().startActivity(intent);
				}
			}

		});
        
        return rootView;
    }

}
