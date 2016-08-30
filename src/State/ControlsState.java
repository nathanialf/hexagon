package State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.game.main.Display;
import com.game.menu.*;
import com.game.menu.buttons.*;
import com.game.menu.checkboxes.*;
import com.game.menu.sliders.*;

public class ControlsState extends State
{
    int base_y = Display.HEIGHT / 6;
    MenuButton buttons [] = {new BackButton(Display.WIDTH / 3, base_y)};
    MenuSlider sliders [] = {};
    MenuCheckbox checkboxes [] =  {};
    
	public ControlsState()
	{
		setName("CONTROLS");
		background.setRect(Display.WIDTH / 3, -Display.HEIGHT, Display.WIDTH, Display.HEIGHT); 
		BASE_HEIGHT = Display.HEIGHT;
	}
	public void update(double delta)
	{
		Display.getMouse().update();
		
		if(is_open_animating)
		{
			if(background.getY() < 0)
			{
				background.setRect(background.getX(), background.getY() + (background.getHeight() / 15), background.getWidth(), background.getHeight());

				for(int a = 0; a < buttons.length; a++)
					buttons[a].buildBody();
				for(int a = 0; a < sliders.length; a++)
					sliders[a].buildBody();
				for(int a = 0; a < checkboxes.length; a++)
					checkboxes[a].buildBody();
			}
			else
			{
				background.setRect(background.getX(), 0, background.getWidth(), background.getHeight());
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
	
	public void render(Graphics2D g)
	{
		g.setFont(Display.BIG_FONT);
		
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