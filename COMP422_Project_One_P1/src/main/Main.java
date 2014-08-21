package main;

import static main.FileConverter.*;

import java.awt.image.BufferedImage;

import operations.EdgeDetection;
import operations.ImageEnhancement;
import operations.MiningHubble;
import operations.NoiseCancellation;

public class Main {
	private static int oper = -1;
	private BufferedImage inputImg = null;
	private static FileConverter f = new FileConverter();

	public static void main(String[] args) {
		if (args.length > 1 || args.length ==0) {
			System.out
					.println("Incorrect Number of Arguments: Please enter either:\n"
					        + "'1' for Edge Detection\n"
					        + "'2' for Noise Cancellation Median Operator\n"
					        + "other programs can be found in the source jar as Python scripts");
		} 

		else {
			oper = Integer.parseInt(args[0]);
		}
if(oper != 1 && oper != 2){
    System.out.println("Incorrect Selection");
}
		switch (oper) {
		case 1:
			System.out.println("Edge Detection selected");
			doEdgeDetection();
			System.out.println("Done");
			break;
		case 2:
			System.out.println("Noise Cancellation selected");
			doNoiseCancellation();
			System.out.println("Done");
			break;

		default:
			break;
		}

	}

	private static void doEdgeDetection() {
		EdgeDetection ed = new EdgeDetection(f.convert("test-pattern.tif"));
		ed.performSobel();

	}

	private static void doNoiseCancellation() {
		NoiseCancellation nc = new NoiseCancellation(
				f.convert("ckt-board-saltpep.tif"));
		nc.performMedianNoiseCancellation();
	}


}
