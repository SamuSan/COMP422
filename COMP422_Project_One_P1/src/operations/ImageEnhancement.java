package operations;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.FileConverter;

public class ImageEnhancement {

	// private int[][] enhanceMask = { { -1, -1, -1 },
	// { -1, 8, -1 },
	// { -1, -1, -1 } };

	private int[][] enhanceMask = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
	// private int[][] enhanceMask = { { 1, -2, -1 }, { -2, 5, -2 }, { 1, -2, -1
	// } };
	// private int[][] enhanceMask = { { -1, -1, -1 }, { -1, 9, -1 }, { -1, -1,
	// -1 } };

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
				tempImg.setRGB(i, j, src.getRGB(i, j));
			}
		}

		FileConverter f = new FileConverter();

		out = new BufferedImage(tempImg.getWidth(), tempImg.getHeight(),
				tempImg.getType());
		int thres = 30;
		for (int i = 1; i < tempImg.getWidth() - 1; i++) {
			for (int j = 1; j < tempImg.getHeight() - 1; j++) {

				pixVal += enhanceMask[0][0] * tempImg.getRGB(i - 1, j - 1);
				pixVal += enhanceMask[0][1] * tempImg.getRGB(i - 1, j);
				pixVal += enhanceMask[0][2] * tempImg.getRGB(i - 1, j + 1);
				pixVal += enhanceMask[1][0] * tempImg.getRGB(i, j - 1);
				pixVal += enhanceMask[1][1] * tempImg.getRGB(i, j);
				pixVal += enhanceMask[1][2] * tempImg.getRGB(i, j + 1);
				pixVal += enhanceMask[2][0] * tempImg.getRGB(i + 1, j);
				pixVal += enhanceMask[2][1] * tempImg.getRGB(i + 1, j - 1);
				pixVal += enhanceMask[2][2] * tempImg.getRGB(i + 1, j + 1);
				Color c = new Color(pixVal);
				Color orig = new Color(tempImg.getRGB(i, j));

				if (c.getRed() - orig.getRed() > thres
						|| c.getGreen() - orig.getGreen()> thres
						|| c.getBlue() - orig.getBlue() > thres) {
					System.out.printf("PIXVAL : %d  Original Val: %d\n",
							pixVal, tempImg.getRGB(i, j), c.getRed(),
							c.getGreen(), c.getBlue());
					System.out.printf("New  Red %d  Grn %d  Bl %d\n",
							c.getRed(), c.getGreen(), c.getBlue());
					System.out.printf("Orig Red %d  Grn %d  Bl %d\n",
							orig.getRed(), orig.getGreen(), orig.getBlue());
					pixVal = tempImg.getRGB(i, j);
				}
				out.setRGB(i, j, pixVal);
				pixVal = 0;
			}
		}
		f.writeOut(out, "outputEnhancedFinalThress"+thres+"");
	}

	public void performImageEnhanceImproved() {
		System.out.println(src.getHeight());
		System.out.println(src.getWidth());
		int s1 = 0;
		int s2 = 0;
		int pixVal = 0;
		tempImg = new BufferedImage(width + 2, height + 2, src.getType());
		Color temp = new Color(255, 255, 255);

		for (int i = 0; i < tempImg.getWidth(); i++) {
			for (int j = 0; j < tempImg.getHeight(); j++) {
				tempImg.setRGB(i, j, temp.getRGB());
			}
		}

		// for (int i = 1; i < tempImg.getWidth() - 2; i++) {
		// for (int j = 1; j < tempImg.getHeight() - 2; j++) {
		// int rgb = src.getRGB(i, j);
		// tempImg.setRGB(i, j, src.getRGB(i, j));
		// }
		// }
		// System.out.println(tempImg.getHeight());
		// System.out.println(tempImg.getWidth());
		FileConverter f = new FileConverter();
		f.writeOut(tempImg, "outputTest");

		out = new BufferedImage(tempImg.getWidth(), tempImg.getHeight(),
				tempImg.getType());
		for (int i = 1; i < tempImg.getWidth() - 1; i++) {
			for (int j = 1; j < tempImg.getHeight() - 1; j++) {
				int red, grn, bl = 0;

				Color c = new Color(tempImg.getRGB(i, j));
				// pixVal += enhanceMask[0][0] * tempImg.getRGB(i - 1, j - 1);
				// pixVal += enhanceMask[0][1] * tempImg.getRGB(i - 1, j);
				// pixVal += enhanceMask[0][2] * tempImg.getRGB(i - 1, j + 1);
				// pixVal += enhanceMask[1][0] * tempImg.getRGB(i, j - 1);
				// pixVal += enhanceMask[1][1] * tempImg.getRGB(i, j);
				// pixVal += enhanceMask[1][2] * tempImg.getRGB(i, j + 1);
				// pixVal += enhanceMask[2][0] * tempImg.getRGB(i + 1, j);
				// pixVal += enhanceMask[2][1] * tempImg.getRGB(i + 1, j - 1);
				// pixVal += enhanceMask[2][2] * tempImg.getRGB(i + 1, j + 1);
				// out.setRGB(i, j, pixVal);
				// pixVal = 0;
			}
		}
		f.writeOut(out, "outputEnhanced2");
	}

}
