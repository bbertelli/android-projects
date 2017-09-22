package com.bertelli.pocketls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bertelli.pocketls.rss.parser.JSONParser;
import com.startapp.android.publish.StartAppSDK;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AgendaActivity extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;
	
	Intent intent;
	public String url_mes;
	public String url_nome_php;
	private String url_get_agenda;

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_AGENDA = "agenda";
	private static final String TAG_ID = "id";
	private static final String TAG_DIA = "dia";
	private static final String TAG_MES = "mes";
	private static final String TAG_ANO = "ano";
	private static final String TAG_CIDADE = "cidade";
	private static final String TAG_EVENTO = "evento";

	// products JSONArray
	JSONArray products = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StartAppSDK.init(this, "102580822", "208457602", true);
		setContentView(R.layout.activity_agenda);
		
		/*// ADMOB
	    AdView adView1 = (AdView) findViewById(R.id.adView_agenda_activity);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView1.loadAd(adRequest);*/

		intent = getIntent();
		url_mes = intent.getStringExtra("mes");
		
		if(url_mes.equals("1")){ url_nome_php = "get_janeiro.php";}
		if(url_mes.equals("2")){ url_nome_php = "get_fevereiro.php";}
		if(url_mes.equals("3")){ url_nome_php = "get_marco.php";}
		if(url_mes.equals("4")){ url_nome_php = "get_abril.php";}
		if(url_mes.equals("5")){ url_nome_php = "get_maio.php";}
		if(url_mes.equals("6")){ url_nome_php = "get_junho.php";}
		if(url_mes.equals("7")){ url_nome_php = "get_julho.php";}
		if(url_mes.equals("8")){ url_nome_php = "get_agosto.php";}
		if(url_mes.equals("9")){ url_nome_php = "get_setembro.php";}
		if(url_mes.equals("10")){ url_nome_php = "get_outubro.php";}
		if(url_mes.equals("11")){ url_nome_php = "get_novembro.php";}
		if(url_mes.equals("12")){ url_nome_php = "get_dezembro.php";}
		
		url_get_agenda = "http://agendashow.esy.es/android_connect/" + url_nome_php;

		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();
		
		// Loading products in Background Thread
		new LoadAllProducts().execute();
		
		// Get listview
		ListView lv = getListView();
		lv.setEmptyView(findViewById(R.id.txt_listavazia));
	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AgendaActivity.this);
			pDialog.setMessage("Atualizando Agenda de Shows!\nPor favor, aguarde...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			params.add(new BasicNameValuePair("idartista", "0"));
			JSONObject json = jParser.makeHttpRequest(url_get_agenda, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_AGENDA);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_ID);
						String dia = c.getString(TAG_DIA);
						String mes = c.getString(TAG_MES);
						String ano = c.getString(TAG_ANO);
						String cidade = c.getString(TAG_CIDADE);
						String evento = c.getString(TAG_EVENTO);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_ID, id);
						map.put(TAG_DIA, dia);
						map.put(TAG_MES, mes);
						map.put(TAG_ANO, ano);
						map.put(TAG_CIDADE, cidade);
						map.put(TAG_EVENTO, evento);

						// adding HashList to ArrayList
						productsList.add(map);
					}
				} else {
					// lista vazia
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							AgendaActivity.this, productsList,
							R.layout.item_list_agenda, new String[] { 
									TAG_ID,
									TAG_DIA,
									TAG_MES,
									TAG_ANO,
									TAG_CIDADE,
									TAG_EVENTO},
							new int[] { 
									R.id.id, 
									R.id.dia, 
									R.id.mes, 
									R.id.ano, 
									R.id.cidade, 
									R.id.evento,
									});
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}