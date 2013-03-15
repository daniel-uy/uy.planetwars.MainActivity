package uy.planetwars;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.help_layout);
	     
	     Button backbtn = (Button)this.findViewById(R.id.backbutton);	     
	     backbtn.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}    	 
	     });
	}

}
