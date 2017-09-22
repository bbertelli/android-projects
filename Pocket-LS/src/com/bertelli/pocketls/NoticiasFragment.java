package com.bertelli.pocketls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.bertelli.pocketls.rss.ListActivity;
import com.bertelli.pocketls.rss.parser.DOMParser;
import com.bertelli.pocketls.rss.parser.RSSFeed;
import com.startapp.android.publish.StartAppSDK;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class NoticiasFragment extends Fragment{
	
	private String RSSFEEDURL = "http://luansantanafc.com.br/feed/";
	RSSFeed feed;
	String fileName;
	ImageButton btn_noticias;
	ProgressDialog dialog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		StartAppSDK.init(getActivity(), "102580822", "208457602", true);

		View rootView = inflater.inflate(R.layout.fragment_noticias, container, false);
		
		/*// ADMOB
	    AdView adView = (AdView) rootView.findViewById(R.id.adView_noticias_fragment);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);*/
		
		btn_noticias = (ImageButton) rootView.findViewById(R.id.noticias_btn_download);

		btn_noticias.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				fileName = "TDRSSFeed.td";

				File feedFile = getActivity().getBaseContext().getFileStreamPath(fileName);

				ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
				if (conMgr.getActiveNetworkInfo() == null) {

					// No connectivity. Check if feed File exists
					if (!feedFile.exists()) {

						// No connectivity & Feed file doesn't exist: Show alert to exit
						// & check for connectivity
						AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
						builder.setMessage(
								"Não foi possível conectar ao servidor, \nPor favor, verifique sua conexão.")
								.setTitle("TD RSS Reader")
								.setCancelable(false)
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog,
													int id) {
												getActivity().finish();
											}
										});

						AlertDialog alert = builder.create();
						alert.show();
					} else {

						// No connectivty and file exists: Read feed from the File
						Toast toast = Toast.makeText(getActivity(),
								"Sem conexão com Internet! Carregando as últimas notícias salvas...",
								Toast.LENGTH_LONG);
						toast.show();
						feed = ReadFeed(fileName);
						startLisActivity(feed);
					}

				} else {

					// Connected - Start parsing
					new AsyncLoadXMLFeed().execute();

				}
 
			}
 
		});
				
		return rootView;
	}
	
	private void startLisActivity(RSSFeed feed) {

		Bundle bundle = new Bundle();
		bundle.putSerializable("feed", feed);

		// launch List activity
		Intent intent = new Intent(getActivity(), ListActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);

		// kill this activity
		//getActivity().finish();

	}

	private class AsyncLoadXMLFeed extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			// Obtain feed
			DOMParser myParser = new DOMParser();
			feed = myParser.parseXml(RSSFEEDURL);
			if (feed != null && feed.getItemCount() > 0)
				WriteFeed(feed);
			return null;

		}
		
		@Override
		protected void onPreExecute() {
			
			// show the dialog to indicate loading
			dialog = ProgressDialog.show(getActivity(), "", "Carregando notícias...", true);
			
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
			startLisActivity(feed);
		}

	}

	// Method to write the feed to the File
	private void WriteFeed(RSSFeed data) {

		FileOutputStream fOut = null;
		ObjectOutputStream osw = null;

		try {
			getActivity();
			fOut = getActivity().openFileOutput(fileName, FragmentActivity.MODE_PRIVATE);
			osw = new ObjectOutputStream(fOut);
			osw.writeObject(data);
			osw.flush();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Method to read the feed from the File
	private RSSFeed ReadFeed(String fName) {

		FileInputStream fIn = null;
		ObjectInputStream isr = null;

		RSSFeed _feed = null;
		File feedFile = getActivity().getBaseContext().getFileStreamPath(fileName);
		if (!feedFile.exists())
			return null;

		try {
			fIn = getActivity().openFileInput(fName);
			isr = new ObjectInputStream(fIn);

			_feed = (RSSFeed) isr.readObject();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				fIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return _feed;

	}

}