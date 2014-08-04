package operations;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

import main.FileConverter;

public class NoiseCancellation {

	private int width = 0;
	private int height = 0;
	private BufferedImage src;
	private BufferedImage out;

	public NoiseCancellation(BufferedImage in) {
		src = in;
		width = src.getWidth();
		height = src.getHeight();
	}

	public BufferedImage getSrc() {
		return src;
	}

	public void performMedianNoiseCancellation() {
		int pixVal = 0;
		out = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
		for (int j = 1; j < out.getWidth() - 1; j++) {
			for (int i = 1; i < out.getHeight() - 1; i++) {
				ArrayList<Integer> vals = new ArrayList<Integer>();

				vals.add(src.getRGB(j - 1, i - 1));// topleft
				vals.add(src.getRGB(j - 1, i));// topmid
				vals.add(src.getRGB(j - 1, i + 1));// topright
				vals.add(src.getRGB(j, i - 1));// midleft
				vals.add(src.getRGB(j, i));// midmid
				vals.add(src.getRGB(j, i + 1));// midright
				vals.add(src.getRGB(j + 1, i - 1));// botleft
				vals.add(src.getRGB(j + 1, i));// botmid
				vals.add(src.getRGB(j + 1, i + 1));// botright

				Collections.sort(vals);
				pixVal = vals.get(5);
				out.setRGB(j, i, pixVal);
			}
		}
		FileConverter f = new FileConverter();
		f.writeOut(out, "outputNoise");
	}

	public void performMedianNoiseWithTweakCancellation() {
		int pixVal = 0;
		out = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
		for (int j = 1; j < out.getWidth() - 1; j++) {
			for (int i = 1; i < out.getHeight() - 1; i++) {
				int medX;
				int medCross;
				int pix;
				ArrayList<Integer> vals = new ArrayList<Integer>();

				
				//X
				vals.add(src.getRGB(j - 1, i - 1));//Topleft
				vals.add(src.getRGB(j - 1, i + 1));//Bottom Left
				vals.add(src.getRGB(j + 1, i - 1));// Top Rght
				vals.add(src.getRGB(j + 1, i + 1));// Bottom Right
				vals.add(src.getRGB(j, i));//Actual Pixel
				System.out.println(vals.size());
				Collections.sort(vals);
				medX = vals.get(2);
				vals.clear();
				
				//+
				vals.add(src.getRGB(j, i - 1));//Mid Top
				vals.add(src.getRGB(j - 1, i));// Mid Left
				vals.add(src.getRGB(j, i + 1));// // Bottom Mid
				vals.add(src.getRGB(j + 1, i));// Mid Right
				vals.add(src.getRGB(j, i));//Actual Pixel
				Collections.sort(vals);
				System.out.println(vals.size());
				medCross = vals.get(2);
				vals.clear();
				
				pix = src.getRGB(j, i);
				vals.add(medX);
				vals.add(medCross);
				vals.add(pix);
//				System.out.println(vals.size());
				//pixel
				Collections.sort(vals);
				pixVal = vals.get(1);
				out.setRGB(j, i, pixVal);
			}
		}

		FileConverter f = new FileConverter();
		f.writeOut(out, "outputNoiseTweaked");
	}

	public void performGaussianNoiseCancellation() {
		System.out.println("Hiehgt " + src.getHeight());
		System.out.println("Width " + src.getWidth());
		int pixVal = 0;
		out = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
		for (int j = 1; j < out.getWidth() - 1; j++) {
			for (int i = 1; i < out.getHeight() - 1; i++) {
				ArrayList<Integer> vals = new ArrayList<Integer>();

				vals.add(src.getRGB(j - 1, i - 1));// topleft
				vals.add(src.getRGB(j - 1, i));// topmid
				vals.add(src.getRGB(j - 1, i + 1));// topright
				vals.add(src.getRGB(j, i - 1));// midleft
				vals.add(src.getRGB(j, i));// midmid
				vals.add(src.getRGB(j, i + 1));// midright
				vals.add(src.getRGB(j + 1, i - 1));// botleft
				vals.add(src.getRGB(j + 1, i));// botmid
				vals.add(src.getRGB(j + 1, i + 1));// botright

				Collections.sort(vals);
				pixVal = vals.get(5);
				// System.out.println(i + " : " + j);
				out.setRGB(j, i, pixVal);

			}
		}
		//
		FileConverter f = new FileConverter();
		f.writeOut(out, "outputNoise");
	}

}
