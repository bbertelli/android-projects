package com.bertelli.pocketls;

import com.bertelli.pocketls.adapter.MySimpleCursorAdapter;
import com.bertelli.pocketls.db.ExternalDbOpenHelper;
import com.startapp.android.publish.StartAppSDK;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoritosFragment extends Fragment{
	
	 String DB_NAME = "luan.sqlite3";
	 String TABLE_NAME = "musica";
	 String KEY_ID_MUSICA = "_id";
	 String KEY_ALBUM_ID = "album_id";
	 String KEY_NOME = "nome";
	 String KEY_TEMPO = "tempo";
	 String KEY_FAVORITO = "fav";
	 String VALOR_FAVORITO = "1";

	 SQLiteDatabase databaseFav;
	 ExternalDbOpenHelper dbOpenHelper;
	 MySimpleCursorAdapter dataAdapter;
	 Cursor cursorFav;	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		StartAppSDK.init(getActivity(), "102580822", "208457602", true);
		
		View rootView = inflater.inflate(R.layout.fragment_favoritos, container, false);
		
		/*// ADMOB
	    AdView adView = (AdView) rootView.findViewById(R.id.adView_favoritos_fragment);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);*/
	    
		dbOpenHelper = new ExternalDbOpenHelper(getActivity(),DB_NAME);
		databaseFav = dbOpenHelper.openDataBase();
	
		cursorFav = pegaMusicasFavoritas();

		// Colunas que serão exibidas
		String[] columns = new String[] { };

		// objetos que serão usados para exibir
		int[] to = new int[] { };

		// cria o adapter definindo o layout e as colunas a serem usadas		
		dataAdapter = new MySimpleCursorAdapter(getActivity(), R.layout.row_lista_favoritos, 
				cursorFav, columns, to);

		ListView listView = (ListView) rootView.findViewById(R.id.lista_favoritos);
		listView.setSelector(R.drawable.listselector);
		
		// linka o adapter na listview
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				 // pega o item que foi clicado
				
				 cursorFav = (Cursor) listView.getItemAtPosition(position);
				 
				 String nome_musica = cursorFav.getString(cursorFav.getColumnIndex("nome")).toString();
				 String id_album = cursorFav.getString(cursorFav.getColumnIndex("album_id")).toString();
				 
				 Intent intent = new Intent(getActivity(), LetraMusicaActivity.class); 
				 intent.putExtra("nome_musica", nome_musica);
				 intent.putExtra("id_album", id_album);
				 startActivity(intent);

			}
		});
		
		return rootView;
	}
	
	Cursor pegaMusicasFavoritas() {
		
		Cursor favCursor = databaseFav.query(true, TABLE_NAME, new String[] {
				KEY_ID_MUSICA, KEY_NOME, KEY_ALBUM_ID, KEY_TEMPO }, KEY_FAVORITO + "=?",
				new String[] { VALOR_FAVORITO }, null, null, null, null);

		if (favCursor != null) {
			favCursor.moveToFirst();
		}
		return favCursor;
	}
		
	void atualizaListaFavoritos(){

		cursorFav = pegaMusicasFavoritas();
		dataAdapter.changeCursor(cursorFav);

	}
	
	@Override
	public void onResume() {
		atualizaListaFavoritos();
		super.onResume();
	}

}