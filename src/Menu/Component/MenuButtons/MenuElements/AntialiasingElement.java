package Menu.Component.MenuButtons.MenuElements;

import org.newdawn.slick.geom.Rectangle;

import Main.Game;
import Menu.Component.MenuElement;

public class AntialiasingElement extends MenuElement
{
	String screen;
	public AntialiasingElement()
	{
		bounds = new Rectangle(0, 0, 148, 20);
		if (Game.alias)
		{
			screen = "ON";
		}
		else
		{
			screen = "OFF";
		}
		this.text = "ANTIALIASING: " + screen;
	}
	
	public void update (int delta)
	{
		bounds.setX(this.textX);
		bounds.setY(this.textY);
		this.text = "ANTIALIASING: " + screen;
	}
	
	public void activate()
	{
		if (Game.alias)
		{
			Game.alias = false;
			screen = "OFF";
		}
		else
		{
			Game.alias = true;
			screen = "ON";
		}
	}
}
