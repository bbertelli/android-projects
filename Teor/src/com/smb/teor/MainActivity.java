package com.smb.teor;

import java.util.Locale;
import com.smb.teor.R;
import com.startapp.android.publish.StartAppAd;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;

public class MainActivity extends Activity {

	EditText peso_campo;
	EditText idade_campo;
	Spinner perfil_spinner;
	Spinner horas_spinner;
	RadioGroup detalhe_rgroup;
	RadioButton detalhe_rbtn;
	Button proximo_btn;
	ImageButton masc_btn;
	ImageButton fem_btn;
	ImageButton language_btn;
	ImageView titulo_logo;
	int flag_language;
	int sexo = 0;
	int perfilIdade = 0;
	double result;
	Locale LOCAL;
	private StartAppAd startAppAd = new StartAppAd(this);
	
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String Start = "startKey";
	public static final String Language = "langKey";
	SharedPreferences sharedpreferences;
	private static final  int REQUEST_CHANGE_LANGUAGE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		 LOCAL = new Locale("pt", "BR");
		 setLanguage();
		
		//ADWARE (DEVELOPERID, APPID)
		StartAppAd.init(this,"102580822","202254876");
		
		
		setContentView(R.layout.activity_main);

		peso_campo = (EditText) findViewById(R.id.peso_campo);
		idade_campo = (EditText) findViewById(R.id.idade_campo);
		masc_btn = (ImageButton) findViewById(R.id.man_btn);
		fem_btn = (ImageButton) findViewById(R.id.female_btn);
		language_btn = (ImageButton) findViewById(R.id.language_btn);
		titulo_logo = (ImageView) findViewById(R.id.titulo_logo);
		
		language_btn.setOnClickListener(onChangeLanguageButtonListener);
		
		
		addListenerOnSpinnerItemSelection();
		addListenerOnButton();
		setFlag();

