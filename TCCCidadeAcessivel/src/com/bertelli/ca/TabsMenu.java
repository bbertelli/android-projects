package com.bertelli.ca;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class TabsMenu extends TabActivity {

	TabHost tabHost;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		tabHost = getTabHost();

		tabHost.setBackgroundColor(Color.WHITE);

		// Tab para lista de Locais cadastrados
		TabSpec esportesspec = tabHost.newTabSpec("Lista de Locais");
		esportesspec.setIndicator("", getResources().getDrawable(R.drawable.icon_list_tab));
		Intent esportesIntent = new Intent(this, ListaLocais.class);
		esportesspec.setContent(esportesIntent);

		// Tab para exibir o mapa com os pontos dos locais cadastrados
		TabSpec sobrespec = tabHost.newTabSpec("Locais no Mapa");
		sobrespec.setIndicator("", getResources().getDrawable(R.drawable.icon_map_tab));
		Intent sobreIntent = new Intent(this, GMapsActivity.class);
		sobrespec.setContent(sobreIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(esportesspec);
		tabHost.addTab(sobrespec);

		// aqui é para setar uma cor padrão em primeiro momento

		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			if (i == 0)
				tabHost.getTabWidget().getChildAt(i)
						.setBackgroundColor(Color.parseColor("#006699"));

			else
				tabHost.getTabWidget().getChildAt(i)
						.setBackgroundColor(Color.parseColor("#FFFFFF"));
		}

		// método para mudar as cores quando seleciona outra tab
		// Perceba que só da pra trabalhar com dois tipos de cores diferentes

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub

				// aqui é pra mudar as cores quando mudar de tab
				for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
					tabHost.getTabWidget().getChildAt(i)
							.setBackgroundColor(Color.parseColor("#FFFFFF")); // unselected
					
				}
				tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
						.setBackgroundColor(Color.parseColor("#006699")); // selected
			}
		});
	}
	
	public void mudarTab(){
		getTabHost().setCurrentTab(1);
	}

}