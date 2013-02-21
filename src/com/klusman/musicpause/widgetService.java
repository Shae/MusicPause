package com.klusman.musicpause;

import java.util.Date;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

public class widgetService extends Service  
{  
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
        String lastUpdated = DateFormat.format("MMMM dd, yyyy h:mmaa", new Date()).toString();  
  
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.widget_layout);  
  
        view.setTextViewText(R.id.storageCountText, lastUpdated);  
  
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