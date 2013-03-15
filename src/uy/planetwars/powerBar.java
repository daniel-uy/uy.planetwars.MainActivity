package uy.planetwars;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
 

 
public class powerBar extends GameObject {

	int shipcount1 = 0;
	int shipcount2 = 0;

    // ===========================================================
    // Constructors
    // ===========================================================
 
    public powerBar(final float pX, final float pY, final TiledTextureRegion pTiledTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
    }
 
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
 
    @Override
     
     public void move() {
     int flag= 1;
    // if (flag==0)
     
    
    
      
    	if(this.getY()>=1200 && flag ==1)
         { this.mPhysicsHandler.setVelocityY(-590);
              flag=0;          
              } 
    	if(this.getY()<=870)
    		this.mPhysicsHandler.setVelocityY(590);
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
}