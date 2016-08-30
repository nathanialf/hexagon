package State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.game.main.Display;
import com.game.menu.*;

public class InformationState extends State
{
    int base_y = Display.HEIGHT / 6;
    
	public InformationState()
	{
		setName("INFORMATION");
		background.setRect(0, -Display.HEIGHT, Display.WIDTH, Display.HEIGHT); 
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
			}
			else
			{
				background.setRect(background.getX(), 0, background.getWidth(), background.getHeight());
				is_open_animating = false;
			}
		}
	}
	
	public void render(Graphics2D g)
	{
		g.setFont(Display.SMALL_FONT);
		
		g.setColor(new Color(239, 0, 239));
		g.drawString("Framerate: " + Display.FPS + " Frames Per Second", 16, 24);
		g.drawString("Updaterate: " + Display.UPS + " Updates Per Second", 16, 48);
		g.drawString("State: " + Display.getState().getName(), 16, 72);
	}
	
	public void setBackground(Rectangle2D.Double r)	
	{
		background = r;
	}

	public void openAnim()
	{
		is_open_animating = true;
	}
}