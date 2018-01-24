/*  File name     :  CountTheDays.java
 *  Purpose       :  Utilize the program CalendarStuff
 *  Author        :  Roberto Ventura
 *  Date          :  2018-01-25 
 *  Description   :  This file uses the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-01-25  Bob           To write code for CalendarStuff
 */
 
 public class CountTheDays {
	 
	public static void main(String[] args) {
		
		String day0 = args[0];
		long dayz = Long.parseLong(day0);
		String month0 = args[1];
		long monthz = Long.parseLong(month0);
		String year0 = args[2];
		long yearz = Long.parseLong(year0);
		String day1 = args[3];
		long dayo = Long.parseLong(day1);
		String month1 = args[4];
		long montho = Long.parseLong(month1);
		String year1 = args[5];
		long yearo = Long.parseLong(year1);
		long test = CalendarStuff.daysBetween(dayz, monthz, yearz, dayo, montho, yearo);
		System.out.println("There are " + test + " days between those dates");
	
	}
 
 }
