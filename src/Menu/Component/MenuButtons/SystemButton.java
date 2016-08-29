package Menu.Component.MenuButtons;

import Menu.Component.MenuButton;
import Menu.Component.MenuElement;
import Menu.Component.MenuButtons.MenuElements.SystemElement;

public class SystemButton extends MenuButton
{

	public SystemButton() 
	{
		this.text = "SYSTEM";
		elements.add(new SystemElement());
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
