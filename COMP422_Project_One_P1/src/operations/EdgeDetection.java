package operations;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.FileConverter;

public class EdgeDetection {

	private int[][] rowMask = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
	private int[][] colMask = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
	private int width = 0;
	private int height = 0;
	private BufferedImage src;
	private BufferedImage tempImg;
	private BufferedImage out;

	public EdgeDetection(BufferedImage in) {
		src = in;
		width = src.getWidth();
		height = src.getHeight();
	}

	public BufferedImage getSrc() {
		return src;
	}

	public void performSobel() {
		System.out.println(src.getHeight());
		System.out.println(src.getWidth());
		int s1 = 0;
		int s2 = 0;
		int pixVal =0;
		tempImg = new BufferedImage(width + 2, height + 2, src.getType());
		int[] srcData = src.getRGB(0, 0, width, height, null, 0, width);
		Color temp = new Color(255, 255, 255);

		for (int i = 0; i < tempImg.getWidth(); i++) {
			for (int j = 0; j < tempImg.getHeight(); j++) {
				tempImg.setRGB(j, i, temp.getRGB());
			}
		}

		for (int i = 1; i < tempImg.getWidth() - 2; i++) {
			for (int j = 1; j < tempImg.getHeight() - 2; j++) {
				int rgb = src.getRGB(j, i);
//				System.out.println(rgb);
				tempImg.setRGB(j, i, src.getRGB(j, i));
			}
		}

		for (int i = 0; i < rowMask.length; i++) {
			for (int j = 0; j < rowMask[0].length; j++) {
//				System.out.printf("%d", rowMask[i][j]);
			}
//			System.out.println();
		}

		for (int i = 0; i < rowMask.length; i++) {
			for (int j = 0; j < rowMask[0].length; j++) {
//				System.out.printf("%d i:%d  j: %d  ", colMask[i][j], i, j);
			}
//			System.out.println();
		}
out = new BufferedImage(tempImg.getWidth(), tempImg.getHeight(), tempImg.getType());
		for (int i = 1; i < tempImg.getWidth()-1; i++) {
			for (int j = 1; j < tempImg.getHeight()-1; j++) {
				s1=0;
				s1 += rowMask[0][0] * tempImg.getRGB(j - 1, i - 1)// topleft
						+ rowMask[0][1] * tempImg.getRGB(j - 1, i)// topmid
						+ rowMask[0][2] * tempImg.getRGB(j - 1, i + 1)// topright
						+ rowMask[1][0] * tempImg.getRGB(j, i - 1)// midleft
						+ rowMask[1][1] * tempImg.getRGB(j, i)//midmid
						+ rowMask[1][2] * tempImg.getRGB(j, i + 1)// midright
						+ rowMask[2][0] * tempImg.getRGB(j + 1, i - 1)// botleft
						+ rowMask[2][1] * tempImg.getRGB(j + 1, i)// botmid
						+ rowMask[2][2] * tempImg.getRGB(j + 1, i + 1);// botright
				
				s2=0;
				s2 += colMask[0][0] * tempImg.getRGB(j - 1, i - 1)// topleft
						+ colMask[0][1] * tempImg.getRGB(j - 1, i)// topmid
						+ colMask[0][2] * tempImg.getRGB(j - 1, i + 1)// topright
						+ colMask[1][0] * tempImg.getRGB(j, i - 1)// midleft
						+ colMask[1][1] * tempImg.getRGB(j, i)//midmid
						+ colMask[1][2] * tempImg.getRGB(j, i + 1)// midright
						+ colMask[2][0] * tempImg.getRGB(j + 1, i - 1)// botleft
						+ colMask[2][1] * tempImg.getRGB(j + 1, i)// botmid
						+ colMask[2][2] * tempImg.getRGB(j + 1, i + 1);// botright
				
//				System.out.println("S1: "+s1+":: S2: "+ s2);
				pixVal = (int )Math.sqrt(Math.pow(s1, 2) + Math.pow(s2, 2));
				out.setRGB(j, i, pixVal);
//				System.out.println("Pix Value: " + pixVal);

			}
		}

		 FileConverter f = new FileConverter();
		 f.writeOut(out, "outputEdgeDetected");
	}

}
