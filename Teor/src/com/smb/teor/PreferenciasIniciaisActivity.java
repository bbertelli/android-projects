package com.smb.teor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PreferenciasIniciaisActivity extends Activity{
	
	RadioGroup pref_rgroup;
	RadioButton pref_rbtn;
	Button pref_ok_btn;
	TextView pref_versao;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String Language = "langKey";
	public static final String Start = "startKey";
	
	SharedPreferences sharedpreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_preferenciasiniciais);
		
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		pref_versao = (TextView) findViewById(R.id.pref_versao);
		
		PackageInfo pInfo;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			String version = pInfo.versionName;
			pref_versao.setText("Version: " + version + " - 2014");
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		checkStart();
		addListenerOnButton();
		
	}
	
	public void checkStart(){
		
		int checkStart = sharedpreferences.getInt(Start, 0);
		
		if (checkStart == 1){
			Intent intent = new Intent(PreferenciasIniciaisActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
	    }
	}
	
	public void addListenerOnButton() {
		
		pref_ok_btn = (Button) findViewById(R.id.pref_ok_btn);
		pref_rgroup = (RadioGroup) findViewById(R.id.pref_group);
		
		pref_ok_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// get selected radio button from radioGroup
				int selectedId = pref_rgroup.getCheckedRadioButtonId();

				// find the radiobutton by returned id
				pref_rbtn = (RadioButton) findViewById(selectedId);
			    int id_language = pref_rgroup.indexOfChild(pref_rbtn);
			    int id_start = 1; 
			    
			    Editor editor = sharedpreferences.edit();
			    editor.putInt(Language, id_language);
			    editor.putInt(Start, id_start);
			    editor.commit();
			    
			    checkStart();
			}
		});
	}
}
