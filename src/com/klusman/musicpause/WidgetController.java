package com.klusman.musicpause;


import java.io.File;
import java.util.Calendar;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.RemoteViews;


public class WidgetController extends AppWidgetProvider {
	private PendingIntent service = null;  
	  
    @Override  
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)  
    {  
        final AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);  
  
        final Calendar TIME = Calendar.getInstance();  
        TIME.set(Calendar.MINUTE, 0);  
        TIME.set(Calendar.SECOND, 0);  
        TIME.set(Calendar.MILLISECOND, 0);  
  
        final Intent i = new Intent(context, widgetService.class);  
  
        if (service == null)  
        {  
            service = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);  
        }  
  
        m.setRepeating(AlarmManager.RTC, TIME.getTime().getTime(), 1000 * 6, service);  
    }  
  
    @Override  
    public void onDisabled(Context context)  
    {  
        final AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);  
  
        m.cancel(service);  
    }  
    
    

}







/// MOVE COMMENTED OUT ITEMS FROM CLASS
//	private File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MUSIC);
//   
//
//    @Override
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
//            int[] appWidgetIds) {
//    	
//    	Collection<File> files = FileUtils.listFiles(path,TrueFileFilter.INSTANCE,TrueFileFilter.INSTANCE);
//    	final int N = appWidgetIds.length;   
//    	int filesSize = files.size();
//    	
//    	for(int i = 0; i < N; i++){
//    		int widID = appWidgetIds[i];
//    		RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//    		
//    		rv.setTextViewText(R.id.storageCountText, String.valueOf(filesSize) + " Songs in Storage");
//    		
//    	}
//    	
//    }