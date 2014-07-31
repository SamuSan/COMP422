package main;

import static main.FileConverter.*;

import java.awt.image.BufferedImage;

public class Main {
	private static int oper = -1;
	private BufferedImage inputImg = null;
	private static FileConverter f = new FileConverter();

	public static void main(String[] args) {
		if (args.length > 1) {
			System.out
					.println("Incorrect Number of Arguments: Please enter '1','2' or '3'");
		} else {
			oper = Integer.parseInt(args[0]);
		}

		switch (oper) {
		case 1:
			System.out.println("Edge Detection selected");
			doEdgeDetection();
			System.out.println("Done");
			break;
		case 2:
			System.out.println("Noise Cancellation selected");
			break;
		case 3:
			System.out.println("Image Enhancement selected");
			break;

		default:
			break;
		}

	}

	private static void doEdgeDetection() {
		EdgeDetection ed = new EdgeDetection(f.convert("test-pattern.tif"));
		ed.performSobel();

	}

}
