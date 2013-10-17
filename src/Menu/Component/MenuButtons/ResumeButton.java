package Menu.Component.MenuButtons;

import Main.Game;
import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class ResumeButton extends MenuButton
{
	boolean clicked = false;
	
	public ResumeButton() 
	{
		this.text = "Resume";
	}

	public void update(int delta, double angle)
	{
		for (MenuElement e : elements)
			e.update(delta);
	}
	
	public void activate()
	{
		Game.paused = false;
	}
}
