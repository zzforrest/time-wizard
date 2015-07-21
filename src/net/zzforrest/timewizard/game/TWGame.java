package net.zzforrest.timewizard.game;

import net.zzforrest.base.Game;
import net.zzforrest.base.entity.Entity;
import net.zzforrest.base.entity.component.*;

/*
 * Time Wizard game
 */
public class TWGame extends Game
{
	public TWGame()
	{
		super();
		
		Entity player = new Entity(scene)
			.addComponent(new DrawHitboxComponent())
			.addComponent(new ControllerComponent(100));
		player.setX(100);
		player.setY(100);
		player.setW(32);
		player.setH(32);
		
		scene.addEntity(player);
	}
}