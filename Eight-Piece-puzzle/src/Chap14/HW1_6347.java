package Chap14;

import javax.swing.JFrame;
import java.io.File;

public class HW1_6347 {

	public static void main(String[] args){
		String windowTitle = "Image Loaded";
		/*
		 * Sorry, I didn't have time to use the getClass().getResource(). I had in the photo
		 * in the folder with the src folder instead of where the .class file is, which is in the bin.
		 * Took me to long to figure out that and why I kept getting a NullPointerException.
		 * 
		 * String path is initialized to a file path of the wanted pic
		 */
		String path = new File("FGCU_logo.png").getAbsolutePath();
		File name = new File(path);
		if(name.exists()){
		JEightPuzzleFrame window = new JEightPuzzleFrame(windowTitle,path);
		}else{
			System.out.println("File location invalid");
			System.exit(0);
		}
	}
}