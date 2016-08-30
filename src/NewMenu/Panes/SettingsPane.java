package NewMenu.Panes;

import com.game.main.Display;
import com.game.menu.*;

public class SettingsPane extends MenuPane
{
	public SettingsPane()
	{
		setX((int) (Display.WIDTH * .25));
		setY(0);
		setHeight(Display.HEIGHT);
		setWidth((int) (Display.WIDTH * .75));
	}
}
