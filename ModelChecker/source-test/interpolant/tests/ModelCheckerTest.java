package interpolant.tests;

import interpolant.checker.ModelChecker;

import java.io.File;
import java.io.IOException;

import logic.executor.MiniSat;
import logic.model.Expression;

import org.junit.Test;

import proof.model.Proof;
import aiger.bmc.Unroller;
import aiger.model.AigerFile;
import aiger.parser.AigerParser;

public class ModelCheckerTest {
	private String examplesDir = "aigExamples";
	private String simpleExamples = examplesDir + File.separator + "simpleExamples";
	private String benchmarks = examplesDir + File.separator + "converted";
	
	public static void testModelChecker(File file){
		ModelChecker.runChecker(file);
	}
	
	@Test
	public void testSimpleState(){ 
		File f = new File(simpleExamples + File.separator + "simplestate.aig");
		testModelChecker(f);
	}
	
	@Test
	public void testTwoState(){
		File f = new File(simpleExamples + File.separator + "twostates.aig");
		testModelChecker(f);
	}
	
	@Test
	public void testEx1(){
		File f = new File(benchmarks + File.separator + "cmu.dme1.B.aig.aag");
		testModelChecker(f);
	}
	
	@Test
	public void testSmallestBenchmark(){
		File f = new File(benchmarks + File.separator + "nusmv.syncarb5^2.B.aig.aag");
		testModelChecker(f);
	}
	
	
	
	@Test
	public void testSimpleSafety(){
		File f = new File(simpleExamples + File.separator + "simple-safety.aag");
		testModelChecker(f);
	}
	
	@Test
	public void testAlwaysFalse(){
		File f = new File(simpleExamples + File.separator + "alwayszero.aig");
		testModelChecker(f);
	}
	
	@Test
	public void testExample0(){
		File f = new File(benchmarks + File.separator + "eijk.bs4863.S.aig.aag");
		testModelChecker(f);
	}
	
	@Test
	public void testExample1(){
		File f = new File(benchmarks + File.separator + "texas.ifetch1^5.E.aig.aag");
		testModelChecker(f); 
	}
	
	@Test
	public void testExample2(){
		File f = new File(benchmarks + File.separator + "ken.flash^03.C.aig.aag");
		testModelChecker(f);
	}
	
	@Test
	public void testExample3(){
		File f = new File(benchmarks + File.separator + "nusmv.dme1-16.B.aig.aag");
		testModelChecker(f);
	}
}
