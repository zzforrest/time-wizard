package net.zzforrest.timewizard.game;

import net.zzforrest.base.Input;
import net.zzforrest.base.entity.component.Component;

public class BasicAIComponent extends Component
{
	private int ximpulse;
	private int yimpulse;
	
	@Override
	public void update(Input input)
	{
		if(Math.random() < 0.01)
		{
			ximpulse = (int)(Math.random() * 3) - 1;
			yimpulse = (int)(Math.random() * 3) - 1;
		}
		
		parent.setXvel(ximpulse * 30);
		parent.setYvel(yimpulse * 30);
	}
}