package main;
import static main.Main.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EdgeDetection {

	private Image src;
	private Image out;
	
	public EdgeDetection (){
		
	}
	
	
	public boolean load(String file){
		
		InputStream input;
		try {
			input = ClassLoader.getSystemResourceAsStream(file);
			
			if(input != null){
				BufferedReader buff = new BufferedReader(new InputStreamReader(input));	
				String line = buff.readLine();
				while(line != null){
					d(line);
					
					 line = buff.readLine();
				}
				
				
				//src = new Image(image);
				
			
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	
	
}
