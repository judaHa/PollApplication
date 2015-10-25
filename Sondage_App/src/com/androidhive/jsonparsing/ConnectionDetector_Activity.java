package com.androidhive.jsonparsing;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class ConnectionDetector_Activity extends Activity {
 
    // flag for Internet connection status
    public Boolean isInternetPresent = false;
 
    // Connection detector class
    ConnectionDetector cd;
 
    public  void Check_Connection() {
 
        // creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
 
        /**
         * Check Internet status button click event
         * */
      
 
                // get Internet status
                isInternetPresent = cd.isConnectingToInternet();
 
                // check for Internet status
                if (isInternetPresent) {
                    // Internet Connection is Present
                    // make HTTP requests
                    showAlertDialog(ConnectionDetector_Activity.this, "Internet Connection",
                            "You have internet connection", true);
                   
                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    showAlertDialog(ConnectionDetector_Activity.this, "No Internet Connection",
                            "You don't have internet connection.", false);
                    
                }
            }
 
        
 
    
 
    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     * */
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.ic_launcher : R.drawable.icone_favoris);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
}