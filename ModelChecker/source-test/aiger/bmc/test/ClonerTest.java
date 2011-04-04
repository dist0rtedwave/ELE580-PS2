package aiger.bmc.test;

import org.junit.Test;

import aiger.bmc.Cloner;
import aiger.model.AndGate;
import aiger.model.Expression;
import aiger.model.Latch;
import aiger.model.Not;

public class ClonerTest {
	
	@Test
	public void testFilpLatch(){
		Latch l = new Latch(0);
		Not n = new Not(l);
		l.setTheNextState(n);
		Cloner.clone(l);
	}
	
	@Test
	public void testTwoLatches(){
		Latch l1 = new Latch(0);
		Latch l2 = new Latch(0);
		l1.setTheNextState(l2);
		l2.setTheNextState(l1);
		Cloner.clone(l1);
		Cloner.clone(l2);
	}
	
//	@Test
//	public void circularAnd(){
//		Latch l1 = new Latch(0);
//		AndGate a = new AndGate(2);
//		a.setTheLeftInput(a);
//		a.setTheRightInput(a);
//		l1.setTheNextState(a);
//		Cloner.clone(l1);
//	}
//	
//	@Test
//	public void circularNottedAnd(){
//		Latch l1 = new Latch(0);
//		AndGate a = new AndGate(2);
//		Not n = new Not(a);
//		a.setTheLeftInput(n);
//		a.setTheRightInput(a);
//		l1.setTheNextState(a);
//		Cloner.clone(l1);
//	}
	
}
