package net.zzforrest.base;

import java.awt.Graphics;

public class Game
{
	protected Menu menu;
	protected Scene scene;
	
	public Game()
	{
		menu = null;
		scene = new Scene();
	}
	
	public void update(Input input)
	{
		if(menu != null)
		{
			menu.update(input);
			return;
		}
		
		scene.update(input);
	}
	
	public void render(Graphics g)
	{
		if(menu != null)
		{
			menu.render(g);
			return;
		}
		
		scene.render(g);
	}
	
	public void setMenu(Menu menu)
	{
		this.menu = menu;
	}
}