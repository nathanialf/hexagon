package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.openal.AL;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;

import Cell.Hexagon;
import Main.Game;
import Menu.MainMenu;
import Menu.Menu;
import Menu.Component.MenuButton;
import Menu.Component.MenuElement;

public class Game extends BasicGame 
{
	//GameContainer
	public static AppGameContainer app;
	
	//dynamic Array of hexagons
	public static ArrayList <Hexagon> hexes = new ArrayList <Hexagon>();
	//how many hexagons to be generated
	public static long size;
	//size of the hexagons from the center to a point
	public static int hexSize;
	
	//anti-aliasing
	public static boolean alias = true;
	
	//for holding the reset on the generator
	boolean reset = false;
	//so it doesn't save multiple logs per execution
	boolean saved = false;
	//holds the pause
	public static boolean paused = false;
	//stops repeated actions for held keys
	boolean clicked = false;
	
	//sets fullscreen
	public static int w = 1280,h = 720;
	public static boolean fullScreen = true;
	//holds beginning time for logging
	static double startTime;
	//holds the ending time for statistics
	public static double endTime;
	//holds how much memory is in use
	public static double memUse;
	public static double cpuUse;
	
	//rgb values
	public static int red, green, blue, colorState;

	//holds important data
	public static Integer Xs[]= {0,0,0,0,0,0};
	public static Integer Ys[] = {0,0,0,0,0,0};
	public static Integer Keys[] = {0,0,0,0,0,0};
	
	//Background Sound
	Sound sound;
	//If it will be muted
	public static boolean muted = true;
	
	//for when the game glitches
	Hexagon last;
	
	public static Polygon playerHex;
	
	//if the game is paused this will be the menu displayed
	public static Menu currentMenu;
	
	public Game() 
	{
		//whats at the top of the window
		super("HEXGAME");
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{	
		//Anti-aliasing
		g.setAntiAlias(alias);
		
		//val is for switch cases
		int val;
		//for background alignment
		val = (app.getWidth() / 16) * 9 < app.getHeight()? 1:0;
		switch (val)
		{
		case 1:
		{
			new Image("res/Test Images/OuterSpace.jpg").draw(0,0,(app.getHeight() * 16) / 9, app.getHeight());
			break;
		}
		case 0:
		{
			new Image("res/Test Images/OuterSpace.jpg").draw(0,0,app.getWidth(), (app.getWidth() / 16) * 9);
			break;
		}
		}
		
		g.setColor(new Color(red, green, blue));
		g.fillRect(0, 0, app.getWidth(), app.getHeight());
		
		//if the hexagon is selected it draws the border of the adjacent checkers that are occupied
		for (int a = 0; a < hexes.size(); a++)
		{
			if (hexes.get(a).selected)
			{
				for (int b = 0; b < hexes.get(a).adjacent.length; b++)
				{
					if (hexes.get(a).adjacent[b] != null)
						hexes.get(a).adjacentChecker[b].draw(g);
				}
				break;
			}
		}

		//draws the player
		g.setColor(new Color(Game.green, Game.blue, Game.red));
		g.setLineWidth(2);
		g.draw(playerHex);
		g.setColor(new Color(Game.green, Game.blue, Game.red,100));
		g.fill(playerHex);
		
		//draws all the hexagons
		for (int a = 0; a < hexes.size(); a++)
		{
			hexes.get(a).draw(g);
		}
		
		if (hexes.size() < size)
		{
			//if it is not done loading
			//adds a gray layer to dim the generation in the background
			g.setColor(new Color(0,0,0,150));
			g.fillRect(0, 0, app.getWidth(), app.getHeight());
			
			//sets the following text to be white
			g.setColor(Color.white);
			//loading bar boundaries
			g.drawRect(32, 32, app.getWidth() - 64, 16);
			//sets how much of the bar will be filled due to how many hexagons are generated over
			//how many there are total
			double hSize = hexes.size(), Size = size;
			g.fillRect(32, 32, (int)((hSize / Size) * (app.getWidth() - 64)), 16);
			//generating message
			String loadingMessage = "GENERATING WITH SCIENCE";
			g.drawString(loadingMessage, (app.getWidth() / 2) - (loadingMessage.length() * 5), 8);
		}
		if (paused)
		{
			//adds a gray layer to dim the generation in the background
			g.setColor(new Color(0,0,0,150));
			g.fillRect(0, 0, app.getWidth(), app.getHeight());
			currentMenu.draw(g);
		}
		

		//sets the color of the following text to be white
		g.setColor(Color.white);
		//draws the fps in a new location to not interfere with the generating loading bar.
		g.drawString("" + app.getFPS(), 16, app.getHeight() - 32);
	}
	public void init(GameContainer gc) throws SlickException
	{
		//MULTIPLES OF 8
		hexSize = 3;
		hexSize *= 8;
		
		AL.destroy();
		sound = new Sound("res/Sounds/Arpology2.wav");
		//sets how it is displayed in width height and if its full screen
		if (fullScreen)
			app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), fullScreen);
		else
			app.setDisplayMode(/*Integer.parseInt(currentMenu.menubuttons.get(2).elements.get(2).textfields.get(0).getText())*/Game.w, /*Integer.parseInt(currentMenu.menubuttons.get(2).elements.get(2).textfields.get(1).getText())*/Game.h, fullScreen);
		
