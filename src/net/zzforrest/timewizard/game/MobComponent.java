package net.zzforrest.timewizard.game;

import net.zzforrest.base.Input;
import net.zzforrest.base.entity.component.Component;

/*
 * Gives information and functions for things like health
 */
public class MobComponent extends Component
{
	/*
	 * Health information
	 * 
	 * maxhealth - maximum possible health. If maxhealth = -1, it's effects don't apply
	 * health    - current health
	 */
	private float maxhealth;
	private float health;
	
	public MobComponent(float health)
	{
		this.maxhealth = health;
		this.health    = health;
	}
	
	@Override
	public void update(Input input)
	{
		if(health < 0)
			parent.die();
	}
	
	public void hurt(float amount)
	{
		health -= amount;
	}
	
	public void heal(float amount)
	{
		health += amount;
		
//		if(health > maxhealth)
//			health = maxhealth;
	}
}