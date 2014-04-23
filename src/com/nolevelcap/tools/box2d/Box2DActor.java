package com.nolevelcap.tools.box2d;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Box2DActor extends Actor {
	private boolean CustomFixture;
	
	private Body body;
	private VerticeLoader vLoader;
	
	public TextureRegion texture;
	
	public Box2DActor(boolean Box2DRendering, TextureRegion r, int originX, int originY, int width, int height) {
		vLoader = new VerticeLoader();
		this.setWidth(width);
		this.setHeight(height);
		this.setBounds(originX, originY, width, height);
		this.setOriginX(originX);
		this.setOriginY(originY);
		this.texture = r;
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}



	public void createPhysicsBody(boolean Box2DRendering, World world, BodyType bodyType) {
		setCustomFixture(true);
		BodyDef def = new BodyDef();
		def.type = bodyType;
		def.position.set(getX(), getY());
		body = world.createBody(def);
		body.setUserData(this);
	}
	
	public void addFixture(FileHandle file, float Density) {
		setCustomFixture(true);
		PolygonShape groundBox = new PolygonShape();
		groundBox.set(vLoader.getVertexInfo(file));
		body.createFixture(groundBox, Density);
		
	}
	
	public void addFixture(int hx,int hy,float Density) {
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(hx, hy);
		body.createFixture(groundBox, Density);
	}
	
	public void removeFixture(int i) {
		body.destroyFixture(this.getMainBodyFixtures(i));
	}
	
	private Fixture getMainBodyFixtures(int i) {
		if(i < body.getFixtureList().size) {
			return body.getFixtureList().get(i);
		} else {
			return null;
		}
	}
	
	public Body getMainBody() {
		return body;
	}
	
	
	public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
		body.applyLinearImpulse(impulse, point, wake);
	}
	
	public void applyLinearImpulse(float impulseX, float impulseY, float pointX, float pointY, boolean wake) {
		body.applyLinearImpulse(impulseX, impulseY, pointX, pointY, wake);
	}
	
	public void applyAngularImpulse(float impulse, boolean wake) {
		body.applyAngularImpulse(impulse, wake);
	}
	
	public void applyForce(Vector2 force, Vector2 point, boolean wake) {
		body.applyForce(force, point, wake);
	}
	
	public void applyForce(float forceX, float forceY, float pointX, float pointY, boolean wake) {
		body.applyForce(forceX, forceY, pointX, pointY, wake);
	}
	
	public void applyForceToCenter(Vector2 force, boolean wake) {
		body.applyForceToCenter(force, wake);
	}
	
	public void applyForceToCenter(float forceX, float forceY, boolean wake) {
		body.applyForceToCenter(forceX, forceY, wake);
	}
	
	public void applyTorque(float torque, boolean wake) {
		body.applyTorque(torque, wake);
	}
	
	public void setLinearDamping(float linearDamping) {
		body.setLinearDamping(linearDamping);
	}



	public boolean hasCustomFixture() {
		return CustomFixture;
	}



	public void setCustomFixture(boolean customFixture) {
		CustomFixture = customFixture;
	}
	
	
	
}
