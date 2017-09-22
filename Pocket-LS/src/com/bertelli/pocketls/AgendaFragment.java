package com.bertelli.pocketls;

import com.bertelli.pocketls.model.ConnectionDetector;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AgendaFragment extends Fragment {
	
	public AgendaFragment(){}
	
	private Button btn_janeiro;
	private Button btn_fevereiro;
	private Button btn_marco;
	private Button btn_abril;
	private Button btn_maio;
	private Button btn_junho;
	private Button btn_julho;
	private Button btn_agosto;
	private Button btn_setembro;
	private Button btn_outubro;
	private Button btn_novembro;
	private Button btn_dezembro;
	
	// Connection detector class
	ConnectionDetector cd;
	// flag for Internet connection status
	Boolean isInternetPresent = false;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
        
        /*// ADMOB
	    AdView adView = (AdView) rootView.findViewById(R.id.adView_agenda_fragment);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);*/
        
        btn_janeiro = (Button) rootView.findViewById(R.id.btn_jan);
        btn_fevereiro = (Button) rootView.findViewById(R.id.btn_fev);
        btn_marco = (Button) rootView.findViewById(R.id.btn_mar);
        btn_abril = (Button) rootView.findViewById(R.id.btn_abril);
        btn_maio = (Button) rootView.findViewById(R.id.btn_maio);
        btn_junho = (Button) rootView.findViewById(R.id.btn_jun);
        btn_julho = (Button) rootView.findViewById(R.id.btn_julho);
        btn_agosto = (Button) rootView.findViewById(R.id.btn_agosto);
        btn_setembro = (Button) rootView.findViewById(R.id.btn_set);
        btn_outubro = (Button) rootView.findViewById(R.id.btn_out);
        btn_novembro = (Button) rootView.findViewById(R.id.btn_nov);
        btn_dezembro = (Button) rootView.findViewById(R.id.btn_dez);
        
        btn_janeiro.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
  			cd = new ConnectionDetector(getActivity().getApplicationContext());
  			
  			// get Internet status
  			isInternetPresent = cd.isConnectingToInternet();

  			// check for Internet status
  			if (isInternetPresent) {
  				// Internet Connection is Present
  				// make HTTP requests	

  			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
  		    intent.putExtra("mes", "1");
  		  getActivity().startActivity(intent);
  		  } else {
  			// Internet connection is not present
  			// Ask user to connect to Internet
  			new AlertDialog.Builder(getActivity())
  			.setTitle("ATENÇÃO!")
  			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
  		    .setIcon(R.drawable.obs)
  		    .setCancelable(true)
  		    .show();
  		}
   
  		  }});
        
        btn_fevereiro.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "2");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_marco.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "3");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_abril.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "4");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_maio.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "5");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_junho.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "6");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_julho.setOnClickListener(new OnClickListener() {
 
		  @Override
		  public void onClick(View arg0) {
			// creating connection detector class instance
	  			cd = new ConnectionDetector(getActivity().getApplicationContext());
	  			
	  			// get Internet status
	  			isInternetPresent = cd.isConnectingToInternet();

	  			// check for Internet status
	  			if (isInternetPresent) {
	  				// Internet Connection is Present
	  				// make HTTP requests	

	  			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
	  		    intent.putExtra("mes", "7");
	  		  getActivity().startActivity(intent);
	  		  } else {
	  			// Internet connection is not present
	  			// Ask user to connect to Internet
	  			new AlertDialog.Builder(getActivity())
	  			.setTitle("ATENÇÃO!")
	  			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
	  		    .setIcon(R.drawable.obs)
	  		    .setCancelable(true)
	  		    .show();
	  		}
	   
	  		  }});
        
        btn_agosto.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "8");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_setembro.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "9");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_outubro.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "10");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_novembro.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "11");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
        
        btn_dezembro.setOnClickListener(new OnClickListener() {
        	 
  		  @Override
  		  public void onClick(View arg0) {
  			// creating connection detector class instance
    			cd = new ConnectionDetector(getActivity().getApplicationContext());
    			
    			// get Internet status
    			isInternetPresent = cd.isConnectingToInternet();

    			// check for Internet status
    			if (isInternetPresent) {
    				// Internet Connection is Present
    				// make HTTP requests	

    			Intent intent = new Intent(getActivity().getBaseContext(), AgendaActivity.class);
    		    intent.putExtra("mes", "12");
    		  getActivity().startActivity(intent);
    		  } else {
    			// Internet connection is not present
    			// Ask user to connect to Internet
    			new AlertDialog.Builder(getActivity())
    			.setTitle("ATENÇÃO!")
    			.setMessage("É necessário estar Conectado à Internet para visualizar a Agenda de Shows.")
    		    .setIcon(R.drawable.obs)
    		    .setCancelable(true)
    		    .show();
    		}
     
    		  }});
		
        return rootView;
    }
}
