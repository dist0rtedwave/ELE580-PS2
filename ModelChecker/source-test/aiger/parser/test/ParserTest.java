	package aiger.parser.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import aiger.model.AigerFile;
import aiger.parser.AigerParser;

import util.Util;

public class ParserTest {

	private String examplesDir = "aigExamples";
	private String simpleExamples = examplesDir + File.separator + "simpleExamples";
	
	public AigerFile parseFile(String qualifiedFileName){
		String s="";
		try {
			s = Util.readFileAsString(qualifiedFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		AigerParser ap = new AigerParser();
		return ap.parseFile(s);
	}
	
	@Test
	public void testNotLiteral(){
		File f = new File(simpleExamples + File.separator + "notLiteral.aig");
		AigerFile ret = parseFile(f.getAbsolutePath());
	}
	
	@Test
	public void testBuffer(){
		File f = new File(simpleExamples + File.separator + "buffer.aig");
		AigerFile ret = parseFile(f.getAbsolutePath());
	}
	
	@Test
	public void testInverter(){
		File f = new File(simpleExamples + File.separator + "inverter.aig");
		AigerFile ret = parseFile(f.getAbsolutePath());
	}
	
	@Test
	public void testSimpleState(){
		File f = new File(simpleExamples + File.separator + "simplestate.aig");
		AigerFile ret = parseFile(f.getAbsolutePath());
	}
	
}
