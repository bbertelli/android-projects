package com.bertelli.pocketls;

import java.io.IOException;

import com.bertelli.pocketls.adapter.MySimpleCursorAdapter;
import com.bertelli.pocketls.db.ExternalDbOpenHelper;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomeFragment extends Fragment {
	
	ImageButton btn_insta;
	ImageButton btn_face;
	ImageButton btn_youtube;
	ImageButton btn_twitter;
	ImageButton btn_gplus;
	ImageButton btn_site;
	
	private static final String DB_NAME = "luan.sqlite3";
	private static final String TABLE_NAME = "musica";
	private static final String KEY_ID_MUSICA = "_id";
	private static final String KEY_ALBUM_ID = "album_id";
	private static final String KEY_NOME = "nome";
	private static final String KEY_TEMPO = "tempo";
	private static final String KEY_FAVORITO = "fav";
	private static final String VALOR_FAVORITO = "1";
	private static final String TABLE_VERSION = "info";
	private static final String KEY_VERSAO = "versao";
	private int versao_atual;
	private int nova_versao;

	private static SQLiteDatabase databaseFav;
	private static MySimpleCursorAdapter dataAdapter;
	private static Cursor cursorFav;
	private static ExternalDbOpenHelper dbOpenHelper;
	
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        btn_insta = (ImageButton) rootView.findViewById(R.id.home_btn_insta);
        btn_face = (ImageButton) rootView.findViewById(R.id.home_btn_face);
        btn_youtube = (ImageButton) rootView.findViewById(R.id.home_btn_youtube);
        btn_twitter = (ImageButton) rootView.findViewById(R.id.home_btn_twitter);
        btn_gplus = (ImageButton) rootView.findViewById(R.id.home_btn_gplus);
        btn_site = (ImageButton) rootView.findViewById(R.id.home_btn_site);
        
        dbOpenHelper = new ExternalDbOpenHelper(getActivity(),DB_NAME);
		nova_versao = dbOpenHelper.DATABASE_VERSION;
		databaseFav = dbOpenHelper.openDataBase();
		pegaVersaoDb();
		atualizarVersaoDb(databaseFav, versao_atual, nova_versao);
	
		cursorFav = pegaMusicasFavoritas();
        
        btn_insta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Uri uri = Uri.parse("http://instagram.com/_u/luansantana");
			    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

			    likeIng.setPackage("com.instagram.android");

			    try {
			        startActivity(likeIng);
			    } catch (ActivityNotFoundException e) {
			        startActivity(new Intent(Intent.ACTION_VIEW,
			                Uri.parse("http://instagram.com/luansantana")));
			    }

				}
			});
        
        btn_face.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Uri uri = Uri.parse("fb://profile/165669036820327");
			    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

			    likeIng.setPackage("com.facebook.katana");

			    try {
			        startActivity(likeIng);
			    } catch (ActivityNotFoundException e) {
			        startActivity(new Intent(Intent.ACTION_VIEW,
			                Uri.parse("https://www.facebook.com/luansantana")));
			    }

				}
			});
        
        btn_youtube.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Uri uri = Uri.parse("https://www.youtube.com/user/luansantanaoficial");
			    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

			    likeIng.setPackage("com.google.android.youtube");

			    try {
			        startActivity(likeIng);
			    } catch (ActivityNotFoundException e) {
			        startActivity(new Intent(Intent.ACTION_VIEW,
			                Uri.parse("https://www.youtube.com/user/luansantanaoficial")));
			    }

				}
			});
        
        btn_twitter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Uri uri = Uri.parse("twitter://user?user_id=59156773");
			    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

			    likeIng.setPackage("com.twitter.android");

			    try {
			        startActivity(likeIng);
			    } catch (ActivityNotFoundException e) {
			        startActivity(new Intent(Intent.ACTION_VIEW,
			                Uri.parse("https://twitter.com/luansantana")));
			    }

				}
			});
        
        btn_gplus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Uri uri = Uri.parse("https://plus.google.com/+LuanSantana/");
			    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

			    likeIng.setPackage("com.google.android.apps.plus");

			    try {
			        startActivity(likeIng);
			    } catch (ActivityNotFoundException e) {
			        startActivity(new Intent(Intent.ACTION_VIEW,
			                Uri.parse("https://plus.google.com/+LuanSantana/posts")));
			    }

				}
			});
        
        btn_site.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, 
	        	    Uri.parse("http://www.luansantana.com.br/")));

				}
			});
         
        return rootView;
        
    }
	
	public void pegaVersaoDb(){

		Cursor cursor = databaseFav.query(TABLE_VERSION, new String[] {KEY_VERSAO},null, null, null, null, null);
		if (cursor.moveToFirst()) {
		  do {		     
			  versao_atual = cursor.getInt(0);
		  } while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
		  cursor.close();
		}
	}
	
	public static Cursor pegaMusicasFavoritas() {

		Cursor favCursor = databaseFav.query(true, TABLE_NAME, new String[] {
				KEY_ID_MUSICA, KEY_NOME, KEY_ALBUM_ID, KEY_TEMPO }, KEY_FAVORITO + "=?",
				new String[] { VALOR_FAVORITO }, null, null, null, null);

		if (favCursor != null) {
			favCursor.moveToFirst();
		}
		return favCursor;
	}
	
	public void atualizarVersaoDb(SQLiteDatabase db, int versaoAtual, int novaVersao){
    	try {
    		Log.d("Versão Atual DB", ""+versaoAtual);
    		Log.d("Versão Nova Code", ""+novaVersao);
    		if(versaoAtual == novaVersao){
    			Log.d("atualizarVersaoDb", "Nao e necessario atualizar, versao mais nova ja instalada.");
    		}
    		if(versaoAtual < novaVersao){
    			Log.d("CursorSalvaFav", "O método foi acionado e os favoritos será carregado...");
    			Cursor cursorSalvaFav = pegaMusicasFavoritas();
    			Log.d("CursorSalvaFav", "Favoritos carregados no cursor");
    			Log.d("CursorSalvaFav", "Número de favoritos: " + cursorSalvaFav.getCount());
    			
    			dbOpenHelper.copyDataBase();
    			
    			if(cursorSalvaFav.getCount() == 0){
    				Log.d("CursorSalvaFav", "Não há nenhuma música marcada como favorito!");
    			} else {			
    				
    				do {
    					Log.d("CursorSalvaFav", "Tem favoritos!");

    					
    					String nome_musica = cursorSalvaFav.getString(0);
    					Log.d("CursorSalvaFav", "ID Música: " + cursorSalvaFav.getString(0));
    					String id_album = cursorSalvaFav.getString(2);
    					Log.d("CursorSalvaFav", "ID Álbum: " + cursorSalvaFav.getString(2));
    					
    					ContentValues values = new ContentValues();
    					Log.d("CursorSalvaFav", "Criou os values");
    					 values.put(KEY_FAVORITO, 1);
    					 Log.d("CursorSalvaFav", "inseriu o valor 1 no fav");
    					 databaseFav.update(TABLE_NAME, values, KEY_ID_MUSICA + "=?" + " AND " +  KEY_ALBUM_ID + "=?",
    								new String[] { nome_musica, id_album });
    					 Log.d("CursorSalvaFav", "Realizou update!");
    				} while (cursorSalvaFav.moveToNext());
    				
    			}
    			
    		}
		} catch (IOException mIOException) {
			 Log.e("OnUpgrade", mIOException.toString());
		}
    }
}
