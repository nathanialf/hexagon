package Menu.Component.MenuButtons;

import Menu.Component.MenuButton;
import Menu.Component.MenuElement;
import Menu.Component.MenuButtons.MenuElements.*;

public class GraphicsButton extends MenuButton
{
	public GraphicsButton() 
	{
		this.text = "GRAPHICS";
		elements.add(new FullscreenElement());
		elements.add(new AntialiasingElement());
		elements.add(new ResolutionElement());
	}
	
	public void update(int delta)
	{
		for (MenuElement e : elements)
			e.update(delta);
	}
	
	public void activate()
	{
	}
}
