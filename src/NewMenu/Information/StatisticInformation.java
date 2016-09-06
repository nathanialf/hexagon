package NewMenu.Information;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Main.*;
import NewMenu.*;
import State.*;

public class StatisticInformation extends MenuInformation
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatisticInformation(int x, int y)
	{
		setText("BACK");
		setX(x);
		setY(y);
		setWidth(Game.app.getWidth() / 4);
		setHeight(Game.app.getHeight() / 8);
		buildBody();
	}
	
	public void render(Graphics g)
	{
		//g.setFont(Game.MEDIUM_FONT);
		g.setColor(c);
		g.fill(body);
		
		g.setColor(Color.white);
		g.drawString("Hexagons Loaded: " + Game.hexes.size(), (int)(getX() + (getWidth() * .05)), (int)(body.getY() + (body.getHeight() / 6)));
		g.drawString("Load Time: " + Game.endTime, (int)(getX() + (getWidth() * .05)), (int)(body.getY() + ((body.getHeight() / 6) * 2)));
		
		g.setColor(new Color(255,255,255,50));
		g.fillRect(getX() + (getWidth() - (getWidth() / 40)), (int) body.getY(), getWidth() / 40, getHeight());
	}
	
	public void buildBody()
	{
		if(Game.getState() == Game.getPauseState())
			body = new Rectangle(Game.getState().getSubState().getBackground().getX(), Game.getState().getSubState().getBackground().getY() + getY(), getWidth(), getHeight());
	}
	
	public void doAction()
	{
		Game.getState().setSubState(new State());
	}
}
