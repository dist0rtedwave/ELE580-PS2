package aiger.bmc.test;

import java.io.File;
import java.io.IOException;

import logic.executor.MiniSat;
import logic.model.Expression;
import logic.test.examples.Examples;

import org.junit.Test;

import proof.model.NamePrinterTraverser;
import proof.model.Proof;

import aiger.bmc.Unroller;
import aiger.model.AigerFile;
import aiger.parser.AigerParser;

public class FullTest {
	private String examplesDir = "aigExamples";
	private String simpleExamples = examplesDir + File.separator + "simpleExamples";
	private String benchmarks = examplesDir + File.separator + "converted";
	
	public static void testRunner(File file, int k){
		AigerFile aigerFile = AigerParser.parseFile(file);
		Expression e = Unroller.unroll(aigerFile, k);
		
		MiniSat ms = new MiniSat();
		boolean res = true;
		try {
			res = ms.exec(e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(!res)
		{
			Proof p = new Proof();
			p.load("/tmp/proof");
			NamePrinterTraverser npt = new NamePrinterTraverser();
			npt.setNameMap(ms.getNameMap());
			p.traverse(npt);
		}
	}
	
	@Test
	public void testSimpleState(){ 
		File f = new File(simpleExamples + File.separator + "simplestate.aig");
		testRunner(f, 1);
	}
	
	@Test
	public void testTwoState(){
		File f = new File(simpleExamples + File.separator + "twostates.aig");
		testRunner(f, 1);
	}
	
	@Test
	public void testEx1(){
		File f = new File(benchmarks + File.separator + "cmu.dme1.B.aig.aag");
		testRunner(f, 2);
	}
	
	@Test
	public void testSmallestBenchmark(){
		File f = new File(benchmarks + File.separator + "nusmv.syncarb5^2.B.aig.aag");
		testRunner(f, 10);
	}
	
	@Test
	public void testSimpleSafety(){
		File f = new File(simpleExamples + File.separator + "simple-safety.aag");
		testRunner(f, 2);
	}
	
	@Test
	public void testAlwaysFalse(){
		File f = new File(simpleExamples + File.separator + "alwaysFalse.aig");
		testRunner(f, 2);
	}
	
	@Test
	public void testExample0(){
		File f = new File(benchmarks + File.separator + "eijk.bs4863.S.aig.aag");
		testRunner(f, 5);
	}
	
	@Test
	public void testExample1(){
		File f = new File(benchmarks + File.separator + "texas.ifetch1^5.E.aig.aag");
		testRunner(f, 5);
	}
	
	@Test
	public void testExample2(){
		File f = new File(benchmarks + File.separator + "ken.flash^03.C.aig.aag");
		testRunner(f, 3);
	}
	
	@Test
	public void testExample3(){
		File f = new File(benchmarks + File.separator + "nusmv.dme1-16.B.aig.aag");
		testRunner(f, 3);
	}
}
