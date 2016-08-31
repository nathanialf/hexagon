package NewMenu.Checkboxes;

import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.*;

public class AntialiasCheckbox extends MenuCheckbox
{
	private static final long serialVersionUID = 1L;

	public AntialiasCheckbox(int x, int y)
	{
		setText("ANTI-ALIASING");
		setX(x);
		setY(y);
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		
		if(Game.alias)
			setChecked(1);
		else
			setChecked(0);
		
		buildBody();
	}
	
	public void buildBody()
	{
		if(Game.getState() == Game.getPauseState())
			body = new Rectangle(Game.getSettingsState().getBackground().getX() + getX(), Game.getSettingsState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void doAction()
	{
		if(isChecked() == 0)
		{
			Game.fullScreen = false;
		}
		else
			Game.fullScreen = true;
	}
}
