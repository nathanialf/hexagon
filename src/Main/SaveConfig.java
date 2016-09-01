package Main;

import java.io.*;

public class SaveConfig 
{
	public SaveConfig()
	{
	}
	
	public void Save() throws IOException
	{
		Game.config.createNewFile();
		
		FileWriter fw = new FileWriter(Game.config);

		fw.write("Resolution-" + Game.app.getWidth() + "x" + Game.app.getHeight() + "\n");
		fw.write("Fullscreen-" + Game.fullScreen + "\n");
		fw.write("AntiAliasing-" + Game.alias + "\n");
		fw.write("Volume-" + Game.volume * 100 + "\n");
		fw.write("Hexes-" + Game.size + "\n");
		fw.write("Size-" + Game.hexSize / 8);
		
		fw.close();
	}
}
