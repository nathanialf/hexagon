package Menu;

import org.newdawn.slick.Graphics;

import Main.Game;
import Menu.Component.MenuButton;
import Menu.Component.MenuButtons.*;

public class StatisticMenu extends Menu
{
	public StatisticMenu()
	{
		this.setBounds(Game.app.getWidth() / 2, Game.app.getHeight() / 2, Game.app.getHeight() / 2);

		h = Math.sin(Math.toRadians(60));
		h *= (Game.app.getHeight()/2);
		
		menubuttons.add(new BackButton());
		menubuttons.add(new SystemButton());
	
		for (int a = 0; a < menubuttons.size(); a++)
		{
			menubuttons.get(a).tri.addPoint(this.getX(), this.getY());

			double X2 = (int) (this.getX() + this.getRadius() * Math.cos(Math.toRadians((a - 2) * 60)));
			double Y2 = (int) (this.getY() + this.getRadius() * Math.sin(Math.toRadians((a - 2) * 60)));
			
			menubuttons.get(a).tri.addPoint((int)X2, (int)Y2);
			
			X2 = (int) (this.getX() + this.getRadius() * Math.cos(Math.toRadians((a - 1) * 60)));
			Y2 = (int) (this.getY() + this.getRadius() * Math.sin(Math.toRadians((a - 1) * 60)));
			
			menubuttons.get(a).tri.addPoint((int)X2, (int)Y2);
		}
	}
	
	public void update (int delta)
	{;
		for (int a = 0; a < menubuttons.size(); a++)
		{
			menubuttons.get(a).update(delta);
			
			menubuttons.get(a).textX = (int) (this.getX() + (h - 35) * Math.cos(Math.toRadians((((a - numAng) * 60) + 30))));
			menubuttons.get(a).textY = (int) (this.getY() + (h - 35) * Math.sin(Math.toRadians((((a - numAng) * 60) + 30))));
			for (int b = 0; b < menubuttons.get(a).elements.size(); b++)
			{
				menubuttons.get(a).elements.get(b).update(delta);
				menubuttons.get(a).elements.get(b).textX = (int) (this.getX() + (h - (35 + ((b + 1) * 35))) * Math.cos(Math.toRadians((((a - numAng) * 60) + 30))));
				menubuttons.get(a).elements.get(b).textY = (int) (this.getY() + (h - (35 + ((b + 1) * 35))) * Math.sin(Math.toRadians((((a - numAng) * 60) + 30))));
			}
		}
		
		checkRotate();
	}
	
	public void draw(Graphics g)
	{
		g.setLineWidth(1);
		
		for (MenuButton m : menubuttons)
			m.draw(g);
	}
}
