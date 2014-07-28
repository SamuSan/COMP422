package test;

import junit.framework.TestCase;
import main.*;

import org.junit.*;
public class PartOneTests extends TestCase {

	
	@Test
	public void testEDConstruct(){
		EdgeDetection ed = new EdgeDetection();
		
		
		
		
		assertTrue(ed.load("images/test-pattern.tif"));
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
