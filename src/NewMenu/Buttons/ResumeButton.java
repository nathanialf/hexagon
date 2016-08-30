package NewMenu.Buttons;

import com.game.main.*;
import com.game.menu.MenuButton;

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
		setWidth(Display.WIDTH / 4);
		setHeight(Display.HEIGHT / 16);
		buildBody();
	}
	
	public void doAction()
	{
		Display.setState(Display.getPlayState());
	}
}
