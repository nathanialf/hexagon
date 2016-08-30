package State;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Main.Game;

public class State 
{
	private static String name;

	public Rectangle background;

	public boolean is_open_animating = false;
	
	public int BASE_HEIGHT;
	
	State sub_state = null;
    
   // static MenuPane current_pane = new MenuPane();
	
	public State s = null;
	
	public State()
	{
		name = "";
	}
	
	public void render(Graphics g)
	{

		if(getSubState() != null)
			getSubState().render(g);
	}
	
	public void update(double delta)
	{
		//Display.getMouse().update();
		
		if(is_open_animating)
		{	
			if(background.getY() < 0)
			{
				background = new Rectangle(background.getX(), background.getY() + (background.getHeight() / 30), background.getWidth(), background.getHeight());
			}
			else
			{
				background = new Rectangle(0, 0, background.getWidth(), background.getHeight());
				is_open_animating = false;
			}
		}
		
		if(getSubState() != null)
			getSubState().update(delta);
	}

	public void openAnim()
	{
		is_open_animating = true;
	}
	
	@SuppressWarnings("static-access")
	public String getName()							{return this.name;}
	public Rectangle getBackground()				{return this.background;}
	public State getSubState()						{return this.sub_state;}
	//public MenuPane getPane()						{return current_pane;}
	
	@SuppressWarnings("static-access")
	public void setName(String n)					{this.name = n;}
	public void setBackground(Rectangle r)			{background = r;}
	public void setSubState(State s)		
	{
		State old = new State();
		
		old = sub_state;
		
		Rectangle newBody = new Rectangle(Game.app.getWidth() / 3, -BASE_HEIGHT, Game.app.getWidth(), BASE_HEIGHT);

		
		sub_state = s;
		sub_state.setBackground(newBody);
		sub_state.openAnim();
		
		newBody = new Rectangle(Game.app.getWidth() / 3, -sub_state.BASE_HEIGHT, Game.app.getWidth(), sub_state.BASE_HEIGHT);

		if(old != null)
			old.setBackground(newBody);
	}
	
	/*
	public void setPane(MenuPane p)		
	{
		MenuPane old = current_pane;
		
		current_pane = p;
		current_pane.openAnim();
		
		Rectangle2D.Double newBody = new Rectangle2D.Double(0, -Display.HEIGHT, Display.WIDTH, Display.HEIGHT);
		
		old.setBody(newBody);
	}
	*/
}
