package Paintpane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Draw implements Serializable{
	int x1,y1;
	int x2,y2;
	int R,G,B;
	int type;
	float stroke;
	String s1;
	String s2;

	void draw(Graphics2D g2d){

	}
}

class Pencil extends Draw {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawLine(x1, y1, x2, y2);
	}
}

class Erase extends Draw {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(255, 255, 255));
		g2d.setStroke(new BasicStroke(stroke + 4, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawLine(x1, y1, x2, y2);
	}
}

class Text extends Draw {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setFont(new Font(s2, x2 + y2, ((int) stroke) * 18));
		if (s1 != null)
			g2d.drawString(s1, x1, y1);
	}
}

class Line extends Draw { 
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawLine(x1, y1, x2, y2);

	}
}

class Rect extends Draw { 
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawRect(Math.min(x1, x2), Math.min(y2, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class Circle extends Draw {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawOval(Math.min(x1, x2), Math.min(y2, y2),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
	}
}

class Oval extends Draw {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawOval(Math.min(x1, x2), Math.min(y2, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class RoundRect extends Draw {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawRoundRect(Math.min(x1, x2), Math.min(y2, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2), 50, 35);
	}
}