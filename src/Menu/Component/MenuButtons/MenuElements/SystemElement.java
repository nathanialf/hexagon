package Menu.Component.MenuButtons.MenuElements;

import org.newdawn.slick.geom.Rectangle;

import Main.Game;
import Menu.Component.MenuElement;

public class SystemElement extends MenuElement
{
	public SystemElement()
	{
		bounds = new Rectangle(0, 0, 130, 20);
		canDraw = false;
	}
	
	public void update (int delta)
	{
		bounds.setX(this.textX);
		bounds.setY(this.textY);
		
		this.text = "HEXAGONS: " + Game.size + "\n" + 
				"TIME LOADING: " + Game.endTime + " sec.\n" + 
				"MEMORY IN USE: " + Game.memUse + " MB";
	}
	
	public void activate()
	{
	}
}
