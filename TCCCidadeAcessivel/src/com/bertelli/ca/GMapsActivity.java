package com.bertelli.ca;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.MarginLayoutParams;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.bertelli.ca.R;

public class GMapsActivity extends MapActivity {
	
	public MapView mapView;
	private MapController mapController;
	private MyLocationOverlay myLocationOverlay;
	
	//Define as coordenadas do local
	
	private static final int l_indaiatuba = (int) (-23.088210 * 1E6);
	private static final int lo_indaiatuba = (int) (-47.223444 * 1E6);
	GeoPoint indaiatuba = new GeoPoint(l_indaiatuba, lo_indaiatuba);
	
	private static final int haoc_lati = (int) (-23.09269 * 1E6);
	private static final int haoc_longi = (int) (-47.214011 * 1E6);
	GeoPoint haoc = new GeoPoint(haoc_lati, haoc_longi);
	
	private static final int santa_ignes_lati = (int) (-23.085291 * 1E6);
	private static final int santa_ignes_longi = (int) (-47.196159 * 1E6);
	GeoPoint santa_ignes = new GeoPoint(santa_ignes_lati, santa_ignes_longi);
	
	private static final int max_lati = (int) (-23.09421 * 1E6);
	private static final int max_longi = (int) (-47.208833 * 1E6);
	GeoPoint maxplanck = new GeoPoint(max_lati, max_longi);
	
	private static final int polo_edu_lati = (int) (-23.068163 * 1E6);
	private static final int polo_edu_longi = (int) (-47.197646 * 1E6);
	GeoPoint polo_educacional = new GeoPoint(polo_edu_lati, polo_edu_longi);
	
	private static final int paguemenos_lati = (int) (-23.080062 * 1E6);
	private static final int paguemenos_longi = (int) (-47.198859 * 1E6);
	GeoPoint paguemenos = new GeoPoint(paguemenos_lati, paguemenos_longi);
	
	private static final int paodeacucar_lati = (int) (-23.086295 * 1E6);
	private static final int paodeacucar_longi = (int) (-47.199753 * 1E6);
	GeoPoint paodeacucar = new GeoPoint(paodeacucar_lati, paodeacucar_longi);
	
	private static final int carrefour_lati = (int) (-23.080279 * 1E6);
	private static final int carrefour_longi = (int) (-47.210294 * 1E6);
	GeoPoint carrefour = new GeoPoint(carrefour_lati, carrefour_longi);
	
	private static final int bosque_lati = (int) (-23.098035 * 1E6);
	private static final int bosque_longi = (int) (-47.203875 * 1E6);
	GeoPoint bosque = new GeoPoint(bosque_lati, bosque_longi);

	private static final int polo_shopping_lati = (int) (-23.116937 * 1E6);
	private static final int polo_shopping_longi = (int) (-47.21897 * 1E6);
	GeoPoint polo_shopping = new GeoPoint(polo_shopping_lati, polo_shopping_longi);

	private static final int jaragua_lati = (int) (-23.083117 * 1E6);
	private static final int jaragua_longi = (int) (-47.213131 * 1E6);
	GeoPoint jaragua = new GeoPoint(jaragua_lati, jaragua_longi);
	
	private static final int ciaei_lati = (int) (-23.098947 * 1E6);
	private static final int ciaei_longi = (int) (-47.22872 * 1E6);
	GeoPoint ciaei = new GeoPoint(ciaei_lati, ciaei_longi);
	
	private static final int itau_lati = (int) (-23.088373 * 1E6);
	private static final int itau_longi = (int) (-47.21706 * 1E6);
	GeoPoint itau = new GeoPoint(itau_lati, itau_longi);

	private static final int bb_lati = (int) (-23.088861 * 1E6);
	private static final int bb_longi = (int) (-47.215619 * 1E6);
	GeoPoint bb = new GeoPoint(bb_lati, bb_longi);
	
	private static final int riograndense_lati = (int) (-23.092059 * 1E6);
	private static final int riograndense_longi = (int) (-47.228201 * 1E6);
	GeoPoint riograndense = new GeoPoint(riograndense_lati, riograndense_longi);

	private static final int pezao_lati = (int) (-23.084992 * 1E6);
	private static final int pezao_longi = (int) (-47.199831 * 1E6);
	GeoPoint pezao = new GeoPoint(pezao_lati, pezao_longi);
	
