package net.zzforrest.base;

import java.awt.Graphics;

/**
 * @author Blake
 * 
 * Overrides a game's functionality, typically used for a menu of sorts
 */
public class Menu
{
	/*
	 * Menu information
	 * 
	 * game   - game which invoked this menu
	 * parent - previous menu.  If null, back() will return to the game
	 */
	protected Game game;
	protected Menu parent;
	
	public Menu(Game game, Menu parent)
	{
		this.game = game;
		this.parent = parent;
	}
	
	public void update(Input input)
	{
		
	}
	
	public void render(Graphics g)
	{
		
	}
	
	public void back()
	{
		game.setMenu(parent);
	}
}