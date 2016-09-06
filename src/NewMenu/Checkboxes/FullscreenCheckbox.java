package NewMenu.Checkboxes;

import java.io.IOException;

import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.*;

public class FullscreenCheckbox extends MenuCheckbox
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FullscreenCheckbox(int x, int y)
	{
		setText("FULLSCREEN");
		setX(x);
		setY(y);
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		
		if(Game.fullScreen)
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
		
		try {
			Game.SAVE_CONFIG.Save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
