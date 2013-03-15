package uy.planetwars;
import java.io.IOException;
import java.io.InputStream;
import java.math.*;

import org.andengine.audio.music.Music;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;
import org.andengine.audio.music.*;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.TextView;

import java.math.*;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends SimpleBaseGameActivity {

    public static final int CAMERA_WIDTH = 720;
    public static final int CAMERA_HEIGHT = 1280;
 
    // ===========================================================
    // Fields
    // ===========================================================
   
    private Camera mCamera;
    private Scene mMainScene;
    private CameraScene mPauseScene;
    private BitmapTextureAtlas mBitmapTextureAtlas,planet1,planet2,shipblue,shipred,angleReticle,powerBar,texExplo,player1turnAtlas,player2turnAtlas;
    private TiledTextureRegion mPlayerTiledTextureRegion,planet1region,planet2region,shipblueregion,shipredregion,angleRegion,powerBarRegion,regExplo, player1turnRegion,player2turnRegion;
	private TextureRegion bg;
	private static int SPR_COLUMN = 5;
	private static int SPR_ROWS = 5;
	private int playerTurn = 0;
	private int redShipSelected =0;
	private int powerbarflag=0;
	public powerBar2 pbar2holder;
	public powerBar  pbarholder;
	private AnimatedSprite sprExplo,player1turnAnimate,player2turnAnimate;
	public AnimatedSprite pbarAnimationHolder1;
	public AnimatedSprite pbarAnimationHolder2;
	private angleReticle reticleHolder;
	private angleReticle reticleHolder2;
	private Sound snd_pew;
	private Sound snd_chung;
	private Music snd_BGM;
	private Display screenSize;
	private Point screenDimensions;
	private int screenHeight;
	private int screenWidth;
	 int playerState1=0;
	 int playerState2=0;
	//Text
	private Font  font;
	private Text  player2score;
	private Text  player1score;
	//float direction;
	//
	//list of ships in scene
	//
	private List<shipslaunch> blueshiplist = new ArrayList();
	private List<shipslaunch> redshiplist = new ArrayList();
	//private Sprite redship, blueship, brownplanet, grayplanet;
    
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

     @SuppressLint("NewApi")
	public EngineOptions onCreateEngineOptions() {
    	   
         screenSize =  getWindowManager().getDefaultDisplay();;
         screenDimensions = new Point();
         screenSize.getSize(screenDimensions);
         screenWidth = screenDimensions.x;
         screenHeight = screenDimensions.y;
         
        this.mCamera = new Camera(30, 0, screenWidth, screenHeight);
      
        EngineOptions en= new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(screenWidth, screenHeight), this.mCamera);
      
        en.getAudioOptions().setNeedsSound(true);
        en.getAudioOptions().setNeedsMusic(true);
        return en;
     
     }
 
    @SuppressLint("NewApi")
	@Override
    protected void onCreateResources() {
      
  //dri ipang load ang mga .PNG
    //player turn sprite	
    	player1turnAtlas = new BitmapTextureAtlas(this.getTextureManager(),640,640, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
    	player1turnRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(player1turnAtlas, this.getAssets(),"gfx/player1.png", 0, 0, 2, 10);
    	player2turnAtlas = new BitmapTextureAtlas(this.getTextureManager(),640,640, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
    	player2turnRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(player2turnAtlas, this.getAssets(),"gfx/player2.png", 0, 0, 2, 10);
  
    //explosion sprite
    	texExplo = new BitmapTextureAtlas(this.getTextureManager(),320,320, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
    	regExplo = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texExplo, this.getAssets(),"gfx/expsprite.png", 0, 0, SPR_COLUMN, SPR_ROWS);
    //ang container 
       this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(),128, 128);
    //ang actual object na mo hold sa texture ug specify wich part sa texture ang ma display like if ang entire picture pa or a specific region lng
       this.mPlayerTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "gfx/ship.png", 0, 0, 1, 1);
    //planets  
       this.planet1 = new BitmapTextureAtlas(this.getTextureManager(),128, 128);
       this.planet1region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.planet1, this, "gfx/planet.png", 0, 0, 1, 1);
       this.planet2 = new BitmapTextureAtlas(this.getTextureManager(),128, 128);
       this.planet2region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.planet2, this, "gfx/planet2.png", 0, 0, 1, 1);
   //ships
       this.shipblue = new BitmapTextureAtlas(this.getTextureManager(),64, 64);
       this.shipblueregion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.shipblue, this, "gfx/ship.png", 0, 0, 1, 1);
       this.shipred = new BitmapTextureAtlas(this.getTextureManager(),64, 64);
       this.shipredregion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.shipred, this, "gfx/ship2.png", 0, 0, 1, 1);
   //angle 
       this.angleReticle = new BitmapTextureAtlas(this.getTextureManager(),128, 128);
       this.angleRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.angleReticle, this, "gfx/circle3.png", 0, 0, 1, 1);
   //powerbar
       this.powerBar = new BitmapTextureAtlas(this.getTextureManager(),256, 512);
       this.powerBarRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.powerBar, this, "gfx/powerbar.png", 0, 0, 1, 1);
       font =FontFactory.create(mEngine.getFontManager(),
    		   mEngine.getTextureManager(), 256, 256,
    		   Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 32f,
    		   true,
    		   Color.WHITE);
       
       font.load();
       player1turnAtlas.load();
       player2turnAtlas.load();
       //e load sa memory ang rescources
       this.mBitmapTextureAtlas.load();
       this.planet1.load();
       this.planet2.load();
       this.shipblue.load();
       this.shipred.load();
       this.angleReticle.load();
       this.powerBar.load();
       this.texExplo.load();
       
       
       
       //kang louie ni nga input wa kaayo ko kasabot same rmn guro sa above..lahi lng pag omplement
       //same2 ra sa kuan ni sa bg - louiee hahaha
       try 
		{
		 
		    ITexture bg = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
		        public InputStream open() throws IOException {
		            return getAssets().open("gfx/bg.png");
		        }
		    });    	  
		    bg.load();		 
			this.bg = TextureRegionFactory.extractFromTexture(bg);			   
		} catch (IOException e) {
		    Debug.e(e);
		}
       
       try
       {
        snd_pew = SoundFactory.createSoundFromAsset(this.getSoundManager(), this.getApplicationContext(),
          "snd/liftoff.wav");
       } catch (IOException e)
       {
        e.printStackTrace();
       
       }
       try
       {
        snd_BGM = MusicFactory.createMusicFromAsset(this.getMusicManager(), this.getApplicationContext(),"snd/bgm.mp3");
       snd_BGM.setLooping(true);
         } catch (IOException e)
       {
        e.printStackTrace();
       }
       try
       {
        snd_chung = SoundFactory.createSoundFromAsset(this.getSoundManager(), this.getApplicationContext(),
          "snd/spaceshiphum.mp3");
       } catch (IOException e)
       {
        e.printStackTrace();
       
       }
      
    }
 
    @Override
    protected Scene onCreateScene() {
    	
        this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate     
        //ang scene where dri ipang butang ang mga objects basically mura siyag root container
        this.mMainScene = new Scene();
        //setting sa abackground
    	Sprite backgroundSprite = new Sprite(0, 0, this.bg, getVertexBufferObjectManager());
		mMainScene.attachChild(backgroundSprite);
		
		//PLayer turn sprite initialization
		player1turnAnimate =new AnimatedSprite(0, 0, player1turnRegion, this.getVertexBufferObjectManager());
		player2turnAnimate =new AnimatedSprite(0, 0, player2turnRegion, this.getVertexBufferObjectManager());
		sprExplo =  new AnimatedSprite(0, 0, regExplo, this.getVertexBufferObjectManager());
		
		mMainScene.attachChild(sprExplo);
		mMainScene.attachChild(player1turnAnimate);
		mMainScene.attachChild(player2turnAnimate);
		sprExplo.setVisible(false);
		player2turnAnimate.setVisible(false);
		player1turnAnimate.setX(screenWidth/2-(player1turnAnimate.getWidth()/2));
		player1turnAnimate.setY(screenHeight/2-(player1turnAnimate.getHeight()/2));
		player2turnAnimate.setX(screenWidth/2-(player2turnAnimate.getWidth()/2));
		player2turnAnimate.setY(screenHeight/2-(player2turnAnimate.getHeight()/2));
		
		//player1turnAnimate.setVisible(false);
		player1turnAnimate.animate(50, 1);
	   // snd_BGM.play();	
		//sprExplo.animate(100);
		
	  
        // Centre the player on the camera.
//        final float centerX = (CAMERA_WIDTH - this.mPlayerTiledTextureRegion.getWidth()) / 2;
//        final float centerY = (CAMERA_HEIGHT - this.mPlayerTiledTextureRegion.getHeight()) / 2;
        final float centerX = (screenWidth - this.mPlayerTiledTextureRegion.getWidth()) / 2;
        final float centerY = (screenHeight - this.mPlayerTiledTextureRegion.getHeight()) / 2;
         
        // Create the sprite and add it to the scene.
      //  final AnimatedSprite reticle = new AnimatedSprite(0, 0,this.angleRegion, getVertexBufferObjectManager());
      
        
        /*create spinning blue ship
        final Player oPlayer = new Player(centerX, centerY, this.mPlayerTiledTextureRegion, this.getVertexBufferObjectManager())
        {
        	//iyang touch event
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                    return true;
          }
        
    };
    //*/
  //create planet gray
    final Player oPlanet1 = new Player(30, 1150, this.planet1region, this.getVertexBufferObjectManager())
    {
    	  @Override
          public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
    		  if(pSceneTouchEvent.isActionDown()) {
                 if(this.shipcount1<10 && playerTurn==0 && playerState1==0)
                 {
    			  addship(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
    			  this.shipcount1++;	
    			  snd_pew.stop();
    			  snd_pew.play();
    			 //snd_BGM.play();
    			playerState1=1;
                 }
               
          }
			return true;
        }

	
    };
//create planet orange
//final powerBar pbar= new powerBar (570, 1300, this.powerBarRegion, this.getVertexBufferObjectManager());
//final AnimatedSprite pbar2= new AnimatedSprite (570, 870, this.powerBarRegion, this.getVertexBufferObjectManager());



final Player oPlanet2 = new Player(620, 0, this.planet2region, this.getVertexBufferObjectManager())
	{
	  @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		  if(pSceneTouchEvent.isActionDown()) {
              if(this.shipcount2<10 && playerTurn==1 && playerState2==0)
              {
 			  addship2(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
 			  this.shipcount2++;
 			  snd_pew.stop();
 			  snd_pew.play(); 	
 			 playerState2=1;
              }
              
      }
		return true;
    }

	
	};
	//dri ipang attach sa scene ang mga object pra makita 
	   // this.mMainScene.attachChild(reticle);
        //this.mMainScene.attachChild(oPlayer);
        mMainScene.attachChild(oPlanet1);
        mMainScene.attachChild(oPlanet2);
    
    //    mMainScene.registerTouchArea(reticle);
       // mMainScene.registerTouchArea(oPlayer);
        mMainScene.registerTouchArea(oPlanet1);
        mMainScene.registerTouchArea(oPlanet2);
        mMainScene.setTouchAreaBindingOnActionDownEnabled(true);
     
        
        mMainScene.registerUpdateHandler(new IUpdateHandler() {
    			public void reset() { }

    			public void onUpdate(final float pSecondsElapsed) {
    				
    				
    					//
    				//set ship collision properties
    				//
    				
    				//if(blueshiplist.isEmpty()==false && redshiplist.isEmpty()==false )
    				{
    				for(int j=0;j<blueshiplist.size();j++)
    				{	
    					if(blueshiplist.get(j).collidesWith(oPlanet2))
    					{
    			          finish();
    					}
    				for(int i=0;i<redshiplist.size();i++)
    				{  
    					if(redshiplist.get(i).collidesWith(oPlanet1))
    					{
    			          finish();
    					}
    				if( blueshiplist.get(j).collidesWith(redshiplist.get(i))) {
    					//sprExp.animate(100);// explode sprite
    					if(playerTurn==0)
    					{
    				
    					redshiplist.get(i).detachSelf();
    					redshiplist.get(i).unregisterUpdateHandler(redshiplist.get(i));
    					mMainScene.unregisterTouchArea(redshiplist.get(i));
    					//mMainScene.detachChild(redshiplist.get(i));
    				    sprExplo.setX(redshiplist.get(i).getX());
    				    sprExplo.setY(redshiplist.get(i).getY());
    					redshiplist.remove(i);
    					sprExplo.setVisible(true);
    					sprExplo.animate(25, 1);
    					oPlanet1.playerscore+=100;
    					  player2score.setText("Score: " + oPlanet2.playerscore);
    					}
    					else
    					{
    						blueshiplist.get(j).detachSelf();
    						blueshiplist.get(j).unregisterUpdateHandler(blueshiplist.get(j));
    						mMainScene.unregisterTouchArea(blueshiplist.get(j));
        					//mMainScene.detachChild(blueshiplist.get(j));
        				    sprExplo.setX(blueshiplist.get(j).getX());
        				    sprExplo.setY(blueshiplist.get(j).getY());
        					blueshiplist.remove(j);
        					sprExplo.setVisible(true);
        				    sprExplo.animate(25, 1);
        				    oPlanet2.playerscore+=100;
        				    player1score.setText("Score: " + oPlanet1.playerscore);
        				    
    					}
    				
    				}
    				}
    					//redship.detachChildren(); // detach or remove red??
    				}
    				}
                  
    			}
    		});
        
        player1score = new Text(0, 0, font, "Player 1 score" , this.getVertexBufferObjectManager())
	     {
		 @Override
		    protected void onManagedUpdate(float pSecondsElapsed) {
		         
		    	
			  player1score.setText("Score: " + oPlanet1.playerscore);
			     
		        super.onManagedUpdate(pSecondsElapsed);
		    }
	     };
	 player2score = new Text(0, 0, font, "Player 2 score", this.getVertexBufferObjectManager())
	 {
		 @Override
		    protected void onManagedUpdate(float pSecondsElapsed) {
		     
			  player2score.setText("Score: " + oPlanet2.playerscore);
		      
		    	 
		        super.onManagedUpdate(pSecondsElapsed);
		    }
	     };
	 mMainScene.attachChild(player1score);
	 mMainScene.attachChild(player2score);
	 mMainScene.registerUpdateHandler(player1score);
	 mMainScene.registerUpdateHandler(player2score);
		
	  player1score.setPosition((float) (CAMERA_WIDTH * .5 - (player1score.getWidth()/2)), (float) (CAMERA_HEIGHT *.95 - (player1score.getHeight()/2)));
	  player2score.setPosition((float) (CAMERA_WIDTH *.5 - (player2score.getWidth()/2)), (float) (CAMERA_HEIGHT  *.05 - (player2score.getHeight()/2)));
     
         
        return this.mMainScene;
    }
    //add red ship
    private void addship2(float x, float y) 
	{   
    	
		shipslaunch ship2 = new shipslaunch(x-150,y + 50,shipredregion,this.getVertexBufferObjectManager())
		 {
			protected void onManagedUpdate(float pSecondsElapsed) {
				
				if(this.state==1)
				{
					float direction=this.angle;
				    direction%=360;
				    this.setRotation(direction);				  
				  ////
				    //moveship
				  ////
				   if(direction<90 && direction>-1)	   
					  // this.mPhysicsHandler.setVelocity(Math.abs((float)(100 * Math.sin(direction))),(-1)*Math.abs((float)(100 * Math.cos(direction))));
				   this.mPhysicsHandler.setVelocity(Math.abs((float)(100 * Math.sin(90-direction))),(-1)*Math.abs((float)(100 * Math.cos(90-direction))));
					 
				   //*(float)(100 * Math.sin(90-direction))
				   else if(direction<361 && direction>270)
					   this.mPhysicsHandler.setVelocity((-1)*Math.abs((float)(100 * Math.cos(direction))),(-1)*Math.abs((float)(100 * Math.cos(direction))));
				   ///*
				   else if(direction<271 && direction>180) 
					   this.mPhysicsHandler.setVelocity((-1)*Math.abs((float)(100 * Math.sin(direction))),Math.abs((float)(100 * Math.cos(direction))));
				   else 
				        this.mPhysicsHandler.setVelocity(Math.abs((float)(100 * Math.cos(direction))),Math.abs((float)(100 * Math.sin(direction))));
				
				   this.state=2;
				    	
				  }
				  //
				   //stop ship
				   //
				    if(this.getPreviousPositionX() != this.getX()|| this.getPreviousPositionY() != this.getY() )
				    {
					  incrementDistancetraveled();
					  setPreviousPosition(getX(),getY());	
				    }
				    if(this.getDistancetraveled()>300)
				   {		 
					  this.mPhysicsHandler.setVelocity(0);
					  resetDistancetraveled();
				      this.state=0;
				      this.tapped=0;
				      playerTurn=0;
				      playerState1=0;
				      player1turnAnimate.animate(25, 1);
				   }  
				////
				  //boundery bounce
				////
				  if(this.getY()>CAMERA_HEIGHT -68){
				    	this.mPhysicsHandler.setVelocityY(-100);
				    	 this.setRotation(this.getRotation() + 180);
				    }
				    if(this.getY()<0){   	
				    	this.mPhysicsHandler.setVelocityY(100);
				    	 this.setRotation(this.getRotation() + 180);
				    }
				    if(this.getX()<30)
				    {
				    	this.mPhysicsHandler.setVelocityX(100);
				    this.setRotation(this.getRotation() + 180);
				    }
				    if(this.getX()>CAMERA_WIDTH -38)
				    {
				    	this.mPhysicsHandler.setVelocityX(-100);
				    	 this.setRotation(this.getRotation() + 180);
				    }
			
				super.onManagedUpdate(pSecondsElapsed);
			}
			
//			   powerBar2 pbar= new powerBar2 (-80, -330, powerBarRegion, this.getVertexBufferObjectManager());
//			   pbar2holder=pbar;
//			   AnimatedSprite pbar2= new AnimatedSprite(-80, -40, powerBarRegion, this.getVertexBufferObjectManager());  
//			   pbarAnimationHolder2 =pbar2;
//		
			  @Override
		      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) 
			  {
				  if(pSceneTouchEvent.isActionDown() && playerTurn==1) {
					  angleReticle reticle = new angleReticle(this.getX()-32,this.getY()-28,angleRegion, getVertexBufferObjectManager())
						 
						 {
						   	  
							  public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY)
							  {
								  if(pSceneTouchEvent.isActionDown()){
								
									  if(tapped==1)
									  {
										   powerBar2 pbar= new powerBar2 (-80, -330, powerBarRegion, this.getVertexBufferObjectManager());
										   pbar2holder=pbar;
										   AnimatedSprite pbar2= new AnimatedSprite(-80, -40, powerBarRegion, this.getVertexBufferObjectManager());  
										   pbarAnimationHolder2 =pbar2;
									   tapped=2;
									   this.flag=1; 
									  mMainScene.attachChild(pbar);
									  mMainScene.attachChild(pbar2);
									  xvelocity=this.getX();
									  yvelocity=this.getY();
									  angle=this.getRotation();
								      //this.move();
									  //this.mPhysicsHandler.setAngularVelocity(0);				
								     
									  }
									  else if(tapped==2)
									  {
										  if(state!=2)
										  {
										  state=1;
										  this.detachSelf();
										  mMainScene.unregisterTouchArea(this);
										  mMainScene.detachChild(pbar2holder);
										  mMainScene.detachChild(pbarAnimationHolder2);
										  }
									  }  
								  };
									  this.mPhysicsHandler.setAngularVelocity(0);
									 // this.detachSelf();	
									  						  
								  return false;
								  
							  };
						  
						 
						 };
					       //reticle.setVisible(false);
						  if(this.tapped==0)
						  {
							  
							   reticle.setVisible(true);					
					           reticle.setZIndex(6);
					           mMainScene.sortChildren();
					           mMainScene.attachChild(reticle);
					           mMainScene.registerTouchArea(reticle);
					           mMainScene.registerUpdateHandler(reticle);
					           this.tapped=1;	
					           
						  }	
						  else if(this.tapped==1)
						  {
							//  this.state=1;
						     
							// reticle.mPhysicsHandler.setVelocity(100);
						  }
					
			      }
					return false;
			    }

				
				};
              //dynamic na object attchment
	      redshiplist.add(ship2);	 
         mMainScene.attachChild(ship2);	 
         mMainScene.registerTouchArea(ship2);
        mMainScene.registerUpdateHandler(ship2);
	}  
    //add blue ship
	private void addship(float x, float y) 
	{   
		final shipslaunch ship = new shipslaunch(x +100,y - 100,shipblueregion,this.getVertexBufferObjectManager())
		 {
			  @Override
			protected void onManagedUpdate(float pSecondsElapsed) {
					
				  if(this.state==1)
					{
					   
						float direction=this.angle;
					    direction%=360;
					    
					    this.setRotation(direction);
					  
					  //*
					   // this.mPhysicsHandler
					   if(direction<90 && direction>-1)
						   this.mPhysicsHandler.setVelocity(Math.abs((float)(100 * Math.cos(90-direction))),(-1)*Math.abs((float)(100 * Math.sin(90-direction))));
					   //*(float)(100 * Math.sin(90-direction))
					   else if(direction<361 && direction>270)
						   this.mPhysicsHandler.setVelocity((-1)*Math.abs((float)(100* Math.sin(180-(direction-270)))),(-1)*Math.abs((float)(100 * Math.cos(180-(direction-270)))));
					   ///*
					   else if(direction<271 && direction>180) 
						   this.mPhysicsHandler.setVelocity((-1)*Math.abs((float)(100 * Math.sin(direction))),Math.abs((float)(100 * Math.cos(direction))));
						  else 
					   this.mPhysicsHandler.setVelocity(Math.abs((float)(100 * Math.sin(direction))),Math.abs((float)(100 * Math.cos(direction))));
					   
					   this.state=2;		
				
					}
				  
				   //
				   //stop ship
				   //
				    if(this.getPreviousPositionX() != this.getX()|| this.getPreviousPositionY() != this.getY() )
				    {
					  incrementDistancetraveled();
					  setPreviousPosition(getX(),getY());	
				    }
				    if(this.getDistancetraveled()>300)
				   {		 
					  this.mPhysicsHandler.setVelocity(0);
					  resetDistancetraveled();
				      this.state=0;
				      this.tapped=0;
				      playerTurn=1;	
				      playerState2=0;
				      player2turnAnimate.setVisible(true);
				      player2turnAnimate.animate(25, 1);
				   }  
				    
				    //
				    //boundery bounce
				    //
				    if(this.getY()>CAMERA_HEIGHT - 68){
				    	this.mPhysicsHandler.setVelocityY(-100);
				    	 this.setRotation(this.getRotation() + 180);
				    }
				    if(this.getY()<0){   	
				    	this.mPhysicsHandler.setVelocityY(100);
				    	 this.setRotation(this.getRotation() + 180);
				    }
				    if(this.getX()<30)
				    {
				    	this.mPhysicsHandler.setVelocityX(100);
				    this.setRotation(this.getRotation() + 180);
				    }
				    if(this.getX()>CAMERA_WIDTH -38)
				    {
				    	this.mPhysicsHandler.setVelocityX(-100);
				    	 this.setRotation(this.getRotation() + 180);
				    }
//				    if(redshiplist.isEmpty()==false)
//				    {
//				    if(checkcollision(this))
//				    	this.detachSelf();
//				    }
				 
				 
					super.onManagedUpdate(pSecondsElapsed);
				}
			  @Override
		      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) 
			  {
				  if(pSceneTouchEvent.isActionDown() && playerTurn==0) {
					  angleReticle reticle = new angleReticle(this.getX()-32,this.getY()-28,angleRegion, getVertexBufferObjectManager())
						 
						 {
						   	  
							  public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY)
							  {
								  if(pSceneTouchEvent.isActionDown()){
								
									  if(tapped==1)
									  {
									   powerBar pbar= new powerBar (570, 1300, powerBarRegion, this.getVertexBufferObjectManager());
									   pbarholder   =pbar;
									   AnimatedSprite pbar2= new  AnimatedSprite(570, 870, powerBarRegion, this.getVertexBufferObjectManager());  
									   pbarAnimationHolder1 =pbar2;
									   tapped=2;
									   this.flag=1; 
									  mMainScene.attachChild(pbar);
									  mMainScene.attachChild(pbar2);
									  xvelocity=this.getX();
									  yvelocity=this.getY();
									  angle=this.getRotation();
								      //this.move();
									  //this.mPhysicsHandler.setAngularVelocity(0);				
								     
									  }
									  else if(tapped==2)
									  {
										  if(state!=2)
										  {
										  state=1;
										  this.detachSelf();
										  mMainScene.unregisterTouchArea(this);
										  mMainScene.detachChild(pbarholder);
										  mMainScene.detachChild(pbarAnimationHolder1);
										  }
									  }  
								  };
									  this.mPhysicsHandler.setAngularVelocity(0);
									 // this.detachSelf();	
									 								  
								  return false;
								  
							  };
						  
						 
						 };
					       //reticle.setVisible(false);
						  if(this.tapped==0)
						  {
							  
							   reticle.setVisible(true);					
					           reticle.setZIndex(6);
					           mMainScene.sortChildren();
					           mMainScene.attachChild(reticle);
					           mMainScene.registerTouchArea(reticle);
					           mMainScene.registerUpdateHandler(reticle);
					           this.tapped=1;	
					           
						  }	
						  else if(this.tapped==1)
						  {
							//  this.state=1;
						     
							// reticle.mPhysicsHandler.setVelocity(100);
						  }
					
			      }
					return false;
			    }

				
				};
		blueshiplist.add(ship);	 
        mMainScene.attachChild(ship);	 
        mMainScene.registerTouchArea(ship);
        mMainScene.registerUpdateHandler(ship);
	}
	
	
	//Pause Game
	private void addAnglereticle(float x , float y)
	{
		
	}
	//pause menu
	 @Override
     public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
             if(pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
                     if(this.mEngine.isRunning()) {
                         //    this.mMainScene.setChildScene(this.mPauseScene, false, true, true);
                             this.mEngine.stop();
                     } else {
                          //   this.mMainScene.clearChildScene();
                             this.mEngine.start();
                     }
                     return true;
             } else {
                     return super.onKeyDown(pKeyCode, pEvent);
             }
     }
	

}
