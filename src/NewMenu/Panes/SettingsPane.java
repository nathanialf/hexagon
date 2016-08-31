package NewMenu.Panes;

import Main.*;
import NewMenu.*;

public class SettingsPane extends MenuPane
{
	public SettingsPane()
	{
		setX((int) (Game.app.getWidth() * .25));
		setY(0);
		setHeight(Game.app.getHeight());
		setWidth((int) (Game.app.getWidth() * .75));
	}
}
