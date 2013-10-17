package Menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.gui.TextField;

import Main.Game;
import Menu.Component.MenuButton;

public class Menu 
{
	public double numAng = 2;
	
	private int x, y, r;
	public double h;
	
	public boolean moving = false;
	int wantedDeg = 60;
	int currentDeg = 0;
	String direction;
	
	public ArrayList <MenuButton> menubuttons = new ArrayList <MenuButton>();
	
	public Menu() 
	{
	}
	
	public void update(int delta)
	{	
	}
	
	public void draw(Graphics g)
	{
	}
	
	
	public void setBounds(int xPos, int yPos, int radius)
	{
		this.x = xPos;
		this.y = yPos;
		this.r = radius;
	}
	
	public void set()
	{
	}
	
	public void checkRotate()
	{
		//ROTATES THE MAIN HEXAGON
		if (!moving)
		{

			if (Game.app.getInput().isKeyPressed(Input.KEY_LEFT))
			{
				moving = true;
				direction = "LEFT";
			}
			if (Game.app.getInput().isKeyPressed(Input.KEY_RIGHT))
			{
				moving = true;
				direction = "RIGHT";
			}
		}
		if (moving && wantedDeg > currentDeg)
		{
			switch(direction)
			{
			case "LEFT":
			{
				for (MenuButton m : menubuttons)
				{
					m.tri = (Polygon) m.tri.transform(Transform.createRotateTransform((float) Math.toRadians(1), this.getX(), this.getY()));
				}
				numAng -= (1/60.0);
				currentDeg++;
				break;
			}
			case "RIGHT":
			{
				for (MenuButton m : menubuttons)
				{
					m.tri = (Polygon) m.tri.transform(Transform.createRotateTransform((float) Math.toRadians(-1), this.getX(), this.getY()));
				}
				numAng += (1/60.0);
				currentDeg++;
				break;
			}
			}
		}
		else if (wantedDeg == currentDeg)
		{
			moving = false;
			currentDeg = 0;
			direction = "";
		}
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public int getRadius(){return this.r;}
	
}
