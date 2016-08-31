package NewMenu;

import javax.swing.JComponent;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Main.*;

public class MenuSlider extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int x;
	private int y;
	private int WIDTH;
	private int HEIGHT;
	
	private double value;
	private double MIN_VALUE;
	private double MAX_VALUE;
	
	private String text;
	
	protected Rectangle body;
	protected Rectangle slide = new Rectangle(0,0,0,0);
	
	public Color MAIN = new Color(100,175,100);
	public Color HOVER = new Color(75, 131, 75);
	public Color CLICK = new Color(50, 87, 50);
	Color c = MAIN;
	
	boolean is_color_animating = false;
	Color newColor = Color.black;
	
	public MenuSlider()
	{
		
	}
	
	public void update(double delta)
	{
		/*
		if(body.contains(Display.getMouseMotion().getX(), Display.getMouseMotion().getY()))
		{
			if(Display.getMouse().getLeftClicked())
			{
				//setColor(CLICK);

				if(slide.contains(Display.getMouseMotion().getX(), Display.getMouseMotion().getY()))
				{
					setValue((int)((Display.getMouseMotion().getX() - slide.getX()) / slide.getWidth() * 100));
					
					doAction();
				}
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
			animateColor(newColor);
		}
		*/
		buildSlider();
	}
	
	public void render(Graphics g)
	{
		g.setFont(Game.SMALL_FONT);
		g.setColor(c);
		g.fill(body);
		
		g.setColor(Color.white);
		g.drawString(getText() + ": " + (int)(((getValue() - getMinimumValue()) / getMaximumValue())* 100) + "%", (int) (getX() + (body.getHeight() / 3)), (int) (body.getY() + (body.getHeight() / 4) + (Game.SMALL_FONT.getLineHeight()/2)));
		
		g.setColor(new Color(255,255,255,50));
		g.fillRect(getX() + (getWidth() - (getWidth() / 40)), (int) body.getY(), getWidth() / 40, getHeight());
		g.fill(slide);
		g.fillRect((int)(getX() + (getWidth() * .05)), (int) body.getY() + (getHeight() / 2), (int) (((getValue() - getMinimumValue()) / getMaximumValue()) * ((getWidth() * .9))), getHeight() / 5);
	}
	
	public void buildBody()
	{
		body = new Rectangle(Game.getState().getBackground().getX() + getX(), Game.getState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void buildSlider()
	{
		/*
		double sX = getX() + (getWidth() * .05);
		double sY = body.getY() + (getHeight() / 2);
		double sW = (getWidth() * .9);
		double sH = getHeight() / 5;
		
		slide = new Rectangle2D.Double(sX, sY, sW, sH);
		*/
		slide =  new Rectangle((int)(getX() + (getWidth() * .05)), (int) body.getY() + (getHeight() / 2), (int) (getWidth() * .9), getHeight() / 5);
	}
	
	public void doAction()
	{
	}
	
	public int getX()						{return this.x;}
	public int getY()						{return this.y;}
	public int getWidth()					{return this.WIDTH;}
	public int getHeight()					{return this.HEIGHT;}
	public double getValue()				{return this.value;}
	public double getMinimumValue()			{return this.MIN_VALUE;}
	public double getMaximumValue()			{return this.MAX_VALUE;}
	public String getText()					{return this.text;}
	//public State getSubState()			{return this.sub_state;}

	public void setValue(double v)			{this.value = v;}
	public void setMinimumValue(double v)	{this.MIN_VALUE = v;}
	public void setMaximumValue(double v)	{this.MAX_VALUE = v;}
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
	
	public void animateColor(Color newColor)
	{
		Color temp = Color.black;
		int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
		
		if(newColor == c)
		{
			is_color_animating = false;
			return;
		}

		if(newColor.getRed() > c.getRed())
			r+=1;
		else if(newColor.getRed() < c.getRed())
			r-=1;

		if(newColor.getGreen() > c.getGreen())
			g+=1;
		else if(newColor.getGreen() < c.getGreen())
			g-=1;

		if(newColor.getBlue() > c.getBlue())
			b+=1;
		else if(newColor.getBlue() < c.getBlue())
			b-=1;
			
		temp = new Color(r,g,b);
		c = temp;
	}
}