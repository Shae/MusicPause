package com.klusman.musicpause;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ListActivity implements SensorEventListener{
	private File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MUSIC);  //works
	private ArrayList<String> songs = new ArrayList<String>();
	private ArrayList<String> songsPath = new ArrayList<String>();
	private String[] mySongsArray;
	Button audio;
	MediaPlayer mp = new MediaPlayer();
	private SensorManager sensorManager;
	private Sensor proximitySensor;
	boolean paused = false;
	double x;

	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorReg();
		updatePlayList();
		mySongsArray = songs.toArray(new String[songs.size()]);
		setListAdapter(new myArrayAdapter(this, mySongsArray));	
	}  // END onCreate

	
	
	
	private void updatePlayList(){

		Collection<File> files = FileUtils.listFiles(path,TrueFileFilter.INSTANCE,TrueFileFilter.INSTANCE);
    	if(files != null){
    		
    		Iterator<File> itr = files.iterator(); 
    		while(itr.hasNext()) {
    			//String name = itr.next().getName(); // Works to show song title in tab
    			String sPath = itr.next().getPath();  // works to show song path in tab
    			songs.add(sPath);   			
    		   
    		} // End Iterator   		
    		
    	}else{
    		Log.i("Songs", "No Songs Found");
    	}  // End IF
   	
   }  // END UpdatePlayList
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if(mp.isPlaying()){
			mp.stop();
		}
		
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		String newURI = selectedValue;
		Uri uri;
		try {		
			uri = Uri.parse(newURI);
			mp = MediaPlayer.create(MainActivity.this , uri);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(mp.isPlaying()){
			mp.pause();
		}else{
			mp.start();			
		}
	}  //  END onListItemClicked
	

	
////OPTIONS MENU  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	} // END onCreateOptionsMenu
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){

			case R.id.menu_pause:
					if(mp.isPlaying()){
						mp.pause();
					}else{
						mp.start();
					}
				break;
				
			case R.id.menu_stop:
				mp.stop();
				break;
			case R.id.menu_play:
				mp.start();
				break;
			case R.id.menu_settings:
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);  
				break;
		}
		return super.onMenuItemSelected(featureId, item);
	}  //  END onMenuItemSelected
	
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor == proximitySensor)
		{
			x = event.values[0];			
				checkProx();
		}
		
	}
	
	public void stopMusic(){
		mp.stop();
		
	}
    

	
	private void sensorReg(){
        /////////// PROX
        if (proximitySensor == null)
        {
        	myToast("No Proximity Sensor Found");
        }
        else
        {
        	sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        	Log.i("SENSOR", "prox registered");
        } 
	}
	
	public void checkProx(){
		if(x == 0.0){  // proximity - close
			if(mp.isPlaying()){
				mp.pause();
				paused = true;
				//runTimer();
				myToast("paused");						
			}
		}else{  // proximity - far
			mp.start();
			paused = false;
			myToast("play");
		}
	}
	
	public void myToast(String text){  // Toast Template
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(MainActivity.this, textIN, duration);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	};// end myToast
}
