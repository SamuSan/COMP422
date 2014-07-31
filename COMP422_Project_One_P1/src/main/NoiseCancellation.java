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
		out = new BufferedImage(src.getWidth(), src.getHeight(),
				src.getType());
		for (int j = 1; j < out.getWidth()-1; j++) {
			for (int i = 1; i < out.getHeight()-1; i++) {
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
//				System.out.println(i  + " : " + j);
				out.setRGB(j, i, pixVal);

			}
		}
//
		FileConverter f = new FileConverter();
		f.writeOut(out, "outputNoise");
	}

}
