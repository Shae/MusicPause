package com.klusman.musicpause;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ListActivity {
	private File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MUSIC);  //works
	private ArrayList<String> songs = new ArrayList<String>();
	private String[] mySongsArray;
	Button audio;
	MediaPlayer mp = new MediaPlayer();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
    			String name = itr.next().getPath();  // works to show song path in tab
    			songs.add(name);   			
    		   // Log.i("SONG", name);
    		} // End Iterator
    		
    		Log.i("# of Songs", String.valueOf(files.size()));

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


	}
	
	

	
////OPTIONS MENU  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}// end onCreateOptionsMenu
	
	
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
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
