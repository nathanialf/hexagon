package NewMenu.Buttons;

import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.*;

public class StatisticsButton extends MenuButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatisticsButton(int x, int y)
	{
		setText("STATISTICS");
		setX(x);
		setY(y);
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 16);
		buildBody();
	}
	
	public void doAction()
	{
		if(Game.getState().getSubState() != Game.getStatisticsState())
		{	
			Rectangle newBody = new Rectangle(Game.app.getWidth() / 3, -Game.getState().BASE_HEIGHT, (float) (Game.app.getWidth() * .66), Game.getState().BASE_HEIGHT);

			if(Game.getState().getSubState() !=  null)
				Game.getState().getSubState().setBackground(newBody);
			Game.getState().setSubState(Game.getStatisticsState());
		}
	}
}
