package uy.planetwars;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenuActivity extends Activity{
	public MediaPlayer bgmOBJ;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mainmenu);
	        
	        bgmOBJ = MediaPlayer.create(this,R.raw.bgm);
	        bgmOBJ.setLooping(true);
	        bgmOBJ.start();
	        
	        Button playbtn = (Button)this.findViewById(R.id.startbutton);
	        Button helpbtn = (Button)this.findViewById(R.id.helpbutton);
	        Button configbtn = (Button)this.findViewById(R.id.configbutton);
	      
	        //play intent
	        playbtn.setOnClickListener(new OnClickListener () 
	        {
	        	public void onClick(View v) {
	        		bgmOBJ.stop();
	        		Intent startgame = new Intent(MainMenuActivity.this, MainActivity.class);
        			MainMenuActivity.this.startActivity(startgame);
	        		
	        		
	        	}
	        });
	        //help intent
	        helpbtn.setOnClickListener(new OnClickListener () 
	        {
	        	public void onClick(View v) {
	        		
	        		Intent helpgame = new Intent(MainMenuActivity.this, HelpActivity.class);
        			MainMenuActivity.this.startActivity(helpgame);
	        		
	        		
	        	}
	        });
	        //config intent
	        configbtn.setOnClickListener(new OnClickListener () 
	        {
	        	public void onClick(View v) {
	        		
	        		Intent configgame = new Intent(MainMenuActivity.this, ConfigActivity.class);
        			MainMenuActivity.this.startActivity(configgame);
	        		
	        		
	        	}
	        });
}
}
