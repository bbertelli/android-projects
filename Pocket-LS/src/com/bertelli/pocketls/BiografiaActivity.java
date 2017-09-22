package com.bertelli.pocketls;

import com.startapp.android.publish.StartAppSDK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class BiografiaActivity extends Activity {
	
	TextView titulo;
	TextView texto;
	ImageView imagem;
	String opcao;
	Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StartAppSDK.init(this, "102580822", "208457602", true);
		setContentView(R.layout.activity_biografia);
		
		/*// ADMOB
	    AdView adView1 = (AdView) findViewById(R.id.adView_biografia_activity);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView1.loadAd(adRequest);*/
	    
		titulo = (TextView) findViewById(R.id.bio1_txt_titulo);
		texto = (TextView) findViewById(R.id.bio1_texto);
		imagem = (ImageView) findViewById(R.id.bio1_icon);
		
		intent = getIntent();
		opcao = intent.getStringExtra("bio");
		
		if(opcao.equals("1")){ 
			titulo.setText("Sobre o Luan");
			imagem.setImageResource(R.drawable.luan_bio1);
			texto.setText(Html.fromHtml(getString(R.string.bio1_texto)));
		}
		if(opcao.equals("2")){ 
			titulo.setText("A História");
			imagem.setImageResource(R.drawable.luan_bio2);
			texto.setText(Html.fromHtml(getString(R.string.bio2_texto)));
		}
		if(opcao.equals("3")){ 
			titulo.setText("Curiosidades");
			imagem.setImageResource(R.drawable.luan_bio3);
			texto.setText(Html.fromHtml(getString(R.string.bio3_texto)));
		}
		if(opcao.equals("4")){ 
			titulo.setText("Prêmios e Conquistas");
			imagem.setImageResource(R.drawable.luan_bio4);
			texto.setText(Html.fromHtml(getString(R.string.bio4_texto)));
		}
		
	}
}