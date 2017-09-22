package com.smb.teor;

import java.util.ArrayList;
import com.smb.teor.R;
import com.startapp.android.publish.StartAppAd;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ListaBebidasActivity extends Activity{

	EditText quantidade_campo;
	Spinner tipo_bebida_spinner;
	Spinner tipo_qtd_spinner;
	Button resultado_btn;
	Button add_btn;
	double result = 0;
	double fator_idade = 0;
	double fator_massa = 0;
	double fator_sexo = 0;
	double valor_peso = 0;
	double perfil_idade = 0;
	int detalhe = 0;
	int pos_horas = 0;
	private StartAppAd startAppAd = new StartAppAd(this);
	
	ListView listView;
	ListaBebidasAdapter myadapter;
	ArrayList<String> arr_cars = new ArrayList<String>();
	ArrayList<String> lista_selecionada = new ArrayList<String>();
	ArrayList<Bitmap> arr_bitmaps = new ArrayList<Bitmap>();
	ArrayList<Bitmap> lista_icones = new ArrayList<Bitmap>();
	ArrayList<Double> lista_calc = new ArrayList<Double>();
	AlertDialog.Builder alertDialogBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//ADWARE (DEVELOPERID, APPID)
		StartAppAd.init(this, "102580822", "202254876");
		
		setContentView(R.layout.activity_listabebidas);
				
		quantidade_campo = (EditText) findViewById(R.id.quantidade_campo);
		listView = (ListView)findViewById(R.id.lista_bebidas);
		listView.setEmptyView(findViewById(R.id.lista_vazia));
		
		add_btn = (Button) findViewById(R.id.add_btn);
		
		addListenerOnSpinnerItemSelection();
		addListenerOnButton();
		 
		 //Valores da tela anterior
		 Bundle b = getIntent().getExtras();
		 valor_peso = b.getDouble("valor_peso");
		 fator_idade = b.getDouble("fator_idade");
		 fator_massa = b.getDouble("fator_massa");
		 fator_sexo = b.getDouble("fator_sexo");
		 perfil_idade = b.getDouble("perfil_idade");
		 detalhe = b.getInt("detalhe");
		 pos_horas = b.getInt("pos_horas");
		 
		 
		 //Array tipo_bebida
		 arr_cars.add(getResources().getString(R.string.bebida_1));
		 arr_cars.add(getResources().getString(R.string.bebida_2));
		 arr_cars.add(getResources().getString(R.string.bebida_3));
		 arr_cars.add(getResources().getString(R.string.bebida_4));
		 arr_cars.add(getResources().getString(R.string.bebida_5));
		 arr_cars.add(getResources().getString(R.string.bebida_6));
		 arr_cars.add(getResources().getString(R.string.bebida_7));
		 arr_cars.add(getResources().getString(R.string.bebida_8));
		 arr_cars.add(getResources().getString(R.string.bebida_9));
		 
		 //Array icones tipo_bebida
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.cerveja));
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.cerveja));
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.wine));
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.champagne));
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.whiskey));
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.cachaca));
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.conhaque));
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.vodka));
		 arr_bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.tequilashot));
		 
	        
	        add_btn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					//Esconde o teclado ao clicar no botão ADD
					InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE); 

					inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                               InputMethodManager.HIDE_NOT_ALWAYS);
					//-------------------
					
					int pos_tipo_bebida = tipo_bebida_spinner.getSelectedItemPosition();
					int pos_qtd_bebida = tipo_qtd_spinner.getSelectedItemPosition();
					String verificaQtd = quantidade_campo.getText().toString();
					
					if(pos_tipo_bebida == 0){
						
						new AlertDialog.Builder(ListaBebidasActivity.this)
						.setTitle(getResources().getString(R.string.t1_msg_atencao))
						.setMessage(getResources().getString(R.string.msg_combobox) + getResources().getString(R.string.tipo_bebida))
					    .setIcon(R.drawable.obs)
					     .show();
					
					} else if(pos_qtd_bebida == 0){
						
						new AlertDialog.Builder(ListaBebidasActivity.this)
						.setTitle(getResources().getString(R.string.t1_msg_atencao))
						.setMessage(getResources().getString(R.string.msg_combobox) + getResources().getString(R.string.tipo_qtd))
					    .setIcon(R.drawable.obs)
					     .show();
						
					} else if(verificaQtd == null || verificaQtd.length() == 0){
						
						new AlertDialog.Builder(ListaBebidasActivity.this)
						.setTitle(getResources().getString(R.string.t1_msg_atencao))
						.setMessage(getResources().getString(R.string.t2_msg_quantidade))
					    .setIcon(R.drawable.obs)
					     .show();
						
					} else {
					
						// Verificando posição do Spinner tipo_bebida para pegar o nome e o icone da bebida
						switch (pos_tipo_bebida) {
						case 1:
							lista_selecionada.add(arr_cars.get(0) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(0));
							lista_calc.add(calcResultadoTeor());						
							break;
						case 2:
							lista_selecionada.add(arr_cars.get(1) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(1));
							lista_calc.add(calcResultadoTeor());
							break;
						case 3:
							lista_selecionada.add(arr_cars.get(2) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(2));
							lista_calc.add(calcResultadoTeor());
							break;
						case 4:
							lista_selecionada.add(arr_cars.get(3) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(3));
							lista_calc.add(calcResultadoTeor());
							break;
						case 5:
							lista_selecionada.add(arr_cars.get(4) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(4));
							lista_calc.add(calcResultadoTeor());
							break;
						case 6:
							lista_selecionada.add(arr_cars.get(5) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(5));
							lista_calc.add(calcResultadoTeor());
							break;
						case 7:
							lista_selecionada.add(arr_cars.get(6) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(6));
							lista_calc.add(calcResultadoTeor());
							break;
						case 8:
							lista_selecionada.add(arr_cars.get(7) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(7));
							lista_calc.add(calcResultadoTeor());
							break;
						case 9:
							lista_selecionada.add(arr_cars.get(8) + "\n" + quantidade_campo.getText() + " " + String.valueOf(tipo_qtd_spinner.getSelectedItem()));
							lista_icones.add(arr_bitmaps.get(8));
							lista_calc.add(calcResultadoTeor());
							break;
	
						default:
							break;
						}
						
				        myadapter.notifyDataSetChanged();
				        quantidade_campo.setText("");
					}
				}
				
			});
	        
	        myadapter = new ListaBebidasAdapter(ListaBebidasActivity.this, lista_icones, lista_selecionada, lista_calc);
	        
	        listView.setSelector( R.drawable.list_selector);
	        listView.setAdapter(myadapter);

	}
	
	@Override
	public void onResume(){
	super.onResume();
	startAppAd.onResume();
	}
	
	public class ListaBebidasAdapter extends BaseAdapter
    {
    
    	public String title[];
    	public String description[];
    	ArrayList<String> arr_calllog_name = new ArrayList<String>();
    	public Activity context;
    	ArrayList<Bitmap> imageId; 

    	public LayoutInflater inflater;
    	
    	public ListaBebidasAdapter(Activity context, ArrayList<Bitmap> arr_bitmaps, ArrayList<String> arr_calllog_name, ArrayList<Double> lista_calc) {
    		super();

    		this.imageId = arr_bitmaps;
    		this.context = context;
    		this.arr_calllog_name = arr_calllog_name; 	
    	    this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	}
    	
    	public ArrayList<Bitmap> getImageId() {
    	    return imageId;
    	}
    	public void setImageId(ArrayList<Bitmap> imageId) {
    	    this.imageId = imageId;
    	}	
		    
    	@Override
    	public int getCount() {
    		return arr_calllog_name.size();
    	}

    	@Override
    	public Object getItem(int position) {
    		return null;
    	}

    	@Override
    	public long getItemId(int position) {
    		return 0;
    	}

    	public class ViewHolder
    	{
    		ImageView image;
    		TextView txtName;
    		Button btn;
    		RelativeLayout row;
    	}

    	@Override
    	public View getView(final int position, View convertView, ViewGroup parent) {

    		final ViewHolder holder;
    		if(convertView==null)
    		{
    			holder = new ViewHolder();
    			convertView = inflater.inflate(R.layout.list_item, null);
    			
    			holder.image = (ImageView) convertView.findViewById(R.id.imageView);
    			holder.txtName = (TextView) convertView.findViewById(R.id.textView);
    			holder.btn = (Button) convertView.findViewById(R.id.button);
    			holder.row = (RelativeLayout) convertView.findViewById(R.id.lineItem);
    			convertView.setTag(holder);
    		}
    		else
    			holder=(ViewHolder)convertView.getTag();

    		holder.image.setImageBitmap(getImageId().get(position));
    		holder.txtName.setText(arr_calllog_name.get(position));
    		
    		holder.btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				 	lista_selecionada.remove(position);
				 	lista_calc.remove(position);
				 	lista_icones.remove(position);
				 	myadapter.notifyDataSetChanged();
				}
			});
    		
    		return convertView;
 		
    	}
	
    }
	
	public void addListenerOnSpinnerItemSelection() {
		
		tipo_bebida_spinner = (Spinner) findViewById(R.id.tipobebida_campo);
		tipo_bebida_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
		tipo_qtd_spinner = (Spinner) findViewById(R.id.tipoqtd_campo);
		tipo_qtd_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}
	
	
	public double calcResultadoTeor(){
		
		double resultadoCalcTeor = 0;
		
		tipo_bebida_spinner = (Spinner) findViewById(R.id.tipobebida_campo);
		tipo_qtd_spinner = (Spinner) findViewById(R.id.tipoqtd_campo);
		
		int pos_tipo_bebida = tipo_bebida_spinner.getSelectedItemPosition();	
		int pos_tipo_qtd = tipo_qtd_spinner.getSelectedItemPosition();
		int valor_quantidade = Integer.parseInt(quantidade_campo.getText().toString());
		
		double fator_tipo_bebida = 0;
		double fator_tipo_qtd = 0;
		
		// Verificando posição do Spinner tipo_bebida para pegar o fator
				switch (pos_tipo_bebida) {
				case 1:
					fator_tipo_bebida = 0.035; //Cerveja Comum
					break;
				case 2:
					fator_tipo_bebida = 0.06; //Cerveja Premium
					break;
				case 3:
					fator_tipo_bebida = 0.12; //Vinho
					break;
				case 4:
					fator_tipo_bebida = 0.11; //Espumante
					break;
				case 5:
					fator_tipo_bebida = 0.40; //Uísque
					break;
				case 6:
					fator_tipo_bebida = 0.42; //Cachaça
					break;
				case 7:
					fator_tipo_bebida = 0.41; //Conhaque
					break;
				case 8:
					fator_tipo_bebida = 0.43; //Vodka
					break;
				case 9:
					fator_tipo_bebida = 0.46; //Tequila
					break;

				default:
					fator_tipo_bebida = 0;
					break;
				}
		
		// Verificando posição do Spinner tipo_quantidade para pegar o fator
				switch (pos_tipo_qtd) {
				case 1:
					fator_tipo_qtd = 200; //copo de 200 ml
					break;
				case 2:
					fator_tipo_qtd = 355; //1 dose de copo 200 ml
					break;
				case 3:
					fator_tipo_qtd = 0.45 * 200; //1 dose de copo 200 ml
					break;
				case 4:
					fator_tipo_qtd = 1; //ml digitado pelo usuário
					break;
				case 5:
					fator_tipo_qtd = 29.57; //fl.oz. digitado pelo usuário
					break;	

				default:
					break;
				}
				
		// Calcular em função do detalhe (Alimentado ou Jejum)
		switch (detalhe) {
		case 0: //Comendo
			resultadoCalcTeor = (valor_quantidade * fator_tipo_qtd * fator_tipo_bebida * fator_sexo)/ (valor_peso * fator_massa * fator_idade * fator_sexo);
			break;
		case 1: //Jejum
			resultadoCalcTeor = (valor_quantidade * fator_tipo_qtd * fator_tipo_bebida)/ (valor_peso * fator_massa * fator_idade * fator_sexo);
			break;

		default:
			break;
		}		
		if(pos_horas > 0){
			for (int i = 0; i < pos_horas; i++) {
				resultadoCalcTeor = resultadoCalcTeor * (1-0.35);
			}
			
		}
		
		return resultadoCalcTeor;
	}

	
	public void addListenerOnButton() {
		

		resultado_btn = (Button) findViewById(R.id.resultado_btn);
		resultado_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {	
				
				if(listView.getCount() == 0){
					new AlertDialog.Builder(ListaBebidasActivity.this)
					.setTitle(getResources().getString(R.string.t1_msg_atencao))
					.setMessage(getResources().getString(R.string.t2_msg_lista))
				    .setIcon(R.drawable.obs)
				     .show();	
				} else {
				
					for (double calc : lista_calc) {
						result = result + calc;
					}
	
					double resultado_teor = result;
					result = 0;
					Intent intent = new Intent(ListaBebidasActivity.this, ResultadoActivity.class);
					Bundle b = new Bundle();
					b.putDouble("resultado_teor", resultado_teor);
					b.putDouble("perfil_idade", perfil_idade);
					intent.putExtras(b);
					startActivity(intent);
				}
			}

		});
	}
	
}
