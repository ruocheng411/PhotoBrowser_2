import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Photo {
	public File filePath;
	public BufferedImage img;
	
	public Photo(File path) {
		// TODO Auto-generated constructor stub
		filePath = path;
//		System.out.println("Image from : "+path);
//		loadPhoto();
	}
	
	public boolean loadPhoto()
	{
		try{
			img = ImageIO.read(filePath);
			System.out.println("The image size: "+img.getWidth()+ " * "+img.getHeight());
			return true;
		}catch (IOException ie) {
			System.out.println(ie.getMessage());
			return false;
		}
	}


}
