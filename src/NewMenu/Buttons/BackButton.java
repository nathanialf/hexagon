package NewMenu.Buttons;

import java.awt.geom.Rectangle2D;

import com.game.main.*;
import com.game.menu.*;
import com.game.state.*;

public class BackButton extends MenuButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BackButton(int x, int y)
	{
		setText("BACK");
		setX(x);
		setY(y);
		setWidth(Display.WIDTH / 4);
		setHeight(Display.HEIGHT / 16);
		buildBody();
	}
	
	public void buildBody()
	{
		if(Display.getState() == Display.getPauseState())
			body = new Rectangle2D.Double(Display.getState().getSubState().getBackground().getX(), Display.getState().getSubState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void doAction()
	{
		Display.getState().setSubState(new State());
	}
}
