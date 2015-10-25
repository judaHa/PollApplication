package com.androidhive.jsonparsing;
import java.util.ArrayList;
import java.util.List;

import com.Myapp.sondagepfe.library.DatabaseHandler;
import com.Myapp.sondagepfe.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Inscription extends Activity  {
	
	
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputDate;
	EditText inputProfession;
	Spinner inputSexe;
	EditText inputEmail;
	EditText inputPassword;
	TextView registerErrorMsg;
	String item ;
	
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_Sexe = "Sexe";
	private static String KEY_Date = "Date";
	private static String KEY_Profession = "Profession";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inscription);
		inputFullName = (EditText) findViewById(R.id.registerName);
		inputDate = (EditText) findViewById(R.id.regisiterDate);
		inputProfession = (EditText) findViewById(R.id.registerProfession);
		
		inputEmail = (EditText) findViewById(R.id.registerEmail);
		inputPassword = (EditText) findViewById(R.id.registerPassword);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
		registerErrorMsg = (TextView) findViewById(R.id.register_error);
		
		inputSexe = (Spinner) findViewById(R.id.SpinnerSexe);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.Sexe_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSexe.setAdapter(adapter);
        inputSexe.setSelection(0);
		
		
		
		// Spinner click listener
	       //spinner.setOnItemSelectedListener(this);
			// Spinner Drop down elements
	      //  List categories = new ArrayList();
	      //  categories.add("Homme");
	      //  categories.add("Femme");
	     // Creating adapter for spinner
	      //  ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
	 
	        // Drop down layout style - list view with radio button
	     //   dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
	      //  spinner.setAdapter(dataAdapter);
	    //
		


	

	


	
		// Importing all assets like buttons, text fields
		
		
		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View view) {
				String name = inputFullName.getText().toString();
				String Date = inputDate.getText().toString();
				String Profession = inputProfession.getText().toString();
				String Sexe =inputSexe.getSelectedItem().toString();
				 
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				
				
				if (name.compareTo("") == 0) {
					registerErrorMsg.setText("entrer votre pseudo.");
				}
				
				else if (!UserFunctions.stringValid(name)){
					registerErrorMsg.setText("Votre pseudo n'est pas valide.");
				}
				

				// Contains spaces
				else if (name.contains(" ")) {
					registerErrorMsg.setText("Votre Pseudo ne peut pas contenir des espaces.");
				}

				// pseudo too short
				else if (name.length() < 3) {
					registerErrorMsg.setText("Votre pseudo doit comporter au moins 3 caractères.");
				}
				

				// No Email
				else if (email.compareTo("") == 0) {
					registerErrorMsg.setText("Entrer votre Email.");
				}
				
				else if (!UserFunctions.validEmail(email)){
					registerErrorMsg.setText("Votre Email n'est pas valide.");
				}

				// Wrong format
				else if (!email.contains(".") || !email.contains("@")
						|| email.contains(" ")) {
					registerErrorMsg.setText("L'adresse electronique doit étre sous la forme suivante: example@exemple.com");
				}

				// No Password
				else if (password.compareTo("") == 0) {
					registerErrorMsg.setText("Entrer votre mot de passe.");
				} 
				
				else if (!UserFunctions.stringValid(password)){
					registerErrorMsg.setText("Mot de passe n'est pa valide.");
				}

				// Same UN/PW
				else if (password.compareTo(name) == 0) {
					registerErrorMsg.setText("Le mot de passe peut pas être le même que le Pseudo.");
				}

				// Pass to short
				else if (password.length() < 5) {
					registerErrorMsg.setText("Votre Mot de passe doit comporter au moins 5 caractères.");
				}

				// Contains spaces
				else if (password.contains(" ")) {
					registerErrorMsg.setText("Votre Mot de passe ne peut pas contenir des espaces.");
				}


				else {
				
				
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.registerUser(name,Date,Sexe,Profession, email, password);
				
				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						registerErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){
							// user successfully registred
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
							
							// Clear all previous data in database
							userFunction.logoutUser(getApplicationContext());
							db.addUser(json_user.getString(KEY_NAME),json_user.getString(KEY_Date),json_user.getString(KEY_Sexe),json_user.getString(KEY_Profession), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
							// Launch main Screen
							Intent main = new Intent(getApplicationContext(), MainActivity.class);
							// Close all views before launching main
							main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(main);
							// Close Registration Screen
							finish();
						}else{
							// Error in registration
							registerErrorMsg.setText("Error occured in registration");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			}});

		// Link to Login Screen
		btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						Authentification.class);
				startActivity(i);
				// Close Registration View
				finish();
			}
		});
	}}

	//@Override
/*	public void onItemSelected(AdapterView parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		//On selecting a spinner item
	
        item = parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}*/
