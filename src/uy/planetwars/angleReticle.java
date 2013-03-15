package uy.planetwars;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
 
//spinning reticle
 
public class angleReticle extends GameObject {

	int shipcount1 = 0;
	int shipcount2 = 0;
    public int flag= 0;
    public int retapped=0;
    // ===========================================================
    // Constructors
    // ===========================================================
 
    public angleReticle(final float pX, final float pY, final TiledTextureRegion pTiledTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
    }
 
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
 
    @Override
     
     public void move() {
    
     if (flag==0)
    	   if (flag==0)      
    	        this.mPhysicsHandler.setAngularVelocity(150);
    	       else
    	    	   this.mPhysicsHandler.setAngularVelocity(0);
    	       
    	    
     /*rotate back and forth
     if(this.getRotation()>360)
        { this.mPhysicsHandler.setAngularVelocity(-180);
             flag= 0;
        }
     
        if(this.getRotation()<=0 && flag ==0)
        { this.mPhysicsHandler.setAngularVelocity(180);
             flag=1;
             }    
     //*/ 
        OutOfScreenX();
    }
    public void stop() {
      
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
}