package uy.planetwars;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
///Blue ship
public class shipslaunch extends ShipObject {
    float flag=0;
    public float angle=0;
    int tapped=0;
    int launch=0;
    int active=0;
    public double xvelocity=0; 
    public double yvelocity=0; 
    private float previouspositionX =this.getX();
    private float previouspositionY =this.getY();
    private int distancetraveled =0; 
    float initialposition =this.getX();
	public shipslaunch(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		
		// TODO Auto-generated constructor stub
	}
	 protected void onManagedUpdate(float pSecondsElapsed) {
		 
		 float currentposition;	
		// this.mPhysicsHandler.setAngularVelocity(180);
		 if(launch==1)
		 {
//			 this.mPhysicsHandler.setVelocityY(this.mPhysicsHandler.getVelocityY()+50);
//			 this.mPhysicsHandler.setVelocityX(this.mPhysicsHandler.getVelocityX()-50);	
			 
		 }
		 	 
	 
	        super.onManagedUpdate(pSecondsElapsed);
	    }
	@Override
	public void move() {
		// TODO Auto-generated method stub
//		final float i , j;
//		
//		i = this.getX();
//		j = this.getY();
//		this.mPhysicsHandler.setVelocityY(-200);
//		this.mPhysicsHandler.setVelocityX(110);
//		if (i >= 200)
//			this.mPhysicsHandler.setVelocity(0);
//	
//				this.setRotation(25);
		//this.mPhysicsHandler.setAccelerationY(100);

	}
	public float moveship(){
		float x=0;
		x = this.getX();
//	this.mPhysicsHandler.setVelocityY(-200);
//	this.mPhysicsHandler.setVelocityX(110);	
				//this.setRotation(25);
			
		return x;}
	public void shipcontrol()
	{ 
			
		
				
		
	}
	
	public void incrementDistancetraveled()
	{
		distancetraveled++;
	}
	public int getDistancetraveled()
	{
		return distancetraveled;
	}
	public void resetDistancetraveled()
	{
		distancetraveled=0;
	}
	public float getPreviousPositionX()
	{
		return previouspositionX;
	}
	public float getPreviousPositionY()
	{
		return previouspositionY;
	}
	public void setPreviousPositionX(float a)
	{
		previouspositionX = a;
	}
	public void setPreviousPositionY(float a)
	{
		previouspositionY = a;
	}
	public void setPreviousPosition(float a , float b)
	{
		previouspositionX = a;
		previouspositionY = b;
	}
}
