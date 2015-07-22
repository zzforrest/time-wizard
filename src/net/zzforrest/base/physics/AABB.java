package net.zzforrest.base.physics;

public class AABB
{
	/*
	 * x - x coordinate
	 * y - y coordinate
	 * w - width
	 * h - height
	 */
	private float x;
	private float y;
	private float w;
	private float h;
	
	public AABB(float x, float y, float w, float h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public AABB(float x, float y)
	{
		this(x, y, 0, 0);
	}
	
	public AABB()
	{
		this(0, 0, 0, 0);
	}
	
	/*
	 * Transform methods
	 */
	public void moveX(float x)
	{
		this.x += x;
	}
	
	public void moveY(float y)
	{
		this.y += y;
	}
	
	public void move(float x, float y)
	{
		this.x += x;
		this.y += y;
	}
	
	/*
	 * Immutable transform methods
	 */
	public AABB translatedX(float x)
	{
		return new AABB(this.x + x, y, w, h);
	}
	
	public AABB translatedY(float y)
	{
		return new AABB(x, this.y + y, w, h);
	}
	
	public AABB translated(float x, float y)
	{
		return new AABB(this.x + x, this.y + y, w, h);
	}
	
	public boolean colliding(AABB aabb)
	{
		if(getLeft() >= aabb.getRight())
			return false;
		if(aabb.getLeft() >= getRight())
			return false;
		if(getTop() >= aabb.getBottom())
			return false;
		if(aabb.getTop() >= getBottom())
			return false;
		
		return true;
	}
	
	/*
	 * Convenience methods
	 */
	/**
	 * @return the left edge
	 */
	public float getLeft()
	{
		return x;
	}
	
	/**
	 * @return the right edge
	 */
	public float getRight()
	{
		return x + w;
	}
	
	/**
	 * @return the top edge
	 */
	public float getTop()
	{
		return y;
	}
	
	/**
	 * @return the bottom edge
	 */
	public float getBottom()
	{
		return y + h;
	}
	
	/*
	 * Getters and setters
	 */
	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public float getW()
	{
		return w;
	}

	public void setW(float w)
	{
		this.w = w;
	}

	public float getH()
	{
		return h;
	}

	public void setH(float h)
	{
		this.h = h;
	}
}