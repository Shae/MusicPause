package com.klusman.musicpause;

import java.util.Date;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.RemoteViews;

public class widgetService extends Service  
{  
	
	boolean checkProx;
    @Override  
    public void onCreate()  
    {  
        super.onCreate();  
    }  
  
    
    @Override  
    public int onStartCommand(Intent intent, int flags, int startId)  
    {  
        buildUpdate();  
  
        return super.onStartCommand(intent, flags, startId);  
    }  
  
    
    private void buildUpdate()  
    {  
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE); 
		
		checkProx = prefs.getBoolean("PROX", checkProx);
		//Log.i("checkProx load", String.valueOf(prefs.getBoolean("PROX", checkProx)));
    	
        String lastUpdated = DateFormat.format("MMMM dd, yyyy h:mmaa", new Date()).toString();  
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.widget_layout);  
        view.setTextViewText(R.id.storageCountText, lastUpdated);  
        if(checkProx == true){
        	view.setTextViewText(R.id.TextActive, "ACTIVE");
        }else{
        	view.setTextViewText(R.id.TextActive, "DEACTIVATED");
        }
  
        // Push update for this widget to the home screen  
        ComponentName thisWidget = new ComponentName(this, WidgetController.class);  
        AppWidgetManager manager = AppWidgetManager.getInstance(this);  
        manager.updateAppWidget(thisWidget, view);  
    }  
  
    
    @Override  
    public IBinder onBind(Intent intent)  
    {  
        return null;  
    }  
}  