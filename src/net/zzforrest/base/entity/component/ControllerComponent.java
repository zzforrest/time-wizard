package net.zzforrest.base.entity.component;

import com.sun.glass.events.KeyEvent;

import net.zzforrest.base.Input;

/*
 * Extremely barebones controller which allows for movement in 4 directions
 * Will probably be taken in the next great culling
 * TODO : annihilate
 */
public class ControllerComponent extends Component
{
	protected float speed;
	
	public ControllerComponent(float speed)
	{
		this.speed = speed;
	}
	
	@Override
	public void input(Input input)
	{
		int impulsex = 0;
		int impulsey = 0;
		
		if(input.getKey(KeyEvent.VK_LEFT))
			impulsex --;
		if(input.getKey(KeyEvent.VK_RIGHT))
			impulsex ++;
		if(input.getKey(KeyEvent.VK_UP))
			impulsey --;
		if(input.getKey(KeyEvent.VK_DOWN))
			impulsey ++;
		
		parent.setXvel(speed * impulsex);
		parent.setYvel(speed * impulsey);
	}
}