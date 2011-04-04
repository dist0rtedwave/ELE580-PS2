package aiger.bmc;

import aiger.model.Latch;
import aiger.model.Visitor;

public class Cloner extends Visitor<ClonerContext>{

	public Cloner(ClonerContext g) {
		super(g);
		// TODO Auto-generated constructor stub
	}
	
	public static void clone(Latch l){
		
	}
// Deep traversal of expression, making a deep copy. 
// Replaces latch with latch.unrolled
	
}
