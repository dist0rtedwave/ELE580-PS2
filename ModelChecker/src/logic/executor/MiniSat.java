package logic.executor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import logic.converter.Converter;
import logic.converter.ImpRemover;
import logic.converter.NotDistributor;
import logic.decoder.Tseitin2CNFDecoder;
import logic.model.BinaryOp;
import logic.model.Expression;
import logic.printer.DimacsPrinter;

public class MiniSat {

   public static Expression convertExpression(Expression e)
   {
	   e = ImpRemover.removeImps((BinaryOp) e);
	   e = NotDistributor.distributeNots(e);
	   e = Converter.convert(e);
	   e = Tseitin2CNFDecoder.decode(e);
	   return e;
   }
   
   public static void writeCNF(Expression e)
   { 
	   e = MiniSat.convertExpression(e);
	    try {
	    	BufferedWriter out = new BufferedWriter(new FileWriter("/tmp/input.cnf"));
	    	out.write(DimacsPrinter.expressionToString(e));
			out.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
   }
   
   public static int exec(Expression e) throws IOException
   {
	   MiniSat.writeCNF(e);
	   String cmd = "../minisat /tmp/input.cnf";
       Runtime runtime = Runtime.getRuntime();
       Process process = runtime.exec(cmd);
       InputStream is = process.getInputStream();
       InputStreamReader isr = new InputStreamReader(is);
       BufferedReader br = new BufferedReader(isr);
       String line;

       System.out.printf("Output of running %s is:",cmd 
           );

       while ((line = br.readLine()) != null) {
         System.out.println(line);
       }
	return 0;
   }
}