		masc_btn.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {

				int action = arg1.getAction();
				if (action == MotionEvent.ACTION_DOWN) {
					masc_btn.setBackgroundResource(R.drawable.man_icon);
					fem_btn.setBackgroundResource(R.drawable.female_icon_dis);
					sexo = 0;
					return true;
				}
				return false;
			}

		});

		fem_btn.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {

				int action = arg1.getAction();
				if (action == MotionEvent.ACTION_DOWN) {
					fem_btn.setBackgroundResource(R.drawable.female_icon);
					masc_btn.setBackgroundResource(R.drawable.man_icon_dis);
					sexo = 1;
					return true;
				}
				return false;
			}

		});
	}
	
	//-------------- MÉTODOS --------------------------------------
	
	//Listener do botão de idiomas (bandeirinha)
	OnClickListener onChangeLanguageButtonListener = new OnClickListener() {

	    public void onClick(View v) {
	    	alertMessage();         
	    }
	 };
	 
	 //Método para confirmar se o usuário quer trocar o idioma
	 public void alertMessage() { 
		 DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() { 
			 public void onClick(DialogInterface dialog, int which) { 
				 switch (which) { 
				 case DialogInterface.BUTTON_POSITIVE: //Clicou em Sim
					 //Chama a tela para trocar as preferências e aguarda um resultado dela
					 startActivityForResult(new Intent(MainActivity.this, TrocarPreferenciasActivity.class), REQUEST_CHANGE_LANGUAGE);
					 break; 
				 case DialogInterface.BUTTON_NEGATIVE: //Clicou em Não
					 // Não faz nada 				  
					 break; 
					 } 
				 } 
			 }; 
			 AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			 builder.setMessage(getResources().getString(R.string.t1_msg_idioma))
			 .setPositiveButton(getResources().getString(R.string.t1_btn_ok_idioma), dialogClickListener)
			 .setNegativeButton(getResources().getString(R.string.t1_btn_no_idioma), dialogClickListener)
			 .show(); 
			 }
	 
	 //Método que recebe um resultado da tela de trocar preferencias
	 // o método recebe a confirmação de troca de idioma e qual foi o idioma selecionado
	 @Override
	   protected void onActivityResult(int requestCode, int resultCode, Intent data){
	     super.onActivityResult(requestCode, resultCode, data);

	     if(resultCode == RESULT_OK && requestCode == REQUEST_CHANGE_LANGUAGE) {
	    	
		      Configuration c = new Configuration(getResources().getConfiguration());
		      int id_lang_alterado = data.getIntExtra("id_lang_alterado", 0); //recebe qual idioma foi escolhido
		      if(id_lang_alterado == 0) { //se for 0 é Brasileiro
		    	  c.locale = LOCAL;
		      }
		      if(id_lang_alterado == 1) {       
		       c.locale = Locale.ENGLISH; //se for 1 é Inglês (US)
		      }
		      getResources().updateConfiguration(c, getResources().getDisplayMetrics()); //faz update nas configurações de resource
		      Intent intent = getIntent();
		      overridePendingTransition(0, 0);
		      intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); //permite fechar e criar a tela sem fazer aquela animação
		      finish(); // finaliza a tela 
		      overridePendingTransition(0, 0);
		      startActivity(intent); // e cria ela novamente

	     }

	   }
	 
	//Método para mudar a imagem do botão de idioma 
	public void setFlag(){
		
		int id_language = sharedpreferences.getInt(Language, 0);
		
		if(id_language == 0) { //se for 0 - Brasileiro
	    	  language_btn.setBackgroundResource(R.drawable.flag_br);
	    	  titulo_logo.setBackgroundResource(R.drawable.titulo_txt);
	      }
        if(id_language == 1) { //se for 1 - US        
        	  language_btn.setBackgroundResource(R.drawable.flag_us);
        	  titulo_logo.setBackgroundResource(R.drawable.titulo_txt_en);
        }
	}
	
	//Método para setar o idioma escolhido nas preferencias iniciais
	public void setLanguage(){
	      
		  Configuration c = new Configuration(getResources().getConfiguration());
	      int id_language = sharedpreferences.getInt(Language, 0); //pega qual idioma foi selecionado
	      
	      if(id_language == 0) { //se for 0 - Brasileiro
	    	  c.locale = LOCAL;
	      }
	      if(id_language == 1) { //se for 1 - US        
	       c.locale = Locale.ENGLISH;
	      }
	      getResources().updateConfiguration(c, getResources().getDisplayMetrics()); //atualiza as configurações de resource
	   }
	
	@Override
	public void onResume(){
	super.onResume();
	startAppAd.onResume();
	}
	
	@Override
	public void onBackPressed() {
		startAppAd.onBackPressed();
		super.onBackPressed();
	}

	//Método que adiciona listener aos combo box
	public void addListenerOnSpinnerItemSelection() {
		perfil_spinner = (Spinner) findViewById(R.id.perfil_campo);
		perfil_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		horas_spinner = (Spinner) findViewById(R.id.horas_campo);
		horas_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	//Método que adiciona listener aos botões
	public void addListenerOnButton() {
		
		detalhe_rgroup = (RadioGroup) findViewById(R.id.detalhe_group);

		perfil_spinner = (Spinner) findViewById(R.id.perfil_campo);
		horas_spinner = (Spinner) findViewById(R.id.horas_campo);
		proximo_btn = (Button) findViewById(R.id.proximo_btn);
		

		proximo_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
		
				String verificaPeso = peso_campo.getText().toString();
				String verificaIdade = idade_campo.getText().toString();
				int pos_perfil = perfil_spinner.getSelectedItemPosition();
				int pos_horas = horas_spinner.getSelectedItemPosition();
				
				if(verificaPeso == null || verificaPeso.length() == 0){
					
					new AlertDialog.Builder(MainActivity.this)
					.setTitle(getResources().getString(R.string.t1_msg_atencao))
					.setMessage(getResources().getString(R.string.t1_msg_peso))
				    .setIcon(R.drawable.obs)
				     .show();	
					
				} else if(verificaIdade == null || verificaIdade.length() == 0){
					
					new AlertDialog.Builder(MainActivity.this)
					.setTitle(getResources().getString(R.string.t1_msg_atencao))
					.setMessage(getResources().getString(R.string.t1_msg_idade))
				    .setIcon(R.drawable.obs)
				     .show();	
					
				} else if(pos_perfil == 0){
						
						new AlertDialog.Builder(MainActivity.this)
						.setTitle(getResources().getString(R.string.t1_msg_atencao))
						.setMessage(getResources().getString(R.string.msg_combobox) + getResources().getString(R.string.perfil))
					    .setIcon(R.drawable.obs)
					     .show();
						
				} else if(pos_horas == 0){
							
							new AlertDialog.Builder(MainActivity.this)
							.setTitle(getResources().getString(R.string.t1_msg_atencao))
							.setMessage(getResources().getString(R.string.msg_combobox) + getResources().getString(R.string.horas))
						    .setIcon(R.drawable.obs)
						     .show();
					
				}else {
					
					// get selected radio button from radioGroup
					int selectedId = detalhe_rgroup.getCheckedRadioButtonId();
		 
					// find the radiobutton by returned id
				    detalhe_rbtn = (RadioButton) findViewById(selectedId);
				    int detalhe = detalhe_rgroup.indexOfChild(detalhe_rbtn);
				        
					double valor_peso = Double.parseDouble(peso_campo.getText().toString());
					int valor_idade = Integer.parseInt(idade_campo.getText().toString());
					
					double fator_idade = calcFatorIdade(valor_idade, sexo);
					double fator_massa = calcFatorMassa(pos_perfil, sexo);
					double fator_sexo  = calcFatorSexo(sexo);				
					
					Intent intent = new Intent(MainActivity.this, ListaBebidasActivity.class);
					Bundle b = new Bundle();
					b.putDouble("valor_peso", valor_peso);
					b.putDouble("fator_idade", fator_idade);
					b.putDouble("fator_massa", fator_massa);
					b.putDouble("fator_sexo", fator_sexo);
					b.putDouble("perfil_idade", perfilIdade);
					b.putInt("detalhe", detalhe);
					b.putInt("pos_horas", pos_horas);
					intent.putExtras(b);
					startActivity(intent);
					
				}
				
			}

		});
	}
	
	public double calcFatorSexo(int sexo){
		
		double resultadoFatorSexo = 0;
		
		switch (sexo) {
			case 0: //Masculino
				resultadoFatorSexo = 0.81;
			break;
			
			case 1: //Feminino
				resultadoFatorSexo = 0.70;
			break;
			
			default:
			break;
		}
			
		return resultadoFatorSexo;
	}
	
	public double calcFatorIdade(int idade, int sexo){
		
		double resultadoFatorIdade = 0;
	
		switch (sexo) {
		case 0: //Masculino
			if(idade >= 0 && idade < 18){
				resultadoFatorIdade = 0.84;
				perfilIdade = 0;
			}
			if(idade >= 18 && idade <= 40){
				resultadoFatorIdade = 1;
				perfilIdade = 1;
			}
			if(idade >= 41 && idade < 60){
				resultadoFatorIdade = 0.92;
				perfilIdade = 2;
			}
			if(idade >= 60 ){
				resultadoFatorIdade = 0.84;
				perfilIdade = 3;
			}
			break;
		case 1: //Feminino
			if(idade >= 0 && idade < 18){
				resultadoFatorIdade = 0.75;
				perfilIdade = 0;
			}
			if(idade >= 18 && idade <= 40){
				resultadoFatorIdade = 0.85;
				perfilIdade = 1;
			}
			if(idade >= 41 && idade < 60){
				resultadoFatorIdade = 0.80;
				perfilIdade = 2;
			}
			if(idade >= 60 ){
				resultadoFatorIdade = 0.75;
				perfilIdade = 3;
			}
			break;

		default:
			Toast.makeText(MainActivity.this, getResources().getString(R.string.t1_msg_toast_perfilidade),Toast.LENGTH_SHORT).show();
			resultadoFatorIdade = 0;
			break;
		}
		
		return resultadoFatorIdade;
	}
	
	public double calcFatorMassa(int perfil, int sexo){
		
		double resultadoFatorMassa = 0;
		
		if(perfilIdade == 1){ //Idade entre 18 ~ 40 anos
			switch (sexo) {
			case 0: //Masculino
				if(perfil == 1){ //Magrinho
					resultadoFatorMassa = 0.85;
				}
				if(perfil == 2){ //Normal
					resultadoFatorMassa = 1;
				}
				if(perfil == 3){ //Gordinho
					resultadoFatorMassa = 1.26;
				}
				if(perfil == 4){ //Peso Pesado
					resultadoFatorMassa = 1.38;
				}
				if(perfil == 5){ //Bombado
					resultadoFatorMassa = 1.13;
				}		
				break;
				
			case 1: //Feminino
				if(perfil == 1){ //Magrinho
					resultadoFatorMassa = 0.73;
				}
				if(perfil == 2){ //Normal
					resultadoFatorMassa = 0.85;
				}
				if(perfil == 3){ //Gordinho
					resultadoFatorMassa = 0.98;
				}
				if(perfil == 4){ //Peso Pesado
					resultadoFatorMassa = 1.18;
				}
				if(perfil == 5){ //Bombado
					resultadoFatorMassa = 1.07;
				}
				break;
				
			default:
				break;
			}
		}
		if(perfilIdade == 2){ //Idade entre 41 ~ 60 anos
			switch (sexo) {
			case 0: //Masculino
				if(perfil == 1){ //Magrinho
					resultadoFatorMassa = 0.78;
				}
				if(perfil == 2){ //Normal
					resultadoFatorMassa = 0.92;
				}
				if(perfil == 3){ //Gordinho
					resultadoFatorMassa = 1.06;
				}
				if(perfil == 4){ //Peso Pesado
					resultadoFatorMassa = 1.27;
				}
				if(perfil == 5){ //Bombado
					resultadoFatorMassa = 1.07;
				}		
				break;
				
			case 1: //Feminino
				if(perfil == 1){ //Magrinho
					resultadoFatorMassa = 0.68;
				}
				if(perfil == 2){ //Normal
					resultadoFatorMassa = 0.80;
				}
				if(perfil == 3){ //Gordinho
					resultadoFatorMassa = 0.93;
				}
				if(perfil == 4){ //Peso Pesado
					resultadoFatorMassa = 1.11;
				}
				if(perfil == 5){ //Bombado
					resultadoFatorMassa = 1.03;
				}
				break;
				
			default:
				break;
			}	
		}
		if(perfilIdade == 3 || perfilIdade == 0){ //Idade entre menor de 18 e 60+
			switch (sexo) {
			case 0: //Masculino
				if(perfil == 1){ //Magrinho
					resultadoFatorMassa = 0.71;
				}
				if(perfil == 2){ //Normal
					resultadoFatorMassa = 0.65;
				}
				if(perfil == 3){ //Gordinho
					resultadoFatorMassa = 0.97;
				}
				if(perfil == 4){ //Peso Pesado
					resultadoFatorMassa = 1.15;
				}
				if(perfil == 5){ //Bombado
					resultadoFatorMassa = 1;
				}		
				break;
				
			case 1: //Feminino
				if(perfil == 1){ //Magrinho
					resultadoFatorMassa = 0.84;
				}
				if(perfil == 2){ //Normal
					resultadoFatorMassa = 0.75;
				}
				if(perfil == 3){ //Gordinho
					resultadoFatorMassa = 0.87;
				}
				if(perfil == 4){ //Peso Pesado
					resultadoFatorMassa = 1.05;
				}
				if(perfil == 5){ //Bombado
					resultadoFatorMassa = 0.98;
				}
				break;
				
			default:
				break;
			}
		}
		return resultadoFatorMassa;
	}

}
