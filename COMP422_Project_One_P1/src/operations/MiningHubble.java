package operations;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import main.FileConverter;

public class MiningHubble {

	int maxVal = Integer.MIN_VALUE;
	int minVal = Integer.MAX_VALUE;
	int thres = 0;
	private BufferedImage src;
	private BufferedImage tempImg;
	private FileConverter f = new FileConverter();

	private Color black = Color.BLACK;
	private Color white = Color.WHITE;

	private float[][] smthFlt = { { 1/9f, 1/9f, 1/9f }, { 1/9f, 1/9f, 1/9f }, { 1/9f, 1/9f, 1/9f } };

	public MiningHubble(BufferedImage in) {
		src = in;
	}

	public void mineSpace() {
		BufferedImage out = new BufferedImage(src.getWidth(), src.getHeight(),
				src.getType());

		for (int i = 0; i < src.getWidth(); i++) {
			for (int j = 0; j < src.getHeight(); j++) {
				if (src.getRGB(i, j) > maxVal) {
					maxVal = src.getRGB(i, j);
				}
				if (src.getRGB(i, j) < minVal) {
					minVal = src.getRGB(i, j);
				}
			}
		}
		System.out.println("Min Val : " + minVal + " MaxVal: " + maxVal);
		System.out.println(Math.abs(maxVal - minVal) / 2);
		System.out.println("Threshold = " + thres);
		thres = (maxVal - minVal) / 3;
		thres = -1 * thres;
//		thres *= 5;
		System.out.println("Threshold = " + thres);
		tempImg = new BufferedImage(src.getWidth(), src.getHeight(),
				src.getType());
		for (int i = 1; i < src.getWidth() - 1; i++) {
			for (int j = 1; j < src.getHeight() - 1; j++) {
				float pixVal = 0;
//				System.out.println("Original"+src.getRGB(i, j));
				pixVal += smthFlt[0][0] * src.getRGB(i - 1, j - 1);
				pixVal += smthFlt[0][1] * src.getRGB(i - 1, j);
				pixVal += smthFlt[0][2] * src.getRGB(i - 1, j + 1);
				pixVal += smthFlt[1][0] * src.getRGB(i, j - 1);
				pixVal += smthFlt[1][1] * src.getRGB(i, j);
				pixVal += smthFlt[1][2] * src.getRGB(i, j + 1);
				pixVal += smthFlt[2][0] * src.getRGB(i + 1, j);
				pixVal += smthFlt[2][1] * src.getRGB(i + 1, j - 1);
				pixVal += smthFlt[2][2] * src.getRGB(i + 1, j + 1);
//				System.out.println("Output"+pixVal);
				tempImg.setRGB(i, j, (int)pixVal);

			}
		}

		for (int j = 1; j < tempImg.getWidth() - 1; j++) {
			for (int i = 1; i < tempImg.getHeight() - 1; i++) {
				ArrayList<Integer> vals = new ArrayList<Integer>();
				int pixVal = 0;
				vals.add(tempImg.getRGB(j - 1, i - 1));// topleft
				vals.add(tempImg.getRGB(j - 1, i));// topmid
				vals.add(tempImg.getRGB(j - 1, i + 1));// topright
				vals.add(tempImg.getRGB(j, i - 1));// midleft
				vals.add(tempImg.getRGB(j, i));// midmid
				vals.add(tempImg.getRGB(j, i + 1));// midright
				vals.add(tempImg.getRGB(j + 1, i - 1));// botleft
				vals.add(tempImg.getRGB(j + 1, i));// botmid
				vals.add(tempImg.getRGB(j + 1, i + 1));// botright

				Collections.sort(vals);
				pixVal = vals.get(5);
				tempImg.setRGB(j, i, pixVal);
			}
		}	
		
		
		
		
		f.writeOut(tempImg, "smoothTest");

		for (int i = 0; i < tempImg.getWidth(); i++) {
			for (int j = 0; j < tempImg.getHeight(); j++) {
				if (tempImg.getRGB(i, j) > thres) {
					out.setRGB(i, j, white.getRGB());

				} else if (tempImg.getRGB(i, j) < thres) {
					out.setRGB(i, j, black.getRGB());
				}
			}
		}
		f.writeOut(out, "hubbleOutput");
	}
}
