package Menu.Component.MenuButtons;

import Main.Game;
import Menu.StatisticMenu;
import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class StatisticsButton extends MenuButton
{
	public StatisticsButton() 
	{
		this.text = "Statistics";
	}

	public void update(int delta, double angle)
	{
		for (MenuElement e : elements)
			e.update(delta);
	}
	
	public void activate()
	{
		//Game.currentMenu = new StatisticMenu();
	}
}
