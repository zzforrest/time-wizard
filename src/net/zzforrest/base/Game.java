package net.zzforrest.base;

import java.awt.Graphics;

public abstract class Game
{
	public Game()
	{
		
	}
	
	public abstract void input(Input input);
	public abstract void update();
	public abstract void render(Graphics g);
}