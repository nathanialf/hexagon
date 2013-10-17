package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log 
{
	public void saveLoading() throws IOException
	{
		File file = new File("log/tracking.csv");
		
		if (!file.exists())
			file.createNewFile();
		
		file.setWritable(true);
			
		FileWriter fw = new FileWriter(file, true);
		
		fw.append("\n" + System.currentTimeMillis());
		fw.append("," + Game.size);
		fw.append("," + (Game.hexes.size() == Game.size));
		fw.append("," + ((System.currentTimeMillis() - Game.startTime) / 1000) + " seconds");
		fw.append("," + Game.alias);
		fw.append("," + Game.app.getWidth()+ "x" + Game.app.getHeight());
		fw.append("," + Game.fullScreen);
		
		fw.flush();
		fw.close();
	}
}
