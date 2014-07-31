package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EdgeDetection {

	private BufferedImage src;
	private BufferedImage out;

	public EdgeDetection(BufferedImage in) {
		src = in;
	}



	public BufferedImage getSrc() {
		return src;
	}

	public BufferedImage getOut() {
		return out;
	}
	
	

}
