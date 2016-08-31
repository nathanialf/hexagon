package NewMenu.Buttons;

import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.MenuButton;
import State.*;

public class SettingsButton extends MenuButton
{
	/**
	 * 
	 */
	public State s = new State();
	private static final long serialVersionUID = 1L;

	public SettingsButton(int x, int y)
	{
		setText("SETTINGS");
		setX(x);
		setY(y);
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		buildBody();

		s.setName("");
		s.background = new Rectangle(Game.app.getWidth() / 3, -Game.app.getHeight(), Game.app.getWidth(), Game.app.getHeight()); 
		s.BASE_HEIGHT = Game.app.getHeight();
	}
	
	public void doAction()
	{
		if(Game.getState().getSubState() != Game.getSettingsState())
		{
			Rectangle newBody = new Rectangle(Game.app.getWidth() / 3, -Game.getState().BASE_HEIGHT, (float) (Game.app.getWidth() * .66), Game.getState().BASE_HEIGHT);
			
			if(Game.getState().getSubState() !=  null)
				Game.getState().getSubState().setBackground(newBody);
			Game.getState().setSubState(Game.getSettingsState());
		}
	}
}
