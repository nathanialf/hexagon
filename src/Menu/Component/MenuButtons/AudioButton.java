package Menu.Component.MenuButtons;

import Menu.Component.MenuButton;
import Menu.Component.MenuElement;
import Menu.Component.MenuButtons.MenuElements.SoundToggleElement;

public class AudioButton extends MenuButton
{
	public AudioButton() 
	{
		this.text = "AUDIO";
		
		elements.add(new SoundToggleElement());
	}
	
	public void update(int delta)
	{
		for (MenuElement m : elements)
		{
			m.update(delta);
		}
	}
	
	public void activate()
	{
	}
}
