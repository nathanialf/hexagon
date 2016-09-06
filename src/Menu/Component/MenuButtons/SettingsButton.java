package Menu.Component.MenuButtons;

import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class SettingsButton extends MenuButton
{
	public SettingsButton() 
	{
		this.text = "Settings";
	}

	public void update(int delta, double angle)
	{
		for (MenuElement e : elements)
			e.update(delta);
	}
	
	public void activate()
	{
		//Game.currentMenu = new SettingMenu();
	}
}
