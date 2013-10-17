package Cell;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;

import Main.Game;

public class Hexagon 
{
	//This Hexagon
	public Polygon hex;
	//The adjacent actual hexagons
	public Hexagon [] adjacent = {null, null, null, null, null, null};
	//The holder hexagons for comparison to find adjacents
	public Hexagon [] adjacentChecker = {null, null, null, null, null, null};
	//Dimensions
	public int size;
	public int x;
	public int y;
	//Checks for the adjacentChecker so it can't pass through if its empty
	boolean[] check = {false, false, false, false, false, false};
	//if it is a base hexagon it has adjacents
	boolean hasAdjacents;
	//if the player is there
	public boolean selected;
	//if it is on the screen
	public boolean canDraw = true;
	
	public Hexagon (int size, int x, int y, boolean hasAdjacents) throws SlickException
	{
		hex = new Polygon();
		this.size = size;
		this.x = (int) x;
		this.y = (int) y;
		
		//if this a base hexagon it has adjacents if it is one of the basic hexagon adjacent checkers it does not
		this.hasAdjacents = hasAdjacents;
		
		//sets points for the hexagons
		for(int a = 0; a < 6; a++)
		{
			hex.addPoint((int) (x + size * Math.cos(a * 2 * Math.PI / 6)), 
					(int) (y + size * Math.sin(a * 2 * Math.PI / 6)));
		}
		//Setsup the annoying adjacent Checkers in a very static way
		if (hasAdjacents)
		{
			adjacentChecker[0] = new Hexagon(size,x - (int)((size * 1.5)), y - (int)(size * (7.0/8)), false);

			adjacentChecker[1] = new Hexagon(size, x, y - (int)(((size * (7.0/8)) * 2)), false);

			adjacentChecker[2] = new Hexagon(size, x + (int)((size * 1.5)), y - (int)(size * (7.0/8)), false);

			adjacentChecker[3] = new Hexagon(size, x + (int)((size * 1.5)), y + (int)(size * (7.0/8)), false);

			adjacentChecker[4] = new Hexagon(size, x, y + (int)(((size * (7.0/8)) * 2)), false);

			adjacentChecker[5] = new Hexagon(size, x - (int)((size * 1.5)), y + (int)(size * (7.0/8)), false);
			getAdjacents();
		}
	}
	
	public void getAdjacents() throws SlickException
	{
		//finds all adjacent hexagons for every hexagon
		check[0] = true;
		check[1] = true;
		check[2] = true;
		check[3] = true;
		check[4] = true;
		check[5] = true;
		
		for (Hexagon h : Game.hexes)
		{
			int a = 0;
			if (h != this)
			{
				for (Hexagon i : adjacentChecker)
				{
					//runs through the adjacentcheckers of all hexagons that isn't this one.
					if (h.size == i.size && h.x == i.x && i.y == h.y)
					{
						//occupies the adjacent if there is a hexagon adjacent
						adjacent[a] = h;
						check[a] = false;
					}//empties it if there isn't
					else if (check[a])
						adjacent[a] = null;
					a++;
				}
			}
		}
	}
	public void draw(Graphics g) throws SlickException
	{
		//if it is on screen
		if (canDraw)
		{
			//if it is a base hexagon
			if (hasAdjacents)
			{
				//if it is where the player is at
				if (selected)
				{
					//draws the player
					new Image("res/Test Images/0_1_transparent.png").draw(x + hex.getX() - (size + 9), y + hex.getY() - (size +8));
				}
				
				//linewidth for the outer rim of base hexagons
				g.setLineWidth(1);
				//color for the center of the hexagon
				g.setColor(new Color(255,255,255,60));
				//fills in the center of the hexagon
				g.fill(hex);
				//sets the color of the outer rim of the hexagon
				g.setColor(Color.white);
				//draws the outer rim
				g.draw(hex);
				
			}
			else
			{
				//bigger linewidth for the current accessible hexagons for visualization to the player
				g.setLineWidth(5);
				//makes it blue to stand out
				g.setColor(new Color(0,0,255, 200));
				//draws the highlight for the accessible hexagons
				g.draw(hex);
			}
		}
	}

	public void update(int delta) throws SlickException 
	{
	}
	
	public void checkCanDraw()
	{
		//checks to see if its on screen
		if (x + hex.getX() < - size|| y + hex.getY() < - size || x + hex.getX() > Game.app.getWidth() + size || y + hex.getY() > Game.app.getHeight() + size)
		{
			canDraw = false;
		}
		else
			canDraw = true;
	}
}
