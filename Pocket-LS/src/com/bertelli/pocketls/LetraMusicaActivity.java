package com.bertelli.pocketls;

import com.bertelli.pocketls.db.ExternalDbOpenHelper;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.startapp.android.publish.StartAppSDK;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LetraMusicaActivity extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {

	static private final String DEVELOPER_KEY = "AIzaSyBnF1Z84mR8SkrS7snWzhEEISzGVNEJy2I";	//API key do youtube
	//minha key = AIzaSyBnF1Z84mR8SkrS7snWzhEEISzGVNEJy2I
	//key eclipse debugkeystore = AIzaSyC9YKwkcen6ZFMSBmNpQesPgZ149NLXZ1o
	//antiga = AIzaSyAJT_Bsz0olRKdCQXqcOgGNj3SqALLWxl0

	private static final String DB_NAME = "luan.sqlite3";
	private static final String TABLE_NAME = "musica";
	private static final String KEY_NOME = "nome";
	private static final String KEY_LETRA = "letra";
	private static final String KEY_TEMPO = "tempo";
	private static final String KEY_COMPOSITOR = "compositor";
	private static final String KEY_VIDEO = "video";
	private static final String KEY_FAVORITO = "fav";
	private static final String KEY_ALBUM_ID = "album_id";
	private static String NOME_MUSICA = "";
	private static String ID_ALBUM = "";

	String letraMusica;
	String tempoMusica;
	String compositorMusica;
	String video;
	String teste_favorito ="";
	
	private SQLiteDatabase database;
	private TextView nome_musica;
	private TextView letra_musica;
	private TextView tempo_musica;
	private TextView compositor_musica;
	ImageButton favorito;
	TextView texto_favorito;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		StartAppSDK.init(this, "102580822", "208457602", true);
		
		setContentView(R.layout.activity_letra_musica);
		
		/*// ADMOB
	    AdView adView1 = (AdView) findViewById(R.id.adView_letramusica_activity);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView1.loadAd(adRequest);*/
		
		nome_musica = (TextView) findViewById(R.id.nome_musica);
		letra_musica = (TextView) findViewById(R.id.letra_musica);
		tempo_musica = (TextView) findViewById(R.id.tempo_musica);
		compositor_musica = (TextView) findViewById(R.id.compositor_musica);
		favorito = (ImageButton) findViewById(R.id.icone_favorito_off);
		texto_favorito = (TextView) findViewById(R.id.texto_favorito);
		
		NOME_MUSICA = getIntent().getStringExtra("nome_musica");
		nome_musica.setText(NOME_MUSICA);
		ID_ALBUM = getIntent().getStringExtra("id_album");

		ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this,
				DB_NAME);
		database = dbOpenHelper.openDataBase();
		
		pegaInfoMusica();
		pegaFavorito();
		verificaFavorito();

		letra_musica.setText(letraMusica);
		tempo_musica.setText("Duração: " + tempoMusica);
		compositor_musica.setText("Composição: " + compositorMusica);

		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(DEVELOPER_KEY, this);
		
		favorito.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {

				int action = arg1.getAction();
				if(action == MotionEvent.ACTION_DOWN) {
					Log.d("Botao", "Funcionou fora do if");
					if(teste_favorito.equals("0")){
						Log.d("Botao", "Funcionou DENTRO DO 0");
						favorito.setBackgroundResource(R.drawable.fav_icon_on);
						texto_favorito.setText("Desmarcar Favorito");
						//teste_favorito = "1";
						atualizaFavorito(1);
						pegaFavorito();
						verificaFavorito();
						//favoritosFragment.atualizaListaFavoritos();
						return true;
					}
					if(teste_favorito.equals("1")){
						Log.d("Botao", "Funcionou DENTRO DO 1");
						favorito.setBackgroundResource(R.drawable.fav_icon_off);
						texto_favorito.setText("Marcar Favorito");
						//teste_favorito = "0";
						atualizaFavorito(0);
						pegaFavorito();
						verificaFavorito();
						//favoritosFragment.atualizaListaFavoritos();
						return true;
					}
				}
				return false;
			}

		});

	}
	
	public void verificaFavorito(){
		
		favorito = (ImageButton) findViewById(R.id.icone_favorito_off);
		Log.d("teste_favorito", teste_favorito);
		if(teste_favorito.equals("0")){
			favorito.setBackgroundResource(R.drawable.fav_icon_off);
			texto_favorito.setText("Marcar Favorito");
		}
		if(teste_favorito.equals("1")){
			favorito.setBackgroundResource(R.drawable.fav_icon_on);
			texto_favorito.setText("Desmarcar Favorito");
		}
	}
	
	
	public void atualizaFavorito(int n_fav){
		 ContentValues values = new ContentValues();
		 values.put(KEY_FAVORITO, n_fav );
		 database.update(TABLE_NAME, values, KEY_NOME + "=?" + " AND " +  KEY_ALBUM_ID + "=?",
					new String[] { NOME_MUSICA, ID_ALBUM });
	}
	
public void pegaFavorito(){
		
		Cursor cursor = database.query(TABLE_NAME, new String[] {KEY_FAVORITO}, KEY_NOME + "=?" + " AND " +  KEY_ALBUM_ID + "=?",
				new String[] { NOME_MUSICA, ID_ALBUM }, null, null, null, null);
		if (cursor.moveToFirst()) {
		  do {
		     
			  teste_favorito = cursor.getString(0);
			  Log.d("favorito", cursor.getString(0));

		  } while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
		  cursor.close();
		}
	}

	public void pegaInfoMusica() {

		Cursor mCursor = database.query(true, TABLE_NAME, new String[] {
				KEY_LETRA, KEY_TEMPO, KEY_COMPOSITOR, KEY_VIDEO}, KEY_NOME + "=?" + " AND " +  KEY_ALBUM_ID + "=?",
				new String[] { NOME_MUSICA, ID_ALBUM }, null, null, null, null);

		if (mCursor.moveToFirst()) {
			do {

				letraMusica = mCursor.getString(0);
				tempoMusica = mCursor.getString(1);
				compositorMusica = mCursor.getString(2);
				video = mCursor.getString(3);
				

			} while (mCursor.moveToNext());
		}
		if (mCursor != null && !mCursor.isClosed()) {
			mCursor.close();
		}
	}

	@Override
	public void onInitializationFailure(Provider provider,
			YouTubeInitializationResult error) {
		Toast.makeText(this, "Ops! Houve algo errado :( " + error.toString(), Toast.LENGTH_LONG)
				.show();
		try{
		    ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.youtube", 0 );
		} catch( PackageManager.NameNotFoundException e ){
			new AlertDialog.Builder(LetraMusicaActivity.this)
			.setTitle("ATENÇÃO!")
			.setMessage("O YouTube não está instalado no seu dispositivo!\nVocê deve instalar o player do YouTube para visualizar o vídeo.")
		    .setIcon(R.drawable.obs)
		    .setCancelable(false)
		    .setPositiveButton("Instalar agora",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						final String appPackageName = "com.google.android.youtube";
						try {
						    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
						} catch (android.content.ActivityNotFoundException anfe) {
						    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
						}
					}
				  })
				.setNegativeButton("Depois",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close/
						// the dialog box and do nothing
						dialog.cancel();
					}
				})
		    .show();
		}
	}

	@Override
	public void onInitializationSuccess(Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		player.cueVideo(video);
	}
	
}