package net.zzforrest.base;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Main window to display content
 */

public class MainComponent extends JPanel implements Runnable
{
	/*
	 * To make Java happy :)
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Width and height of window
	 */
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	
	/*
	 * FPS and delta
	 * delta is used to normalize velocities
	 */
	public static final float FPS = 60.0f;
	public static final float DELTA = 1 / FPS;
	
	/*
	 * Variables for threading and main loop
	 */
	private Thread thread;
	private boolean running = false;
	
	/*
	 * JFrame
	 */
	private JFrame frame;
	
	/*
	 * Game info
	 */
	private Input input;
	private Game game;
	
	/**
	 * A JPanel which will maintain and render a given Game object.
	 * Creates it's own JFrame.
	 * 
	 * @param game
	 * 			Game object to be maintained and rendered
	 */
	public MainComponent(Game game)
	{
		super();
		
		/*
		 * Initialize game data
		 */
		input = new Input(this);
		this.game = game;
		
		/*
		 * Initialize the JPanel portion of MainComponent
		 */
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		
		/*
		 * Create and display JFrame
		 */
		frame = new JFrame("TITLE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Initializes and starts thread
	 */
	public void start()
	{
		if(running)
			return;
		running = true;
		
		thread = new Thread(this);
		thread.run();
	}
	
	/**
	 * Closes thread
	 */
	public void stop()
	{
		if(!running)
			return;
		running = false;
		
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Main loop where everything happens
	 * Calls stop() at the end to ensure threads are closed
	 */
	@Override
	public void run()
	{
		/*
		 * Frame counter information
		 */
		int frames = 0;
		long last = System.currentTimeMillis();
		
		/*
		 * Main game loop
		 */
		while(running)
		{
			/*
			 * Game logic
			 */
			game.update();
			game.input(input);
			
			input.update();
			repaint();
			
			/*
			 * Thread sleep as frame timer
			 */
			try
			{
				Thread.sleep((long)(1000.0f / FPS));
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			/*
			 * Frame counter
			 */
			frames ++;
			
			if(System.currentTimeMillis() - last >= 1000)
			{
				last += 1000;
				
				System.out.println(frames + "fps");
				frames = 0;
			}
		}
		
		stop();
	}
	
	/**
	 * Paint method
	 * Everything is rendered here
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		game.render(g);
	}
}