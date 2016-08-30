package State;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.*;
import NewMenu.Buttons.*;

public class PauseState extends State
{
    int base_y = Game.app.getHeight() / 6;
    public MenuButton buttons [] = {new ResumeButton(0, base_y), 
    		new MapButton(0, base_y + (Game.app.getHeight() / 16) + 16), 
    		new SettingsButton(0, base_y + (((Game.app.getHeight() / 16) + 16) * 2)),
    		new ControlsButton(0, base_y + (((Game.app.getHeight() / 16) + 16) * 3)),
    		new ExitButton(0, base_y + (((Game.app.getHeight() / 16) + 16) * 4))};
    
	public PauseState()
	{
		setName("PAUSE");
		background = new Rectangle(0, -Game.app.getHeight(), Game.app.getWidth(), Game.app.getHeight()); 
		BASE_HEIGHT = Game.app.getHeight();
	}
	public void update(double delta)
	{
		//Display.getMouse().update();
		
		if(is_open_animating)
		{	
			if(background.getY() < 0)
			{
				background = new Rectangle(background.getX(), background.getY() + (background.getHeight() / 15), background.getWidth(), background.getHeight());
				
				for(int a = 0; a < buttons.length; a++)
					buttons[a].buildBody();
			}
			else
			{
				background = new Rectangle(0, 0, background.getWidth(), background.getHeight());
				is_open_animating = false;
			}
		}
		
		for(int a = 0; a < buttons.length; a++)
		{
			buttons[a].update(delta);
		}
		
		Game.getPlayState().setSubState(new State());
		
		if(Game.getState() != this)
		{
			setSubState(new State());
		}
		
		if(getSubState() != null)
			getSubState().update(delta);
	}
	
	public void render(Graphics g)
	{
		g.setColor(new Color(239,239,239));
		g.fill(background);
		
		for(int a = 0; a < buttons.length; a++)
			buttons[a].render(g);

		if(getSubState() != null)
			getSubState().render(g);
	}
	
	public void setBackground(Rectangle r)	
	{
		background = r;
		
		for(int a = 0; a < buttons.length; a++)
			buttons[a].buildBody();
	}

	public void openAnim()
	{
		is_open_animating = true;
		
		for(int a = 0; a < buttons.length; a++)
		{
			buttons[a].buildBody();
		}
	}
}