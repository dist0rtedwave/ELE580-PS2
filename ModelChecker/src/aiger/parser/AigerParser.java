package aiger.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import util.Util;


import aiger.model.AigerFile;
import aiger.model.AndGate;
import aiger.model.Expression;
import aiger.model.FalseLiteral;
import aiger.model.Latch;
import aiger.model.Not;
import aiger.model.TrueLiteral;
import aiger.model.Variable;

public class AigerParser {
	private int m;
	private int i;
	private int l;
	private int o;
	private int a;
	
	private TrueLiteral theTrueLiteral=new TrueLiteral();
	private FalseLiteral theFalseLiteral= new FalseLiteral();
	
	private Map<Integer, Integer> unresolvedLatches = new HashMap<Integer, Integer>();
	private Map<Integer, Expression> symbols = new HashMap<Integer, Expression>(); 
	private List<Integer> unresolvedOutputs = new LinkedList<Integer>();
	private Map<Integer, List<Integer>> unresolvedAnds = new HashMap<Integer, List<Integer>>();
	
	private void readHeader(String header){
		Scanner s= new Scanner(header);
		s.next(); // throw out aag
		m=s.nextInt();
		i=s.nextInt();
		l=s.nextInt();
		o=s.nextInt();
		a=s.nextInt();
	}
	
	private Variable readInput(String var){
		Scanner s = new Scanner(var);
		int id=s.nextInt();
		id=id/2;
		Variable v = new Variable(id);
		symbols.put(v.getTheVariableName(), v);
		return v;
	}
	
	private Latch readLatch(String latch){
		Scanner s = new Scanner(latch);
		int id = s.nextInt()/2;
		int nextID = s.nextInt();
		unresolvedLatches.put(id, nextID);
		Latch l = new Latch(theFalseLiteral);
		symbols.put(id, l);
		return l;
	}
	
	private void readOutput(String output){
		Scanner s = new Scanner(output);
		unresolvedOutputs.add(s.nextInt());
	}
	
	public static AigerFile parseFile(File file){
		String fileString="";
		try {
			fileString = Util.readFileAsString(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		AigerParser parser = new AigerParser();
		return parser.parseFile(fileString);
	}
	
	public AigerFile parseFile(String file){
		Scanner lineScanner = new Scanner(file);
		readHeader(lineScanner.nextLine());
		
		for(int c=0; c<i; c++){
			readInput(lineScanner.nextLine());
		}
		
		for(int c=0; c<l; c++){
			readLatch(lineScanner.nextLine());
		}
		
		for(int c=0; c<o; c++){
			readOutput(lineScanner.nextLine());
		}
		
		for(int c=0; c<a; c++){
			readAndGate(lineScanner.nextLine());
		}
		AigerFile af = new AigerFile();
		link(af);
		af.setTheSymbols(symbols);
		return af;
	}
	
	private Expression getSymbol(int n){
		if(n==0){
			return theFalseLiteral;
		}
		else if(n==1){
			return theTrueLiteral;
		}
		else if(n%2==0){
			return symbols.get(n/2);
		}
		else{
			return new Not(symbols.get((n-1)/2));
		}
	}
	
	private void resolveAnds(){
		for(Integer i : unresolvedAnds.keySet()){
			List<Integer> l = unresolvedAnds.get(i);
			int lhs = l.get(0);
			int rhs = l.get(1);
			AndGate ag = (AndGate)symbols.get(i);
			ag.setTheLeftInput(getSymbol(lhs));
			ag.setTheRightInput(getSymbol(rhs));
		}
	}
	
	private Set<Latch> resolveLatches(){
		Set<Latch> latches = new HashSet<Latch>();
		for(Integer i: unresolvedLatches.keySet()){
			int expID = unresolvedLatches.get(i);
			Latch l = (Latch)symbols.get(i);
			l.setTheNextState(getSymbol(expID));
			latches.add(l);
		}
		return latches;
	}
	
	private Set<Expression> resolveOutputs(){
		Set<Expression> ret = new HashSet<Expression>();
		for(Integer i: unresolvedOutputs){
			ret.add(getSymbol(i));
		}
		return ret;
	}
	
	private void link(AigerFile af){
			resolveAnds();
			af.setTheLatches(resolveLatches());
			af.setTheOutputs(resolveOutputs());
	}
	
	private void readAndGate(String andGate) {
		Scanner s = new Scanner(andGate);
		int id = s.nextInt()/2;
		int lhsID = s.nextInt();
		int rhsID = s.nextInt();
		List<Integer> lis = new LinkedList<Integer>();
		lis.add(lhsID);
		lis.add(rhsID);
		unresolvedAnds.put(id, lis);
		AndGate ag = new AndGate(id);
		symbols.put(id, ag);
	}
}
