package logic.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class MiniSat {
   public static int exec() throws IOException
   {
	   String cmd = "pwd";
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
