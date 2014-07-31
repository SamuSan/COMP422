package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

public class NoiseCancellation {

	private int width = 0;
	private int height = 0;
	private BufferedImage src;
	private BufferedImage tempImg;
	private BufferedImage out;

	public NoiseCancellation(BufferedImage in) {
		src = in;
		width = src.getWidth();
		height = src.getHeight();
	}

	public BufferedImage getSrc() {
		return src;
	}

	public void performNoiseCancellation() {
		System.out.println("Hiehgt "+src.getHeight());
		System.out.println("Width "+src.getWidth());
		int pixVal = 0;
		tempImg = new BufferedImage(width + 2, height + 2, src.getType());
		Color temp = new Color(255, 255, 255);
		System.out.println("Hiehgt "+tempImg.getHeight());
		System.out.println("Width "+(tempImg.getWidth()));
		for (int i = 0; i < tempImg.getWidth()-1; i++) {
			tempImg.setRGB(i,0, temp.getRGB());
			System.out.println(i);
//			for (int j = 0; j < tempImg.getHeight()-1; j++) {
//				System.out.printf(" i ; %d  j ; %d\n", i,j);
//				tempImg.setRGB(j,i, temp.getRGB());
//			}
		}

		for (int i = 1; i < tempImg.getWidth() - 2; i++) {
			for (int j = 1; j < tempImg.getHeight() - 2; j++) {
				int rgb = src.getRGB(j, i);
				tempImg.setRGB(j, i, src.getRGB(j, i));
			}
		}

		out = new BufferedImage(tempImg.getWidth(), tempImg.getHeight(),
				tempImg.getType());
		for (int i = 1; i < tempImg.getWidth() - 1; i++) {
			for (int j = 1; j < tempImg.getHeight() - 1; j++) {
				ArrayList<Integer> vals = new ArrayList<Integer>();

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
				out.setRGB(j, i, pixVal);

			}
		}

		FileConverter f = new FileConverter();
		f.writeOut(out, "outputNoise");
	}

}
