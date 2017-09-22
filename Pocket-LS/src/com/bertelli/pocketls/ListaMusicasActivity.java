package com.bertelli.pocketls;

import com.bertelli.pocketls.db.ExternalDbOpenHelper;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListaMusicasActivity extends Activity {
	
	private StartAppAd startAppAd = new StartAppAd(this);

	private static final String DB_NAME = "luan.sqlite3";
	private static final String TABLE_NAME = "musica";
	private static String KEY_ID_CD = "";
	private static final String KEY_ALBUM_ID = "album_id";
	private static final String KEY_ID_MUSICA = "_id";
	private static final String KEY_NOME = "nome";

	private SQLiteDatabase database;
	private SimpleCursorAdapter dataAdapter;
	private TextView titulo_cd;
	private String tituloAlbum;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StartAppSDK.init(this, "102580822", "208457602", true);
		setContentView(R.layout.activity_lista_musicas);
		
		/*// ADMOB
	    AdView adView1 = (AdView) findViewById(R.id.adView_listamusicas_activity);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView1.loadAd(adRequest);*/

		KEY_ID_CD = getIntent().getStringExtra("idAlbum");
		tituloAlbum = getIntent().getStringExtra("tituloAlbum");
		
		ImageView imgAlbum = (ImageView) findViewById(R.id.imagem_cd);
		titulo_cd = (TextView) findViewById(R.id.titulo_cd);
		
		titulo_cd.setText(tituloAlbum);
		if (KEY_ID_CD.equals("1")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd1));
		}
		if (KEY_ID_CD.equals("2")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd2));
		}
		if (KEY_ID_CD.equals("3")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd3));
		}
		if (KEY_ID_CD.equals("4")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd4));
		}
		if (KEY_ID_CD.equals("5")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd5));
		}
		if (KEY_ID_CD.equals("6")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd6));
		}
		if (KEY_ID_CD.equals("7")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd7));
		}
		if (KEY_ID_CD.equals("8")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd8));
		}
		if (KEY_ID_CD.equals("9")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd9));
		}
		if (KEY_ID_CD.equals("10")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd10));
		}
		if (KEY_ID_CD.equals("11")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd11));
		}
		if (KEY_ID_CD.equals("12")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd12));
		}
		if (KEY_ID_CD.equals("13")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd13));
		}
		if (KEY_ID_CD.equals("14")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd14));
		}
		if (KEY_ID_CD.equals("15")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd15));
		}
		if (KEY_ID_CD.equals("16")) {
			imgAlbum.setImageDrawable(getResources()
					.getDrawable(R.drawable.cd16));
		}
		

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this,
				DB_NAME);
		database = dbOpenHelper.openDataBase();

		Log.d("Versão BD", Integer.toString(database.getVersion()));
		
		exibeListView();
	}

	private void exibeListView() {

		Cursor cursor = pegaTodosRegistros();

		// Colunas que serão exibidas
		String[] columns = new String[] { KEY_ID_MUSICA, KEY_NOME };

		// objetos que serão usados para exibir
		int[] to = new int[] { R.id.musica_id,R.id.musica_titulo };

		// cria o adapter definindo o layout e as colunas a serem usadas
		dataAdapter = new SimpleCursorAdapter(this, R.layout.row_lista_musicas,
				cursor, columns, to, 0);

		ListView listView = (ListView) findViewById(R.id.lista);
		listView.setSelector( R.drawable.listselector);
		// linka o adapter na listview
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				// pega o item que foi clicado
				
				 Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				 
				 String nome_musica = cursor.getString(cursor.getColumnIndexOrThrow("nome")).toString();
				 
				 Intent intent = new Intent(ListaMusicasActivity.this, LetraMusicaActivity.class); 
				 intent.putExtra("nome_musica", nome_musica);
				 intent.putExtra("id_album", KEY_ID_CD);
				 startActivity(intent);
				 startAppAd.showAd();
				 startAppAd.loadAd();
				 

			}
		});

	}

	public Cursor pegaTodosRegistros() {

		Cursor mCursor = database.query(true, TABLE_NAME, new String[] {
				KEY_ID_MUSICA, KEY_NOME }, KEY_ALBUM_ID + "=?",
				new String[] { KEY_ID_CD }, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

}