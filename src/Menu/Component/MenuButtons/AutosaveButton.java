package Menu.Component.MenuButtons;

import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class AutosaveButton extends MenuButton
{
	public AutosaveButton() 
	{
		this.text = "AUTOSAVE";
	}

	public void update(int delta, double angle)
	{
		for (MenuElement e : elements)
			e.update(delta);
	}
	
	public void activate()
	{
	}
}
