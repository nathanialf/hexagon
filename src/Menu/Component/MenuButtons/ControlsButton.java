package Menu.Component.MenuButtons;
import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class ControlsButton extends MenuButton
{
	public ControlsButton() 
	{
		this.text = "CONTROLS";
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
