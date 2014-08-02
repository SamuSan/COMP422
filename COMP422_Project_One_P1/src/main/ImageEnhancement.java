package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageEnhancement {

//	 private int[][] enhanceMask = { { -1, -1, -1 },
//	 { -1, 8, -1 },
//	 { -1, -1, -1 } };

//	private int[][] enhanceMask = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
//	private int[][] enhanceMask = { { 1, -2, -1 }, { -2, 5, -2 }, { 1, -2, -1 } };
	private int[][] enhanceMask = { { -1, -1, -1 }, { -1, 9, -1 }, { -1, -1, -1 } };

	private int width = 0;
	private int height = 0;
	private BufferedImage src;
	private BufferedImage tempImg;
	private BufferedImage out;

	public ImageEnhancement(BufferedImage in) {
		src = in;
		width = src.getWidth();
		height = src.getHeight();
	}

	public BufferedImage getSrc() {
		return src;
	}

	public void performImageEnhance() {
		System.out.println(src.getHeight());
		System.out.println(src.getWidth());
		int s1 = 0;
		int s2 = 0;
		int pixVal = 0;
		tempImg = new BufferedImage(width + 2, height + 2, src.getType());
		int[] srcData = src.getRGB(0, 0, width, height, null, 0, width);
		Color temp = new Color(255, 255, 255);

		for (int i = 0; i < tempImg.getWidth(); i++) {
			for (int j = 0; j < tempImg.getHeight(); j++) {
				tempImg.setRGB(i, j, temp.getRGB());
			}
		}

		for (int i = 1; i < tempImg.getWidth() - 2; i++) {
			for (int j = 1; j < tempImg.getHeight() - 2; j++) {
				int rgb = src.getRGB(i, j);
				// System.out.println(rgb);
				tempImg.setRGB(i, j, src.getRGB(i, j));
			}
		}

		FileConverter f = new FileConverter();
		f.writeOut(tempImg, "outputTest");

//		for (int i = 0; i < enhanceMask.length; i++) {
//			for (int j = 0; j < enhanceMask[0].length; j++) {
//
//				System.out.printf("%d  i: %d j :  %d\n", enhanceMask[i][j], i,
//						j);
//			}
//
//			System.out.println();
//		}

		out = new BufferedImage(tempImg.getWidth(), tempImg.getHeight(),
				tempImg.getType());
		for (int i = 1; i < tempImg.getWidth() - 1; i++) {
			for (int j = 1; j < tempImg.getHeight() - 1; j++) {
				
//				System.out.println("Original pixVal at start : "  + pixVal);
				pixVal += enhanceMask[0][0] * tempImg.getRGB(i - 1, j - 1);
				pixVal += enhanceMask[0][1] * tempImg.getRGB(i - 1, j);
				pixVal += enhanceMask[0][2] * tempImg.getRGB(i - 1, j + 1);
				pixVal += enhanceMask[1][0] * tempImg.getRGB(i, j - 1);
				pixVal += enhanceMask[1][1] * tempImg.getRGB(i, j);
				pixVal += enhanceMask[1][2] * tempImg.getRGB(i, j + 1);
				pixVal += enhanceMask[2][0] * tempImg.getRGB(i + 1, j);
				pixVal += enhanceMask[2][1] * tempImg.getRGB(i + 1, j - 1);
				pixVal += enhanceMask[2][2] * tempImg.getRGB(i + 1, j + 1);
//System.out.println("Pixvalue " + pixVal );
				// System.out.println("S1: "+s1+":: S2: "+ s2);
				// pixVal = (int )Math.sqrt(Math.pow(s1, 2) + Math.pow(s2, 2));
				out.setRGB(i, j, pixVal);
				pixVal = 0;
				// System.out.println("Pix Value: " + pixVal);

			}
		
		}

		f.writeOut(out, "outputEnhanced2");
	}

}
