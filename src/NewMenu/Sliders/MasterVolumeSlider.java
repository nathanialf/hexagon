package NewMenu.Sliders;

import java.io.IOException;

import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.*;

public class MasterVolumeSlider extends MenuSlider
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MasterVolumeSlider(int x, int y)
	{
		setText("MASTER VOLUME");
		setX(x);
		setY(y);
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		
		buildBody();
		
		setMinimumValue(0);
		setValue(Game.volume);
		setMaximumValue(100);
	}

	
	public void buildBody()
	{
		if(Game.getState() == Game.getPauseState())
			body = new Rectangle(Game.getSettingsState().getBackground().getX(), Game.getSettingsState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void doAction()
	{
		Game.volume = (int) getValue();
	}
}
