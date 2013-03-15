package uy.planetwars;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//
//superclass sa ships
//
public abstract class ShipObject extends AnimatedSprite {
 int state=0;
	 // ===========================================================
    // Constants
    // ===========================================================
 
    // ===========================================================
    // Fields
    // ===========================================================
 
    public PhysicsHandler mPhysicsHandler;
    
    // ===========================================================
    // Constructors
    // ===========================================================
 
    public ShipObject(final float pX, final float pY, final ITiledTextureRegion pTiledTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
        this.mPhysicsHandler = new PhysicsHandler(this);     
        this.registerUpdateHandler(this.mPhysicsHandler);
   


    }
 
    // ===========================================================
    // Getter & Setter
    // ===========================================================
 
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
 
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
     
    	//shipcontrol();
    	
        super.onManagedUpdate(pSecondsElapsed);
    }
 
    // ===========================================================
    // Methods
    // ===========================================================
 
    public abstract void move();
    public abstract void shipcontrol();
}
