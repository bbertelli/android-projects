package com.bertelli.radarmovel;

import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.util.Log;
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

public class RadarDiario extends Activity {

	private static final String DB_NAME = "agenda.sqlite3";
	private static final String TABLE_NAME = "agenda";
	private static final String KEY_CODE = "_id";
	private static final String KEY_DIA = "dia";
	private static final String KEY_N_DIA = "n_dia";
	private static final String KEY_SENTIDO = "sentido";
	private static final String KEY_LOCAL = "local";
	private static final String KEY_HORA = "hora";

	private SQLiteDatabase database;
	private SimpleCursorAdapter dataAdapter;
	
	Calendar data = Calendar.getInstance();
	int dia = data.get(Calendar.DAY_OF_MONTH);
	int dia_nome = data.get(Calendar.DAY_OF_WEEK);
	int mes = data.get(Calendar.MONTH)+1;
	int ano = data.get(Calendar.YEAR);

	String diaSemana = null;
	String mesAno = null;
	String query_dia = Integer.toString(dia);
	private ImageButton btn_maps_diario;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar_diario);
		
		btn_maps_diario = (ImageButton) findViewById(R.id.btn_maps_diario);
		
		btn_maps_diario.setOnTouchListener(new OnTouchListener() {
        	
        	@Override
        	public boolean onTouch(View arg0, MotionEvent me) {        		
        		
        		if (me.getAction() == MotionEvent.ACTION_DOWN) {
        			return true;
        			
        		} else if (me.getAction() == MotionEvent.ACTION_UP) {
        			pegaItensLista();
        			return true;
        		}
        		
        		return false;
        		
        	}
        	
        });

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
		database = dbOpenHelper.openDataBase();

		exibeListView();
		montaTitulo();

	}


	private void exibeListView() { 

		Cursor cursor = pegaRadaresDia();

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

		ListView listView = (ListView) findViewById(R.id.lista_diario);
		// linka o adapter na listview
		listView.setAdapter(dataAdapter);

	}

	public Cursor pegaRadaresDia() {

//		Cursor mCursor = database.query(TABLE_NAME, new String[] {KEY_CODE, KEY_DIA, KEY_N_DIA, KEY_LOCAL, KEY_SENTIDO, KEY_HORA},
//				null, null, null, null, null);
		Cursor mCursor = database.query(true, TABLE_NAME, new String[] {
				KEY_CODE,
				KEY_DIA,
				KEY_N_DIA,
				KEY_LOCAL,
				KEY_SENTIDO,
				KEY_HORA}, 
				KEY_DIA + "=?", 
                new String[] {query_dia},
                null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public void pegaItensLista() {

//		Cursor mCursor = database.query(TABLE_NAME, new String[] {KEY_CODE, KEY_DIA, KEY_N_DIA, KEY_LOCAL, KEY_SENTIDO, KEY_HORA},
//				null, null, null, null, null);
		Cursor mCursor = database.query(true, TABLE_NAME, new String[] {
				KEY_CODE,
				KEY_DIA,
				KEY_N_DIA,
				KEY_LOCAL,
				KEY_SENTIDO,
				KEY_HORA}, 
				KEY_DIA + "=?", 
                new String[] {query_dia},
                null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
			
			String [][] listaDia = new String[mCursor.getCount()][5];
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				listaDia[i][0] = mCursor.getString(1);
				listaDia[i][1] = mCursor.getString(2);
				listaDia[i][2] = mCursor.getString(3);
				listaDia[i][3] = mCursor.getString(4);
				listaDia[i][4] = mCursor.getString(5);
				
				Log.d("Variavel", "Valor da linha : " + i + " Coluna : " + listaDia[i][0]);
				Log.d("Variavel", "Valor da linha : " + i + " Coluna : " + listaDia[i][1]);
				Log.d("Variavel", "Valor da linha : " + i + " Coluna : " + listaDia[i][2]);
				Log.d("Variavel", "Valor da linha : " + i + " Coluna : " + listaDia[i][3]);
				Log.d("Variavel", "Valor da linha : " + i + " Coluna : " + listaDia[i][4]);
			}
//			do {
//				  String d_dia = mCursor.getString(1);
//			  String d_n_dia = mCursor.getString(2);
//				  String d_local = mCursor.getString(3);
//			  String d_sentido = mCursor.getString(4);
//			  String d_hora = mCursor.getString(5);
//				
//
//				  String row = mCursor.getString(1) + ";" + mCursor.getString(2) + ";" + mCursor.getString(3) + ";" 
//				  + mCursor.getString(4) + ";" + mCursor.getString(5) + ";";
//				  
//				  Log.d("Variavel", "Valor de row: " + row);
//				  
//				  
//			  } while (mCursor.moveToNext());
		}
	}


	public void montaTitulo(){

		switch (dia_nome){
		case 1:{diaSemana = "Domingo";break;}  
		case 2:{diaSemana = "Segunda-Feira";break;}
		case 3:{diaSemana = "Terça-Feira";break;}  
		case 4:{diaSemana = "Quarta-Feira";break;}  
		case 5:{diaSemana = "Quinta-Feira";break;}  
		case 6:{diaSemana = "Sexta-Feira";break;}  
		case 7:{diaSemana = "Sábado";break;}
		}

		switch (mes){
		case 1:{mesAno = "Janeiro";break;}  
		case 2:{mesAno = "Fevereiro";break;}  
		case 3:{mesAno = "Março";break;}  
		case 4:{mesAno = "Abril";break;}  
		case 5:{mesAno = "Maio";break;}  
		case 6:{mesAno = "Junho";break;}  
		case 7:{mesAno = "Julho";break;}
		case 8:{mesAno = "Agosto";break;}
		case 9:{mesAno = "Setembro";break;}
		case 10:{mesAno = "Outubro";break;}
		case 11:{mesAno = "Novembro";break;}
		case 12:{mesAno = "Dezembro";break;}
		}

		String titulo_dia = diaSemana + " " + dia + " de " + mesAno + " de " + ano; 
		TextView l_titulo = (TextView) findViewById(R.id.titulo_dinamico_diario);
		l_titulo.setText(titulo_dia);

	}

}
