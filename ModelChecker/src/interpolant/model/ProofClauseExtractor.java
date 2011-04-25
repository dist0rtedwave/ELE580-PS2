package interpolant.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import proof.model.RootClause;

import logic.model.ClauseVisitor;
import logic.model.Variable;
import logic.printer.NameMap;

public class ProofClauseExtractor extends ClauseVisitor<Set<RootClause>> {
	NameMap nameMap;
	RootClause current;
	
	public ProofClauseExtractor(Set<RootClause> g)
	{
		super(g);
		this.current = new RootClause();
	}
	
	public void setNameMap(NameMap nameMap)
	{
		this.nameMap= nameMap;
		
	}
	
	public int getProofVarNo(String VarName)
	{
		Integer ret = nameMap.getIntegerVarIndex(VarName);
		assert ret != null;
		return ret;
	}
	
	@Override
	public void addNegativeLiteral(logic.model.Variable v) {
		int proof_var_no = this.getProofVarNo(v.getTheName());
		this.current.addLiteral(new proof.model.Literal(proof_var_no, true));
	}

	@Override
	public void addPositiveLiteral(Variable v) {
		int proof_var_no = this.getProofVarNo(v.getTheName());
		this.current.addLiteral(new proof.model.Literal(proof_var_no, false));

	}

	@Override
	public void endClause() {
		g.add(this.current);
		this.current = new RootClause();
	}

}
