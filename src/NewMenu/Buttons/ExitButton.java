package NewMenu.Buttons;

import Main.*;
import NewMenu.*;

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
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		buildBody();
	}
	
	public void doAction()
	{
		/*try {
			Game.SAVE_WRITER.saveConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		System.exit(0);
	}
}
