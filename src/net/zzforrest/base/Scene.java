package net.zzforrest.base;

import java.awt.Graphics;
import java.util.ArrayList;

import net.zzforrest.base.entity.Entity;

/*
 * Contains a scene graph of entities with components
 */
public class Scene
{
	private ArrayList<Entity> entities;
	
	public Scene()
	{
		entities = new ArrayList<>();
	}
	
	public void input(Input input)
	{
		for(Entity entity : entities)
			entity.input(input);
	}
	
	public void update()
	{
		//TODO: Collision
		
		/*
		 * Update all entities in the list
		 */
		
		for(Entity entity : entities)
			entity.update();
		
		/*
		 * Remove dead entities
		 */
		for(int i = 0; i < entities.size(); i ++)
			if(entities.get(i).dead())
				entities.remove(--i);
	}
	
	public void render(Graphics g)
	{
		for(Entity entity : entities)
			entity.render(g);
	}
	
	/**
	 * Adds an entity to the scene
	 * 
	 * @param entity
	 * 			Entity to be added
	 */
	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}
}