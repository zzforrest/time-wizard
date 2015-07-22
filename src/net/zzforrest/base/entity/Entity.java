package net.zzforrest.base.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import net.zzforrest.base.Input;
import net.zzforrest.base.MainComponent;
import net.zzforrest.base.Scene;
import net.zzforrest.base.entity.component.Component;
import net.zzforrest.base.physics.AABB;

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
	public static final long FLAG_SOLID          = 1 << 0; // Other entities cannot overlap this one
	
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
	 * aabb - collision information
	 * xvel - x velocity in pixels / second
	 * yvel - y velocity in pixels / second
	 */
	protected AABB aabb;
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
		
		aabb       = new AABB();
		components = new ArrayList<>();
	}
	
	public void update(Input input)
	{
		for(Component component : components)
			component.update(input);
		
		/*
		 * Collision
		 */
		
		/*
		 * If this entity isn't moving, don't proceed with the next part
		 */
		if(xvel == 0 && yvel == 0)
			return;
		
		/*
		 * Horizontal collision
		 */

		ArrayList<Entity> entities = scene.getEntities();
		
		float dx = xvel * MainComponent.DELTA;
		int   sx = (int)Math.signum(dx);       // The sign of velocity
		
		aabb.moveX(dx);
		
		for(Entity entity : entities)
		{
			if(entity == this || entity.dead())
				continue;
			
			if(entity.colliding(aabb))
			{
				if(entity.hasFlags(Entity.FLAG_SOLID))
				{
					float moveamt = sx > 0 ? entity.getAABB().getLeft() - aabb.getRight() :
											 entity.getAABB().getRight() - aabb.getLeft();
					aabb.moveX(moveamt);
				}
				
				if(hasFlags(Entity.FLAG_SOLID))
				{
					float moveamt = sx > 0 ? entity.getAABB().getLeft() - aabb.getRight() :
						 entity.getAABB().getRight() - aabb.getLeft();
					entity.getAABB().moveX(-moveamt);
				}
			}
		}
		
		/*
		 * Vertical collision
		 */
		
		/*
		 * If not moving vertically, don't do the next bit
		 */
		if(yvel == 0)
			return;
		
		float dy = yvel * MainComponent.DELTA;
		int   sy = (int)Math.signum(dy);       // The sign of velocity
		
		aabb.moveY(dy);
		
		for(Entity entity : entities)
		{
			if(entity == this || entity.dead())
				continue;
			
			if(entity.colliding(aabb))
			{
				if(entity.hasFlags(Entity.FLAG_SOLID))
				{
					float moveamt = sy > 0 ? entity.getAABB().getTop() - aabb.getBottom() :
											 entity.getAABB().getBottom() - aabb.getTop();
					aabb.moveY(moveamt);
				}
				
				if(hasFlags(Entity.FLAG_SOLID))
				{
					float moveamt = sy > 0 ? entity.getAABB().getTop() - aabb.getBottom() :
						 entity.getAABB().getBottom() - aabb.getTop();
					entity.getAABB().moveY(-moveamt);
				}
			}
		}
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
	 * @param aabb
	 * 			The other AABB
	 * @return true if this entity's AABB is overlapping the provided AABB
	 */
	public boolean colliding(AABB aabb)
	{
		return this.aabb.colliding(aabb);
	}
	
	/**
	 * Flags entity for removal
	 */
	public void die()
	{
		dead = true;
	}
	
	/**
	 * @return true if the entity needs to be removed from the scene
	 */
	public boolean dead()
	{
		return dead;
	}
	
	/**
	 * @return the hitbox of this entity
	 */
	public AABB getAABB()
	{
		return aabb;
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