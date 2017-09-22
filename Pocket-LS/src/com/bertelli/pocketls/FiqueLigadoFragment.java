package com.bertelli.pocketls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FiqueLigadoFragment extends Fragment {
	
	public FiqueLigadoFragment(){}
	
	ImageButton btn_face;
	ImageButton btn_twitter;
	ImageButton btn_gplus;
	ImageButton btn_whats;
	//ImageButton btn_jmapp;
	
	
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_fiqueligado, container, false);
        
        
        
            // --------------------------------------------------------------
     		// MÉTODOS ONCREATE
     		// --------------------------------------------------------------

     		uiHelper = new UiLifecycleHelper(getActivity(), callback);
     		uiHelper.onCreate(savedInstanceState);

     		if (savedInstanceState != null) {
     			String name = savedInstanceState
     					.getString(PENDING_ACTION_BUNDLE_KEY);
     			pendingAction = PendingAction.valueOf(name);
     		}

     		final FragmentManager fm = getFragmentManager();

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

     		canPresentShareDialog = FacebookDialog.canPresentShareDialog(getActivity(),
     				FacebookDialog.ShareDialogFeature.SHARE_DIALOG);

     		// --------------------------------------------------------------
     		// --------------------------------------------------------------
        
        /*// ADMOB
	    AdView adView = (AdView) rootView.findViewById(R.id.adView_fiqueligado_fragment);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);*/
        
        btn_face = (ImageButton) rootView.findViewById(R.id.fl_btn_facebook);
        btn_twitter = (ImageButton) rootView.findViewById(R.id.fl_btn_twitter);
        btn_gplus = (ImageButton) rootView.findViewById(R.id.fl_btn_gplus);
        btn_whats = (ImageButton) rootView.findViewById(R.id.fl_btn_whats);
        //btn_jmapp = (ImageButton) rootView.findViewById(R.id.fl_btn_jmapp);
        
        addListenerOnButton();
        
       /* btn_face.setOnClickListener(new OnClickListener() {
            @Override
      		public void onClick(View arg0) {            	
            	try{
    		    ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.facebook.katana", 0 );
    		    share("com.facebook.katana");
    		} catch( PackageManager.NameNotFoundException e ){
    			new AlertDialog.Builder(getActivity())
    			.setTitle(getResources().getString(R.string.msg_atencao))
    			.setMessage(getResources().getString(R.string.msg_face))
    		    .setIcon(R.drawable.obs)
    		    .show();
    		}
    	}});*/
        
        btn_twitter.setOnClickListener(new OnClickListener() {
            @Override
      		public void onClick(View arg0) {
            try{
    		    ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.twitter.android", 0 );
    		    share("com.twitter.android");
    		} catch( PackageManager.NameNotFoundException e ){
    			new AlertDialog.Builder(getActivity())
    			.setTitle(getResources().getString(R.string.msg_atencao))
    			.setMessage(getResources().getString(R.string.msg_twitter))
    		    .setIcon(R.drawable.obs)
    		    .show();
    		}
    	}});
        
        btn_gplus.setOnClickListener(new OnClickListener() {     	 
    		  @Override
    		  public void onClick(View arg0) {       
    			  try{
    				    ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.google.android.apps.plus", 0 );
    				    share("com.google.android.apps.plus");
    				} catch( PackageManager.NameNotFoundException e ){
    					new AlertDialog.Builder(getActivity())
    					.setTitle(getResources().getString(R.string.msg_atencao))
    					.setMessage(getResources().getString(R.string.msg_gplus))
    				    .setIcon(R.drawable.obs)
    				    .show();
    				}   			  
      		  }});
        
        btn_whats.setOnClickListener(new OnClickListener() {     	 
  		  @Override
  		  public void onClick(View arg0) {       
  			  try{
  				    ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.whatsapp", 0 );
  				    share("com.whatsapp");
  				} catch( PackageManager.NameNotFoundException e ){
  					new AlertDialog.Builder(getActivity())
  					.setTitle(getResources().getString(R.string.msg_atencao))
  					.setMessage(getResources().getString(R.string.msg_whats))
  				    .setIcon(R.drawable.obs)
  				    .show();
  				}   			  
    		  }});
        
        /*btn_jmapp.setOnClickListener(new OnClickListener() {     	 
  		  @Override
  		  public void onClick(View arg0) {       
  			  Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.bertelli.pocketjm"));
  			  startActivity(intent);  			  
    		  }});*/
              
        return rootView;
    }
	
	
	
	public void addListenerOnButton() {

		btn_face.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				try{
				    ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.facebook.katana", 0 );
				    postStatusUpdate();
				} catch( PackageManager.NameNotFoundException e ){
					new AlertDialog.Builder(getActivity())
	    			.setTitle(getResources().getString(R.string.msg_atencao))
	    			.setMessage(getResources().getString(R.string.msg_face))
	    		    .setIcon(R.drawable.obs)
	    		    .show();
				}
				
			}

		});}
	
	//Método para compartilhar no Whatsapp
		void share(String nameApp) {
			  try
			  {
			      List<Intent> targetedShareIntents = new ArrayList<Intent>();
			      Intent share = new Intent(android.content.Intent.ACTION_SEND);
			      share.setType("text/*");
			      List<ResolveInfo> resInfo = getActivity().getPackageManager().queryIntentActivities(share, 0);
			      if (!resInfo.isEmpty()){
			          for (ResolveInfo info : resInfo) {
			              Intent targetedShare = new Intent(android.content.Intent.ACTION_SEND);
			              targetedShare.setType("text/*"); // put here your mime type
			              if (info.activityInfo.packageName.toLowerCase().contains(nameApp) || info.activityInfo.name.toLowerCase().contains(nameApp)) {
			                  targetedShare.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.txt_compartilhamento)+"\n\n");
			                  targetedShare.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.txt_twitter) + getResources().getString(R.string.link_app));
			                  targetedShare.setPackage(info.activityInfo.packageName);
			                  targetedShareIntents.add(targetedShare);
			              }
			          }
			          Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), getResources().getString(R.string.titulo_compartilhamento));
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

		/*protected void onResume() {
			super.onResume();
			uiHelper.onResume();

			// Call the 'activateApp' method to log an app event for use in
			// analytics and advertising reporting. Do so in
			// the onResume methods of the primary Activities that an app may be
			// launched into.
			AppEventsLogger.activateApp(getActivity());

			// updateUI();
		}

		protected void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			uiHelper.onSaveInstanceState(outState);

			outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
		}

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
		}*/

		private void onSessionStateChange(Session session, SessionState state,
				Exception exception) {
			if (pendingAction != PendingAction.NONE
					&& (exception instanceof FacebookOperationCanceledException || exception instanceof FacebookAuthorizationException)) {
				new AlertDialog.Builder(getActivity())
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

			new AlertDialog.Builder(getActivity()).setTitle(title).setMessage(alertMessage)
					.setPositiveButton(R.string.ok, null).show();
		}

		private FacebookDialog.ShareDialogBuilder createShareDialogBuilder() {

			return new FacebookDialog.ShareDialogBuilder(getActivity())
					.setName(getResources().getString(R.string.app_name))
					.setDescription(getResources().getString(R.string.txt_compartilhamento))
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
