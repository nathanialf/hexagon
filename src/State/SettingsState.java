package State;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Main.Game;
import NewMenu.*;
import NewMenu.Buttons.*;
import NewMenu.Checkboxes.*;
import NewMenu.Sliders.*;

public class SettingsState extends State
{
    int base_y = Game.app.getHeight() / 6;
    MenuButton buttons [] = {new BackButton(Game.app.getWidth() / 3, base_y)};
    MenuSlider sliders [] = {new MasterVolumeSlider(Game.app.getWidth() / 3, base_y + (Game.app.getHeight() / 16) + 16)};
    MenuCheckbox checkboxes [] =  {new FullscreenCheckbox(0, base_y + (((Game.app.getHeight() / 16) + 16) * 3))};
	public SettingsState()
	{
		setName("SETTINGS");
		background = new Rectangle(Game.app.getWidth() / 3, -Game.app.getHeight(), Game.app.getWidth(), Game.app.getHeight()); 
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
				for(int a = 0; a < sliders.length; a++)
					sliders[a].buildBody();
				for(int a = 0; a < checkboxes.length; a++)
					checkboxes[a].buildBody();
			}
			else
			{
				background = new Rectangle(background.getX(), 0, background.getWidth(), background.getHeight());
				is_open_animating = false;
			}
		}

		for(int a = 0; a < buttons.length; a++)
			buttons[a].update(delta);
		for(int a = 0; a < sliders.length; a++)
			sliders[a].update(delta);
		for(int a = 0; a < checkboxes.length; a++)
			checkboxes[a].update(delta);
	}
	
	public void render(Graphics g)
	{
		//g.setFont(Display.BIG_FONT);
		
		g.setColor(new Color(239,239,239));
		g.fill(background);
		g.setColor(new Color(75, 131, 75));
		g.drawString("SETTINGS", (int) background.getX(), (int) (background.getY() + background.getHeight() / 8));

		for(int a = 0; a < buttons.length; a++)
			buttons[a].render(g);
		for(int a = 0; a < sliders.length; a++)
			sliders[a].render(g);
		for(int a = 0; a < checkboxes.length; a++)
			checkboxes[a].render(g);
	}
	
	public void setBackground(Rectangle2D.Double r)	
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