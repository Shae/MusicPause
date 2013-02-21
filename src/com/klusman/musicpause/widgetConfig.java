package com.klusman.musicpause;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class widgetConfig extends Activity implements OnClickListener{
	
		AppWidgetManager awm;
		Context _context;
		int awID;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_layout);
		
		Button b = (Button)findViewById(R.id.menu_pause);
		b.setOnClickListener(this);
		
		_context = widgetConfig.this;
		// get info about the widget that launched
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		if(extras != null){
			awID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}else{
			finish();
		}
		
		awm = AppWidgetManager.getInstance(_context);
		
	}

	@Override
	public void onClick(View v) {
		
		
	}

}
