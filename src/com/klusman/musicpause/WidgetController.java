package com.klusman.musicpause;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;


public class WidgetController extends AppWidgetProvider {


   

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {

    	final int N = appWidgetIds.length;   
    	
    	for(int i = 0; i < N; i++){
    		int widID = appWidgetIds[i];
    		RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
    		
    	}
    	
    }
    
    

}
