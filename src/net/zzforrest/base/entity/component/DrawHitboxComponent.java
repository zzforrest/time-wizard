package net.zzforrest.base.entity.component;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Draws a rectangle showing the entity's hitbox
 * Or at least it tries.  Awt's rectangles sure are awtful
 */
public class DrawHitboxComponent extends Component
{
	private Color color;
	
	public DrawHitboxComponent()
	{
		color = Color.black;
	}
	
	public DrawHitboxComponent(Color color)
	{
		this.color = color;
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(color);
		g.drawRect((int)parent.getAABB().getX(), (int)parent.getAABB().getY(), (int)parent.getAABB().getW(), (int)parent.getAABB().getH());
	}
}