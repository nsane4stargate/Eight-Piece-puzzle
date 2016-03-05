package Chap14;


import java.awt.Graphics2D;
import java.util.Random; // random generator
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;//m
import java.awt.BorderLayout;
import java.awt.GridLayout; // how image component will lay
import java.io.File;// for reading and writing files
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;//for reading and writing images
import javax.swing.JFrame; // provide basic window
import javax.swing.Icon; // interface used to manipulate image
import javax.swing.ImageIcon; //loads images
import javax.swing.JButton;// make a JButton component
import javax.swing.JLabel; // make a JLabel component



public class JEightPuzzleFrame extends JFrame{
	private JFrame window;
	private static Icon image[] = new ImageIcon[9];
	private static BufferedImage pic; // variable were imaged selected will be stored
	private static String path;
	private static int rows = 3;
	private static int cols = 3;
	private static JButton buttons [] = new JButton [9];// 
	private static BufferedImage imgs[] = new BufferedImage[rows*cols];//
	private JLabel statusBar;
	
	/*
	 * Beginning of constructor
	 */
	
	public JEightPuzzleFrame(String windowTitle,String path){
		super(windowTitle);
		window = new JFrame();
		statusBar = new JLabel("");
		window.setLayout(new GridLayout(3,3));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPathName(path);
		System.out.println("Name: " + getPathName());
		makeJButtons();
		MouseHandler actions = new MouseHandler();

		for(int j =0; j < buttons.length;j++){
			buttons[j].add(statusBar, BorderLayout.CENTER);
			buttons[j].addMouseMotionListener(actions);
			buttons[j].addMouseListener(actions);
		}
		
	}// end of Constructor
	
	/*
	 * Set &Get methods for path name
	 */
	
	public void setPathName(String path){
		this.path = path;
	}
	public static String getPathName(){
		return path;
	}
	
	/*
	 *  Tried making a class that would have handled the actions when the mouse is used
	 */
	
	private class MouseHandler implements MouseListener,MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e){
			JButton temp []= new JButton[9];
			statusBar.setText(String.format("Clicked at[%d,%d]" ,e.getX(),e.getY()));
			for(int i = 0; i < buttons.length;i ++){
				if(e.getX()>= buttons[i].getWidth()|| e.getY()>= buttons[i].getHeight()){
					temp[i] = buttons[i];
					System.out.println("Button "+i+ " clicked");
					buttons[i].revalidate();
					System.out.println(e.getX()+ e.getY());
					System.out.println("Button "+i+ " clicked");
					buttons[i] = buttons[buttons.length -i];
					buttons[buttons.length -1] = temp[i];
					
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			e.getX();
			e.getY();
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			e.getX();
			e.getY();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			e.getX();
			e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			e.getX();
			e.getY();
		}

		
		
		@Override
		public void mouseDragged(MouseEvent e) {
			e.getX();
			e.getY();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			e.getX();
			e.getY();
		}
		

	}
	/*
	 * Method for making JButton and assigning each a random picture
	 */
	
	public void makeJButtons(){
		newImageFiles();
		Random randomNumber = new Random();
		int num =  randomNumber.nextInt(9);
		System.out.println("---Verifing all files posted on JButtons---");
		Boolean empty;
		int zero = 0;
		boolean runAgain = true;
		int numbersFilled[] = new int[9];// array made to keep track of what random numbers have been 
										// assigned an icon image already for BufferedButton[random]
		for(int j = 0; j< buttons.length; j++){
			if(buttons[j] != null){
				empty = false;
			}else {
				empty = true;
			}
			if(empty == true){
				while(runAgain){
					num = randomNumber.nextInt(9);
					if(num != 0 && numbersFilled[num]== 0){
						numbersFilled[num]= num;
						System.out.println("Array" + j + " filled with image" +num+ ".png" );
						buttons[j] = new JButton();
						buttons[j].setIcon(new ImageIcon("img"+ num + ".png"));// sets buttons to ImageIcon but file name
						buttons[j].setSize(pic.getWidth(),pic.getHeight());
						window.add(buttons[j],statusBar);
						break;
					}else if(num==0 && numbersFilled[num]==0){
						numbersFilled[num]= num;
						zero++;
						if(zero<2){
						System.out.println("Array" + 0 + " filled with image" +num+ ".png" );
						buttons[j] = new JButton();
						buttons[j].setIcon(new ImageIcon("img"+ num + ".png"));
						buttons[j].setSize(pic.getWidth(),pic.getHeight());
						window.add(buttons[0],statusBar);
						break;
						}else{
							runAgain = true;
						}
					}else if((num==0 && numbersFilled[num]!=0)){
						runAgain = true;
					}else if(num !=0 && numbersFilled[num ]!=0){
						runAgain = true;
					}
				}//end of While-Loop
			num = 0 + randomNumber.nextInt(8); // re-initialized random num variable after jumping out of the While-Loop
			}// end of IF-statement to add Images to JButtons
		}
		window.pack();
		window.setVisible(true);
		System.out.println("--------------------------------------------");
	}//end of makeButton method
	
	
	/*
	 * Method for m
	 */
	
	public static void newImageFiles(){
		/*
		 *  Reading images into a BufferedImage object via ImageIO.read
		 */
		System.out.println("Starting to read and write images.");
		try{
			File file = new File(getPathName());
			FileInputStream photo = new FileInputStream(file);
			pic = ImageIO.read(photo);
			String extension = file.getPath(); // variable made to later identify extension file type
			
			int count = 0;
			for(int x=0;x<rows;x++){
				for(int y=0;y<cols;y++){
					imgs[count] = new BufferedImage(pic.getWidth(), pic.getHeight(),BufferedImage.TYPE_INT_ARGB);
					/*
					 *  Creating and storing graphics into and array by dividing the picture up according to the grid dimensions
					 */
					Graphics2D gr = imgs[count++].createGraphics();
					gr.drawImage(pic, 0, 0, pic.getWidth(), pic.getHeight(), (pic.getWidth() * y)/3, (pic.getHeight() * x)/3, (pic.getWidth() * y + pic.getWidth())/3,(pic.getHeight() * x + pic.getHeight())/3, null);
					gr.dispose();
				}
			}
			System.out.println("Successful in writing images.");
			/*
			 * Writing every newly created image back into the location where the original photo is located
			 */
	        for(int i = 1; i <9; i++) {
	        	  ImageIO.write(imgs[i],extension.substring(extension.indexOf('.')+1),new File("img" + i +extension.substring(extension.indexOf('.'))));
	              image[i]= new ImageIcon(imgs[i]);     
	         }
		}catch(IOException e){
			System.err.println("That image does not exist in the location entered.");
			System.exit(1);
		}catch(NullPointerException e1){
			e1.printStackTrace();
		}
	}//end of newImageFiles
}

