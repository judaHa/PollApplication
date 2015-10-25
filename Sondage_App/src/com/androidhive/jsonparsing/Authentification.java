package com.androidhive.jsonparsing;

import com.Myapp.sondagepfe.library.DatabaseHandler;
import com.Myapp.sondagepfe.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Authentification extends Activity {
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;
	
	
	
	public static boolean connectBool= false;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_SEXE = "Sexe";
	private static String KEY_Date = "Date";
	private static String KEY_Profession = "Profession";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authentification);

		// Importing all assets like buttons, text fields
		inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				Log.d("Button", "Login");
				JSONObject json = userFunction.loginUser(email, password);

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							// user successfully logged in
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(
									getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");

							// Clear all previous data in database
							userFunction.logoutUser(getApplicationContext());
							db.addUser(json_user.getString(KEY_NAME),
									json_user.getString(KEY_EMAIL),
									json_user.getString(KEY_Date),
									json_user.getString(KEY_SEXE),
									json_user.getString(KEY_Profession),
									json.getString(KEY_UID),
									json_user.getString(KEY_CREATED_AT));

							// Launch Main Screen
							Intent Sondage = new Intent(
									getApplicationContext(),
									MainActivity.class);
							
							connectBool=true; 

							// Close all views before launching Sondage
							Sondage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(Sondage);

							// Close Login Screen
							finish();
						} else {
							// Error in login
							loginErrorMsg
									.setText("Incorrect username/password");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						Inscription.class);
				startActivity(i);
				finish();
			}
		});
	}
}
