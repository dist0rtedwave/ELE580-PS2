package logic.executor.test;

import java.io.IOException;

import org.junit.Test;

import logic.executor.MiniSat;
import logic.model.EF;
import logic.model.Expression;
import logic.printer.DimacsPrinter;
import logic.test.examples.Examples;

public class MiniSatTest {
	
	@Test
	public  void testPrintExample1() throws IOException{
		MiniSat ms = new MiniSat();
		Boolean ret = ms.exec(Examples.getExample1());
		assert(ret==true);
	}
	
	@Test
	public  void testUnsatExample() throws IOException{
		MiniSat ms = new MiniSat();
		Boolean ret = ms.exec(Examples.getExample7());
		assert(ret==false);
	}
	
	@Test
	public  void testPrintExample2() throws IOException{
		MiniSat ms = new MiniSat();
		ms.exec(Examples.getTotalExample2());
	}
	
	@Test
	public  void testPrintExample3() throws IOException{
		MiniSat ms = new MiniSat();
		ms.exec(Examples.getTotalExample3());
	}
	
	
	@Test
	public  void testPrintExample4() throws IOException{
		MiniSat ms = new MiniSat();
		ms.exec(Examples.getTotalExample4());
	}
	
	
	@Test
	public  void testPrintExample5() throws IOException{
		MiniSat ms = new MiniSat();
		ms.exec(Examples.getTotalExample5());
	}
	
	
	@Test
	public  void testPrintExample6() throws IOException{
		MiniSat ms = new MiniSat();
		ms.exec(Examples.getTotalExample6());
	}
	
	@Test
	public  void testPrintExample7() throws IOException{
		MiniSat ms = new MiniSat();
		ms.exec(Examples.getTotalExample7());
	}
	
}
