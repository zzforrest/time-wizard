package net.zzforrest.timewizard.game;

import java.awt.Color;

import net.zzforrest.base.Game;
import net.zzforrest.base.MainComponent;
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
			.addComponent(new ControllerComponent(100))
			;
		player.getAABB().move(100, 100);
		player.getAABB().setW(32);
		player.getAABB().setH(32);
		
		for(int i = 0; i < 175; i ++)
		{
			Entity object = new Entity(scene)
				.addComponent(new DrawHitboxComponent(new Color((float)Math.random(), (float)Math.random(), (float)Math.random())))
				.addComponent(new BasicAIComponent())
				.addFlag(Entity.FLAG_SOLID)
				;
			object.getAABB().move((float)Math.random() * (800 - 20), (float)Math.random() * (600 - 20));
			object.getAABB().setW(20);
			object.getAABB().setH(20);
			
			scene.addEntity(object);
		}
		
		/*
		 * border the screen
		 */
		Entity left = new Entity(scene).addFlag(Entity.FLAG_SOLID);
		left.getAABB().move(-1, 0);
		left.getAABB().setW(1);
		left.getAABB().setH(MainComponent.WINDOW_HEIGHT);
		
		Entity right = new Entity(scene).addFlag(Entity.FLAG_SOLID);
		right.getAABB().move(MainComponent.WINDOW_WIDTH, 0);
		right.getAABB().setW(1);
		right.getAABB().setH(MainComponent.WINDOW_HEIGHT);
		
		Entity top = new Entity(scene).addFlag(Entity.FLAG_SOLID);
		top.getAABB().move(0, -1);
		top.getAABB().setW(MainComponent.WINDOW_WIDTH);
		top.getAABB().setH(1);
		
		Entity bottom = new Entity(scene).addFlag(Entity.FLAG_SOLID);
		bottom.getAABB().move(0, MainComponent.WINDOW_HEIGHT);
		bottom.getAABB().setW(MainComponent.WINDOW_WIDTH);
		bottom.getAABB().setH(1);
		
		scene.addEntity(left);
		scene.addEntity(right);
		scene.addEntity(top);
		scene.addEntity(bottom);
		
//		scene.addEntity(player);
	}
}