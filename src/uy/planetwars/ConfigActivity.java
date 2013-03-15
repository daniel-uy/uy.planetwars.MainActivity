package uy.planetwars;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConfigActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.config_layout);
	     
	     Button okbtn = (Button)this.findViewById(R.id.button1);	  
	     Button backbtn = (Button)this.findViewById(R.id.button2);
	     
	     //Back Button
	     backbtn.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}    	 
	     });
	     
	     //OK Button
	     okbtn.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}    	 
		 });
	     
//	     Typeface type = Typeface.createFromAsset(getAssets(), "fonts/pdark.tff");
//	     TextView txt1 = (TextView)findViewById(R.id.textView2);
//	     TextView txt2 = (TextView)findViewById(R.id.textView3);
//	     
//	     txt1.setTypeface(type);
//	     txt2.setTypeface(type);
	}
}
