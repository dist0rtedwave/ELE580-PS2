package logic.executor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import logic.converter.Converter;
import logic.converter.ImpRemover;
import logic.converter.NotDistributor;
import logic.decoder.Tseitin2CNFDecoder;
import logic.model.BinaryOp;
import logic.model.Expression;
import logic.printer.DimacsPrinter;
import logic.printer.PrintVisitor;

public class MiniSat {
   DimacsPrinter printer;
	
   public static Expression convertExpression(Expression e)
   {
	  // System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	//   e = ImpRemover.removeImps(e);
	 //  System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   e = NotDistributor.distributeNots(e);
	 //  System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   e = Converter.convert(e);
	 // System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   e = Tseitin2CNFDecoder.decode(e);
	 //  System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   e = NotDistributor.distributeNots(e);
	  // System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   return e;
   }
   
   public void writeCNF(Expression e)
   { 
	   e = MiniSat.convertExpression(e);
	    try {
	    	FileOutputStream fs = new FileOutputStream("/tmp/input.cnf", false);
	    	this.printer = new DimacsPrinter(new StringBuilder());
	    	String out = this.printer.expressionToString(e); 
	    	fs.write(out.getBytes());
            fs.flush();
            fs.getFD().sync();
            fs.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
   }
   
   public Boolean exec(Expression e) throws IOException
   {
       Runtime runtime = Runtime.getRuntime();
       runtime.exec("rm /tmp/input.cnf");
       this.writeCNF(e);
       String cmd = "../minisat/minisat /tmp/input.cnf -p /tmp/proof -r /tmp/result";
       //String removeDoubles = "../removeDoubles";
       runtime.exec("rm /tmp/proof");
       //runtime.exec(removeDoubles); //doesn't work because not synchronous.
       Process process = runtime.exec(cmd);
       InputStream is = process.getInputStream();
       InputStreamReader isr = new InputStreamReader(is);
       BufferedReader br = new BufferedReader(isr);
       String line;

       System.out.printf("Output of running %s is:\n",cmd 
           );

       while ((line = br.readLine()) != null) {
         System.out.println(line);
       }
       
       BufferedReader in = new BufferedReader(new FileReader("/tmp/result"));
       String test = in.readLine();
       if(test.startsWith("UNSAT"))
       {
    	   return false;
       }
       return true;
   }
   
	public HashMap<String, Integer> getNameMap()
	{
		return this.printer.getNameMap();
	}
}
