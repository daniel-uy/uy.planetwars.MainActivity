package uy.planetwars;
import java.math.*;

import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//redship
public class shipslaunch2 extends ShipObject {
	  float flag=0;
	    public float angle=0;
	    int tapped=0;
	    public double xvelocity=0; 
	    public double yvelocity=0; 
	public shipslaunch2(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		int i , j;
		float quadrant;
		
     
		if (angle > 360)
			angle %= 360;
		if(this.angle == 180 )
		
		this.mPhysicsHandler.setVelocityX((float) (100 * Math.cos(this.getRotation())));
		this.mPhysicsHandler.setVelocityY((float) (-100 * Math.sin(this.getRotation())));
		
		if(this.angle == 90 )
			this.mPhysicsHandler.setVelocityX((float) (100 * Math.cos(this.getRotation())));
		    this.mPhysicsHandler.setVelocityY((float) (-100 * Math.sin(this.getRotation())));
			//this.mPhysicsHandler.setAccelerationY(100);
	
	}

	@Override
	public void shipcontrol() {
		// TODO Auto-generated method stub
		
	}

}
