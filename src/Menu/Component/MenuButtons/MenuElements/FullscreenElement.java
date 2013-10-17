package Menu.Component.MenuButtons.MenuElements;

import org.newdawn.slick.geom.Rectangle;

import Main.Game;
import Menu.Component.MenuElement;

public class FullscreenElement extends MenuElement
{
	String screen;
	public FullscreenElement()
	{
		bounds = new Rectangle(0, 0, 130, 20);
		if (Game.fullScreen)
		{
			screen = "ON";
		}
		else
		{
			screen = "OFF";
		}
		this.text = "FULLSCREEN: " + screen;
	}
	
	public void update (int delta)
	{
		bounds.setX(this.textX);
		bounds.setY(this.textY);
		
		this.text = "FULLSCREEN: " + screen;
	}
	
	public void activate()
	{
		if (Game.fullScreen)
		{
			Game.fullScreen = false;
			screen = "OFF";
		}
		else
		{
			Game.fullScreen = true;
			screen = "ON";
		}
	}
}
