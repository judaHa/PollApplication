package com.androidhive.jsonparsing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.androidhive.jsonparsing.AndroidJSONParsingActivity;
public class SingleMenuItemActivity  extends Activity {
	
	// JSON node keys
	private static final String TAG_ID = "id_sondage";
	private static final String TAG_Titre = "titre_Sondage";
	private static final String TAG_Description = "description_Sondage";
	private static final String TAG_Auteur = "auteur_Sondage";
	private static final String TAG_Q = "nb_Questions";
	private static final String TAG_POINTS = "points";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        int pos=in.getExtras().getInt("position");
        Toast.makeText(this, "pos"+pos, 5000).show();
        
        
  String idS= AndroidJSONParsingActivity.sondageList.get(pos).get("id_sondage");
        
        TextView lbltitre = (TextView) findViewById(R.id.titre_label);
        TextView lbldescription = (TextView) findViewById(R.id.description_label);
        TextView lblpoints = (TextView) findViewById(R.id.points_label);
          

        lbltitre.setText(AndroidJSONParsingActivity.sondageList.get(pos).get("titre_Sondage"));
        lbldescription.setText(AndroidJSONParsingActivity.sondageList.get(pos).get("description_Sondage"));
        lblpoints.setText(AndroidJSONParsingActivity.sondageList.get(pos).get("points"));
       
        
    
        
        addListenerOnButton(idS);
	}
        public void addListenerOnButton(final String idS) {   		 
    		final Context context = this;
    		((Button)findViewById(R.id.Participate_btn)).setOnClickListener(new OnClickListener(){
    			@Override
    			public void onClick(View arg0) {
    			    Intent intent = new Intent(context, SondageActivity.class);
    			    intent.putExtra("sondageID",idS); 
    			    intent.putExtra("counteri",-1);
                                startActivity(intent);   
     
                                
    			}
    		});
     
    	}
        
        

    
}