	private static final int vaga1_lati = (int) (-23.087576 * 1E6);
	private static final int vaga1_longi = (int) (-47.216209 * 1E6);
	GeoPoint vaga1 = new GeoPoint(vaga1_lati, vaga1_longi);
	
	private static final int vaga2_lati = (int) (-23.077101 * 1E6);
	private static final int vaga2_longi = (int) (-47.213812 * 1E6);
	GeoPoint vaga2 = new GeoPoint(vaga2_lati, vaga2_longi);

	private static final int raia_lati = (int) (-23.08595 * 1E6);
	private static final int raia_longi = (int) (-47.199145 * 1E6);
	GeoPoint raia = new GeoPoint(raia_lati, raia_longi);

	private static final int treze_lati = (int) (-23.087395 * 1E6);
	private static final int treze_longi = (int) (-47.212273 * 1E6);
	GeoPoint treze = new GeoPoint(treze_lati, treze_longi);

	private static final int saae_lati = (int) (-23.089907 * 1E6);
	private static final int saae_longi = (int) (-47.213509 * 1E6);
	GeoPoint saae = new GeoPoint(saae_lati, saae_longi);

	private static final int correio_lati = (int) (-23.08746 * 1E6);
	private static final int correio_longi = (int) (-47.216411 * 1E6);
	GeoPoint correio = new GeoPoint(correio_lati, correio_longi);

	private static final int delegacia_lati = (int) (-23.090125 * 1E6);
	private static final int delegacia_longi = (int) (-47.21308 * 1E6);
	GeoPoint delegacia = new GeoPoint(delegacia_lati, delegacia_longi);

	private static final int cpfl_lati = (int) (-23.083262 * 1E6);
	private static final int cpfl_longi = (int) (-47.197742 * 1E6);
	GeoPoint cpfl = new GeoPoint(cpfl_lati, cpfl_longi);
	
	
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Constroi o Mapa
        mapView = (MapView) findViewById(R.id.map_view);       
        mapView.setBuiltInZoomControls(true);
        
        //Constroi a lista do mapa
        mapController = mapView.getController();
        List<Overlay> mapOverlays = mapView.getOverlays();
        
        
        myLocationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(myLocationOverlay);
        
        
        
        //Cria os icones
        Drawable drawable = this.getResources().getDrawable(R.drawable.marker_green);
        Drawable hospital = this.getResources().getDrawable(R.drawable.marker_hospital);
        Drawable escola = this.getResources().getDrawable(R.drawable.marker_school);
        Drawable supermercado = this.getResources().getDrawable(R.drawable.marker_market);
        Drawable lazer = this.getResources().getDrawable(R.drawable.shopping);
        Drawable banco = this.getResources().getDrawable(R.drawable.marker_bank);
        Drawable restaurante = this.getResources().getDrawable(R.drawable.restaurant);
        Drawable vaga = this.getResources().getDrawable(R.drawable.vaga);
        Drawable servico = this.getResources().getDrawable(R.drawable.service);
        
        //Cria o CustomItemizedOverlay (Seta o icone no local)
        CustomItemizedOverlay itemizedOverlay = new CustomItemizedOverlay(drawable, this);
        CustomItemizedOverlay io_hospital = new CustomItemizedOverlay(hospital,this);
        CustomItemizedOverlay io_escola = new CustomItemizedOverlay(escola,this);
        CustomItemizedOverlay io_supermercado = new CustomItemizedOverlay(supermercado,this);
        CustomItemizedOverlay io_lazer = new CustomItemizedOverlay(lazer,this);
        CustomItemizedOverlay io_banco = new CustomItemizedOverlay(banco,this);
        CustomItemizedOverlay io_restaurante = new CustomItemizedOverlay(restaurante,this);
        CustomItemizedOverlay io_vaga = new CustomItemizedOverlay(vaga,this);
        CustomItemizedOverlay io_servico = new CustomItemizedOverlay(servico,this);
		
		
		//Cria o Overlay (titulo e a descrição)
        
        //Indaiatuba
		OverlayItem overlayitem = new OverlayItem(indaiatuba, "Cidade: Indaiatuba", "Olá, Você está em Indaiatuba");
		
