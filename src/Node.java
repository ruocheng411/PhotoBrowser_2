import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Node {
	public  Node parent;
	public ArrayList<Node> children;
	boolean visible;
	
	public void setVisible(boolean b) {
		visible = b;
	}
//	paint node and paint children
	public void cascadingPaint() {
		
	}
//	fill And Stroke Colors
	public void graphicalContext() {
		
	}
//	returns the bounds of this node
	public Rectangle getBounds() {
		Rectangle rectangle = null;
		return rectangle;
	}
	
}

