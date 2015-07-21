package net.zzforrest.base.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import net.zzforrest.base.Input;
import net.zzforrest.base.MainComponent;
import net.zzforrest.base.Scene;
import net.zzforrest.base.entity.component.Component;

/*
 * A basic entity class
 * The only things intrinsic to an entity are position, size, and velocity
 * Contains components which can add more information
 */
public class Entity
{
	/*
	 * List of predefined flags
	 */
	public static final long FLAG_PLAYER = 0 << 0; // The current entity is a player
	public static final long FLAG_ENEMY  = 0 << 1; // The current entity is an enemy
	
	/*
	 * Scene information
	 * 
	 * scene - the scene in which this entity exists
	 * flags - bit list style flag system, can have 63 total flags (darn you signage!)
	 * dead  - true if this entity needs to be removed from the scene
	 */
	protected Scene scene;
	protected long flags;
	protected boolean dead;
	
	/* 
	 * Spatial information
	 * 
	 * x    - x coordinate
	 * y    - y coordinate
	 * w    - width
	 * h    - height
	 * xvel - x velocity in pixels / second
	 * yvel - y velocity in pixels / second
	 */
	protected float x;
	protected float y;
	protected float w;
	protected float h;
	protected float xvel;
	protected float yvel;
	
	/*
	 * Component information
	 * 
	 * components - list of components parented by this entity
	 */
	private ArrayList<Component> components;
	
	public Entity(Scene scene)
	{
		this.scene = scene;
		
		components = new ArrayList<>();
	}
	
	public void update(Input input)
	{
		for(Component component : components)
			component.update(input);
		
		x += xvel * MainComponent.DELTA;
		y += yvel * MainComponent.DELTA;
	}
	
	public void render(Graphics g)
	{
		for(Component component : components)
			component.render(g);
	}
	
	/**
	 * @param flag
	 * 			Flag to be added
	 * @return itself
	 */
	public Entity addFlag(long flag)
	{
		flags = (flags | flag);
		
		return this;
	}
	
	/**
	 * @param flag
	 * 			Flag to be removed
	 * @return itself
	 */
	public Entity removeFlag(long flag)
	{
		flags = (flags & ~flag);
		
		return this;
	}
	
	/**
	 * @param flag
	 * 			Flag or flags (combined with or (|) operation) to be tested
	 * @return true if the entity has all flags given
	 */
	public boolean hasFlags(long flag)
	{
		return (flags & flag) == flag;
	}
	
	/**
	 * @param component
	 * 			Component to be added to this entity
	 * @return itself
	 */
	public Entity addComponent(Component component)
	{
		components.add(component);
		component.setParent(this);
		
		return this;
	}
	
	/**
	 * @param entity
	 * 			The other entity
	 * @return true if this and the provided entity are overlapping
	 */
	public boolean isColliding(Entity entity)
	{
		if(getLeft() > entity.getRight())
			return false;
		if(entity.getLeft() > getRight())
			return false;
		if(getTop() > entity.getBottom())
			return false;
		if(entity.getTop() > getBottom())
			return false;
		
		return true;
	}
	
	/**
	 * @return true if the entity needs to be removed from the scene
	 */
	public boolean dead()
	{
		return dead;
	}
	
	/**
	 * @return left edge of the hitbox
	 */
	public float getLeft()
	{
		return x;
	}
	
	/**
	 * @return right edge of the hitbox
	 */
	public float getRight()
	{
		return x + w;
	}
	
	/**
	 * @return top edge of the hitbox
	 */
	public float getTop()
	{
		return y;
	}
	
	/**
	 * @return bottom edge of the hitbox
	 */
	public float getBottom()
	{
		return y + h;
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public float getW()
	{
		return w;
	}

	public void setW(float w)
	{
		this.w = w;
	}

	public float getH()
	{
		return h;
	}

	public void setH(float h)
	{
		this.h = h;
	}

	public float getXvel()
	{
		return xvel;
	}

	public void setXvel(float xvel)
	{
		this.xvel = xvel;
	}

	public float getYvel()
	{
		return yvel;
	}

	public void setYvel(float yvel)
	{
		this.yvel = yvel;
	}
}