package NewMenu.Buttons;

import java.awt.geom.Rectangle2D;

import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.*;
import State.*;

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
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		buildBody();
	}
	
	public void buildBody()
	{
		if(Game.getState() == Game.getPauseState())
			body = new Rectangle(Game.getState().getSubState().getBackground().getX(), Game.getState().getSubState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void doAction()
	{
		Game.getState().setSubState(new State());
	}
}
