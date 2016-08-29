package Menu.Component.MenuButtons;

import org.newdawn.slick.SlickException;

import Main.Game;
import Menu.MainMenu;
import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class ApplyButton extends MenuButton
{
	public ApplyButton() 
	{
		this.text = "APPLY";
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
		Game.adjust(Game.app.getWidth() / 2, Game.app.getHeight() / 2);
		
		Game.currentMenu = new MainMenu();
	}
}
