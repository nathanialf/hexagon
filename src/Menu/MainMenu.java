package Menu;

import org.newdawn.slick.*;

import Main.Game;
import Menu.Component.MenuButtons.*;

public class MainMenu extends Menu
{
	public MainMenu()
	{
		//Sets the bounds for this menu
		this.setBounds(Game.app.getWidth() / 2, Game.app.getHeight() / 2, Game.app.getHeight() / 2);
		
		h = Math.sin(Math.toRadians(60));
		h *= (Game.app.getHeight()/2);
		
		//SELF EXPLANATORY
		menubuttons.add(new ResumeButton());
		menubuttons.add(new SettingsButton());
		menubuttons.add(new SaveButton());
		menubuttons.add(new ExitButton());
		menubuttons.add(new LoadButton());
		menubuttons.add(new StatisticsButton());
	
		for (int a = 0; a < menubuttons.size(); a++)
		{
			menubuttons.get(a).tri.addPoint(this.getX(), this.getY());

			double X2 = (int) (this.getX() + this.getRadius() * Math.cos(Math.toRadians((a - numAng) * 60)));
			double Y2 = (int) (this.getY() + this.getRadius() * Math.sin(Math.toRadians((a - numAng) * 60)));
			
			menubuttons.get(a).tri.addPoint((int)X2, (int)Y2);
			
			X2 = (int) (this.getX() + this.getRadius() * Math.cos(Math.toRadians((a - (numAng - 1)) * 60)));
			Y2 = (int) (this.getY() + this.getRadius() * Math.sin(Math.toRadians((a - (numAng - 1)) * 60)));
			
			menubuttons.get(a).tri.addPoint((int)X2, (int)Y2);
		}
	}
	
	public void update (int delta)
	{
		for (int a = 0; a < menubuttons.size(); a++)
		{	
			menubuttons.get(a).update(delta);

			menubuttons.get(a).textX = (int) (this.getX() + (h - 35) * Math.cos(Math.toRadians((((a - numAng) * 60) + 30))));
			menubuttons.get(a).textY = (int) (this.getY() + (h - 35) * Math.sin(Math.toRadians((((a - numAng) * 60) + 30))));
			for (int b = 0; b < menubuttons.get(a).elements.size(); b++)
			{
				menubuttons.get(a).elements.get(b).textX = (int) (this.getX() + (h - (35 + ((b + 1) * 35))) * Math.cos(Math.toRadians((((a - numAng) * 60) + 30))));
				menubuttons.get(a).elements.get(b).textY = (int) (this.getY() + (h - (35 + ((b + 1) * 35))) * Math.sin(Math.toRadians((((a - numAng) * 60) + 30))));
			}
		}
		
		checkRotate();
	}
	
	public void draw(Graphics g)
	{
		for (int a = 0; a < menubuttons.size(); a++)
		{
			menubuttons.get(a).draw(g);
		}
	}
}
