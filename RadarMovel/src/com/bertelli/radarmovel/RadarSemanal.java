package com.bertelli.radarmovel;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RadarSemanal extends Activity {

	private static final String DB_NAME = "agenda.sqlite3";
	private static final String TABLE_NAME = "agenda";
	private static final String KEY_CODE = "_id";
	private static final String KEY_DIA = "dia";
	private static final String KEY_N_DIA = "n_dia";
	private static final String KEY_SENTIDO = "sentido";
	private static final String KEY_LOCAL = "local";
	private static final String KEY_HORA = "hora";
	private static final String TABLE_TITULO = "titulo";
	private static final String KEY_TITULO = "titulo";

	private SQLiteDatabase database;
	private SimpleCursorAdapter dataAdapter;
	
	private ImageButton btn_lista_diaria;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar_semanal);
		
		btn_lista_diaria = (ImageButton) findViewById(R.id.btn_lista_diaria);      		
	        
		btn_lista_diaria.setOnTouchListener(new OnTouchListener() {
	        	
	        	@Override
	        	public boolean onTouch(View arg0, MotionEvent me) {
	        		
	        		Intent telaListaDiaria = new Intent(RadarSemanal.this, RadarDiario.class);
	        		
	        		
	        		if (me.getAction() == MotionEvent.ACTION_DOWN) {
	        			return true;
	        			
	        		} else if (me.getAction() == MotionEvent.ACTION_UP) {
	        			RadarSemanal.this.startActivity(telaListaDiaria);
	        			return true;
	        		}
	        		
	        		return false;
	        		
	        	}
	        	
	        });

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
		database = dbOpenHelper.openDataBase();

		exibeListView();
		pegaTitulo();

	}


	private void exibeListView() { 

		Cursor cursor = pegaTodosRegistros();

		// Colunas que serão exibidas
		String[] columns = new String[] {
				KEY_DIA,
				KEY_N_DIA,
				KEY_SENTIDO,
				KEY_HORA,
				KEY_LOCAL


		};

		// objetos que serão usados para exibir
		int[] to = new int[] {
				R.id.dia,
				R.id.n_dia,
				R.id.sentido,
				R.id.hora,
				R.id.local
		};

		// cria o adapter definindo o layout e as colunas a serem usadas
		dataAdapter = new SimpleCursorAdapter(
				this, R.layout.radar_info, 
				cursor, 
				columns, 
				to,
				0);

		ListView listView = (ListView) findViewById(R.id.lista);
		// linka o adapter na listview
		listView.setAdapter(dataAdapter);


		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view, 
					int position, long id) {
				// pega o item que foi clicado
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				
				String local_lista = cursor.getString(cursor.getColumnIndexOrThrow("local")).toString();
				String dia_lista = cursor.getString(cursor.getColumnIndexOrThrow("dia")).toString();
				String n_dia_lista = cursor.getString(cursor.getColumnIndexOrThrow("n_dia")).toString();
				String sentido_lista = cursor.getString(cursor.getColumnIndexOrThrow("sentido")).toString();
				String hora_lista = cursor.getString(cursor.getColumnIndexOrThrow("hora")).toString();

				Intent intent = new Intent(RadarSemanal.this, GMapsSemanal.class);
				intent.putExtra("local", local_lista);
				intent.putExtra("dia", dia_lista);
				intent.putExtra("n_dia", n_dia_lista);
				intent.putExtra("sentido", sentido_lista);
				intent.putExtra("hora", hora_lista);
				startActivity(intent);
				

			}
		});

	}

	public Cursor pegaTodosRegistros() {

		Cursor mCursor = database.query(TABLE_NAME, new String[] {KEY_CODE, KEY_DIA, KEY_N_DIA, KEY_LOCAL, KEY_SENTIDO, KEY_HORA}, 
				null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	
	public void pegaTitulo(){
		
		Cursor cursor = database.query(TABLE_TITULO, new String[] {KEY_TITULO},null, null, null, null, null);
		if (cursor.moveToFirst()) {
		  do {
		     
			  String titulo = cursor.getString(0); // pega o titulo da base de dados
			  TextView l_titulo = (TextView) findViewById(R.id.titulo_dinamico);
			  l_titulo.setText(titulo);


		  } while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
		  cursor.close();
		}
	}
    
}
