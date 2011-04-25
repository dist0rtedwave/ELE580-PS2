package interpolant.model;

import java.util.HashMap;
import java.util.Set;
import logic.model.ClauseVisitor;
import logic.model.Variable;
import logic.printer.NameMap;

public class ProofVariableExtractor extends ClauseVisitor<Set<proof.model.Variable>> {
	NameMap nameMap;
	
	public ProofVariableExtractor(Set<proof.model.Variable> g)
	{
		super(g);
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
		g.add(new proof.model.Variable(proof_var_no));
	}

	@Override
	public void addPositiveLiteral(Variable v) {
		int proof_var_no = this.getProofVarNo(v.getTheName());
		g.add(new proof.model.Variable(proof_var_no));
	}

	@Override
	public void endClause() {
	}

}
