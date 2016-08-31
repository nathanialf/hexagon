package State;

import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import Cell.Hexagon;
import Main.*;

import Main.Game;

public class PlayState extends State
{
	//Player
	//private static Player player = new Player();
	
	//GAME WORLD
	//private static World world = new World(new File("res/maps/error.map"));
	//private static World world = new World(null);
	
	public PlayState()
	{
		setName("PLAY");
		background = new Rectangle(0, 0, Game.app.getWidth(), Game.app.getHeight()); 
		BASE_HEIGHT = Game.app.getHeight();
	}
	
	public void update(GameContainer gc, double delta)
	{
		if(is_open_animating)
		{
			if(background.getY() < 0)
			{
				background = new Rectangle(background.getX(), background.getY() + (background.getHeight() / 15), background.getWidth(), background.getHeight());
				//getWorld().buildBody();
				//getPlayer().buildBody();
			}
			else
			{
				background = new Rectangle(0, 0, background.getWidth(), background.getHeight());
				is_open_animating = false;
			}
		}
		
		Game.getPauseState().setSubState(new State());
		//Display.getMouse().update();
		
		//getWorld().update(delta);
		//getPlayer().update(delta);
		
		if(Game.getState() != this)
		{
			setSubState(new State());
		}
		
		if(getSubState() != null)
			getSubState().update(gc, delta);
		if(Game.hexes.size() == Game.size)
		{
			if (gc.getInput().isKeyPressed(Input.KEY_F2))
			{
				Game.takeScreenShot(gc, gc.getGraphics());
			}

			int c = 0;
			for (Hexagon h : Game.hexes)
			{
				if (!h.selected)
				{
					c++;
				}
				else
					break;
			}
			if (c == Game.hexes.size())
				Game.last.selected = true;
		
			//keypress
			for (int a = 0; a < Game.Keys.length; a++)
			{
				//if the key is pressed
				if (gc.getInput().isKeyPressed(Game.Keys[a]))
				{
					boolean canDo = true;
					for (Hexagon h : Game.hexes)
					{
						if (h.selected && h.adjacent[a] != null )
						{
							Game.last = h;
							//it finds the selected hexagon and if its adjacent hexagon is there
							//it sets that one to be the selected hexagon
							for (Hexagon i : Game.hexes)
							{
								i.selected = false;
							}
							h.adjacent[a].selected = true;
							//System.out.println(hexes.indexOf(h.adjacent[a]));
							//stops when it was found
							break;
						}//if it is selected without and its adjacent wasn't there it wont let the player move
						else if (h.selected && h.adjacent[a] == null)
							canDo = false;
					}
					if (canDo)
					{
						for (Hexagon h : Game.hexes)
						{
							//moves the hexagons
							h.hex.setX(h.hex.getX() - Game.Xs[a]);
							h.hex.setY(h.hex.getY() - Game.Ys[a]);
							h.playerHex.setX(h.playerHex.getX() - Game.Xs[a]);
							h.playerHex.setY(h.playerHex.getY() - Game.Ys[a]);
							for (Hexagon i : h.adjacentChecker)
							{
								//moves the hexagons adjacent checkers
								i.hex.setX(i.hex.getX() - Game.Xs[a]);
								i.hex.setY(i.hex.getY() - Game.Ys[a]);
							}
							//rechecks to see if which ones are now visible
							h.checkCanDraw();
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		//is a rectangle with boundaries I use for collisions. Google Rectangle2D 
		g.setColor(new Color(20,20,20));
		g.fill(background);
		
		//Draws world
		//getWorld().render(g);
		
		//Draws Player
		//getPlayer().render(g);

		if(getSubState() != null)
			getSubState().render(g);
		
		g.setColor(new Color(Game.red, Game.green, Game.blue));
		g.fillRect(0, 0, Game.app.getWidth(), Game.app.getHeight());
		
		//if the hexagon is selected it draws the border of the adjacent checkers that are occupied
		for (int a = 0; a < Game.hexes.size(); a++)
		{
			if (Game.hexes.get(a).selected)
			{
				for (int b = 0; b < Game.hexes.get(a).adjacent.length; b++)
				{
					if (Game.hexes.get(a).adjacent[b] != null)
						Game.hexes.get(a).adjacentChecker[b].draw(g);
				}
				break;
			}
		}

		//draws the player
		g.setColor(new Color(Game.green, Game.blue, Game.red));
		g.setLineWidth(2);
		//g.draw(playerHex);
		g.setColor(new Color(Game.green, Game.blue, Game.red,100));
		//g.fill(playerHex);
		
		//draws all the hexagons
		for (int a = 0; a < Game.hexes.size(); a++)
		{
			Game.hexes.get(a).draw(g);
		}
		
		if (Game.hexes.size() < Game.size)
		{
			//if it is not done loading
			//adds a gray layer to dim the generation in the background
			g.setColor(new Color(0,0,0,150));
			g.fillRect(0, 0, Game.app.getWidth(), Game.app.getHeight());
			
			//sets the following text to be white
			g.setColor(Color.white);
			//loading bar boundaries
			g.drawRect(32, 32, Game.app.getWidth() - 64, 8);
			//sets how much of the bar will be filled due to how many hexagons are generated over
			//how many there are total
			double hSize = Game.hexes.size(), Size = Game.size;
			g.fillRect(32, 32, (int)((hSize / Size) * (Game.app.getWidth() - 64)), 8);
			//generating message
			String loadingMessage = "GENERATING WITH SCIENCE";
			g.drawString(loadingMessage, 32, 8);
			String hexCount = Game.hexes.size() + " / " + Game.size + " Hexagons";
			g.drawString(hexCount, 32, 48);
		}

		//sets the color of the following text to be white
		g.setColor(new Color(Game.green, Game.blue, Game.red));
		//draws the fps in a new location to not interfere with the generating loading bar.
		if(Game.showFPS)
			g.drawString("" + Game.app.getFPS(), 16, Game.app.getHeight() - 32);
	}
	
	//public static Player getPlayer()			{return player;}
	//public static World getWorld()				{return world;}
	
	//public static void setPlayer(Player p)		{player = p;}
	//public static void setWorld(World w)		{world = w;}
	
	public void setBackground(Rectangle r)	
	{
		background = r;
		//getWorld().buildBody();
		//getPlayer().buildBody();
	}
}