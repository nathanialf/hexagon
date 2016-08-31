package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import State.ControlsState;
import State.MapState;
import State.PauseState;
import State.PlayState;
import State.SettingsState;
import State.State;

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
	public static long size = 2000;
	//size of the hexagons from the center to a point
	public static int hexSize;
	//Randomizer HEx
	public static int checkHex;
	
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
	public static int w = 2560,h = 1440;
	public static boolean fullScreen = true;
	//show the FPS counter
	public static boolean showFPS = false;
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
	public static Sound sound;
	//If it will be muted
	public static boolean muted = false;
	//Volume
	public static float volume = 0.01f;
	
	//for when the game glitches
	public static Hexagon last;
	
	//if the game is paused this will be the menu displayed
	//public static Menu currentMenu;
	

	
	//GAME STATE
	//Will define what needs to happen when in each state to
	//improve performance over previous games
	private static State current_state;
	private static PlayState PLAY_STATE;
	private static PauseState PAUSE_STATE;
	private static SettingsState SETTINGS_STATE;
	private static ControlsState CONTROLS_STATE;
	private static MapState MAP_STATE;
	

	public static Font BIG_FONT;
	public static Font MEDIUM_FONT;
	public static Font SMALL_FONT;
	
	public Game() 
	{
		//whats at the top of the window
		super("HEXGAME");
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{	
		//Anti-aliasing
		g.setAntiAlias(alias);
		
		/*
		if (paused)
		{
			//adds a gray layer to dim the generation in the background
			g.setColor(new Color(0,0,0,175));
			g.fillRect(0, 0, app.getWidth(), app.getHeight());
			//currentMenu.draw(g);
		}
		*/
		
		current_state.render(g);
	}
	public void init(GameContainer gc) throws SlickException
	{
		//MULTIPLES OF 8
		hexSize = 2;
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
			sound.loop(1, volume);
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

		
		current_state = new State();
		PLAY_STATE = new PlayState();
		current_state = getPlayState();
		PAUSE_STATE = new PauseState();
		SETTINGS_STATE = new SettingsState();
		CONTROLS_STATE = new ControlsState();
		MAP_STATE = new MapState();
	}

	public void update(GameContainer gc, int delta) throws SlickException
	{
		//leave the game quickly
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			AL.destroy();
			System.exit(0);
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_F1))
		{
			showFPS = !showFPS;
		}
		
		////////////////////////////////////////////////////////
		boolean re = reset;
		if (hexes.size() < size)
		{
			//while its not done generating it continuously generates
			reset = new generateHexMap().procedural(re);
			//tries to garbage collect higher than 120MB in use
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
			if(gc.getInput().isKeyPressed(Input.KEY_ENTER) && !getState().is_open_animating && !clicked)
			{
				if(getState() == getPlayState())
				{
					setState(getPauseState());
					clicked = true;
				}
				else if(getState() == getPauseState())
				{
					setState(getPlayState());
					clicked = true;
				}
			}
			else if (clicked)
				clicked = false;
		}
		
		CycleColors(delta / 6);
		current_state.update(gc, delta);
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
		GL11.glReadBuffer(GL11.GL_FRONT);
		int width = Display.getDisplayMode().getWidth();
		int height= Display.getDisplayMode().getHeight();
		int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
		GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer );
		
		File file = new File("res/ScreenShots/screen_" + System.currentTimeMillis() + ".png"); // The file to save to.
		String format = "PNG"; // Example: "PNG" or "JPG"
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		   
		for(int x = 0; x < width; x++) 
		{
		    for(int y = 0; y < height; y++)
		    {
		        int i = (x + (width * y)) * bpp;
		        int red = buffer.get(i) & 0xFF;
		        int green = buffer.get(i + 1) & 0xFF;
		        int blue = buffer.get(i + 2) & 0xFF;
		        image.setRGB(x, height - (y + 1), (0xFF << 24) | (red << 16) | (green << 8) | blue);
		    }
		}
		   
		try {
		    ImageIO.write(image, format, file);
		} catch (IOException e) { e.printStackTrace(); }
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
		    if(red <= 150)
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

	
	public static State getState()							{return current_state;}
	public static PlayState getPlayState()					{return PLAY_STATE;}
	public static PauseState getPauseState()				{return PAUSE_STATE;}
	public static SettingsState getSettingsState()			{return SETTINGS_STATE;}
	public static ControlsState getControlsState()			{return CONTROLS_STATE;}
	public static MapState getMapState()					{return MAP_STATE;}
	
	public static void setState(State s)		
	{
		State old = current_state;
		
		current_state = s;
		current_state.openAnim();
		
		Rectangle newBody = new Rectangle(0, -current_state.BASE_HEIGHT, app.getWidth(), current_state.BASE_HEIGHT);
		
		old.setBackground(newBody);
	}
}