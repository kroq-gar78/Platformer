package plat.gameplay;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Unit {

	public Player(String name, Image image, Vector2f position,
			Shape collisionShape, Vector2f direction, float speed)
	{
		super(name, image, position, collisionShape, direction, speed, 1);
		// TODO Auto-generated constructor stub
	}

	public Player(String name, Image image, Vector2f position,
			Shape collisionShape)
	{
		super(name, image, position, collisionShape, 1);
		// TODO Auto-generated constructor stub
	}
	
	public void update( GameContainer g , Game game , int delta )
	{
		if( jumping )
		{
			applyForce(gravity.negate());
			jumping=false;
		}
		super.update(g, game, delta);
	}
	
	public void jump() // call this in update
	{
		applyForce(new Vector2f( 0f, -1.25f ));
		jumping=true;
	}
	
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	private boolean jumping=false;
}
