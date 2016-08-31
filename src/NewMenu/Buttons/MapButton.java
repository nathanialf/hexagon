package NewMenu.Buttons;

import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.MenuButton;

public class MapButton extends MenuButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MapButton(int x, int y)
	{
		setText("MAP");
		setX(x);
		setY(y);
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		buildBody();
	}
	
	public void doAction()
	{
		if(Game.getState().getSubState() != Game.getMapState())
		{
			Rectangle newBody = new Rectangle(Game.app.getWidth() / 3, -Game.getState().BASE_HEIGHT, (float) (Game.app.getWidth() * .66), Game.getState().BASE_HEIGHT);
			
			if(Game.getState().getSubState() !=  null)
				Game.getState().getSubState().setBackground(newBody);
			Game.getState().setSubState(Game.getMapState());
		}
	}
}
