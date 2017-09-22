package com.bertelli.pocketls;

import java.util.ArrayList;

import com.bertelli.pocketls.adapter.CustomGridViewAdapter;
import com.bertelli.pocketls.model.FavListItemModel;
import com.startapp.android.publish.StartAppSDK;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AlbunsFragment extends Fragment {
	
	public AlbunsFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		StartAppSDK.init(getActivity(), "102580822", "208457602", true);

		View rootView = inflater.inflate(R.layout.fragment_albuns, container, false);
		
		/*// ADMOB
	    AdView adView = (AdView) rootView.findViewById(R.id.adView_albuns_fragment);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);*/
		
		GridView gridView;
		ArrayList<FavListItemModel> gridArray = new ArrayList<FavListItemModel>();
		CustomGridViewAdapter customGridAdapter;

		// set grid view item
		Bitmap cd1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd1);
		Bitmap cd2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd2);
		Bitmap cd3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd3);
		Bitmap cd4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd4);
		Bitmap cd5 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd5);
		Bitmap cd6 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd6);
		Bitmap cd7 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd7);
		Bitmap cd8 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd8);
		Bitmap cd9 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cd15);

		final String titulo1 = getResources().getString(R.string.cd1);
		final String titulo2 = getResources().getString(R.string.cd2);
		final String titulo3 = getResources().getString(R.string.cd3);
		final String titulo4 = getResources().getString(R.string.cd4);
		final String titulo5 = getResources().getString(R.string.cd5);
		final String titulo6 = getResources().getString(R.string.cd6);
		final String titulo7 = getResources().getString(R.string.cd7);
		final String titulo8 = getResources().getString(R.string.cd8);
		final String titulo9 = getResources().getString(R.string.cd15);

		gridArray.add(new FavListItemModel(cd1, titulo1));
		gridArray.add(new FavListItemModel(cd2, titulo2));
		gridArray.add(new FavListItemModel(cd3, titulo3));
		gridArray.add(new FavListItemModel(cd4, titulo4));
		gridArray.add(new FavListItemModel(cd5, titulo5));
		gridArray.add(new FavListItemModel(cd6, titulo6));
		gridArray.add(new FavListItemModel(cd7, titulo7));
		gridArray.add(new FavListItemModel(cd8, titulo8));
		gridArray.add(new FavListItemModel(cd9, titulo9));

		gridView = (GridView) rootView.findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(getActivity(),
				R.layout.row_albuns_gridview, gridArray);
		gridView.setAdapter(customGridAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				Integer p = position;
				
				if (p.equals(0)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "1");
					intent.putExtra("tituloAlbum", titulo1);
					startActivity(intent);
				}
				if (p.equals(1)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "2");
					intent.putExtra("tituloAlbum", titulo2);
					startActivity(intent);
				}
				if (p.equals(2)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "3");
					intent.putExtra("tituloAlbum", titulo3);
					startActivity(intent);
				}
				if (p.equals(3)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "4");
					intent.putExtra("tituloAlbum", titulo4);
					startActivity(intent);
				}
				if (p.equals(4)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "5");
					intent.putExtra("tituloAlbum", titulo5);
					startActivity(intent);
				}
				if (p.equals(5)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "6");
					intent.putExtra("tituloAlbum", titulo6);
					startActivity(intent);
				}
				if (p.equals(6)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "7");
					intent.putExtra("tituloAlbum", titulo7);
					startActivity(intent);
				}
				if (p.equals(7)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "8");
					intent.putExtra("tituloAlbum", titulo8);
					startActivity(intent);
				}
				if (p.equals(8)) {
					Intent intent = new Intent(getActivity(),
							ListaMusicasActivity.class);
					intent.putExtra("idAlbum", "15");
					intent.putExtra("tituloAlbum", titulo9);
					startActivity(intent);
				}
			}

		});
		
		return rootView;
		
	}
	
	
}
