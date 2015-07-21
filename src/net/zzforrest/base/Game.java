package net.zzforrest.base;

import java.awt.Graphics;

public class Game
{
	protected Scene scene;
	
	public Game()
	{
		scene = new Scene();
	}
	
	public void input(Input input)
	{
		scene.input(input);
	}
	
	public void update()
	{
		scene.update();
	}
	
	public void render(Graphics g)
	{
		scene.render(g);
	}
}