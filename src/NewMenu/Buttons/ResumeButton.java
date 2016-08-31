package NewMenu.Buttons;

import Main.*;
import NewMenu.MenuButton;

public class ResumeButton extends MenuButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResumeButton(int x, int y)
	{
		setText("RESUME");
		setX(x);
		setY(y);
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		buildBody();
	}
	
	public void doAction()
	{
		Game.setState(Game.getPlayState());
	}
}
