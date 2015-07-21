package net.zzforrest.base;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JComponent;

/*
 * A class to easily encapsulate all basic inputs
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener, FocusListener
{
	/*
	 * Constants
	 * 
	 * KEY_CODES     - number of virtual keys accepted
	 * MOUSE_BUTTONS - number of mouse buttons accepted
	 */
	public static final int KEY_CODES     = 256;
	public static final int MOUSE_BUTTONS = 3;
	
	/*
	 * Key information
	 * 
	 * keysDown - keys currently pressed
	 * keysLast - keys pressed in the last cycle
	 */
	private boolean[] keysDown;
	private boolean[] keysLast;
	
	/*
	 * Mouse information
	 * 
	 * mouseDown     - mouse buttons currently pressed
	 * mouseLast     - mouse buttons pressed in the last cycle
	 * mouseInWindow - true if the mouse is in the window
	 * mouseX        - x coordinate of mouse relative to top left corner
	 * mouseY        - y coordinate of mouse relative to top left corner
	 */
	private boolean[] mouseDown;
	private boolean[] mouseLast;
	private boolean mouseInWindow = true;
	private int mouseX;
	private int mouseY;
	
	/*
	 * Focus information
	 * 
	 * focused - true if the window is the current focus
	 */
	private boolean focused;
	
	/**
	 * Constructor which adds itself to a component
	 * 
	 * @param component
	 * 			JComponent to which the input automatically adds all necessary listeners
	 */
	public Input(JComponent component)
	{
		/*
		 * Initialize Key Listener data
		 */
		keysDown = new boolean[KEY_CODES];
		keysLast = new boolean[KEY_CODES];
		
		/*
		 * Initialize Mouse Listener data
		 */
		mouseDown = new boolean[MOUSE_BUTTONS];
		mouseLast = new boolean[MOUSE_BUTTONS];
		
		/*
		 * Makes component focusable, then adds this as listeners for appropriate events
		 */
		component.setFocusable(true);
		component.addKeyListener(this);
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
		component.addFocusListener(this);
	}
	
	/*
	 * Access to information
	 */
	
	/*
	 * Key information
	 */
	/**
	 * @param key The index corresponding to a key on the virtual keyboard
	 * @return true if a key is pressed down, or false if it is not pressed down or outside the range of [0, KEY_CODES - 1]
	 */
	public boolean getKey(int key)
	{
		if(key >= KEY_CODES || key < 0)
			return false;
		return keysDown[key];
	}
	
	/**
	 * @param key The index corresponding to a key on the virtual keyboard
	 * @return true if a key has been pressed down in the last update cycle,
	 * 	or false if it is not pressed down or outside the range of [0, KEY_CODES - 1]
	 */
	public boolean getKeyPressed(int key)
	{
		if(key >= KEY_CODES || key < 0)
			return false;
		return !keysLast[key] && keysDown[key];
	}
	
	/**
	 * @param key The index corresponding to a key on the virtual keyboard
	 * @return true if a key has been released in the last update cycle,
	 * 	or false if it is pressed down or outside the range of [0, KEY_CODES - 1]
	 */
	public boolean getKeyReleased(int key)
	{
		if(key >= KEY_CODES || key < 0)
			return false;
		return keysLast[key] && !keysDown[key];
	}
	
	/*
	 * Mouse information
	 */
	/**
	 * @param button The index corresponding to a button on the virtual mouse
	 * @return true if the mouse button is down, or false if it is outside the range of [0, MOUSE_BUTTONS - 1]
	 */
	public boolean getMouseDown(int button)
	{
		if(button < 0 || button >= MOUSE_BUTTONS)
			return false;
		return mouseDown[button];
	}
	
	/**
	 * @param button The index corresponding to a button on the virtual mouse
	 * @return true if the mouse button has been pressed during the last update cycle,
	 *  or false if it is outside the range of [0, MOUSE_BUTTONS - 1]
	 */
	public boolean getMousePressed(int button)
	{
		if(button < 0 || button >= MOUSE_BUTTONS)
			return false;
		return !mouseLast[button] && mouseDown[button];
	}
	
	/**
	 * @param button The index corresponding to a button on the virtual mouse
	 * @return true if the mouse button has been released during the last update cycle,
	 *  or false if it is outside the range of [0, MOUSE_BUTTONS - 1]
	 */
	public boolean getMouseReleased(int button)
	{
		if(button < 0 || button >= MOUSE_BUTTONS)
			return false;
		return mouseLast[button] && !mouseDown[button];
	}
	
	/**
	 * @return true if the mouse is currently inside the window
	 */
	public boolean isMouseInWindow()
	{
		return mouseInWindow;
	}
	
	/**
	 * @return x coordinate of mouse
	 */
	public int getMouseX()
	{
		return mouseX;
	}
	
	/**
	 * @return y coordinate of mouse
	 */
	public int getMouseY()
	{
		return mouseY;
	}
	
	/*
	 * Focus information
	 */
	/**
	 * @return true if the window is the current focus
	 */
	public boolean isFocused()
	{
		return focused;
	}
	
	/*
	 * 
	 */
	
	/**
	 * Updates the arrays so that pressed/released functions work
	 */
	public void update()
	{
		keysLast  = Arrays.copyOf(keysDown, keysDown.length);  // Why must arrays be difficult
		mouseLast = Arrays.copyOf(mouseDown, mouseDown.length);
	}
	
	/*
	 * Listener functions
	 */
	
	/*
	 * Key Listener functions
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key >= 0 && key < KEY_CODES)
			keysDown[key] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key >= 0 && key < KEY_CODES)
			keysDown[key] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {} // Ignored because it's useless
	
	/*
	 * Mouse Listener functions
	 */
	@Override
	public void mouseClicked(MouseEvent e) {} // Ignored because it's useless

	@Override
	public void mouseEntered(MouseEvent e)
	{
		mouseInWindow = true;
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		mouseInWindow = false;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int button = e.getButton() - 1;				//- 1 because when e.getButton = 0, that means nothing is pressed, which means there
													//	are a lot of problems right now
		if(button >= 0 && button < MOUSE_BUTTONS)
			mouseDown[button] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		int button = e.getButton() - 1;
		if(button >= 0 && button < MOUSE_BUTTONS)
			mouseDown[button] = true;
	}
	
	/*
	 * Mouse Motion Listener functions
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	/*
	 * Focus Listener functions
	 */
	@Override
	public void focusGained(FocusEvent e)
	{
		focused = true;
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		focused = false;
	}	
}