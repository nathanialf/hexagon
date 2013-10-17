package Menu.Component.MenuButtons.MenuElements;

import org.newdawn.slick.geom.Rectangle;

import Main.Game;
import Menu.Component.MenuElement;

public class SoundToggleElement extends MenuElement
{
	String sound;
	public SoundToggleElement() 
	{
		bounds = new Rectangle(0, 0, 96, 20);
		if (!Game.muted)
		{
			sound = "ON";
		}
		else
		{
			sound = "OFF";
		}
		this.text = "SOUND: " + sound;
	}
	
	public void update(int delta)
	{
		bounds.setX(this.textX);
		bounds.setY(this.textY);
		this.text = "SOUND: " + sound;
	}
	
	public void activate()
	{
		if (Game.muted)
		{
			Game.muted = false;
			sound = "ON";
		}
		else
		{
			Game.muted = true;
			sound = "OFF";
		}
	}
}
