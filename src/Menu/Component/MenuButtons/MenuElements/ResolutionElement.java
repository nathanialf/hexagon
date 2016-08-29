package Menu.Component.MenuButtons.MenuElements;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;

import Main.Game;
import Menu.Component.MenuElement;

public class ResolutionElement extends MenuElement
{
	public ResolutionElement()
	{
		bounds = new Rectangle(0, 0, 130, 20);
		bounds.setX(this.textX);
		bounds.setY(this.textY);
		textfields.add(new TextField(Game.app, Game.app.getDefaultFont(), textX, textY, 40, 20));
		textfields.add(new TextField(Game.app, Game.app.getDefaultFont(), textX, textY, 40, 20));
		
		textfields.get(0).setText("" + Game.app.getWidth());
		textfields.get(1).setText("" + Game.app.getHeight());
		this.text = "W       H";
	}
	
	public void update (int delta)
	{
		bounds.setX(this.textX);
		bounds.setY(this.textY);
		for (TextField t : textfields)
		{
			t.setLocation(textX, textY);
		}
	}
	
	public void activate()
	{
	}
}
