package net.zzforrest.base.entity.component;

import java.awt.Graphics;

import net.zzforrest.base.Input;
import net.zzforrest.base.entity.Entity;

public class Component
{
	protected Entity parent;
	
	public Component()
	{
		
	}
	
	public void update(Input input)
	{
		
	}
	
	public void render(Graphics g)
	{
		
	}
	
	public void setParent(Entity parent)
	{
		this.parent = parent;
	}
}