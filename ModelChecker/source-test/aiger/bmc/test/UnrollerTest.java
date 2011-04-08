package aiger.bmc.test;

import java.io.File;

import logic.model.Expression;
import logic.printer.PrintVisitor;

import org.junit.Test;

import aiger.bmc.Unroller;
import aiger.model.AigerFile;
import aiger.parser.AigerParser;

public class UnrollerTest {
	
	private String examplesDir = "aigExamples";
	private String simpleExamples = examplesDir + File.separator + "simpleExamples";
	private String benchmarks = examplesDir + File.separator + "converted";
	
	private Expression testUnroller(File file, int k){
		AigerFile aigerFile = AigerParser.parseFile(file);
		return Unroller.unroll(aigerFile, k);
	}
	
	@Test
	public void testSimpleState(){
		File f = new File(simpleExamples + File.separator + "simplestate.aig");
		testUnroller(f, 4);
	}
	
	@Test
	public void testTwoState(){
		File f = new File(simpleExamples + File.separator + "twostates.aig");
		testUnroller(f, 1);
	}
	
	@Test
	public void testEx1(){
		File f = new File(benchmarks + File.separator + "cmu.dme1.B.aig.aag");
		testUnroller(f, 2);
	}
	
	@Test
	public void testSmallestBenchmark(){
		File f = new File(benchmarks + File.separator + "nusmv.syncarb5^2.B.aig.aag");
		testUnroller(f, 1);
	}
	
	@Test
	public void testSimpleSafety(){
		File f = new File(simpleExamples + File.separator + "simple-safety.aag");
		System.out.println(PrintVisitor.expressionToString(testUnroller(f, 2)));
	}
	
	
	@Test
	public void testExample0(){
		File f = new File(benchmarks + File.separator + "eijk.bs4863.S.aig.aag");
		testUnroller(f, 20);
	}
	
	@Test
	public void testExample1(){
		File f = new File(benchmarks + File.separator + "texas.ifetch1^5.E.aig.aag");
		testUnroller(f, 20);
	}
	
	@Test
	public void testExample2(){
		File f = new File(benchmarks + File.separator + "ken.flash^03.C.aig.aag");
		testUnroller(f, 3);
	}
	
	@Test
	public void testExample3(){
		File f = new File(benchmarks + File.separator + "nusmv.dme1-16.B.aig.aag");
		testUnroller(f, 3);
	}
	
	
	
}
