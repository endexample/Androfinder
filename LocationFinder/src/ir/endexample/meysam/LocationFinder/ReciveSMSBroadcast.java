package ir.endexample.meysam.LocationFinder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class ReciveSMSBroadcast extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("SMS RECIVED ","SMS REcived broad cast");
		// check for location: ignore case
		 final Bundle bundle = intent.getExtras();
		 String senderNum = null ;
         String message = null ;
	        try {
	             
	            if (bundle != null) {
	                 
	                final Object[] pdusObj = (Object[]) bundle.get("pdus");
	                 
	                for (int i = 0; i < pdusObj.length; i++) {
	                     
	                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
	                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
	                     
	                     senderNum = phoneNumber;
	                     message = currentMessage.getDisplayMessageBody();
	 
	                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
	                     
	 
	                  
	                     
	                } // end for loop
	              } // bundle is null
	            GPSTracker gps = null ;
	            if(message.equalsIgnoreCase("location:")){
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	// create class object
	        		 gps = new GPSTracker(context);
	        		// check if GPS enabled
	        		// gps.showSettingsAlert();
	        		if (gps.canGetLocation()) {

	        			 double latitude = gps.getLatitude();
	        			 double longitude = gps.getLongitude();
	        				SmsManager smsManager = SmsManager.getDefault();
	    	               	String ms="lat is: "+String.valueOf(latitude+"\nlon is: "+longitude);
	                    	smsManager.sendTextMessage(senderNum, null, ms, null, null);
	        			
	        		} else {
	        			// can't get location
	        			// GPS or Network is not enabled
	        			// Ask user to enable GPS/network in settings
	        			SmsManager smsManager = SmsManager.getDefault();
	                	smsManager.sendTextMessage(senderNum, null, "Location not avilable", null, null);
	        			gps.showSettingsAlert();
	        			
	        		}

	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	/*
	            	Intent intent1=new Intent("android.location.GPS_ENABLED_CHANGE");
	            	intent1.putExtra("enabled", true);
	            	context.sendBroadcast(intent1);
	            	Log.i("reciveSMSBroadCast", "Should Get Current Location");
	            */	
	            	
	            	/*
	            	
	            	LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	                // Define the criteria how to select the locatioin provider -> use
	                // default
	                Criteria criteria = new Criteria();
	                String provider = locationManager.getBestProvider(criteria, false);
	                Location location = locationManager.getLastKnownLocation(provider);

	                Thread.sleep(999);
	                 location = locationManager.getLastKnownLocation(provider);
	                // Initialize the location fields
	                 
	                 
	                 
	                if (location != null) {
	               	   Log.i("Locations ", "lat is: "+String.valueOf(location.getLatitude()));
	               	SmsManager smsManager = SmsManager.getDefault();
	               	String ms="lat is: "+String.valueOf(location.getLatitude())+"\nlon is: "+String.valueOf(location.getLongitude());
                	smsManager.sendTextMessage(senderNum, null, ms, null, null);
	               	   // replay sms
	                } else {
	                	Log.i("Locations ", "Location not avilable");
	                	// replay sms
	                	SmsManager smsManager = SmsManager.getDefault();
	                	smsManager.sendTextMessage(senderNum, null, "Location not avilable", null, null);
	                }
	        		
	        		*/
	        		
	        		
	            	
	            	
	            }else if(message.equalsIgnoreCase("off:")){
	            	try {
	            		Log.i("off command recive", "off command recived");
	            		String cmd = "su -c shutdown";
	                   
	                    Runtime.getRuntime().exec(cmd);
	            
	            	} catch (Exception ex) {
	            	    ex.printStackTrace();
	            	}
	            	
	            }
	            
	 
	        } catch (Exception e) {
	            Log.e("SmsReceiver", "Exception smsReceiver" +e);
	             
	        }
		
		
		
	}

	
	

	
	
}
