package logic.converter.test;

import java.util.HashSet;
import java.util.Set;

import logic.model.Expression;

public class CycleCheckerContext {
	 boolean cycleDetected=false;
	 Set<Expression> seen = new HashSet<Expression>();
}
