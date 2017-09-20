package com.example.sms4give;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {
	ListView mListData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);
		 mListData = (ListView)findViewById(R.id.addToDictionary);
	        // receive list incoming messages
	        ArrayList<SmsInfo> listSms = getIntent().getParcelableArrayListExtra("ListSMS");
	        // check condition
	        if(listSms != null && listSms.size() > 0) {
	            // set dat to list
	            SmsInfoAdapter adapter = new SmsInfoAdapter(this, listSms);
	            mListData.setAdapter(adapter);
	        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
