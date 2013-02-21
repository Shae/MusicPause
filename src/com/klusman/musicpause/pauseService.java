package com.klusman.musicpause;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class pauseService extends Service {
	private final int VIB_NOTE_ID = 1;
	 @Override  
	    public void onCreate()  
	    {  
	        super.onCreate();  
	    }  
	  
	    
	    @Override  
	    public int onStartCommand(Intent intent, int flags, int startId)  
	    {  
	          
	    	notifyMe();
	        return super.onStartCommand(intent, flags, startId);  
	    }  
	  
	    
	    
	  
	    
	    @Override  
	    public IBinder onBind(Intent intent)  
	    {  
	        return null;  
	    }  
	    
	    
		public void notifyMe(){  // Haptic Vibrations for Pause reminder
			NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			Notification note = new Notification();
			note.defaults = Notification.DEFAULT_VIBRATE;

			nm.notify(VIB_NOTE_ID, note);
			
		}
}
