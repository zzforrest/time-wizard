package net.zzforrest.base;

import java.awt.Graphics;

public class Game
{
	protected Scene scene;
	
	public Game()
	{
		scene = new Scene();
	}
	
	public void update(Input input)
	{
		scene.update(input);
	}
	
	public void render(Graphics g)
	{
		scene.render(g);
	}
}