package NewMenu;

import javax.swing.JComponent;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import Main.*;

public class MenuInformation extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int x;
	private int y;
	private int WIDTH;
	private int HEIGHT;
	
	private String text;
	
	protected Rectangle body;
	
	public Color MAIN = new Color(100,175,100);
	public Color HOVER = new Color(75, 131, 75);
	public Color CLICK = new Color(50, 87, 50);
	public Color c = MAIN;
	
	boolean is_color_animating = false;
	Color newColor = Color.black;
	
	//State sub_state = new State();


	//public State s;
	
	public MenuInformation()
	{
	}
	
	public void update(GameContainer gc, double delta)
	{
		
		if(body.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY()))
		{
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				setColor(CLICK);
			}
			else
			{
				setColor(HOVER);
			}
		}
		else
		{
			setColor(MAIN);
		}
		
		
		if(is_color_animating)
		{
			animateColor(newColor,delta);
		}
		/*
		if(getSubState() != null)
			getSubState().update(delta);*/
	}
	
	public void render(Graphics g)
	{
		//g.setFont(Game.MEDIUM_FONT);
		g.setColor(c);
		g.fill(body);
		
		g.setColor(Color.white);
		g.drawString(getText(), (int)(getX() + (getWidth() * .05)), (int)(body.getY() + (body.getHeight() / 2) + (6/2)));
		
		g.setColor(new Color(255,255,255,50));
		g.fillRect(getX() + (getWidth() - (getWidth() / 40)), (int) body.getY(), getWidth() / 40, getHeight());

		/*if(getSubState() != null)
			getSubState().render(g);*/
	}
	
	public void buildBody()
	{
		body = new Rectangle(Game.getState().getBackground().getX() + getX(), Game.getState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public int getX()				{return this.x;}
	public int getY()				{return this.y;}
	public int getWidth()			{return this.WIDTH;}
	public int getHeight()			{return this.HEIGHT;}
	public String getText()			{return this.text;}
	//public State getSubState()			{return this.sub_state;}

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
/*	public void setSubState(State s)		
	{
		State old = new State();
		
		old = sub_state;
		
		sub_state = s;
		sub_state.openAnim();
		
		Rectangle2D.Double newBody = new Rectangle2D.Double(Display.WIDTH / 3, -sub_state.BASE_HEIGHT, Display.WIDTH, sub_state.BASE_HEIGHT);

		old.setBackground(newBody);
	}*/
	public void setText(String s)			{text = s;}
	
	public void setColor(Color c)			
	{
		newColor = c;
		is_color_animating = true;
	}
	
	public void animateColor(Color newColor, double delta)
	{
		Color temp = Color.black;
		int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
		
		if(newColor == c)
		{
			is_color_animating = false;
			return;
		}

		if(newColor.getRed() > c.getRed())
			r+= .5 * delta;
		else if(newColor.getRed() < c.getRed())
			r-= .5 * delta;

		if(newColor.getGreen() > c.getGreen())
			g+= .5 * delta;
		else if(newColor.getGreen() < c.getGreen())
			g-= .5 * delta;

		if(newColor.getBlue() > c.getBlue())
			b+= .5 * delta;
		else if(newColor.getBlue() < c.getBlue())
			b-= .5 * delta;
			
		temp = new Color(r,g,b);
		c = temp;
	}
}
