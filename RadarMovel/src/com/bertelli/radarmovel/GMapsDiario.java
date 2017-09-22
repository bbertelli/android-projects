package com.bertelli.radarmovel;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapActivity;

public class GMapsDiario extends MapActivity {

	private GoogleMap map;
	private LatLng local_mapa;
	private double latitude, longitude;
	
	private String local_lista;
	private String dia_lista;
	private String n_dia_lista;
	private String sentido_lista;
	private String hora_lista;

	//Define as coordenadas dos locais

	private static final double l_local0 = -23.085525685258997;
	private static final double lo_local_0 = -47.2250120460327;
	
	private static final double l_local1 = -23.088265691575508;
	private static final double lo_local_1 = -47.20285461534422;
	
	private static final double l_local2 = -23.077887580957146;
	private static final double lo_local_2 = -47.21605049814758;
	
	private static final double l_local3 = -23.0844098;
	private static final double lo_local_3 = -47.190082700000005;
	
	private static final double l_local4 = -23.075533;
	private static final double lo_local_4 = -47.20668260000002;
	
	private static final double l_local5 = -23.085756040882075;
	private static final double lo_local_5 = -47.22310031191711;
	
	private static final double l_local6 = -23.0824063;
	private static final double lo_local_6 = -47.195904799999994;
	
	private static final double l_local7 = -23.1178061;
	private static final double lo_local_7 = -47.22318510000002;
	
	private static final double l_local8 = -23.09174532509599;
	private static final double lo_local_8 = -47.227971793115216;
	
	private static final double l_local9 = -23.09282521781698;
	private static final double lo_local_9 = -47.194219823526;
	
	private static final double l_local10 = -23.09553635003748;
	private static final double lo_local_10 = -47.22537869206542;
	
	private static final double l_local11 = -23.1141754;
	private static final double lo_local_11 = -47.23296779999998;
	
	private static final double l_local12 = -23.0836504;
	private static final double lo_local_12 = -47.20309910000003;
	
	private static final double l_local13 = -23.1132345;
	private static final double lo_local_13 = -47.231026499999984;
	
	private static final double l_local14 = -23.08465312305102;
	private static final double lo_local_14 = -47.19045004180299;
	
	private static final double l_local15 = -23.07978934514487;
	private static final double lo_local_15 = -47.221716188885466;
	
	private static final double l_local16 = -23.098497;
	private static final double lo_local_16 = -47.22512119999999;
	
	String local_encontrado = "";
	
	//Array com os valores a ser pesquisado
	String[] palavrasChave = {"2028","199","363","2351","2657","Barnabé X Rua Cristiano Steffen",
			   "1158","3093","2800","GIN. MUN. ESPORTES","3991","5422","1226","4925",
			   "Vargas X Rua Duilia Zoppi Garcia","Barnabé X Rua Carlos Klinke","3981","NÃO OPERA"};


		@Override
		public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar_mapview);
		
		pegaItemSelecionado(); 	

	}
		
		public void setMapa(double lat, double log){
			//Constroi o Mapa e seta o marker de acordo com o item que foi clicado na lista
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();       
			local_mapa = new LatLng(lat,log);
			Marker kiel = map.addMarker(new MarkerOptions()
	        .position(local_mapa)
	        .title("Data: " + dia_lista + " " + n_dia_lista)
	        .snippet("Sentido: " + sentido_lista + " Período: " + hora_lista)
	        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_green_radar)));

			map.moveCamera(CameraUpdateFactory.newLatLngZoom(local_mapa, 15));
		}
		
		public void pegaItemSelecionado(){
			
			local_lista = getIntent().getStringExtra("local");
			dia_lista  = getIntent().getStringExtra("dia");
			n_dia_lista = getIntent().getStringExtra("n_dia");
			sentido_lista = getIntent().getStringExtra("sentido");
			hora_lista = getIntent().getStringExtra("hora");
			
			if(sentido_lista == "B/C"){
				sentido_lista = "Bairro/Centro";
			}else{
				sentido_lista = "Centro/Bairro";
			}

			if(local_lista != null){
				int posicao = 0;
				int posicao_encontrada = 0;
		        
		        //loop para verificar qual o endereço que a pessoa clicou
		        for(int i=0; i < palavrasChave.length; i++){
		        	
		               
		                //verifica se o local que veio da lista contém a palavra chave
		                if(local_lista.contains(palavrasChave[i])){

		                        //quando encontrar ele recebe a posição do vetor
		                        posicao_encontrada = posicao;
		                        local_encontrado = palavrasChave[i];
		                        break;
		                       
		                }
		                posicao++;
		        }		

		    //testa para encontrar o local e exibir no mapa
			if(posicao_encontrada == 0){
				latitude = l_local0;
				longitude = lo_local_0;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 1){
				latitude = l_local1;
				longitude = lo_local_1;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 2){
				latitude = l_local2;
				longitude = lo_local_2;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 3){
				latitude = l_local3;
				longitude = lo_local_3;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 4){
				latitude = l_local4;
				longitude = lo_local_4;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 5){
				latitude = l_local5;
				longitude = lo_local_5;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 6){
				latitude = l_local6;
				longitude = lo_local_6;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 7){
				latitude = l_local7;
				longitude = lo_local_7;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 8){
				latitude = l_local8;
				longitude = lo_local_8;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 9){
				latitude = l_local9;
				longitude = lo_local_9;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 10){
				latitude = l_local10;
				longitude = lo_local_10;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 11){
				latitude = l_local11;
				longitude = lo_local_11;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 12){
				latitude = l_local12;
				longitude = lo_local_12;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 13){
				latitude = l_local13;
				longitude = lo_local_13;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 14){
				latitude = l_local14;
				longitude = lo_local_14;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 15){
				latitude = l_local15;
				longitude = lo_local_15;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 16){
				latitude = l_local16;
				longitude = lo_local_16;
				setMapa(latitude, longitude);
				Log.d("IF", "entrou no if");
				}
			if(posicao_encontrada == 17){
				Toast.makeText(getApplicationContext(),"Nessa data o Radar NÃO OPERA", Toast.LENGTH_SHORT).show();
				Log.d("IF", "entrou no if 17");
				}
			}
			else{
				Toast.makeText(getApplicationContext(),"Local não cadastrado!", Toast.LENGTH_SHORT).show();
			}
		}


		@Override
		protected boolean isRouteDisplayed() {
			// TODO Auto-generated method stub
			return false;
		}

}