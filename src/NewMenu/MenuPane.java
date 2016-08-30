package NewMenu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.game.main.Display;

public class MenuPane 
{
	private int x;
	private int y;
	private int WIDTH;
	private int HEIGHT;
	
	protected Rectangle2D body;
	
	public Color MAIN = Color.RED;
	Color c = MAIN;

	public boolean is_open_animating = false;
	
	public MenuPane()
	{
		
	}
	
	public void update(double delta)
	{
		if(is_open_animating)
		{	
			if(body.getY() < 0)
			{
				body.setRect(body.getX(), body.getY() + (body.getHeight() / 30), body.getWidth(), body.getHeight());
			}
			else
			{
				body.setRect(body.getX(), body.getY(), body.getWidth(), body.getHeight());
				is_open_animating = false;
			}
		}
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(c);
		g.fill(body);
	}
	
	public void buildBody()
	{
		body = new Rectangle2D.Double(Display.getState().getBackground().getX() + getX(), Display.getState().getBackground().getY() + getY(), getWidth(), getHeight());
	}

	
	public int getX()				{return this.x;}
	public int getY()				{return this.y;}
	public int getWidth()			{return this.WIDTH;}
	public int getHeight()			{return this.HEIGHT;}

	protected void setX(int x)		
	{
		this.x = x;
		buildBody();
	}
	protected void setY(int y)		
	{
		this.y = y;
		buildBody();
	}
	protected void setWidth(int w)	
	{
		this.WIDTH = w;
		buildBody();
	}
	protected void setHeight(int h)	
	{
		this.HEIGHT = h;
		buildBody();
	}
	public void setBody(Rectangle2D.Double r)	{body = r;}

	public void openAnim()
	{
		is_open_animating = true;
	}
}
