package proof.model.test;


import java.io.IOException;

import logic.executor.MiniSat;
import logic.test.examples.Examples;

import org.junit.Test;

import proof.model.Proof;

public class ProofTest {

	@Test
	public  void testUnSat() throws IOException{
		MiniSat ms = new MiniSat();
		ms.exec(Examples.getExample7());
		
		Proof p = new Proof();
		p.traverse("/tmp/proof");
		p.printClauses();
	}
}