		if (hexes.size() < size)
		{
			//sets up a base hexagon where the player starts
			Game.hexes.add(new Hexagon(hexSize, (Game.app.getWidth() / 2), (Game.app.getHeight() / 2), true));
			hexes.get(0).selected = true;
		}
		
		if (!muted)
		{
			sound.loop();
		}
		else
		{
			sound.stop();
		}
		
		//holds difference of x position of main hexagon to its adjacents for easy computing
		double hexCheck;
		hexCheck = hexSize * 1.5;
		Xs[0] = -(int)(hexCheck);
		Xs[1] = 0; 
		Xs[2] = (int)(hexCheck); 
		Xs[3] = (int)(hexCheck);
		Xs[4] = 0;
		Xs[5] = -(int)(hexCheck);

		//holds difference of y position of main hexagon to its adjacents for easy computing
		hexCheck = hexSize * (7.0/8);
		Ys[0] = -(int)hexCheck; 
		Ys[1] = -(int)((hexCheck * 2)); 
		Ys[2] = -(int)hexCheck; 
		Ys[3] = (int)hexCheck; 
		Ys[4] = (int)(hexCheck * 2); 
		Ys[5] = (int)hexCheck;
		
		//input keys for direction for more dynamic key pressing and less code
		Keys[0] = Input.KEY_Q; 
		Keys[1] = Input.KEY_W;
		Keys[2] = Input.KEY_E; 
		Keys[3] = Input.KEY_D; 
		Keys[4] = Input.KEY_S;
		Keys[5] = Input.KEY_A;

		
		playerHex = new Polygon();
		//sets points for the hexagons
		for(int a = 0; a < 6; a++)
		{
			playerHex.addPoint((int) ((app.getWidth() /2) + (hexSize *.66) * Math.cos(a * 2 * Math.PI / 6)), 
					(int) ((app.getHeight() / 2) + (hexSize*.66) * Math.sin(a * 2 * Math.PI / 6)));
		}
	}

	public void update(GameContainer gc, int delta) throws SlickException
	{
		//leave the game quickly
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			AL.destroy();
			System.exit(0);
		}
		
		////////////////////////////////////////////////////////
		boolean re = reset;
		if (hexes.size() < size)
		{
			//while its not done generating it continuously generates
			reset = new generateHexMap().procedural(re);
			//tries to garbge collect higher than 120MB in use
			if (memUse > 120)
				System.gc();
		}
		else if (hexes.size() > size)
		{
			//if it goes over the amount it takes off the most recently add hexagons
			hexes.remove(hexes.size() -1);
		}
		else if (hexes.size() == size && !saved)
		{
			endTime = (System.currentTimeMillis() - startTime) / 1000;
			saved = true;
		}
		////////////////////////////////////////////////////////
		
		//only if its done loading...
		if (hexes.size() == size)
		{	
			if (!paused)
			{
				//you can take screenshots
				if (gc.getInput().isKeyPressed(Input.KEY_F2))
				{
					takeScreenShot(gc, gc.getGraphics());
				}

				int c = 0;
				for (Hexagon h : hexes)
				{
					if (!h.selected)
					{
						c++;
					}
					else
						break;
				}
				if (c == hexes.size())
					last.selected = true;
				
				//keypress
				for (int a = 0; a < Keys.length; a++)
				{
					//if the key is pressed
					if (gc.getInput().isKeyPressed(Keys[a]))
					{
						boolean canDo = true;
						for (Hexagon h : hexes)
						{
							if (h.selected && h.adjacent[a] != null )
							{
								last = h;
								//it finds the selected hexagon and if its adjacent hexagon is there
								//it sets that one to be the selected hexagon
								for (Hexagon i : hexes)
								{
									i.selected = false;
								}
								h.adjacent[a].selected = true;
								//stops when it was found
								break;
							}//if it is selected without and its adjacent wasn't there it wont let the player move
							else if (h.selected && h.adjacent[a] == null)
								canDo = false;
						}
						if (canDo)
						{
							for (Hexagon h : hexes)
							{
								//moves the hexagons
								h.hex.setX(h.hex.getX() - Xs[a]);
								h.hex.setY(h.hex.getY() - Ys[a]);
								for (Hexagon i : h.adjacentChecker)
								{
									//moves the hexagons adjacent checkers
									i.hex.setX(i.hex.getX() - Xs[a]);
									i.hex.setY(i.hex.getY() - Ys[a]);
								}
								//rechecks to see if which ones are now visible
								h.checkCanDraw();
							}
						}
					}
				}
			}
			else
			{
				//sets the Memory in Use
				memUse = ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(1024 * 1024));
				
				currentMenu.update(delta);
				for (MenuButton m : currentMenu.menubuttons)
				{
					if (m.tri.contains(app.getInput().getMouseX(), app.getInput().getMouseY()) && app.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
					{
						boolean yes = true;
						for (MenuElement e : m.elements)
						{
							if (e.bounds.contains(app.getInput().getMouseX(), app.getInput().getMouseY()))
							{
								e.activate();
								yes = false;
								break;
							}
						}
						if (yes)m.activate();
					}
				}
			}
			if (gc.getInput().isKeyDown(Input.KEY_ENTER))
			{
				if (paused & !clicked)
				{
					paused = false;
					clicked = true;
				}
				else if (!paused && !clicked)
				{
					currentMenu = new MainMenu();
					paused = true;
					clicked = true;
				}
			}
			else if (clicked)
				clicked = false;
		}
		
		CycleColors(delta / 6);
	}
    
	public static void main (String args[]) throws SlickException
	{
		
		//Red
		red = 255;
		//Blue
		blue = 0;
		//Green
		green = 0;
		//Color State
		colorState = 0;
		//how many hexagons are going to be generated
		size = 1000;
		//sets up start time for logging
		startTime = System.currentTimeMillis();
		//sets the container to hold the game
		app = new AppGameContainer(new Game());
		//sets if you want to see the fps (i did not like the position so i recreated it in render so it would
		//be in a new location)
		app.setShowFPS(false);
		//keeps delta stable
		app.setMaximumLogicUpdateInterval(0);
		app.setMinimumLogicUpdateInterval(0);
		
		//starts the game
		app.start();
	}

	public static void takeScreenShot(GameContainer gc, Graphics g)
	{
		//creates a blank image the size of the screen
		BufferedImage screen = new BufferedImage(app.getWidth(), app.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < app.getWidth(); x++)
		{
			for (int y = 0; y < app.getHeight(); y++)
			{
				//gets the color from each pixel
				Color col = new Color(g.getPixel(x, y));
				//sets the color to java.awt.color because slick is limited for screenshots
				java.awt.Color c = new java.awt.Color(col.r, col.g, col.b);
				//sets the pixel in the same location to the color of the pixel on the screen
				screen.setRGB(x, y, c.getRGB());
			}
		}
		//sets the name of the image to be screen_currentTime
		String name = "screen_" + System.currentTimeMillis();
		//accesses the file
		File fi = new File("res/ScreenShots/" + name + ".png");
		try {
			//gets the parent directory and creates it if it doesn't exist
			if (!fi.getParentFile().exists())
				fi.getParentFile().mkdir();
			//creates the file because it should not exist
			if (!fi.exists())
				fi.createNewFile();
			//allows writable to the file
			fi.canWrite();
			//sets the file to be screen
			ImageIO.write(screen, "png", fi);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void adjust(int centerX, int centerY)
	{
		Hexagon sel = null;
		for (Hexagon  h : hexes)
		{
			if (h.selected)
			{
				sel = h;
				break;
			}
		}
		int moveX = (int) ((sel.x - centerX) + sel.hex.getX());
		int moveY = (int) ((sel.y - centerY) + sel.hex.getY());
	
		for (Hexagon h : hexes)
		{
			//moves the hexagons
			h.hex.setX((h.hex.getX() - moveX));
			h.hex.setY((h.hex.getY() - moveY));
			for (Hexagon i : h.adjacentChecker)
			{
				//moves the hexagons adjacent checkers
				i.hex.setX((i.hex.getX() - moveX));
				i.hex.setY((i.hex.getY() - moveY));
			}
			//rechecks to see if which ones are now visible
			h.checkCanDraw();
		}
	}
	
	public static void CycleColors(int delta)
	{
		if(colorState == 0){
		    green += delta;
		    if(green >= 255)
		    	colorState = 1;
		}
		if(colorState == 1){
		    red -= delta;
		    if(red <= 100)
		    	colorState = 2;
		}
		if(colorState == 2){
		    blue += delta;
		    if(blue >= 255)
		    	colorState = 3;
		}
		if(colorState == 3){
		    green -= delta;
		    if(green <= 0)
		    	colorState = 4;
		}
		if(colorState == 4){
		    red += delta;
		    if(red >= 255)
		    	colorState = 5;
		}
		if(colorState == 5){
		    blue -= delta;
		    if(blue <= 0)
		    	colorState = 0;
		}
	}
}