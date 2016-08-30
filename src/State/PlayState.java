package State;

import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

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
	
	public void update(double delta)
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
		
		Game.app.getPauseState().setSubState(new State());
		//Display.getMouse().update();
		
		//getWorld().update(delta);
		//getPlayer().update(delta);
		
		if(Game.app.getState() != this)
		{
			setSubState(new State());
		}
		
		if(getSubState() != null)
			getSubState().update(delta);
	}
	
	public void render(Graphics g)
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