package uy.planetwars;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class splash1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.splash);
	       // getActionBar().setDisplayHomeAsUpEnabled(true);
	        
	        MediaPlayer mSplash = MediaPlayer.create(this,R.raw.doom);
	        mSplash.start();
	        Thread logoTimer = new Thread(){
	        	public void run(){
	        		try{
	        			short logoTimer = 0;
	        			while(logoTimer<5000)
	        			{
	        				sleep(100);
	        				logoTimer+=100;
	        			}
	        			startActivity(new Intent("android.mainmenu"));
	        		} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        		finally
	        		{
	        			finish();
	        		}
	        		
	        	}
	        };
	        logoTimer.start();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
