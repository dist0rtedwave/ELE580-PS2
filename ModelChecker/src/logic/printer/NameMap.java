package logic.printer;

import java.util.HashMap;
import java.util.Map.Entry;

//maps MiniSat Var No to 
public class NameMap {
	protected HashMap<String, Integer> nameMap;
	protected HashMap<Integer, String> reverseMap;
	protected int nameCounter;
	
	NameMap()
	{
		this.nameCounter = 0;
		this.reverseMap = null;
		this.nameMap = new HashMap<String, Integer>();
	}
	
	public int getVarIndex(String var_name)
	{
		if (!this.nameMap.containsKey(var_name))
		{
			this.nameMap.put(var_name, new Integer(this.nameCounter));
			this.nameCounter += 1;
		}
		return this.nameMap.get(var_name);
	}
	
	public Integer getIntegerVarIndex(String var_name)
	{
		if (!this.nameMap.containsKey(var_name))
		{
			this.nameMap.put(var_name, new Integer(this.nameCounter));
			this.nameCounter += 1;
		}
		return this.nameMap.get(var_name);
	}
	
	public int getDimacsIndex(String var_name)
	{
	  return this.getVarIndex(var_name)+1;	
	}
	
	public int getSize()
	{
		return this.nameMap.size(); 
	}
	
	public void buildReverseMap()
	{
		this.reverseMap = new HashMap<Integer, String>();
		for(Entry<String, Integer> entry: nameMap.entrySet())
		{
			this.reverseMap.put(entry.getValue(), entry.getKey());
		}
	}
	
	public HashMap<Integer, String> getReverseMap()
	{
		if(this.reverseMap == null)
		{
			this.buildReverseMap();
		}
		assert this.reverseMap != null;
		return this.reverseMap;
	}
	
	public String getVarName(int var_index)
	{
		return this.getReverseMap().get(new Integer (var_index));
	}
}
