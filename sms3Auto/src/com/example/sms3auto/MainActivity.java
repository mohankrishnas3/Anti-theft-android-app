package com.example.sms3auto;


import java.util.ArrayList;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
 
public class MainActivity extends Activity {
    ListView mListData;
	private Bundle savedInstanceState;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
		super.onCreate(savedInstanceState);
         setContentView(R.layout.main);
 
       mListData = (ListView)findViewById(R.id.lvData);
      // receive list incoming 
     ArrayList<SmsInfo> listSms = getIntent().getParcelableArrayListExtra("ListSMS");
        // check condition
        if(listSms != null && listSms.size() > 0) {
            // set dat to list
            SmsInfoAdapter adapter = new SmsInfoAdapter(this, listSms);
            mListData.setAdapter(adapter);
        }
    }
}
