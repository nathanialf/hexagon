package Menu.Component;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

public class MenuButton 
{
	public String text;
	public boolean activated = false;
	
	public Polygon tri = new Polygon();
	String description;
	
	public int textX, textY;
	
	public ArrayList <MenuElement> elements = new ArrayList <MenuElement>();
	
	public MenuButton()
	{
	}
	
	public void update (int delta)
	{
	}
	
	public void draw(Graphics g)
	{
		g.setLineWidth(1);
		g.setColor(new Color(0,0,0,100));
		g.fill(tri);
		g.setColor(Color.white);
		g.drawString(text, textX, textY);
		g.setLineWidth(3);
		g.draw(tri);
		
		for(MenuElement e : elements)
			e.draw(g);
	}
	
	public void activate()
	{
		
	}
}
