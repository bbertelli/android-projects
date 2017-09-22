package com.bertelli.pocketls.adapter;

import com.bertelli.pocketls.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleCursorAdapter extends SimpleCursorAdapter {

    public MySimpleCursorAdapter(Activity activity, int layout, Cursor cur,
             String[] from, int[] to) {
        super(activity, layout, cur, from, to);
    }

	public void bindView(View v, Context context, Cursor c) {
	
    	   String numMusica = c.getString(c.getColumnIndex("_id"));
           String nomeMusica = c.getString(c.getColumnIndex("nome"));
           String albumMusica = c.getString(c.getColumnIndex("album_id"));
           String tempoMusica = c.getString(c.getColumnIndex("tempo"));

           ImageView imgAlbum = (ImageView) v.findViewById(R.id.fav_img_album);
           TextView num_text = (TextView) v.findViewById(R.id.fav_num);
           TextView nome_text = (TextView) v.findViewById(R.id.fav_nome);
           TextView album_text = (TextView) v.findViewById(R.id.fav_album);
           TextView tempo_text = (TextView) v.findViewById(R.id.fav_tempo);
           
           num_text.setText(numMusica);
           nome_text.setText(nomeMusica);
           tempo_text.setText(tempoMusica);
           
           //verificar album          
           if(albumMusica.equals("1")){
        	   album_text.setText(R.string.cd1);
        	   imgAlbum.setBackgroundResource(R.drawable.cd1);
           }
           if(albumMusica.equals("2")){
        	   album_text.setText(R.string.cd2);
        	   imgAlbum.setBackgroundResource(R.drawable.cd2);
           }
           if(albumMusica.equals("3")){
        	   album_text.setText(R.string.cd3);
        	   imgAlbum.setBackgroundResource(R.drawable.cd3);
           }
           if(albumMusica.equals("4")){
        	   album_text.setText(R.string.cd4);
        	   imgAlbum.setBackgroundResource(R.drawable.cd4);
           }
           if(albumMusica.equals("5")){
        	   album_text.setText(R.string.cd5);
        	   imgAlbum.setBackgroundResource(R.drawable.cd5);
           }
           if(albumMusica.equals("6")){
        	   album_text.setText(R.string.cd6);
        	   imgAlbum.setBackgroundResource(R.drawable.cd6);
           }
           if(albumMusica.equals("7")){
        	   album_text.setText(R.string.cd7);
        	   imgAlbum.setBackgroundResource(R.drawable.cd7);
           }
           if(albumMusica.equals("8")){
        	   album_text.setText(R.string.cd8);
        	   imgAlbum.setBackgroundResource(R.drawable.cd8);
           }
           if(albumMusica.equals("9")){
        	   album_text.setText(R.string.cd9);
        	   imgAlbum.setBackgroundResource(R.drawable.cd9);
           }
           if(albumMusica.equals("10")){
        	   album_text.setText(R.string.cd10);
        	   imgAlbum.setBackgroundResource(R.drawable.cd10);
           }
           if(albumMusica.equals("11")){
        	   album_text.setText(R.string.cd11);
        	   imgAlbum.setBackgroundResource(R.drawable.cd11);
           }
           if(albumMusica.equals("12")){
        	   album_text.setText(R.string.cd12);
        	   imgAlbum.setBackgroundResource(R.drawable.cd12);
           }
           if(albumMusica.equals("13")){
        	   album_text.setText(R.string.cd13);
        	   imgAlbum.setBackgroundResource(R.drawable.cd13);
           }
           if(albumMusica.equals("14")){
        	   album_text.setText(R.string.cd14);
        	   imgAlbum.setBackgroundResource(R.drawable.cd14);
           }
           if(albumMusica.equals("15")){
        	   album_text.setText(R.string.cd15);
        	   imgAlbum.setBackgroundResource(R.drawable.cd15);
           }
           if(albumMusica.equals("16")){
        	   album_text.setText(R.string.cd16);
        	   imgAlbum.setBackgroundResource(R.drawable.cd16);
           }
    }

}