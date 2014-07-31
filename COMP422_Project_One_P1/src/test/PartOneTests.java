package test;
import static main.FileConverter.*;
import junit.framework.TestCase;
import main.*;

import org.junit.*;

public class PartOneTests extends TestCase {

	private FileConverter f = new FileConverter();
	@Test
	public void testEDConstruct() {
		EdgeDetection ed = new EdgeDetection(f.convert("test-pattern.tif"));
		assertNotNull(ed.getSrc());

	}

}
