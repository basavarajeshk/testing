package com.example.geolocation;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
//added in app
public class RatePage extends Activity {

	RatingBar ratingBar,ratingBar1;
	Button submitRateButton;
	TextView rateDisplay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate_page);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		submitRateButton = (Button) findViewById(R.id.ratingSubmitButton);
		rateDisplay = (TextView) findViewById(R.id.ratingDisplay);
		rateDisplay.setText("Rate:");

	}

	public void rateSubmit(View view) {

		String ratingValue = String.valueOf(ratingBar.getRating());
		rateDisplay.setText("Rate: " + ratingValue);
		Toast.makeText(getApplicationContext(), "Rate: " + ratingValue, Toast.LENGTH_LONG).show();

	}
	
	public void ratealert(View view) {

		final Dialog rankDialog = new Dialog(RatePage.this, R.style.FullHeightDialog);
        rankDialog.setContentView(R.layout.rank_dialog);
        rankDialog.setCancelable(true);
        ratingBar1 = (RatingBar)rankDialog.findViewById(R.id.dialog_ratingbar);
        ratingBar1.setRating(ratingBar1.getRating());

        TextView text = (TextView) rankDialog.findViewById(R.id.rank_dialog_text1);
        text.setText(String.valueOf(ratingBar1.getRating()));

        Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(ratingBar1.getRating()<0.5){
            		Toast.makeText(getApplicationContext(), "Cannot rate zero value " , Toast.LENGTH_LONG).show();
            	}else{
                    rankDialog.dismiss();
            	}
            }
        });
        Button remind_later = (Button) rankDialog.findViewById(R.id.remind_later_dialog_button);
        remind_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankDialog.dismiss();
            }
        });
        //now that the dialog is set up, it's time to show it    
        rankDialog.show();        

	}

	public void popupalert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(RatePage.this);
		alertDialog.setTitle("Rate 5");
		alertDialog.setMessage("Rate this app");

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				Log.d("Internet Available ", "----");
				if(ratingBar.getRating()<0.5){
            		Toast.makeText(getApplicationContext(), "Cannot rate zero value " , Toast.LENGTH_LONG).show();
            	}else{
            		dialog.dismiss();
            	}
			}
		});

		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("Remind me later", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
	
	public void onBackPressed(){
		Toast.makeText(getApplicationContext(), "Nothing", Toast.LENGTH_SHORT).show();
		Log.d("hi","hi");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rate_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
