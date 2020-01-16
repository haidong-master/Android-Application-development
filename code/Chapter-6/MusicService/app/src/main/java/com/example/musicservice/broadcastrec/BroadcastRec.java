package com.example.musicservice.broadcastrec;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.List;

public class BroadcastRec extends BroadcastReceiver {
	private static final String TAG = "BroadcastRec";
	private static final String INTENAL_ACTION_BROADCAST_STATIC = "com.expamle.music.Broadcast.static";
	private static final String INTENAL_ACTION_BROADCAST_STATIC_BACK = "com.expamle.music.Broadcast.static.back"; 

	public void staticSendBroadcast(Context context) {
		Intent intent = new Intent(context,BroadcastService.class);
		intent.setAction(INTENAL_ACTION_BROADCAST_STATIC_BACK);
		context.sendBroadcast(intent);
		//Intent intent = new Intent();
		//intent.setAction(INTENAL_ACTION_BROADCAST_STATIC_BACK);
		// final Intent eintent = new Intent(createExplicitFromImplicitIntent(context,intent));
		//context.sendBroadcast(eintent);
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v(TAG, "BroadcastRec onReceive");
		// TODO Auto-generated method stub
		if(intent != null){  
			if(INTENAL_ACTION_BROADCAST_STATIC.equals(intent.getAction())){  
				 staticSendBroadcast(context);
			}  
		}  
	}
	/***
	 * Android L (lollipop, API 21) introduced a new problem when trying to invoke implicit intent,
	 * "java.lang.IllegalArgumentException: Service Intent must be explicit"
	 *
	 * If you are using an implicit intent, and know only 1 target would answer this intent,
	 * This method will help you turn the implicit intent into the explicit form.
	 *
	 * @param context
	 * @param implicitIntent - The original implicit intent
	 * @return Explicit Intent created from the implicit original intent
	 */
	public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
		// Retrieve all services that can match the given intent
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

		// Make sure only one match was found
		if (resolveInfo == null || resolveInfo.size() != 1) {
			return null;
		}

		// Get component info and create ComponentName
		ResolveInfo serviceInfo = resolveInfo.get(0);
		String packageName = serviceInfo.serviceInfo.packageName;
		String className = serviceInfo.serviceInfo.name;
		ComponentName component = new ComponentName(packageName, className);

		// Create a new intent. Use the old one for extras and such reuse
		Intent explicitIntent = new Intent(implicitIntent);

		// Set the component to be explicit
		explicitIntent.setComponent(component);

		return explicitIntent;
	}
}
