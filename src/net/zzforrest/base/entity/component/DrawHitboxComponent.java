package net.zzforrest.base.entity.component;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Draws a rectangle showing the entity's hitbox
 * Or at least it tries.  Awt's rectangles sure are awtful
 */
public class DrawHitboxComponent extends Component
{
	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect((int)parent.getX(), (int)parent.getY(), (int)parent.getW(), (int)parent.getH());
	}
}