import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.midi.VoiceStatus;

public class PhotoComponentMouseAdapter extends MouseAdapter{
	PhotoComponent photoComponent = null;
	PhotoBrowser photoBrowser = null;
	public PhotoComponentMouseAdapter(PhotoComponent pc,PhotoBrowser pb) {
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
//			System.out.println(photoComponent.flipped);
		}
	}
	
	public void mouseEntered(MouseEvent m) {
		// TODO mouse in
		}
	
	@Override
	public void mouseExited(MouseEvent me) {
		// TODO mouse out
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO mouse pressed
}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO mouse released
		
	}

}
	

