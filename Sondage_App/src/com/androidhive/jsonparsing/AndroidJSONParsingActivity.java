package com.androidhive.jsonparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
public class AndroidJSONParsingActivity extends ListActivity {

	// url to make request
	//private static String url = "http://10.0.2.2:81/sondageJson";
	private static String url = "http://10.0.2.2:81/sondage/sondageJson.php";

	// contacts JSONArray
	JSONArray sondage = null ;
	public static ArrayList<HashMap<String, String>> sondageList = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
	
	
	// Hashmap for ListView
	
	
	// Creating JSON Parser instance
	JSONParser jParser = new JSONParser();

	// getting JSON string from URL
	JSONObject json = jParser.getJSONFromUrl(url);


		try {
			// Getting Array of Contacts
			sondage = json.getJSONArray("sondage");
			
			// looping through All Contacts
			for(int i = 0; i < sondage.length(); i++){
				JSONObject c = sondage.getJSONObject(i);
				
				
				// Storing each json item in variable
				
				String id = c.getString("id_sondage");
				String titre = c.getString("titre_Sondage");
				String description = c.getString("description_Sondage");
				String auteur = c.getString("auteur_Sondage");
				String nb_questions = c.getString("nb_Questions");
				String points = c.getString("points");
				String id_Q = c.getString("id_Question");

				 // creating new HashMap
			    HashMap<String, String> map = new HashMap<String, String>();
			    
			    // adding each child node to HashMap key => value
			    map.put("id_sondage",id);
			    map.put("titre_Sondage",titre);
			    map.put("description_Sondage",description);
			    map.put("auteur_Sondage",auteur);
			    map.put("nb_Questions",nb_questions);
			    map.put("points",points);
			    map.put("id_Question",id_Q);
			    
			 // adding HashList to ArrayList
				sondageList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
			   
		
			    /**
				 * Updating parsed JSON data into ListView
				 * */
				ListAdapter adapter = new SimpleAdapter(
						this, sondageList, R.layout.list_item,
						new String[] {"titre_Sondage", "points" }, new int[] {R.id.titre, R.id.points});
				setListAdapter(adapter);

				// selecting single ListView item
				ListView lv = getListView();
				
				
				// Launching new screen on Selecting Single ListItem
				lv.setOnItemClickListener(new OnItemClickListener() {


					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					
						// getting values from selected ListItem
						String titre = ((TextView) view.findViewById(R.id.titre)).getText().toString();
						String points = ((TextView) view.findViewById(R.id.points)).getText().toString();
						
						Toast.makeText(AndroidJSONParsingActivity.this, " Sondage", 5000).show();
						// Starting new intent
						Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
						
						
					in.putExtra("position", position);
						
						
						
						startActivity(in);
						
					
						
						
						
					}
				});
		} }