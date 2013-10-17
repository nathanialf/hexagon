package Menu.Component.MenuButtons;

import Main.Game;
import Menu.MainMenu;
import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class BackButton extends MenuButton
{
	double h;
	int X2, Y2;
	boolean clicked = false;
	
	public BackButton() 
	{
		this.text = "BACK";

		h = Math.sin(Math.toRadians(60));
		h *= (Game.app.getHeight()/2);
	}

	public void update(int delta)
	{
		for (MenuElement e : elements)
			e.update(delta);
	}
	
	public void activate()
	{
		Game.currentMenu = new MainMenu();
	}
}
