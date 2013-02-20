package com.klusman.musicpause;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ListActivity {
	File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MUSIC);  //works
	private ArrayList<String> songs = new ArrayList<String>();
	Object mySongs[];
	private String[] mySongsArray;


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

    		mySongs = files.toArray();

    		
    		Iterator<File> itr = files.iterator(); 
    		while(itr.hasNext()) {

    			String name = itr.next().getName();
    			songs.add(name);
    			
    		    Log.i("SONG", name);
    		    

    		} 



    		
    		Log.i("# of Songs", String.valueOf(files.size()));

    	}else{
    		Log.i("Songs", "No Songs Found");
    	}
   	
   }  // END UpdatePlayList
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	}

	
}
