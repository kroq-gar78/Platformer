package org.newdawn.fizzy;

import java.util.ArrayList;

//import org.jbox2d.collision.shapes.Shape;

/**
 * A primitive shape handling adding to the world and configuration of physics 
 * properties.
 * 
 * @author kevin
 *
 * @param <T> A shape definition for the primitive
 */
public abstract class PrimitiveShape<T extends org.jbox2d.collision.shapes.Shape> implements Shape {
	/** The array list that contains this shape */
	protected ArrayList<org.jbox2d.collision.shapes.Shape> jbox2DShapes = new ArrayList<org.jbox2d.collision.shapes.Shape>();
	/** The primitive shape that represent this fizzy shape */
	protected T jbox2DShape;

	/** The body this shape is being used in if any */
	protected Body body;
	
	/**
	 * The body this shape is being used in if any
	 * 
	 * @return The body this shape is being used in if any
	 */
	public Body getBody() {
		return body;
	}
	
	/**
	 * Create a new primitive shape 
	 * 
	 * @param localDef The shape definition backing this fizzy shape
	 */
	/*protected PrimitiveShape(T localDef) {
		this.def = localDef;
	}*/
	
	/*@Override
	public void createInBody(Body body) {
		this.body = body;
		jbox2DShape = body.getJBoxBody().createShape(def);
		jbox2DShapes.add(jbox2DShape);
	}*/

	@Override
	public ArrayList<org.jbox2d.collision.shapes.Shape> getJBoxShapes() {
		return jbox2DShapes;
	}

	

}
