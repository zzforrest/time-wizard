package net.zzforrest.timewizard.game;

import net.zzforrest.base.MainComponent;

/*
 * Entry point for the program
 * Only purpose is to start the program with the
 * 	Time Wizard game info
 * Feels lonely and only wants your love
 */
public class Main
{
	public static void main(String[] args)
	{
		new MainComponent(new TWGame()).start();
	}
}