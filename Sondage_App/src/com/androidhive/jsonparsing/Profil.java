package com.androidhive.jsonparsing;
import java.util.ArrayList;
import java.util.HashMap;
import com.Myapp.sondagepfe.library.*;
import com.Myapp.sondagepfe.*;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Myapp.sondagepfe.library.DatabaseHandler;
import com.androidhive.jsonparsing.JSONParser;
import com.Myapp.sondagepfe.library.UserFunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profil extends Activity{

	/*private static String url = "http://10.0.2.2:81/sondage/userInfo.php?userId=1";
	// Creating JSON Parser instance
	JSONParser jParser = new JSONParser();

	// getting JSON string from URL
	JSONObject json = jParser.getJSONFromUrl(url);
	// Users JSONArray
	JSONArray users = null ;
	TextView txtName ;
	TextView txtDate ;
	TextView txtProfession ;
	TextView txtEmail ;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		
		 txtName = (EditText) findViewById(R.id.txtName);
	     txtDate = (EditText) findViewById(R.id.txtDate);
	     txtProfession = (EditText) findViewById(R.id.txtProfession);
	     txtEmail = (EditText) findViewById(R.id.txtEmail);
	    
		
	try {
		// Getting Array of Contacts
		users = json.getJSONArray("users");
		
		// looping through All Contacts
		for(int i = 0; i < users.length(); i++){
			JSONObject c = users.getJSONObject(i);
			
			// Displaying each json item in variable
			txtName.setText( (c.getString("name")).toString());
			Log.i("uiiiid", (c.getString("name")).toString());
			
	
}
} catch (JSONException e) {
	e.printStackTrace();
}}}
	   
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	String uid;
	
	TextView txtName ;
	TextView txtDate ;
	TextView txtProfession ;
	TextView txtEmail ;
	
    Dialog pDialog;
		
	private static String KEY_SUCCESS = "success";
	private static final String KEY_user = "user";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	//private static String KEY_Sexe = "Sexe";
	private static String KEY_Date = "Date";
	private static String KEY_Profession = "Profession";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	
	Button btnUpdate;
	// JSON parser class
    JSONParser jsonParser = new JSONParser();
 
    // single product url
    private static final String url_User_detials = "http://10.0.2.2:81/sondage/userInfo.php?userId=1";
 
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		
		// getting user details from intent
        Intent i = getIntent();
 
        // getting user id (uid) from intent
        uid = i.getStringExtra(KEY_UID);
 
        // Getting complete user details in background thread
       new      GetUserDetails().onPreExecute();
      
       
       
       btnUpdate = (Button) findViewById(R.id.btnUpdate);
       btnUpdate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				Intent i = new Intent(getApplicationContext(),
				UpdateProfil.class);
				i.putExtra("UserId", i.getStringExtra(KEY_UID));
				startActivity(i);
				finish();
				
			}
		});
          
	}
class GetUserDetails extends AsyncTask<String, String, String> {
			  
	        //**
	        // * Before starting background thread Show Progress Dialog
	         
	   //     @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Profil.this);
	            ((AlertDialog) pDialog).setMessage("Loading user details. Please wait...");
	            ((ProgressDialog) pDialog).setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }
	 
	           // * Getting user details in background thread
	        
	       protected String doInBackground(String... params) {
	 
	            // updating UI from Background Thread
	            runOnUiThread(new Runnable() {
	                public void run() {
	                    // Check for success tag
	                    int success;
	                    try {
	                        // Building Parameters
	                        List<NameValuePair> params = new ArrayList<NameValuePair>();
	                        params.add(new BasicNameValuePair("idUser", uid));
	 
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
	                            txtName = (TextView) findViewById(R.id.txtName);
	                            txtDate = (TextView) findViewById(R.id.txtDate);
	                            txtProfession = (TextView) findViewById(R.id.txtProfession);
	                            txtEmail = (TextView) findViewById(R.id.txtEmail);
	                           
	                            
	 
	                            // display user data in EditText
	                            txtName.setText(user.getString(KEY_NAME));
	                            Log.i("naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaame",user.getString(KEY_NAME));
	                            txtDate.setText(user.getString(KEY_Date));
	                            txtProfession.setText(user.getString(KEY_Profession));
	                            txtEmail.setText(user.getString(KEY_EMAIL));
	                            
	 
	                        }
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                    }
	                }
	            });
	 
	            return null;
	        }
	 
	        //** * After completing background task Dismiss the progress dialog
	         
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog once got all details
	            pDialog.dismiss();
	        }
	 }		
	
		
		 
		   // String name = txtName.getText().toString();
			//String Date = txtDate.getText().toString();
			//String Profession = txtProfession.getText().toString();
			//String Sexe =item.toString();
			 
			//String email = txtEmail.getText().toString();
		

       
}