package Menu.Component.MenuButtons;

import Main.Game;
import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class AudioButton extends MenuButton
{
	String sound;
	public AudioButton() 
	{
		if (!Game.muted)
		{
			sound = "ON";
		}
		else
		{
			sound = "OFF";
		}
		this.text = "AUDIO: " + sound;
	}
	
	public void update(int delta)
	{
		this.text = "AUDIO: " + sound;

		for (MenuElement e : elements)
			e.update(delta);
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
