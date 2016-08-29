package Main;

import java.util.Random;
import org.newdawn.slick.SlickException;

import Cell.Hexagon;

public class generateHexMap 
{	
	int baseHex = 0;
	
	int hexSize;
	
	Random r = new Random();

	Integer Xs[] = Game.Xs;
	Integer Ys[] = Game.Ys;
	
	public generateHexMap() throws SlickException
	{
		this.hexSize = Game.hexSize;
	}
	
	public boolean procedural(boolean re) throws SlickException
	{
		if (!re)
		{
			//sets where to generate from
			baseHex = Game.hexes.size() - 1;
		}
		else
		{
			//sets where to generate from if the last hexagon was boxed in
			int check = baseHex;
			for (Hexagon h : Game.hexes)
			{
				for (Hexagon i : h.adjacent)
				{
					if (i == null)
					{
						//finds first hexagon with an empty hole adjacent and sets that as the base
						baseHex = Game.hexes.indexOf(h);
						break;
					}
				}
				//when it finds one it ends the loop
				if (baseHex != check)
					break;
			}
		}
		
		//counts the adjacent
		int aCount = 0;
		
		//finds how many adjacent hexagons are empty
		for (int a = 0; a < Game.hexes.get(baseHex).adjacent.length; a++)
		{
			if (Game.hexes.get(baseHex).adjacent[a] == null)
			{
				aCount++;
			}
		}
		
		//if empty it returns that it will need to be reset because it is boxed in
		if (aCount == 0)
		{
			return true;
		}
		
		//starts the placement of hexagons
		for (int count = 0; count < aCount; count++)
		{
			//chooses which one it wants to fill
			int adjNumber = r.nextInt(6);
			//if the one it wants to fill is free it plugs it in
			if (Game.hexes.get(baseHex).adjacent[adjNumber] == null)
			{
				Game.hexes.add(new Hexagon(hexSize, Game.hexes.get(baseHex).x + Xs[adjNumber], Game.hexes.get(baseHex).y + Ys[adjNumber], true));
			}
			//checks adjacent to all hexagons so it knows where it can place other ones.
			for (Hexagon h : Game.hexes)
			{
				h.getAdjacents();
				h.checkCanDraw();
			}
		}
		// if it makes it here it can restart without the need to reset.
		return false;
	}
}
