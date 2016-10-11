
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.io.Serializable;
import java.util.ArrayList;

public class Drawing implements Serializable{
	int x1,y1;
	int x2,y2;
	int R,G,B;
	int type;
	float thickness;
	String s1 = "" ; 
	String s2 = "TimesRoman";
	Path2D path2d;

	void draw(Graphics2D g2d){

	}
}

class Pencil extends Drawing {

	public Pencil() {
		// TODO Auto-generated constructor stub
		path2d = new Path2D.Float();
	}

	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.draw(path2d);

	}
}

class Erase extends Drawing {
	public Erase() {
		// TODO Auto-generated constructor stub
		path2d = new Path2D.Float();
		R = 255;
		G = 255;
		B = 255;
	}
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(255, 255, 255));
		g2d.setStroke(new BasicStroke(thickness + 4, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.draw(path2d);
	}
}

class Text extends Drawing {
	int width;
	int height;
	public Text(int w, int h) {
		// TODO Auto-generated constructor stub
		width = w;
		height = h;
	}
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setFont(new Font(s2, Font.PLAIN, ((int) thickness) * 18));
		if (s1 != null){
			FontMetrics fontMetrics = g2d.getFontMetrics();
			int stringH = fontMetrics.getHeight();
			int stringW = fontMetrics.stringWidth(s1);
			System.out.println(x1 + stringW);
			//			System.out.println(width);

			int space = width-x1-5;
			String string = s1;

			if(stringW<=space){
				int index = 0;
				boolean hasTab = false;
				while (fontMetrics.stringWidth(string)>0) {
					for(int i=0; i<string.length();i++){
						hasTab = false;
						if((string.charAt(i) == '\n')){
							hasTab = true;
							String sPrint = string.substring(0, i+1);
							g2d.drawString(sPrint, x1, y1+stringH*index);
							string = string.substring(i+1, string.length());
							System.out.println("press return");
							index++;
							break;
						}
						//					g2d.drawString(s1, x1, y1);
					}
					if(!hasTab){
						g2d.drawString(string, x1, y1+stringH*index);
						System.out.println("did not have return");
						break;
					}
				}
			}else{
				string = s1;
				int index = 0;
				while (fontMetrics.stringWidth(string)> 0) {
					int pos1 = 0;
					int pos2 = 0;
					int pos3 = 0;

					System.out.println(fontMetrics.stringWidth(string)+" string " +string);
					for(int i=0;i<=string.length();i++){

						if((i<string.length())&&(string.charAt(i) == '\n')){
							//							String sPrint = string.substring(0, i+1);
							//							g2d.drawString(sPrint, x1, y1+stringH*index);
							//							string = string.substring(i+1, string.length());
							pos3 = i;
							System.out.println("tab or return "+pos3);
							break;
						}

						String line = string.substring(0, i);
						if(fontMetrics.stringWidth(line)>space){
							while((i<string.length())&&(string.charAt(i)==' ')){
								i++;
							}
							pos1 = i;
							break;
						}
					}

					for(int i=pos1-1;i>0;i--){
						if(string.charAt(i)== ' '){
							pos2 = i;
							break;
						}
					}
					if (pos3 != 0) {
						String sPrint = string.substring(0, pos3+1);
						g2d.drawString(sPrint, x1, y1+stringH*index);
						string = string.substring(pos3+1, string.length());
						index++;
					}
					else if (pos2 != 0) {
						String sPrint = string.substring(0, pos2+1);
						g2d.drawString(sPrint, x1, y1+stringH*index);
						string = string.substring(pos2+1, string.length());
						index++;
						//						System.out.println("pos2 "+sPrint);
					}else if(pos1 !=0){
						String sPrint = string.substring(0,pos1-1);
						g2d.drawString(sPrint, x1, y1+stringH*index);
						string = string.substring(pos1-1, string.length());
						index++;
					}else {
						break;
					}



				}
				g2d.drawString(string, x1, y1+stringH*index);
			}
			///case1 /n
			///case2 空格的地方换行


		}
	}
}

class Line extends Drawing { 
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawLine(x1, y1, x2, y2);

	}
}

class Rect extends Drawing { 
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness));
		g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class Circle extends Drawing {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness));
		g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
	}
}

class Oval extends Drawing {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness));
		g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class RoundRect extends Drawing {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness));
		g2d.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2), 50, 50);
	}
}