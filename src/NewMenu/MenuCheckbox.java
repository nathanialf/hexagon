package NewMenu;

import javax.swing.JComponent;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Main.*;

public class MenuCheckbox extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int x;
	private int y;
	private int WIDTH;
	private int HEIGHT;
	
	private int checked;
	
	private String text;
	
	protected Rectangle body;
	protected Rectangle box = new Rectangle(0,0,0,0);
	
	public Color MAIN = new Color(100,175,100);
	public Color HOVER = new Color(75, 131, 75);
	public Color CLICK = new Color(50, 87, 50);
	Color c = MAIN;
	
	int checkbox_location[][] = {
		{0, 0,16,16},
		{16,0,32,16}
	};
	
	boolean is_color_animating = false;
	Color newColor = Color.black;
	
	boolean canDoAction = true;
	
	public MenuCheckbox()
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

				if(box.contains(Display.getMouseMotion().getX(), Display.getMouseMotion().getY()) && canDoAction)
				{
					//setValue((int)((Display.getMouseMotion().getX() - slide.getX()) / slide.getWidth() * 100));
					if(isChecked() == 0)
						setChecked(1);
					else
						setChecked(0);
					
					doAction();
					
					canDoAction = false;
				}
			}
			else
			{
				setColor(HOVER);
				canDoAction = true;
			}
		}
		else
		{
			setColor(MAIN);
		}
		*/
		if(is_color_animating)
		{
			animateColor(newColor);
		}
		
		buildBox();
	}
	
	public void render(Graphics g)
	{
		g.setFont(Game.MEDIUM_FONT);
		g.setColor(c);
		g.fill(body);
		
		g.setColor(Color.white);
		g.drawString(getText(), (int)(body.getX() + (getWidth() * .05)), (int) (body.getY() + (body.getHeight() / 2) + (Game.MEDIUM_FONT.getLineHeight()/2)));
		
		g.setColor(new Color(255,255,255,50));
		g.fillRect((int) (body.getX() + (getWidth() - (getWidth() / 40))), (int) body.getY(), getWidth() / 40, getHeight());
/*
		if(getSubState() != null)
			getSubState().render(g);*/
		
		/*
		Image img1 = Toolkit.getDefaultToolkit().getImage("res/ui/uisheet.png");
		g.drawImage(img1,(int) (box.getX()), (int) (box.getY()), (int) (box.getX() + box.getWidth()), (int) (box.getY() + box.getHeight()), checkbox_location[isChecked()][0], checkbox_location[isChecked()][1],  checkbox_location[isChecked()][2], checkbox_location[isChecked()][3], this);
	    //g.drawImage(img1, getX(), (int)body.getY(), getWidth(), getHeight(), this);
	    g.finalize();
	    */
	}

	public void buildBody()
	{
		body = new Rectangle(Game.getState().getBackground().getX() + getX(), Game.getState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void buildBox()
	{
		/*
		double sX = getX() + (getWidth() * .05);
		double sY = body.getY() + (getHeight() / 2);
		double sW = (getWidth() * .9);
		double sH = getHeight() / 5;
		
		slide = new Rectangle2D.Double(sX, sY, sW, sH);
		*/
		box =  new Rectangle((float)(body.getX() + (getWidth() / 2.1)), (float) (body.getY() + (getHeight() / 2.75)), (float)(getHeight() / 3), (float)(getHeight() / 3));
	}
	
	public void doAction()
	{
	}
	
	public int getX()						{return this.x;}
	public int getY()						{return this.y;}
	public int getWidth()					{return this.WIDTH;}
	public int getHeight()					{return this.HEIGHT;}
	public int isChecked()					{return this.checked;}
	public String getText()					{return this.text;}
	//public State getSubState()			{return this.sub_state;}

	public void setChecked(int v)			{this.checked = v;}
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