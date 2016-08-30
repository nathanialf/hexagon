package NewMenu.Buttons;

import java.awt.geom.Rectangle2D;

import com.game.main.Display;
import com.game.menu.*;

public class ControlsButton extends MenuButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlsButton(int x, int y)
	{
		setText("CONTROLS");
		setX(x);
		setY(y);
		setWidth(Display.WIDTH / 4);
		setHeight(Display.HEIGHT / 16);
		buildBody();
	}
	
	public void doAction()
	{
		if(Display.getState().getSubState() != Display.getControlsState())
		{	
			Rectangle2D.Double newBody = new Rectangle2D.Double(Display.WIDTH / 3, -Display.getState().BASE_HEIGHT, Display.WIDTH * .66, Display.getState().BASE_HEIGHT);

			if(Display.getState().getSubState() !=  null)
				Display.getState().getSubState().setBackground(newBody);
			Display.getState().setSubState(Display.getControlsState());
		}
	}
}
