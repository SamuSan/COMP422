package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public  class FileConverter {



	public BufferedImage convert(String s) {
		BufferedImage src = null;
		try{
		File outputFile = new File(s.substring(0, s.indexOf('.')) + ".bmp");
		BufferedImage image = ImageIO.read(this.getClass().getResource(s));
		BufferedImage convertedImage = new BufferedImage(image.getWidth(),
		image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		convertedImage.createGraphics().drawRenderedImage(image, null);
		ImageIO.write(convertedImage, "bmp", outputFile);
		src = ImageIO.read(outputFile);
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
		return src;

	}
}
