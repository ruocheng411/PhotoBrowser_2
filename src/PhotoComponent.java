import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class PhotoComponent extends JComponent implements MouseListener{

	BufferedImage image = null;
	int parentSizex;
	int parentSizey;
	int offsetx;
	int offsety;
	Image background;
	int photoIndexCurrent;
	ArrayList<Photo> photoList = new ArrayList<Photo>();;
	Boolean flipped = false;
	public File backgroundPath = FileSystems.getDefault().getPath("image", "background.jpg").toFile();
	PhotoBrowser photoBrowser = null;
	int index;
	
	public PhotoComponent(PhotoBrowser pb) {
		// TODO Auto-generated constructor stub
		photoBrowser = pb;
		try{
			background = ImageIO.read(backgroundPath);
			revalidate();
			repaint();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Resources loading error");
		}
		offsetx = 0;
		offsety = 0;
		photoIndexCurrent = -1;
//		addMouseListener(this);
		addMouseListener(new PhotoComponentMouseAdapter(this,photoBrowser));
		
		revalidate();
		repaint();
	}

	public void init() {
		System.out.println("Parent size = "+parentSizex+"  "+parentSizey);
		offsetx = 0;
		offsety = 0;
		updateComponentSize();
		revalidate();
		repaint();

	}

	public void updateComponentSize() {
		int w;
		int h;
		System.out.println("Photo "+photoIndexCurrent);
		if(photoIndexCurrent>=0){
			image = photoList.get(photoIndexCurrent).img;
			int imgW = image.getWidth();
			int imgH = image.getHeight();
			this.setSize(imgW, imgH);
			System.out.println("photo component size "+imgW+" * "+imgH);
			if((imgW>parentSizex)&&(imgH>parentSizey)){
				offsetx = 0;
				offsety = 0;
				w = imgW;
				h = imgH;
			}else if (imgW>parentSizex) {
				offsetx = 0;
				offsety = (parentSizey-imgH)/2;
				w = imgW;
				h = parentSizey;			
			}else if (imgH>parentSizey) {
				offsety = 0;
				offsetx = (parentSizex-imgW)/2;
				w = parentSizex;
				h = imgH;
			}else {
				offsetx = (parentSizex-imgW)/2;
				offsety = (parentSizey-imgH)/2;
				w = parentSizex;
				h = parentSizey;
			}
		}else{
			w = 0;
			h = 0;
		}
		setPreferredSize(new Dimension(w, h));
	}

	public void deletePhoto() {
		// TODO Auto-generated method stub
		if (photoIndexCurrent>-1) {
			if(photoIndexCurrent<=photoList.size()-1){
				photoList.remove(photoIndexCurrent);
				photoBrowser.setStatusMes(" photo "+photoIndexCurrent+" has been deleted");
				photoIndexCurrent --;
				if(photoIndexCurrent>=0){
					photoList.get(photoIndexCurrent).loadPhoto();
				}
				init();
			}else {
				System.out.println("photo choose error");
			}
			System.out.println("Opened photo number "+photoList.size());			
		}else {
			System.out.println("There is no photo.");
		}
	}

	public void addPhotos(File[] paths) {
		// TODO Auto-generated method stub
		for (int i=0; i<paths.length;++i) {
			Photo photo = new Photo(paths[i]);
			photoList.add(photo);
		}
		photoIndexCurrent = photoList.size()-1;
		loadPhoto(photoIndexCurrent);
		init();
		photoBrowser.setStatusMes(photoList.size()+" photo(s) has been opened");
	}

	public void loadPhoto(int index) {
		try
		{
			photoList.get(index).loadPhoto();
			System.out.println("Image: "+photoList.get(index).filePath);
		}catch ( Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override  
	public void paintComponent(Graphics graphics) { 
		super.paintComponent(graphics);
		paintBackground(graphics);
		if(photoIndexCurrent>-1){
			image = photoList.get(photoIndexCurrent).img;
			if ((image != null)&&(flipped == false))  {
				paintPhoto(graphics);
			}
			if ((image != null)&&flipped) {
				System.out.println("flipped the photo");
				paintPhotoBack(graphics);
			}
		}
	}  
	
	private void paintBackground(Graphics graphics){
		graphics.drawImage(background, 0, 0, null);
	}

	private void paintPhoto(Graphics graphics) { 
		super.paintComponent(graphics);
		int x = offsetx;  
		int y = offsety;  
		Graphics g = (Graphics) graphics;  
		if (null == image) { 
			System.out.println("error");
			return;  
		}  
		g.drawImage(image, x, y, image.getWidth(null), image.getHeight(null),null); 
	}
	
	private void paintPhotoBack(Graphics graphics) { 
		super.paintComponent(graphics);
		int x = offsetx;  
		int y = offsety;  
		photoList.get(photoIndexCurrent).imgBack = (BufferedImage) createImage(image.getWidth(),image.getHeight());
		Graphics2D g2d = (Graphics2D) photoList.get(photoIndexCurrent).imgBack.getGraphics();  
		g2d.setColor(Color.white);
		g2d.fillRect(x, y, image.getWidth(), image.getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);		
		
		int j = 0;
		while (j <= index) {
			draw(g2d, itemList[j]);
			j++;
		}
		g.drawImage(image2, 0, 0, null);

		
		graphics.drawImage(photoList.get(photoIndexCurrent).imgBack, x, y, null); 

	}

	public void name() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
//			flipped = true;
//			System.out.println("Mouse double clicked");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
