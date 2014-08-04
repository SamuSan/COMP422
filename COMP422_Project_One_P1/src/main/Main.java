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
			doNoiseCancellation();
			System.out.println("Done");
			break;
		case 3:
			System.out.println("Image Enhancement selected");
			doImageEnhancement();
			System.out.println("Done");
			break;
		case 4:
			System.out.println("Mining Space Image selected");
			doSpaceMining();
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
//		nc.performMedianNoiseWithTweakCancellation();
	}

	private static void doImageEnhancement() {
		ImageEnhancement ie = new ImageEnhancement(f.convert("blurry-moon.tif"));
		ie.performImageEnhance();
//		ie.performImageEnhanceImproved();
	}
	private static void doSpaceMining(){
		MiningHubble mh =  new MiningHubble(f.convert("hubble.tif"));
		mh.mineSpace();
		
	}
}
