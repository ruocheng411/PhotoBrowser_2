import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PhotoBrowser extends JFrame{
	JMenuBar menuBar = new JMenuBar();  
	JMenu fileMenu = new JMenu("File");  
	JMenu viewMenu = new JMenu("View"); 
	JMenuItem importFile = new JMenuItem("Import");
	JMenuItem deleteFile = new JMenuItem("Delete");
	JMenuItem quitFile = new JMenuItem("Quit");

	JFileChooser fileChooser = new JFileChooser();
	JRadioButton photoView = new JRadioButton("Photo");
	JRadioButton browserView = new JRadioButton("Browser");
	JRadioButton splitModeView = new JRadioButton("Split mode");

	JPanel photoPanel = new JPanel(new BorderLayout());
	JLabel photoLabel = new JLabel();
	JPanel statusPanel = new JPanel();
	JLabel statusLabel = new JLabel("status");

	JToolBar toolBar = new JToolBar();
	ArrayList<JToggleButton> optionsListJTB = new ArrayList<JToggleButton>();

	String message = new String("");
	JScrollPane scrollPane;
	PhotoComponent photoComponent = new PhotoComponent();

	public PhotoBrowser() {
		// TODO Auto-generated constructor stub
		super("Photo Browser");
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setMaximumSize(screenSize);
		setMinimumSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(400, 300));
		setMenu();
		setMainArea();
		setToolbar();
		setVisible(true);
		validate();
		pack();	
	}


	private void setMenu(){
		setFileMenu();
		setViewMenu();
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		setJMenuBar(menuBar);
	}

	private void setMainArea() {
		Color ivory = new Color(255, 255, 240);
		photoPanel.setBackground(ivory);
		statusBar();
		add(statusPanel,java.awt.BorderLayout.SOUTH);
		add(photoPanel, BorderLayout.CENTER);
		setScrollPane();
		//		photoPanel.add(scrollPane);
		



	}

	private void setScrollPane() {
		scrollPane =  new JScrollPane(photoComponent,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//		scrollPane.setBackground(Color.black);
		scrollPane.setViewportView(photoComponent);
		Dimension screenSize = new Dimension(400, 300);
		scrollPane.setSize(screenSize);

	}
	private void setToolbar() {
		// TODO Auto-generated method stub
		addOptionsJTB();
		setSizeOfOptionsList();
		for(JToggleButton button : optionsListJTB){
			toolBar.add(button);
		}

		add(toolBar,java.awt.BorderLayout.NORTH);

	}


	private void setFileMenu() {
		// File Menu
		importFile.addActionListener(
				Event -> importFileFunc()
				);
		fileMenu.add(importFile);
		deleteFile.addActionListener(			
				Event -> deleteFileFunc()
				);
		fileMenu.add(deleteFile);

		quitFile.addActionListener(
				Event ->this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING))
				);
		fileMenu.add(quitFile);

	}

	private void importFileFunc() {
		// TODO Auto-generated method stub
		setStatusMes("Import File");
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "jpg","jpeg","png","gif","bmp"));
		int returnVal = fileChooser.showOpenDialog(this);
		if(returnVal == JFileChooser.CANCEL_OPTION){
			setStatusMes("Operation canceled");
			return;
		}
		if (returnVal == JFileChooser.ERROR_OPTION) {
			setStatusMes("Open error");
			return;
		}
		if(returnVal == 0)
		{
			File[] paths = fileChooser.getSelectedFiles();
			photoComponent.addPhotos(paths);
			//			scrollPane.add(photoComponent);
			System.out.println("scrollpane size "+scrollPane.getSize());
			System.out.println("photopanel size "+photoPanel.getSize());
			photoPanel.add(scrollPane,BorderLayout.CENTER);
//			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//			scrollPane.setPreferredSize(screenSize);
			repaint();
			validate();
		}

	}

	private void deleteFileFunc() {
		// TODO Auto-generated method stub
		setStatusMes("Delete File");
		photoComponent.deletePhoto();
	}

	private void setViewMenu() {
		// View Menu
		photoView.addActionListener(
				Event -> photoViewFunc()
				);
		viewMenu.add(photoView);
		browserView.addActionListener(
				event -> browserViewFunc()
				);
		viewMenu.add(browserView);
		splitModeView.addActionListener(
				Event -> splitModeViewFunc()
				);
		viewMenu.add(splitModeView);
	}

	private void splitModeViewFunc() {
		// TODO Auto-generated method stub
		setStatusMes("Split Mode");
	}

	private void browserViewFunc() {
		// TODO Auto-generated method stub
		setStatusMes("Browser");
	}

	private void photoViewFunc() {
		// TODO Auto-generated method stub
		setStatusMes("Photo");
	}

	public void statusBar(){
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel,BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusPanel.setBackground(Color.LIGHT_GRAY);

		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusLabel.setPreferredSize(new Dimension(100,16));
		statusLabel.setText(" Status : ");

		statusPanel.add(statusLabel, BorderLayout.WEST);
	}

	private void setStatusMes(String message){
		statusLabel.setText(" Status : " + message);
	}

	private void addOptionsJTB(){
		JToggleButton familyJTB = new JToggleButton("Family");
		familyJTB.addActionListener(event->familyJTBfunc());
		JToggleButton vacationJTB = new JToggleButton("Vaction");
		vacationJTB.addActionListener(event->vacationJTBfunc());
		JToggleButton schoolJTB = new JToggleButton("School"); 
		schoolJTB.addActionListener(event->schoolJTBfunc());
		optionsListJTB.add(familyJTB);
		optionsListJTB.add(vacationJTB);
		optionsListJTB.add(schoolJTB);
	}
	private void setSizeOfOptionsList(){

		Dimension maximumSize = new Dimension(60, 20);
		Dimension minimumSize = new Dimension(60, 20);
		for(JToggleButton button : optionsListJTB){
			button.setMaximumSize(maximumSize);
			button.setMinimumSize(minimumSize);
		}

	}
	private void schoolJTBfunc() {
		// TODO Auto-generated method stub
		setStatusMes("School");
	}


	private void vacationJTBfunc() {
		// TODO Auto-generated method stub
		setStatusMes("Vacation");

	}


	private void familyJTBfunc() {
		// TODO Auto-generated method stub
		setStatusMes("Famile");
	}


	public static void main(String[] args) {
		PhotoBrowser myBrowser = new PhotoBrowser();
	}



}
