package aiger.bmc;

import aiger.model.AigerFile;
import aiger.model.Latch;

public class Unroller {
// iterates over latches, calls clone on each. When done updates state
// then calls class to create CNF
	public static void unrollOnce(AigerFile af){
		for(Latch l : af.getTheLatches()){
			Cloner.clone(l);
		}
		for(Latch l : af.getTheLatches()){
			l.setTheCurrentState(l.getTheInterimState());
		}
	}
}
