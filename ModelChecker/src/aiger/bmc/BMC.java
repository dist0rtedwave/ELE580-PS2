package aiger.bmc;

import java.io.File;

import aiger.bmc.test.FullTest;

public class BMC {

	public static void printWelcome()
	{
		System.out.println("Bounded Model Checker");
		System.out.println("Joey Dodds, Matvey Arye, Matt Zoufaly");
		System.out.println("NOTE: minisats main directory must be in the directory above the one this exe is run in");
	}
	
	public static void printHelp()
	{
		System.out.println("<executable> <unroll times> <.aag file>");
		System.out.println("NOTE: minisats main directory must be in the directory above the one this exe is run in");
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printWelcome();
		try 
		{
			if(args[0].toLowerCase().equals("help") || args[0].toLowerCase().equals("h"))
			{
				printHelp();
				return;
			}
			Integer k = Integer.valueOf(args[0]);
			String fileName = args[1];
			File f = new File(fileName);
			FullTest.testRunner(f, k);
			
		}
		catch( Exception e)
		{
			printHelp();
			return;
		}
	}

}
