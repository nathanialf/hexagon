package State;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.*;
import NewMenu.Buttons.*;
import NewMenu.Checkboxes.*;
import NewMenu.Sliders.*;

public class ControlsState extends State
{
    int base_y = Game.app.getHeight() / 6;
    MenuButton buttons [] = {new BackButton(Game.app.getWidth() / 3, base_y)};
    MenuSlider sliders [] = {};
    MenuCheckbox checkboxes [] =  {};
    
	public ControlsState()
	{
		setName("CONTROLS");
		background = new Rectangle(Game.app.getWidth() / 3, -Game.app.getHeight(), Game.app.getWidth(), Game.app.getHeight()); 
		BASE_HEIGHT = Game.app.getHeight();
	}
	public void update(GameContainer gc, double delta)
	{
		//Display.getMouse().update();
		
		if(is_open_animating)
		{
			if(background.getY() < 0)
			{
				background = new Rectangle(background.getX(),(float) (background.getY() + (background.getHeight() / 165) * (.5 * delta)), background.getWidth(), background.getHeight());

				for(int a = 0; a < buttons.length; a++)
					buttons[a].buildBody();
				for(int a = 0; a < sliders.length; a++)
					sliders[a].buildBody();
				for(int a = 0; a < checkboxes.length; a++)
					checkboxes[a].buildBody();
			}
			else
			{
				background = new Rectangle(background.getX(), 0, background.getWidth(), background.getHeight());
				is_open_animating = false;
				for(int a = 0; a < buttons.length; a++)
					buttons[a].buildBody();
				for(int a = 0; a < sliders.length; a++)
					sliders[a].buildBody();
				for(int a = 0; a < checkboxes.length; a++)
					checkboxes[a].buildBody();
			}
		}

		for(int a = 0; a < buttons.length; a++)
			buttons[a].update(gc, delta);
		for(int a = 0; a < sliders.length; a++)
			sliders[a].update(gc, delta);
		for(int a = 0; a < checkboxes.length; a++)
			checkboxes[a].update(gc, delta);
	}
	
	public void render(Graphics g)
	{
		//g.setFont(Game.BIG_FONT);
		
		g.setColor(new Color(239,239,239));
		g.fill(background);
		g.setColor(new Color(75, 131, 75));
		g.drawString("CONTROLS", (int) background.getX(), (int) (background.getY() + background.getHeight() / 8));

		for(int a = 0; a < buttons.length; a++)
			buttons[a].render(g);
		for(int a = 0; a < sliders.length; a++)
			sliders[a].render(g);
		for(int a = 0; a < checkboxes.length; a++)
			checkboxes[a].render(g);
	}
	
	public void setBackground(Rectangle r)	
	{
		background = r;
		
		for(int a = 0; a < buttons.length; a++)
			buttons[a].buildBody();
		for(int a = 0; a < sliders.length; a++)
			sliders[a].buildBody();
		for(int a = 0; a < checkboxes.length; a++)
			checkboxes[a].buildBody();
	}

	public void openAnim()
	{
		is_open_animating = true;

		for(int a = 0; a < buttons.length; a++)
			buttons[a].buildBody();
		for(int a = 0; a < sliders.length; a++)
			sliders[a].buildBody();
		for(int a = 0; a < checkboxes.length; a++)
			checkboxes[a].buildBody();
	}
}