package com.klusman.musicpause;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;


public class SettingsActivity extends Activity{
	
	boolean checkProx;
	boolean checkNote;
	boolean checkBatt;
	CheckBox prox;
	CheckBox note;
	CheckBox batt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.settings);
		prox = (CheckBox)findViewById(R.id.checkBoxProx);
		note = (CheckBox)findViewById(R.id.checkBoxPauseNote);
		batt = (CheckBox)findViewById(R.id.checkBoxBat);
		
		loadSettings();	
		
		super.onCreate(savedInstanceState);
	}  //  onCreate

	
	private void saveSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit(); 
		
		editor.putBoolean("PROX", prox.isChecked());
		Log.i("checkProx save", String.valueOf(checkProx));
		editor.putBoolean("NOTE", note.isChecked());
		Log.i("checkNote save", String.valueOf(checkNote));
		editor.putBoolean("BATT", batt.isChecked());
		Log.i("checkBatt save", String.valueOf(checkBatt));
		editor.commit(); 
	}  // END saveSettings
	
	
	private void loadSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE); 
		
		checkProx = prefs.getBoolean("PROX", checkProx);
		prox.setChecked(prefs.getBoolean("PROX", checkProx));
		Log.i("checkProx load", String.valueOf(prefs.getBoolean("PROX", checkProx)));
		
		checkProx = prefs.getBoolean("NOTE", checkNote); 
		note.setChecked(prefs.getBoolean("NOTE", checkNote));
		Log.i("checkNote load", String.valueOf(prefs.getBoolean("NOTE", checkNote)));
		
		checkBatt = prefs.getBoolean("BATT", checkBatt);
		batt.setChecked(prefs.getBoolean("BATT", checkBatt));
		Log.i("checkBatt load", String.valueOf(prefs.getBoolean("BATT", checkBatt)));
		
	
	}  //  END loadSettings
	
	
////OPTIONS MENU  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.settings_menu, menu);
		return true;
	}// end onCreateOptionsMenu
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){

			case R.id.menu_back:
				saveSettings();
				Intent next = new Intent(this, MainActivity.class);
				startActivity(next);
				//super.onBackPressed();
				break;
		
		}
		return super.onMenuItemSelected(featureId, item);
	}

}// END
