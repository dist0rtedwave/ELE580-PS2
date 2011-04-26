package proof.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Proof {
	ArrayList<Clause> clauses = null;
	
	public void load(String filename)
	{
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clauses = new ArrayList<Clause>();
		
		long tmp;
		int idx;
		RootClause root_clause;
		ChainClause chain_clause;
		Literal lit;
		ArrayList<Clause> resolution_clauses;
		ArrayList<Variable> pivots;
		int id;
		
		try {
			while (true)
			{
				tmp = getLong(fs);
				//System.out.println("root tmp = "+tmp);
				if (tmp == -1)
					break;
				
				if((tmp & 1) == 0)
				{
					root_clause = new RootClause();
					tmp = tmp >> 1;
					idx = (int) tmp;
					//System.out.println("first literal idx = " +idx+" variable "+(idx/2));
					lit = new Literal(idx);
					root_clause.addLiteral(lit);
		            while(true){
		                tmp = getLong(fs);
		                if (tmp == 0) break;
		                idx += (int) tmp;
		              //  System.out.println("subsequent idx = " +idx+" variable "+(idx/2));
		                lit = new Literal(idx);
		                root_clause.addLiteral(lit);
		            }
		            clauses.add(root_clause);
				}
				else
				{
					resolution_clauses = new ArrayList<Clause>();
					pivots = new ArrayList<Variable>();
					id = clauses.size();
					tmp = tmp >> 1;
					idx = (int)tmp;
					resolution_clauses.add(clauses.get(id-idx));
					while(true)
					{
						tmp=getLong(fs);
						if(tmp == 0) break;
						pivots.add(new Variable((int)tmp-1));
						tmp=getLong(fs);
						idx = (int)tmp;
						resolution_clauses.add(clauses.get(id-idx));
					}
					
					if(pivots.size() == 0)
					{
						
						//assert(false); //need to implement deleted
					}
					else
					{
						chain_clause = new ChainClause(resolution_clauses, pivots);
						clauses.add(chain_clause);
					}
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}

	
	public void traverse(ProofTraverser t)
	{
		Iterator<Clause> it = clauses.iterator();
		while (it.hasNext())
		{
			it.next().traverse(t);
		}
	}
	
	public Clause getLastClause()
	{
		return this.clauses.get(this.clauses.size()-1);
	}
	
static long getLong(FileInputStream in) throws IOException	
{
	long ret = Proof.getLongInt(in);
	//System.out.println("got value "+ret);
	return ret;
}
	
static long getLongInt(FileInputStream in) throws IOException
{
    long byte0, byte1, byte2, byte3, byte4, byte5, byte6, byte7;
    byte0 = in.read();
    if (byte0 == -1)
        return -1;
    if ((byte0 & 0x80) == 0x00)
       return byte0;
    else{
        switch ((int)(byte0 & 0x60) >> 5){
        case 0:
            byte1 = in.read();
            return ((byte0 & 0x1F) << 8) | byte1;
        case 1:
            byte1 = in.read();
            byte2 = in.read();
            return ((byte0 & 0x1F) << 16) | (byte1 << 8) | byte2;
        case 2:
            byte1 = in.read();
            byte2 = in.read();
            byte3 = in.read();
            return ((byte0 & 0x1F) << 24) | (byte1 << 16) | (byte2 << 8) | byte3;
        default:
        	assert((byte0 & 0x80)==0);
            byte0 = in.read();
            byte1 = in.read();
            byte2 = in.read();
            byte3 = in.read();
            byte4 = in.read();
            byte5 = in.read();
            byte6 = in.read();
            byte7 = in.read();
            return ((long)((byte0 << 24) | (byte1 << 16) | (byte2 << 8) | byte3) << 32)
                 |  (long)((byte4 << 24) | (byte5 << 16) | (byte6 << 8) | byte7);
        }
    }
}
}
