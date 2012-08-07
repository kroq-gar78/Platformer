package org.newdawn.fizzy.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.World;

/**
 * A canvas that uses Java2D to display the world for debug
 * 
 * @author kevin
 */
public class WorldCanvas extends Canvas {
	/** The world we're displaying */
	private World world;
	/** True if the simulation is running */
	private boolean running;
	
	/**
	 * Create a new canvas
	 * 
	 * @param world The to be displayed
	 */
	public WorldCanvas(World world) {
		this.world = world;
	}
	
	/** 
	 * Start the render loop
	 */
	public void start() {
		createBufferStrategy(2);
		running = true;
		runLoop();
	}
	
	/**
	 * The logic and render loop
	 */
	private void runLoop() {
		BufferStrategy buffer = getBufferStrategy();
		
		while (running) {
			world.update(0.003f);
			
			Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
			
			g.clearRect(0,0,getWidth(),getHeight());
			g.translate(getWidth()/2,getHeight()/2);
			g.scale(1,-1);
			for (int i=0;i<world.getBodyCount();i++) {
				drawBody(g, world.getBody(i));
			}
			
			g.dispose();
			buffer.show();
		}
	}
	
	/**
	 * Stop the rendering
	 */
	public void stop() {
		running = false;
	}
	
	/**
	 * Get the world being rendered
	 * 
	 * @return The world being renderer
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Draw a body to the canvas
	 * 
	 * @param g The graphics context on which to draw
	 * @param body The body to be rendered
	 */
	private void drawBody(Graphics2D g, Body body) {
		for(Shape shape : body.getShapes())
			drawShape(g,body,shape);
	}

	/**
	 * Draw a shape 
	 * 
	 * @param g The graphics context to render to
	 * @param body The body to be rendered
	 * @param shape The shape representing the body
	 */
	private void drawShape(Graphics2D g, Body body, Shape shape) {
		if (shape instanceof CircleShape) {
			drawCircle(g, body, (CircleShape) shape);
		}
		if (shape instanceof PolygonShape) {
			drawPolygon(g, body, (PolygonShape) shape);
		}
		/*if (shape instanceof CompoundShape) {
			drawCompound(g, body, (CompoundShape) shape);
		}*/
	}

	/**
	 * Draw a compound shape 
	 * 
	 * @param g The graphics context to render to
	 * @param body The body to be rendered
	 * @param shape The shape representing the body
	 */
	/*private void drawCompound(Graphics2D g, Body body, CompoundShape shape) {
		int count = shape.getShapeCount();
		for (int i=0;i<count;i++) {
			drawShape(g, body, shape.getShape(i));
		}
	}*/
	
	/**
	 * Draw a body represented by a circle
	 * 
	 * @param g The graphics context to render to
	 * @param body The body to be rendered
	 * @param shape The shape representing the body
	 */
	private void drawCircle(Graphics2D g, Body body, CircleShape shape) {
		g = (Graphics2D) g.create();
		g.translate(body.getX(), body.getY());
		g.rotate(body.getRotation());
		
		float radius = shape.m_radius;
		
		g.setColor(Color.black);
		g.drawOval((int) -radius,(int) -radius,(int) (radius*2),(int) (radius*2));
		g.drawLine(0,0,0,(int) -radius);
	}
	
	/**
	 * Draw a body represented by a polygon
	 * 
	 * @param g The graphics context on which to render
	 * @param body The body to be rendered
	 * @param shape The shape representing the body
	 */
	private void drawPolygon(Graphics2D g, Body body, PolygonShape shape) {
		g = (Graphics2D) g.create();
		g.translate(body.getX(), body.getY());
		g.rotate(body.getRotation());
		//g.translate(shape.getXOffset(), shape.getYOffset());
		//g.rotate(shape.getAngleOffset());

		g.setColor(Color.black);
		for (int i=0;i<shape.getVertexCount();i++) {
			g.drawLine((int) shape.getVertex(i).x, (int) shape.getVertex(i).y,
					   (int) shape.getVertex((i+1)%shape.getVertexCount()).x, (int) shape.getVertex((i+1)%shape.getVertexCount()).y);
		}
		
	}
}
