package com.androidhive.jsonparsing;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	TabHost mTabHost;
	
	
	  public static final int ADD_MENU = Menu.FIRST+1;
	  public static final int RESET_MENU = Menu.FIRST+2;
	  //public static final int CAP_MENU = Menu.FIRST+3;
	  //public static final int REMOVE_MENU = Menu.FIRST+4;
	  
	  
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu
	      .add(Menu.NONE, ADD_MENU, Menu.NONE, "Actualiser")
	      .setIcon(R.drawable.refresh);
	    menu
	      .add(Menu.NONE, RESET_MENU, Menu.NONE, "à propos")
	      .setIcon(R.drawable.info);
	    return(super.onCreateOptionsMenu(menu));
	  }
	
	/*  @Override
	  public void onCreateContextMenu(ContextMenu conMenu, View conView,
	                                    ContextMenu.ContextMenuInfo conMenuInfo) {
	    conMenu.add(Menu.NONE, CAP_MENU, Menu.NONE, "Refresh");
	    conMenu.add(Menu.NONE, REMOVE_MENU, Menu.NONE, "About");
	  }*/
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case ADD_MENU:
	      
	        return(true);
	      case RESET_MENU:
	      
	        return(true);
	    }
	    return(super.onOptionsItemSelected(item));
	  }
	  
	  
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mTabHost=getTabHost();
	
		
		
		TabSpec SondageSpec =mTabHost.newTabSpec("Sondages"); 
		SondageSpec.setIndicator("Sondages", getResources().getDrawable(R.drawable.mytab));
		Intent FirstIntent = new Intent(this,AndroidJSONParsingActivity.class);
		SondageSpec.setContent(FirstIntent);
		
		TabSpec HistoriqueSpec =mTabHost.newTabSpec("Historique"); 
		HistoriqueSpec.setIndicator("Historique",  getResources().getDrawable(R.drawable.mytab));
		Intent SecondIntent = new Intent(this,Historique.class);
		HistoriqueSpec.setContent(SecondIntent);
		
		TabSpec CompteSpec =mTabHost.newTabSpec("Compte"); 
		CompteSpec.setIndicator("Compte",  getResources().getDrawable(R.drawable.mytab));
		Intent thirdIntent = new Intent(this,Compte.class);
		CompteSpec.setContent(thirdIntent);
		

		mTabHost.addTab(SondageSpec);
		mTabHost.addTab(HistoriqueSpec);
		mTabHost.addTab(CompteSpec);
		
		
		
		
		
		
	}

	

}
