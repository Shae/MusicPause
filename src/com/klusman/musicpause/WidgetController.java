package com.klusman.musicpause;


import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Environment;
import android.widget.RemoteViews;


public class WidgetController extends AppWidgetProvider {

	private File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MUSIC);
   

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
    	
    	Collection<File> files = FileUtils.listFiles(path,TrueFileFilter.INSTANCE,TrueFileFilter.INSTANCE);
    	final int N = appWidgetIds.length;   
    	int filesSize = files.size();
    	
    	for(int i = 0; i < N; i++){
    		int widID = appWidgetIds[i];
    		RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
    		
    		rv.setTextViewText(R.id.storageCountText, String.valueOf(filesSize) + " Songs in Storage");
    		
    	}
    	
    }
    
    

}
