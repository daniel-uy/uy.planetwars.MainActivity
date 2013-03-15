package uy.planetwars;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
 

 
public class Player extends GameObject {

	int shipcount1 = 0;
	int shipcount2 = 0;
    int playerscore = 0;
    int playerstate =0;
    // ===========================================================
    // Constructors
    // ===========================================================
 
    public Player(final float pX, final float pY, final TiledTextureRegion pTiledTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
    }
 
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
 
    @Override
     
     public void move() {
     int flag= 0;
    // if (flag==0)  
      // this.mPhysicsHandler.setAngularVelocity(50);
     /*rotate back and forth
     if(this.getRotation()>360)
        { this.mPhysicsHandler.setAngularVelocity(-90);
             flag= 0;
        }
     
        if(this.getRotation()<=0 && flag ==0)
        { this.mPhysicsHandler.setAngularVelocity(90);
             flag=1;
             }    
     //*/ 
        OutOfScreenX();
    }
 
    // ===========================================================
    // Methods
    // ===========================================================
 
    private void OutOfScreenX() {
        if (mX > MainActivity.CAMERA_WIDTH) { // OutOfScreenX (right)
            mX = 0;
        } else if (mX < 0) { // OutOfScreenX (left)
            mX = MainActivity.CAMERA_WIDTH;
        }
    }
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
     
    	//shipcontrol();
    	
        super.onManagedUpdate(pSecondsElapsed);
    }
}