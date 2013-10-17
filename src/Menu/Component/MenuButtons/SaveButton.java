package Menu.Component.MenuButtons;

import org.newdawn.slick.SlickException;

import Main.Game;
import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class SaveButton extends MenuButton
{

	public SaveButton() 
	{
		this.text = "SAVE";
	}

	public void update(int delta, double angle)
	{

		for (MenuElement e : elements)
			e.update(delta);
	}
	
	public void activate()
	{
		try {
			Game.app.reinit();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		Game.currentMenu.set();
	}
}
