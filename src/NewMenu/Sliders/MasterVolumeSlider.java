package NewMenu.Sliders;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

import com.game.main.*;
import com.game.menu.*;

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
		setWidth(Display.WIDTH / 4);
		setHeight(Display.HEIGHT / 16);
		
		buildBody();
		
		setMinimumValue(0);
		setValue(Display.volume);
		setMaximumValue(100);
	}

	
	public void buildBody()
	{
		if(Display.getState() == Display.getPauseState())
			body = new Rectangle2D.Double(Display.getSettingsState().getBackground().getX(), Display.getSettingsState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void doAction()
	{
		Display.volume = (int) getValue();
	}
}
