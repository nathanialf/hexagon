package Menu.Component.MenuButtons;

import org.lwjgl.openal.AL;

import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class ExitButton extends MenuButton
{
	public ExitButton() 
	{
		this.text = "EXIT";
	}

	public void update(int delta, double angle)
	{

		for (MenuElement e : elements)
			e.update(delta);
	}
	
	public void activate()
	{
		AL.destroy();
		System.exit(0);
	}
}