		//Hospitais
		OverlayItem o = new OverlayItem(bosque, "Bosque do Saber", "Teste");
		OverlayItem hospital_haoc = new OverlayItem(haoc, "Hospital HAOC", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		OverlayItem hospital_santa_ignes = new OverlayItem(santa_ignes, "Hospital Santa Ignes", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		
		//Escolas
		OverlayItem escola_max = new OverlayItem(maxplanck, "Faculdade Max Planck", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso aos Andares Superiores\n- Sanitários Adaptados");
		OverlayItem escola_polo = new OverlayItem(polo_educacional, "Colégio Polo Educacional", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		
		//Supermercados
		OverlayItem supermercado_paguemenos = new OverlayItem(paguemenos, "Supermercado Pague Menos", "- Elevador de Acesso\n- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		OverlayItem supermercado_paodeacucar = new OverlayItem(paodeacucar, "Supermercado Pão de Açúcar", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		OverlayItem supermercado_carrefour = new OverlayItem(carrefour, "Supermercado Carrefour", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		
		//Lazer
		OverlayItem lazer_bosque = new OverlayItem(bosque, "Bosque do Saber", "- Elevador de Acesso\n- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		OverlayItem lazer_polo = new OverlayItem(polo_shopping, "Polo Shopping", "- Telefones Exclusivos\n-Cadeira de rodas Disponível para uso\n- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		OverlayItem lazer_jaragua = new OverlayItem(jaragua, "Shopping Jaraguá", "- Telefones Exclusivos\n-Cadeira de rodas Disponível para uso\n- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		OverlayItem lazer_ciaei = new OverlayItem(ciaei, "CIAEI", "- Vaga de Estacionamento Exclusivo\n- Assentos Reservados\n- Sanitários Adaptados");
		
		//Bancos
		OverlayItem banco_itau = new OverlayItem(itau, "Banco Itaú", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		OverlayItem banco_bb = new OverlayItem(bb, "Banco do Brasil", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		
		//Restaurantes
		OverlayItem rest_rio = new OverlayItem(riograndense, "Churrascaria Rio Grandense", "- Rampas de Acesso ao Piso Inferior e Superior\n- Sanitários Adaptados");
		OverlayItem rest_pezao = new OverlayItem(pezao, "Restaurante Pezão", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso aos Andares Superiores\n- Sanitários Adaptados");
				
		//Vagas
		OverlayItem vaga_1 = new OverlayItem(vaga1, "Vaga Exclusiva", "- Vaga de estacionamento para portadores de deficiência");
		OverlayItem vaga_2 = new OverlayItem(vaga2, "Vaga Exclusiva", "- Vaga de estacionamento para portadores de deficiência");
		
		//Farmacias
		OverlayItem farmacia_raia = new OverlayItem(raia, "Farmácia Drogaria Raia", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso");
		OverlayItem farmacia_treze = new OverlayItem(treze, "Farmácia Droga Treze", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso");
		
		//Serviços
		OverlayItem servico_saae = new OverlayItem(saae, "SAAE", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso\n- Sanitários Adaptados");
		OverlayItem servico_correio = new OverlayItem(correio, "Correio", "- Rampas de Acesso");
		OverlayItem servico_delegacia = new OverlayItem(delegacia, "Delegacia de Polícia", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso");
		OverlayItem servico_cpfl = new OverlayItem(cpfl, "CPFL", "- Vaga de Estacionamento Exclusivo\n- Rampas de Acesso");
		
		//Adiciona o overlay ao costumizedOverlay
		
		//Indaiatuba
		itemizedOverlay.addOverlay(overlayitem);
		
		//Hospitais
		io_hospital.addOverlay(hospital_haoc);
		io_hospital.addOverlay(hospital_santa_ignes);
		
		//Escolas
		io_escola.addOverlay(escola_max);
		io_escola.addOverlay(escola_polo);
		
		//Supermercados
		io_supermercado.addOverlay(supermercado_paguemenos);
		io_supermercado.addOverlay(supermercado_paodeacucar);
		io_supermercado.addOverlay(supermercado_carrefour);
		
		//Lazer
		io_lazer.addOverlay(lazer_bosque);
		io_lazer.addOverlay(lazer_polo);
		io_lazer.addOverlay(lazer_jaragua);
		io_lazer.addOverlay(lazer_ciaei);
		
		//Bancos
		io_banco.addOverlay(banco_itau);
		io_banco.addOverlay(banco_bb);
		
		//Restaurantes
		io_restaurante.addOverlay(rest_rio);
		io_restaurante.addOverlay(rest_pezao);
		
		//Vagas
		io_vaga.addOverlay(vaga_1);
		io_vaga.addOverlay(vaga_2);
		
		//Farmacias
		io_hospital.addOverlay(farmacia_raia);
		io_hospital.addOverlay(farmacia_treze);
		
		//Serviços
		io_servico.addOverlay(servico_saae);
		io_servico.addOverlay(servico_correio);
		io_servico.addOverlay(servico_delegacia);
		io_servico.addOverlay(servico_cpfl);
		
		//Adiciona o local no mapa
		mapOverlays.add(itemizedOverlay);
		mapOverlays.add(io_hospital);
		mapOverlays.add(io_escola);
		mapOverlays.add(io_supermercado);
		mapOverlays.add(io_lazer);
		mapOverlays.add(io_banco);
		mapOverlays.add(io_restaurante);
		mapOverlays.add(io_vaga);
		mapOverlays.add(io_servico);
		
		
	
		String gid = getIntent().getStringExtra("gid");
		String cid = getIntent().getStringExtra("cid");
		
		
		
		if(gid!=null && cid!=null){
		int v1 = Integer.parseInt(gid);
		int v2 = Integer.parseInt(cid);
		
		Log.d("Variavel", "valor gid: "+ gid);
		Log.d("Variavel", "valor cid: "+ cid);
		
		Log.d("Variavel", "valor v1: "+ v1);
		Log.d("Variavel", "valor v2: "+ v2);
		
		if(v1 == 0 && v2 == 0){
			mapController.animateTo(haoc);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 0 && v2 == 1){
			mapController.animateTo(santa_ignes);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 1 && v2 == 0){
			mapController.animateTo(maxplanck);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 1 && v2 == 1){
			mapController.animateTo(polo_educacional);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 2 && v2 == 0){
			mapController.animateTo(paguemenos);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 2 && v2 == 1){
			mapController.animateTo(paodeacucar);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 2 && v2 == 2){
			mapController.animateTo(carrefour);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 3 && v2 == 0){
			mapController.animateTo(bosque);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 3 && v2 == 1){
			mapController.animateTo(polo_shopping);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 3 && v2 == 2){
			mapController.animateTo(jaragua);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 3 && v2 == 3){
			mapController.animateTo(ciaei);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 4 && v2 == 0){
			mapController.animateTo(itau);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 4 && v2 == 1){
			mapController.animateTo(bb);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 5 && v2 == 0){
			mapController.animateTo(riograndense);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 5 && v2 == 1){
			mapController.animateTo(pezao);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 6 && v2 == 0){
			mapController.animateTo(vaga1);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 6 && v2 == 1){
			mapController.animateTo(vaga2);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 7 && v2 == 0){
			mapController.animateTo(raia);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 7 && v2 == 1){
			mapController.animateTo(treze);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 8 && v2 == 0){
			mapController.animateTo(saae);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 8 && v2 == 1){
			mapController.animateTo(correio);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 8 && v2 == 2){
			mapController.animateTo(delegacia);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		if(v1 == 8 && v2 == 3){
			mapController.animateTo(cpfl);
			mapController.setZoom(19);
			Log.d("IF", "entrou no if");
			}
		}
		else{
			myLocationOverlay.runOnFirstFix(new Runnable() {
				
				@Override
				public void run() {
					mapController.animateTo(myLocationOverlay.getMyLocation());
					mapController.setZoom(19);
				}
			});
			//mapController.animateTo(indaiatuba);
			//mapController.setZoom(19);
			
		}
				
        
    }
    
    @Override
    protected void onResume() {
    	super.onResume();//This line has to stay
    	//Enable my location
    	myLocationOverlay.enableMyLocation();
    }//end method onResume
    
    /**
     * This is a method that is used in the android
     * application life cycle, in this method we will stop 
     * the MyLocationOverlay of sensing our location. This is important because when the
     * user closes the application these are things that should
     * not be running in the background as they can take up the 
     * users battery life and also eat into their data as well 
     */
	@Override
	protected void onPause() {
		super.onPause();//This line has to stay
		//Disable location detection
		myLocationOverlay.disableCompass();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
}