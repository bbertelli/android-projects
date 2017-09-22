package com.smb.teor;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facebook.AppEventsLogger;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.ProfilePictureView;
import com.smb.teor.R;
import com.startapp.android.publish.StartAppAd;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadoActivity extends FragmentActivity implements
		AnimationListener {

	double resultado_teor = 0;
	double perfil_idade = 0;
	String img_facebook;
	String texto_facebook;
	String texto_whatsapp;
	Button facebook_btn;
	Button whatsapp_btn;
	ImageView carimbo;
	TextView teor_txt;
	TextView tempo_txt;
	TextView lei_txt;
	TextView status_txt;
	TextView efeito_txt;
	Animation animZoomOut;
	private StartAppAd startAppAd = new StartAppAd(this);

	// --------------------------------------------------------------
	// DECLARAÇÃO DOS ATRIBUTOS FACE
	// --------------------------------------------------------------

	private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";

	private Button postStatusUpdateButton;
	private Button postPhotoButton;
	private Button pickFriendsButton;
	private Button pickPlaceButton;
	private ProfilePictureView profilePictureView;
	private TextView greeting;
	private PendingAction pendingAction = PendingAction.NONE;
	private ViewGroup controlsContainer;
	private GraphUser user;
	private GraphPlace place;
	private List<GraphUser> tags;
	private boolean canPresentShareDialog;

	private enum PendingAction {
		NONE, POST_PHOTO, POST_STATUS_UPDATE
	}

	private UiLifecycleHelper uiHelper;

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	private FacebookDialog.Callback dialogCallback = new FacebookDialog.Callback() {
		@Override
		public void onError(FacebookDialog.PendingCall pendingCall,
				Exception error, Bundle data) {
		}

		@Override
		public void onComplete(FacebookDialog.PendingCall pendingCall,
				Bundle data) {
		}
	};

	// --------------------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ADWARE (DEVELOPERID, APPID)
		StartAppAd.init(this, "102580822", "202254876");

		setContentView(R.layout.activity_resultado);

		teor_txt = (TextView) findViewById(R.id.teor_txt);
		tempo_txt = (TextView) findViewById(R.id.tempo_txt);
		lei_txt = (TextView) findViewById(R.id.lei_txt);
		status_txt = (TextView) findViewById(R.id.status_txt);
		efeito_txt = (TextView) findViewById(R.id.efeito_txt);
		carimbo = (ImageView) findViewById(R.id.carimbo);

		// Valores da tela anterior
		Bundle b = getIntent().getExtras();
		resultado_teor = b.getDouble("resultado_teor");
		perfil_idade = b.getDouble("perfil_idade");

		String resultadoFormatado;
		DecimalFormat fmt = new DecimalFormat("0.00");
		resultadoFormatado = fmt.format(resultado_teor);

		// load the animation
		animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.zoom_out);

		// set animation listener
		animZoomOut.setAnimationListener(this);

		verificaStatus(resultado_teor);
		teor_txt.setText(String.valueOf(resultadoFormatado) + " (g/L)");
		
		String tempoVerificado = verificaTempo(resultado_teor);
		tempo_txt.setText(tempoVerificado);

		addListenerOnButton();

		// --------------------------------------------------------------
		// MÉTODOS ONCREATE
		// --------------------------------------------------------------

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			String name = savedInstanceState
					.getString(PENDING_ACTION_BUNDLE_KEY);
			pendingAction = PendingAction.valueOf(name);
		}

		final FragmentManager fm = getSupportFragmentManager();

		// Listen for changes in the back stack so we know if a fragment got
		// popped off because the user
		// clicked the back button.
		fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
			@Override
			public void onBackStackChanged() {
				if (fm.getBackStackEntryCount() == 0) {
					// We need to re-show our UI.
					controlsContainer.setVisibility(View.VISIBLE);
				}
			}
		});

		canPresentShareDialog = FacebookDialog.canPresentShareDialog(this,
				FacebookDialog.ShareDialogFeature.SHARE_DIALOG);

		// --------------------------------------------------------------
		// --------------------------------------------------------------

	}
	
	public String verificaTempo(double teor){
		
		double resultadoTempoParcial = teor/0.2;
		double resultadoHora = (int)resultadoTempoParcial;
		double resultadoFracaoMin = resultadoTempoParcial - resultadoHora; 
		double resultadoParcialMin = resultadoFracaoMin * 60;
		double resultadoMin = (int)resultadoParcialMin;
		
		String resultadoHoraFormatado;
		DecimalFormat fmt1 = new DecimalFormat("00");
		resultadoHoraFormatado = fmt1.format(resultadoHora);
		
		String resultadoMinFormatado;
		DecimalFormat fmt2 = new DecimalFormat("00");
		resultadoMinFormatado = fmt2.format(resultadoMin);
		
		
		String tempoRecuperacao =  resultadoHoraFormatado + ":" + resultadoMinFormatado;
		
		return tempoRecuperacao;
	}

	public void verificaStatus(double resultado_teor) {

		// Verifica se é menor de idade
		if (perfil_idade == 0) {
			lei_txt.setText(getResources().getString(R.string.t3_msg_leimenor));
		}

		// 0 - ZERADO
		if (resultado_teor == 0) {
			carimbo.setBackgroundResource(R.drawable.carimbo_zerado);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_0));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_0));
			img_facebook = getResources().getString(R.string.t3_link_0);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText();
			texto_whatsapp = getResources().getString(R.string.t3_wt_0);

		}
		// 1 - FELIZ
		if (resultado_teor > 0 && resultado_teor <= 0.35) {
			carimbo.setBackgroundResource(R.drawable.carimbo_feliz);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_1));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_1));
			img_facebook = getResources().getString(R.string.t3_link_1);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText();
			texto_whatsapp = getResources().getString(R.string.t3_wt_1);
		}
		// 2 - SIMPATICO
		if (resultado_teor > 0.35 && resultado_teor <= 0.55) {
			carimbo.setBackgroundResource(R.drawable.carimbo_simpatico);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_2));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_2));
			img_facebook = getResources().getString(R.string.t3_link_2);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText();
			texto_whatsapp = getResources().getString(R.string.t3_wt_2);
		}
		// 3 - DIVERTIDO
		if (resultado_teor > 0.55 && resultado_teor <= 0.79) {
			carimbo.setBackgroundResource(R.drawable.carimbo_divertido);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_3));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_3));
			img_facebook = getResources().getString(R.string.t3_link_3);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText(); 
			texto_whatsapp = getResources().getString(R.string.t3_wt_3);
		}
		// 4 - TAGARELA
		if (resultado_teor > 0.79 && resultado_teor <= 1.13) {
			carimbo.setBackgroundResource(R.drawable.carimbo_tagarela);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_4));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_4));
			img_facebook = getResources().getString(R.string.t3_link_4);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText(); 
			texto_whatsapp = getResources().getString(R.string.t3_wt_4);
		}
		// 5 - AMOROSO
		if (resultado_teor > 1.13 && resultado_teor <= 1.40) {
			carimbo.setBackgroundResource(R.drawable.carimbo_amoroso);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_5));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_5));
			img_facebook = getResources().getString(R.string.t3_link_5);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText(); 
			texto_whatsapp = getResources().getString(R.string.t3_wt_5);
		}
		// 6 - CHATO
		if (resultado_teor > 1.40 && resultado_teor <= 1.72) {
			carimbo.setBackgroundResource(R.drawable.carimbo_chato);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_6));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_6));
			img_facebook = getResources().getString(R.string.t3_link_6);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText(); 
			texto_whatsapp = getResources().getString(R.string.t3_wt_6);
		}
		// 7 - CORAJOSO
		if (resultado_teor > 1.72 && resultado_teor <= 2.20) {
			carimbo.setBackgroundResource(R.drawable.carimbo_corajoso);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_7));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_7));
			img_facebook = getResources().getString(R.string.t3_link_7);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText(); 
			texto_whatsapp = getResources().getString(R.string.t3_wt_7);
		}
		// 8 - DEMPRIMIDO
		if (resultado_teor > 2.20 && resultado_teor <= 2.80) {
			carimbo.setBackgroundResource(R.drawable.carimbo_deprimido);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_8));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_8));
			img_facebook = getResources().getString(R.string.t3_link_8);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText(); 
			texto_whatsapp = getResources().getString(R.string.t3_wt_8);
		}
		// 9 - CHAPADO
		if (resultado_teor > 2.80 && resultado_teor <= 4.10) {
			carimbo.setBackgroundResource(R.drawable.carimbo_chapadao);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_9));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_9));
			img_facebook = getResources().getString(R.string.t3_link_9);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText(); 
			texto_whatsapp = getResources().getString(R.string.t3_wt_9);
		}
		// 10 - DEU PT
		if (resultado_teor > 4.10) {
			carimbo.setBackgroundResource(R.drawable.carimbo_deupt);
			carimbo.startAnimation(animZoomOut);
			status_txt.setText(getResources().getString(R.string.t3_sta_10));
			efeito_txt.setText(getResources().getString(R.string.t3_efe_10));
			img_facebook = getResources().getString(R.string.t3_link_10);
			texto_facebook = status_txt.getText() + getResources().getString(R.string.t3_facebook) + efeito_txt.getText(); 
			texto_whatsapp = getResources().getString(R.string.t3_wt_10);
		}

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation

		// check for zoom in animation
		if (animation == animZoomOut) {
		}

	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	@Override
	public void onAnimationStart(Animation animation) {

	}

	public void addListenerOnButton() {

		facebook_btn = (Button) findViewById(R.id.facebook_btn);

		facebook_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				try{
				    ApplicationInfo info = getPackageManager().getApplicationInfo("com.facebook.katana", 0 );
				    postStatusUpdate();
				} catch( PackageManager.NameNotFoundException e ){
					new AlertDialog.Builder(ResultadoActivity.this)
					.setTitle(getResources().getString(R.string.t1_msg_atencao))
					.setMessage(getResources().getString(R.string.t3_msg_facebook))
				    .setIcon(R.drawable.obs)
				    .show();
				}
				
			}

		});
		
		whatsapp_btn = (Button) findViewById(R.id.whatsapp_btn);

		whatsapp_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				try{
				    ApplicationInfo info = getPackageManager().getApplicationInfo("com.whatsapp", 0 );
				    share("com.whatsapp");
				} catch( PackageManager.NameNotFoundException e ){
					new AlertDialog.Builder(ResultadoActivity.this)
					.setTitle(getResources().getString(R.string.t1_msg_atencao))
					.setMessage(getResources().getString(R.string.t3_msg_whatsapp))
				    .setIcon(R.drawable.obs)
				    .show();
				}
				
			}

		});
	}
	
	//Método para compartilhar no Whatsapp
	void share(String nameApp) {
		  try
		  {
		      List<Intent> targetedShareIntents = new ArrayList<Intent>();
		      Intent share = new Intent(android.content.Intent.ACTION_SEND);
		      share.setType("text/*");
		      List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(share, 0);
		      if (!resInfo.isEmpty()){
		          for (ResolveInfo info : resInfo) {
		              Intent targetedShare = new Intent(android.content.Intent.ACTION_SEND);
		              targetedShare.setType("text/*"); // put here your mime type
		              if (info.activityInfo.packageName.toLowerCase().contains(nameApp) || info.activityInfo.name.toLowerCase().contains(nameApp)) {
		                  targetedShare.putExtra(Intent.EXTRA_SUBJECT, texto_whatsapp+"\n");
		                  targetedShare.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.t3_txt_whatsapp) + getResources().getString(R.string.link_app));
		                  targetedShare.setPackage(info.activityInfo.packageName);
		                  targetedShareIntents.add(targetedShare);
		              }
		          }
		          Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), getResources().getString(R.string.t3_titulo_compartilhamento));
		          chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
		          startActivity(chooserIntent);
		      }
		  }
		  catch(Exception e){
		  }
		}

	// --------------------------------------------------------------
	// MÉTODOS SOLTOS
	// --------------------------------------------------------------
	@Override
	protected void onResume() {
		super.onResume();
		uiHelper.onResume();

		// Call the 'activateApp' method to log an app event for use in
		// analytics and advertising reporting. Do so in
		// the onResume methods of the primary Activities that an app may be
		// launched into.
		AppEventsLogger.activateApp(this);

		startAppAd.onResume();

		// updateUI();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);

		outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (pendingAction != PendingAction.NONE
				&& (exception instanceof FacebookOperationCanceledException || exception instanceof FacebookAuthorizationException)) {
			new AlertDialog.Builder(ResultadoActivity.this)
					.setTitle(R.string.cancelled)
					.setMessage(R.string.permission_not_granted)
					.setPositiveButton(R.string.ok, null).show();
			pendingAction = PendingAction.NONE;
		} else if (state == SessionState.OPENED_TOKEN_UPDATED) {
			handlePendingAction();
		}
		updateUI();
	}

	private void updateUI() {
		Session session = Session.getActiveSession();
		boolean enableButtons = (session != null && session.isOpened());

		postStatusUpdateButton.setEnabled(enableButtons
				|| canPresentShareDialog);
		postPhotoButton.setEnabled(enableButtons);
		pickFriendsButton.setEnabled(enableButtons);
		pickPlaceButton.setEnabled(enableButtons);

		if (enableButtons && user != null) {
			profilePictureView.setProfileId(user.getId());
			greeting.setText(getString(R.string.hello_user, user.getFirstName()));
		} else {
			profilePictureView.setProfileId(null);
			greeting.setText(null);
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void handlePendingAction() {
		PendingAction previouslyPendingAction = pendingAction;
		// These actions may re-set pendingAction if they are still pending, but
		// we assume they
		// will succeed.
		pendingAction = PendingAction.NONE;

		switch (previouslyPendingAction) {
		case POST_STATUS_UPDATE:
			postStatusUpdate();
			break;
		}
	}

	private interface GraphObjectWithId extends GraphObject {
		String getId();
	}

	private void showPublishResult(String message, GraphObject result,
			FacebookRequestError error) {
		String title = null;
		String alertMessage = null;
		if (error == null) {
			title = getString(R.string.success);
			String id = result.cast(GraphObjectWithId.class).getId();
			alertMessage = getString(R.string.successfully_posted_post,
					message, id);
		} else {
			title = getString(R.string.error);
			alertMessage = error.getErrorMessage();
		}

		new AlertDialog.Builder(this).setTitle(title).setMessage(alertMessage)
				.setPositiveButton(R.string.ok, null).show();
	}

	private FacebookDialog.ShareDialogBuilder createShareDialogBuilder() {

		return new FacebookDialog.ShareDialogBuilder(this)
				.setName(getResources().getString(R.string.app_name))
				.setPicture(img_facebook)
				.setDescription(texto_facebook)
				.setLink(getResources().getString(R.string.link_app));
	}

	public void postStatusUpdate() {
		if (canPresentShareDialog) {
			FacebookDialog shareDialog = createShareDialogBuilder().build();
			uiHelper.trackPendingDialogCall(shareDialog.present());
		} else if (user != null && hasPublishPermission()) {
			final String message = getString(R.string.status_update,
					user.getFirstName(), (new Date().toString()));
			Request request = Request.newStatusUpdateRequest(
					Session.getActiveSession(), message, place, tags,
					new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							showPublishResult(message,
									response.getGraphObject(),
									response.getError());
						}
					});
			request.executeAsync();
		} else {
			pendingAction = PendingAction.POST_STATUS_UPDATE;
		}
	}

	private boolean hasPublishPermission() {
		Session session = Session.getActiveSession();
		return session != null
				&& session.getPermissions().contains("publish_actions");
	}

	// --------------------------------------------------------------

}
