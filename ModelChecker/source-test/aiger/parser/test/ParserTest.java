package aiger.parser.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import aiger.model.AigerFile;
import aiger.parser.AigerParser;

import util.Util;

public class ParserTest {

	private String examplesDir = "aigExamples";
	private String simpleExamples = File.separator + "simpleExamples";
	
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
		File f = new File(examplesDir + simpleExamples);
		parseFile(f.getAbsolutePath());
	}
	
}
