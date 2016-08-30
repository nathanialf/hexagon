package NewMenu.Buttons;

import java.io.IOException;

import com.game.main.*;
import com.game.menu.MenuButton;

public class ExitButton extends MenuButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExitButton(int x, int y)
	{
		setText("EXIT");
		setX(x);
		setY(y);
		setWidth(Display.WIDTH / 4);
		setHeight(Display.HEIGHT / 16);
		buildBody();
	}
	
	public void doAction()
	{
		try {
			Display.SAVE_WRITER.saveConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}
}
