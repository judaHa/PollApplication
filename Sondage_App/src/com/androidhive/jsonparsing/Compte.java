package com.androidhive.jsonparsing;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.app.Activity;
import android.app.ProgressDialog;

import com.Myapp.sondagepfe.library.*;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class Compte extends Activity {

/*String uid;
	
	TextView txtName ;
	TextView txtDate ;
	TextView txtProfession ;
	TextView txtEmail ;
	 private ProgressDialog pDialog;
	
	private static String KEY_SUCCESS = "success";
	private static final String KEY_user = "user";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	//private static String KEY_Sexe = "Sexe";
	private static String KEY_Date = "Date";
	private static String KEY_Profession = "Profession";
	private static String KEY_EMAIL = "email";
	JSONParser jsonParser = new JSONParser();
	 
    // single product url
    private static final String url_User_detials = "http://10.0.2.2:81/android_login_api/include/user_info.php";
 
	
	*/
	
	UserFunctions userFunctions;
	Button btnProfil;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		userFunctions = new UserFunctions();
		//if(userFunctions.isUserLoggedIn(getApplicationContext())){
			//setContentView(R.layout.compteconnecte);
		
		if (Authentification.connectBool== true) {
			setContentView(R.layout.compteconnecte);
			addListenerOnButtonCompteConnecte();
			
		}else{
				setContentView(R.layout.compte);
			addListenerOnButton();}
	}
		
	
	
		/*((Button)findViewById(R.id.cnx)).setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		// Nous affichons un message à l’utilisateur
		//Toast.makeText(this,"connexion ", Toast.LENGTH_LONG).show(); */
	
	public void addListenerOnButton() {
		 
		final Context context = this;
		((Button)findViewById(R.id.cnx)).setOnClickListener(new OnClickListener(){
		
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, Authentification.class);
                            startActivity(intent);   
 
			}
 
		});
 
	}
	
	
	
	  public  void addListenerOnButtonCompteConnecte() {
      	
      	
     	 userFunctions = new UserFunctions();

     			 
     			final Context context = this;
     			((Button)findViewById(R.id.btnLogout)).setOnClickListener(new OnClickListener(){
     				@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				userFunctions.logoutUser(getApplicationContext());
 				Intent login = new Intent(getApplicationContext(), MainActivity.class);
 	        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 	        	startActivity(login);
 	        	// Closing  screen
 	        	Authentification.connectBool=false;
 	        	finish();
 			}
 		});
     			 btnProfil = (Button) findViewById(R.id.btnProfil);
     	        
     	        
     	        btnProfil.setOnClickListener(new View.OnClickListener() {

     				public void onClick(View view) {
     					
     					
     		     
     					/*int success;
	                    try {
	                    	
	                    	
	                        // Building Parameters
	                        List<NameValuePair> params = new ArrayList<NameValuePair>();
	                        params.add(new BasicNameValuePair("uid", uid));
	 
	                        // getting user details by making HTTP request
	                        // Note that user details url will use GET request
	                        JSONObject json = jsonParser.makeHttpRequest(
	                                url_User_detials, "GET", params);
	 
	                        // check your log for json response
	                        Log.d("Single user Details", json.toString());
	 
	                        // json success tag
	                        success = json.getInt(KEY_SUCCESS);
	                        if (success == 1) {
	                            // successfully received user details
	                            JSONArray userObj = json
	                                    .getJSONArray(KEY_user); // JSON Array
	 
	                            // get first user object from JSON Array
	                            JSONObject user = userObj.getJSONObject(0);
	 
	                            // user with this user found
	                            // Edit Text
	                            txtName = (EditText) findViewById(R.id.txtName);
	                            txtDate = (EditText) findViewById(R.id.txtDate);
	                            txtProfession = (EditText) findViewById(R.id.txtProfession);
	                            txtEmail = (EditText) findViewById(R.id.txtEmail);
	                           
	                            
	 
	                            // display user data in EditText
	                            txtName.setText(user.getString(KEY_NAME));
	                            txtDate.setText(user.getString(KEY_Date));
	                            txtProfession.setText(user.getString(KEY_Profession));
	                            txtEmail.setText(user.getString(KEY_EMAIL));
	                            
	 
	                        }else{
	                            // product with uid not found
	                        }
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                    }
	                
     			        
     				*/
     					Intent i = new Intent(getApplicationContext(),
     							Profil.class);
     					startActivity(i);
     					finish();
     				}
     			});
     	    			
		
	  }}



