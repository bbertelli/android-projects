package com.bertelli.ca;

import com.bertelli.ca.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class SelecionarCidade extends Activity{
	
	public ImageButton btn_entrar_cidade;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cidade);
        
        btn_entrar_cidade = (ImageButton) findViewById(R.id.btn_entrar_cidade);      		
        
        btn_entrar_cidade.setOnTouchListener(new OnTouchListener() {
        	
        	@Override
        	public boolean onTouch(View arg0, MotionEvent me) {
        		
        		Intent telaGMap = new Intent(SelecionarCidade.this, TabsMenu.class);
        		
        		
        		if (me.getAction() == MotionEvent.ACTION_DOWN) {
        			btn_entrar_cidade.setImageResource(R.drawable.btn_ok_blue_pressed);
        			return true;
        			
        		} else if (me.getAction() == MotionEvent.ACTION_UP) {
        			btn_entrar_cidade.setImageResource(R.drawable.btn_ok_blue);
        			SelecionarCidade.this.startActivity(telaGMap);
        			return true;
        		}
        		
        		return false;
        		
        	}
        	
        });
    }
    
    public void onClickEnterData(View btnAdd) {

        startActivity(new Intent(SelecionarCidade.this, TabsMenu.class));

    }
    
}