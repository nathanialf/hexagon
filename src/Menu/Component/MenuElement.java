package Menu.Component;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;

import Main.Game;

public class MenuElement 
{
	public Rectangle bounds = new Rectangle(0,0,0,0);
	public String text = "";
	public boolean canDraw = true;
	
	public ArrayList <TextField> textfields = new ArrayList <TextField>();
	
	public int textX = 0, textY = 0;
	
	public MenuElement()
	{
	}
	
	public void update (int delta)
	{
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.setLineWidth(1);
		g.drawString(text, textX, textY);
		if (canDraw)
		{
			g.draw(bounds);
		}

		for (TextField t : textfields)
			t.render(Game.app, g);
	}
	
	public void activate()
	{
		
	}
}
