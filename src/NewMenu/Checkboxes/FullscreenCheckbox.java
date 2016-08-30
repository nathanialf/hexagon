package NewMenu.Checkboxes;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

import com.game.main.Display;
import com.game.menu.*;

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
		setWidth(Display.WIDTH / 4);
		setHeight(Display.HEIGHT / 16);
		
		if(Display.FULLSCREEN)
			setChecked(1);
		else
			setChecked(0);
		
		buildBody();
	}
	
	public void buildBody()
	{
		if(Display.getState() == Display.getPauseState())
			body = new Rectangle2D.Double(Display.getSettingsState().getBackground().getX() + getX(), Display.getSettingsState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void doAction()
	{
		if(isChecked() == 0)
		{
			Display.FULLSCREEN = false;
		}
		else
			Display.FULLSCREEN = true;
	}
}
