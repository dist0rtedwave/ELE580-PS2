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
import logic.converter.ConverterContext;
import logic.converter.ImpRemover;
import logic.converter.LiteralSimplifier;
import logic.converter.NotDistributor;
import logic.decoder.Tseitin2CNFDecoder;
import logic.model.BinaryOp;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.model.TrueLiteral;
import logic.printer.DimacsPrinter;
import logic.printer.PrintVisitor;

public class MiniSat {
   DimacsPrinter printer;
   public boolean trivial = false;
   public Expression theExpression;
	
   private static int freshBase = 0;
   
   public static Expression convertExpression(Expression e)
   {
//	   System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	//   e = ImpRemover.removeImps(e);
	//   System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   e = NotDistributor.distributeNots(e);
	   
	//   System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   e = LiteralSimplifier.simplifyLiterals(e);
	   
	  // System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   ConverterContext conv = Converter.convert(e, freshBase);
	   freshBase = conv.getFreshNumber();
	   e = conv.getVisitResult();
	   
	 //  System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   e = Tseitin2CNFDecoder.decode(e);

	//   System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   e = NotDistributor.distributeNots(e);
    
	//   System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
	   return e;
   }
   
   public void writeCNF(Expression e)
   {
	    try {
	    	this.theExpression = e;
	    	FileOutputStream fs = new FileOutputStream("/tmp/input.cnf", false);
	    	this.printer = new DimacsPrinter(new StringBuilder());
	    	String out = this.printer.expressionToString(e); 
	    	//System.out.println("Writing:"+out);
	    	fs.write(out.getBytes());
            fs.flush();
            fs.getFD().sync();
            fs.close();
            //System.exit(0);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
   }
   
   public Boolean exec(Expression e) throws IOException
   {
	   e = MiniSat.convertExpression(e);
	   return this.execPrepared(e);
	   
   }
   
   public void execWaitFor(String cmd) throws IOException
   {
	   Process process;
	   process = Runtime.getRuntime().exec(cmd);
	   try {
		process.waitFor();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
   }
   
   
   public Boolean execPrepared(Expression e) throws IOException
   {
       this.execWaitFor("rm /tmp/input.cnf");

       System.out.println("starting simplifier");
       System.out.println(PrintVisitor.expressionToString(e) +"\n\n");

       e = LiteralSimplifier.simplifyLiterals(e);

       System.out.println(PrintVisitor.expressionToString(e) +"\n\n");
       
       if(e.getDescriptor() == TrueLiteral.DESCRIPTOR)
       {
    	  System.out.printf("Trivially true");
    	  this.trivial = true;
    	  return true;
       }
       if(e.getDescriptor() == FalseLiteral.DESCRIPTOR)
       {
    	  System.out.printf("Trivially false");
    	  this.trivial = true;
    	  return false;
       }
       this.trivial = false;
       this.writeCNF(e);
       System.out.println("Running minisat!");
       String cmd = "../minisat/minisat /tmp/input.cnf -p /tmp/proof -r /tmp/result";
       //String removeDoubles = "../removeDoubles";
       this.execWaitFor("rm /tmp/proof");
       //runtime.exec(removeDoubles); //doesn't work because not synchronous.
       
       Process process;
       process = Runtime.getRuntime().exec(cmd);
       InputStream is = process.getInputStream();
       InputStreamReader isr = new InputStreamReader(is);
       BufferedReader br = new BufferedReader(isr);
       String line;

       try {
		process.waitFor();
	   } catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	   }
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
   
	public logic.printer.NameMap getNameMap()
	{
		return this.printer.getNameMap();
	}
}
