package com.smb.teor;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		/*Toast.makeText(parent.getContext(), 
				"Item selecionado: " + parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_SHORT).show();*/
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
