import java.awt.FontMetrics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PhotoKeyAdapter extends KeyAdapter {
	PhotoComponent photoComponent = null;
	PhotoBrowser photoBrowser = null;

	public PhotoKeyAdapter(PhotoComponent pc,PhotoBrowser pb) {
		// TODO Auto-generated constructor stub
		photoComponent = pc;
		photoBrowser = pb;
	}
	public void keyPressed(KeyEvent e){

	}
	public void keyReleased(KeyEvent e){

	}
	public void keyTyped(KeyEvent e){
		if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
			deleteChar();
		}if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
		}
		else{
			insertChar(e.getKeyChar());
		}

	}

	public void deleteChar() {
		if(photoComponent.stroke.s1.length()>1){
			String res = photoComponent.stroke.s1.substring(0, photoComponent.stroke.s1.length()-1);
			photoComponent.stroke.s1  = res;
			photoComponent.repaint();
		}
	}

	public void insertChar(char c) {
		photoComponent.stroke.s1 += c;
		photoComponent.repaint();

	}

}











