package com.androidhive.jsonparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SondageActivity extends Activity {

	
	

	//QUESTIONS
	private static String urlQ = "http://10.0.2.2:81/sondage/getquestionbyidJson.php?idsondage=" ;
	JSONArray Qsondage = null ;
	public static ArrayList<String> QuestionsList = new ArrayList<String>();

	//SUGGESTIONS
	private static String urlS = "http://10.0.2.2:81/sondage/getquggestionsbyidJson.php?idquestion=";
	JSONArray suggestions = null;
	public static ArrayList<String> SuggestionsList = new ArrayList<String>();
	
	//Answers
	public static ArrayList<String> AnswersList = new ArrayList<String>();

	Button mybtn;
    TextView txtViewQuestion;
   
	
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sondage_activity_layout);
	
		
	
		mybtn = (Button)findViewById(R.id.Btn_suivant);
		txtViewQuestion=(TextView)findViewById(R.id.Txt_question);

		// getting intent data
		 Bundle b = getIntent().getExtras();
		 String sondageID=b.getString("sondageID");
		 int counteri=b.getInt("counteri");
		 counteri++;
		
		//Intent in = getIntent();
		
		 /*
		 String sondageID=in.getExtras().getString("sondageID");
		int counteri=in.getExtras().getInt("counteri");
		counteri++;
		*/
		Toast.makeText(this, "sondageID"+sondageID, 5000).show();
		Toast.makeText(this, "counteri"+counteri, 5000).show();
	

		//______//
		
		int init=0;
		boolean btnClicked= true;
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		urlQ= urlQ+sondageID; 
		JSONObject json = jParser.getJSONFromUrl(urlQ);

		try{
			// Getting Array of Questions
			Qsondage = json.getJSONArray("questions");
			Log.i("QsondageQsondageQsondageQsondage",Qsondage.toString());


			
			if (counteri < Qsondage.length()) {
				
				urlS= "http://10.0.2.2:81/sondage/getquggestionsbyidJson.php?idquestion=";
				JSONObject c = Qsondage.getJSONObject(counteri);
				// Storing each json item in variable
				String idQ = c.getString("id_Question");
				String Question = c.getString("Question");


				Log.i("QQQQQuestion",Question.toString());
				Log.i("idQuestion i ",idQ);
	
					((TextView)findViewById(R.id.Txt_question)).setText(Question);


				//////////////////////////////////////////////////////////////////////////////////////////////   			 

				JSONParser jParserS = new JSONParser();
				urlS= urlS+idQ; 
				Log.i("urlSurlSurlSurlSurlSurlSurlSurl",urlS);
				
				JSONObject jsons = jParserS.getJSONFromUrl(urlS);
				suggestions = jsons.getJSONArray("suggestions");

				Log.i("Suggggggestion",suggestions.toString());

				// looping through All Suggestions 
				for(int j = 0; j < suggestions.length(); j++){

					// getting JSON string from new URL of Suggestion
					JSONObject s = suggestions.getJSONObject(j);
					Log.i("SuggggggestionJSONOBJ",s.toString());

					// Storing each json item in variable
					//String idS = s.getString("id_Suggestion");
					String  Suggestion = s.getString("Suggestion");
					
				
					Log.i("Suggggggestion",Suggestion);
					// adding  to ArrayList
					SuggestionsList.add(Suggestion);

					//add radio buttons
					
					RadioButton[] rb = new RadioButton[suggestions.length()];
					
					RadioGroup rg = (RadioGroup)this.findViewById(R.id.optionRadioGroup);
					
					//rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
					RadioButton r  = new RadioButton(this);
					r.setText(Suggestion);
					//r.setId(j);
					rg.addView(r); //the RadioButtons are added to the radioGroup instead of the layout
//					LinearLayout ll2= (LinearLayout)this.findViewById(R.id.ll2);
//					ll2.addView(rg);//you add the whole RadioGroup to the layout
					
				//	ll2.invalidate();
					Log.i("idChekkked", String.valueOf(rg.getCheckedRadioButtonId())); 
					
					//AnswersList.set(i, String.valueOf(rg.getCheckedRadioButtonId())  );
					 //Log.i("messagAnswerList", AnswersList.toString());
					 	
				}
				
			
				
				// adding  to ArrayList
				QuestionsList.add(Question); 
				
				addListenerOnButtonNext(counteri,sondageID);
				
			}
			

		} catch (JSONException e) {
			e.printStackTrace(); }
		
	}
		 public void saveAnswers() {
		        LinearLayout root = (LinearLayout) findViewById(R.id.ll2); //or whatever your root control is
		        loopQuestions(root);
		    }
		 
		    private void loopQuestions(ViewGroup parent) {
		        for(int i = 0; i < parent.getChildCount(); i++) {
		            View child = parent.getChildAt(i);
		            if(child instanceof RadioGroup ) {
		                //Support for RadioGroups
		                RadioGroup radio = (RadioGroup)child;
		                storeAnswer(radio.getId(), radio.getCheckedRadioButtonId(), i);
		            }
		          /*  else if(child instanceof CheckBox) {
		                //Support for Checkboxes
		                CheckBox cb = (CheckBox)child;
		                int answer = cb.isChecked() ? 1 : 0;
		                storeAnswer(cb.getId(), answer);
		            }
		            else if(child instanceof EditText) {
		                //Support for EditText
		                EditText et = (EditText)child;
		                Log.w("ANDROID DYNAMIC VIEWS:", "EdiText: " + et.getText());
		            }
		            else if(child instanceof ToggleButton) {
		                //Support for ToggleButton
		                ToggleButton tb = (ToggleButton)child;
		                Log.w("ANDROID DYNAMIC VIEWS:", "Toggle: " + tb.getText());
		            }
		            else {
		                //Support for other controls
		            }*/
		 
		            if(child instanceof ViewGroup) {
		                //Nested Q&A
		                ViewGroup group = (ViewGroup)child;
		                loopQuestions(group);
		            }
		        }
		    }
		 
		    private void storeAnswer(int question, int answer, int index) {
		    	AnswersList.set(index, String.valueOf(answer));
		    	
		        Log.w("ANDROID DYNAMIC VIEWS:", "Question: " + String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer) );
		        
		        Toast toast = Toast.makeText(this, String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer), Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.TOP, 25, 400);
		        toast.show();
		 
		        
		    }
		
		    
		    public void addListenerOnButtonNext(final int counteri, final String sondageID) {   		 
	    		final Context context = this;
	    		
	    		((Button)findViewById(R.id.Btn_suivant)).setOnClickListener(new OnClickListener(){
	    			@Override
	    			public void onClick(View arg0) {
	    				
	    				
	    				Intent intent = new Intent(context,  SondageActivity.class);
	    			    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    			    
	    			    
	    			    
	    			    intent.putExtra("sondageID",sondageID);
	    			    intent.putExtra("counteri",counteri);
	    			    
	    			   
	                                startActivity(intent);   
	     
	                                
	    			}
	    		});
	     
	    	}
	        
	        
		}
