import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import org.omg.CORBA.ParameterModeHelper;


public class PhotoMouseAdapter extends MouseAdapter{
	PhotoComponent photoComponent = null;
	PhotoBrowser photoBrowser = null;
	public PhotoMouseAdapter(PhotoComponent pc,PhotoBrowser pb) {
		// TODO Auto-generated constructor stub
		photoComponent = pc;
		photoBrowser = pb;
	}

	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		if(e.getClickCount() == 2){
			photoComponent.flipped = !photoComponent.flipped;
			photoComponent.init();
			photoBrowser.setStatusMes("Fliping "+photoComponent.flipped);
			if (photoComponent.flipped) {			
				photoBrowser.imageToolBar.setVisible(true);;
			}else{
				photoBrowser.imageToolBar.setVisible(false);
			}
		}
	}

	public void mouseEntered(MouseEvent m) {
		// TODO mouse in
		photoBrowser.setStatusMes("mouse entred position: ["+m.getX()+","+m.getY()+"]");
	}

	@Override
	public void mouseExited(MouseEvent m) {
		// TODO mouse out
	}

	@Override
	public void mousePressed(MouseEvent m) {
		photoComponent.requestFocus();
		// TODO mouse pressed
		photoBrowser.setStatusMes("mouse pressed position: ["+m.getX()+","+m.getY()+"]");
		
		if(photoComponent.flipped){
			photoComponent.createNewDraw();
			photoComponent.stroke.x1 = photoComponent.stroke.x2 = m.getX();
			photoComponent.stroke.y1 = photoComponent.stroke.y2 = m.getY();
			
			if (photoComponent.currentChoice == 5 || photoComponent.currentChoice == 6) {
				photoComponent.stroke.path2d.moveTo(m.getX(), m.getY());
			}

			photoComponent.updateStrokeParameters();
			
			photoComponent.strokeList.add(photoComponent.stroke);
			photoComponent.repaint();
			photoComponent.index++;	

		}

	}

	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO mouse released
		photoBrowser.setStatusMes("mouse released position ["+m.getX()+","+m.getX()+"]");

	}

	@Override
	public void mouseDragged(MouseEvent m)// mouse dragged 
	{
		photoBrowser.setStatusMes("mouse dragged position [" + m.getX() + " ," + m.getY() + "]");
		if(photoComponent.flipped){
			if (photoComponent.currentChoice == 5 || photoComponent.currentChoice == 6) {
				photoComponent.stroke.path2d.lineTo(m.getX(), m.getY());
				
			}else {
				photoComponent.stroke.x2 = m.getX();
				photoComponent.stroke.y2 = m.getY();

			}


			photoComponent.repaint();
		}
	}

	public void mouseMoved(MouseEvent m)// mouse move
	{
		photoBrowser.setStatusMes("mouse position£∫[" + m.getX() + " ," + m.getY() + "]");
	}


}


