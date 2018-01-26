/*  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Authors       :  Roberto Ventura , B.J. Johnson (for Template)
 *  Date          :  2018-01-25 
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates. It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time". It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.To create a method that determines if a year entered 
 *		     by the user is a leap year or not. This method will also be implemented to determine how 
 *		     many days are in any specified year. Create a method that determines if a date entered by 
 *		     the user is a valid date. Create a method that determines the amount of days between two specified days.
 *  Notes         :  Did not use all methods provided in original template. 
		     This includes: dateEquals, compareDate, toMonthString, and toDayOfWeekString
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 *  @version 1.0.2  2018-01-25  Bob           To write code for CalendarStuff
 */
public class CalendarStuff {
	
	public static boolean isLeapYear (long year) {
		boolean leapY = ((year % 4 == 0) && (year % 100 != 0) )|| (year % 400 == 0);
		return leapY;
	}
	
	public static long daysInMonth (long month, long year) {
		long daysI = 0;
		boolean Leap = isLeapYear(year);
		int intmonth = (int) month;
		if (intmonth >= 1 || intmonth <= 12) {    		//check if month as integer is in bounds, if not program will stop
			String some = "unneccessary string to fill in space since month is correct";
		}
		else {
			System.out.println("This is not a valid month, try again!");
			System.exit(0);
		}
		if (intmonth == 2 && Leap == true) {
			daysI = 29;
		}
		else if (intmonth == 2) {			
			daysI = 28;
		}
		switch (intmonth) {
			case 1: daysI = 31;	
					break;
			case 3: daysI = 31;
					break;
			case 4: daysI = 30;
					break;
			case 5: daysI = 31;
					break;
			case 6: daysI = 30;
					break;
			case 7: daysI = 31;
					break;
			case 8: daysI = 31;
					break;
			case 9: daysI = 30;
					break;
			case 10: daysI = 31;
					break;
			case 11: daysI = 30;
					break;
			case 12: daysI = 31;
					break;
		}
		
		return daysI;
	}
	
	
	//methods not written
	public static boolean dateEquals(long month1, long day1, long year1, long month2, long day2, long year2) {
	    return true;  // replace this with the actual code
	}
	public static int compareDate(long month1, long day1, long year1, long month2, long day2, long year2) {
	    return 0;  // replace this with the actual code
	}
    	public static String toMonthString(int month ) {
            switch( month - 1 ) {
            default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
            }
    	 }
	 public static String toDayOfWeekString( int day ) {
	      switch( day - 1 ) {
	         default       : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
	      }
	  }
	  //end of methods not writtten
	
	
	public static boolean isValidDate(long month, long day, long year) {
		boolean valid;
		int intmonth = (int) month;
		int intday = (int) day;
		if (intmonth == 2) {
			if (isLeapYear(year)) {			        //if month is february, check if leap year and 
			return day <= 29;				//if day is less than or is 29
			}
			else {
			return day <=28;				//if not leap year, if day is less than or is 28
			}
		}
		if (intmonth >= 1 && intmonth <= 12) {
			long checkDay = daysInMonth(month,year);
			valid = (intday <= checkDay && day >= 1);       //check if day specified is within bound of days in month
			return valid;
		}
		else{
			return valid = false;				//if intmonth is not between 0 and 12, must be invalid date
		}  
	}
		
	public static long daysBetween (long month0, long day0, long year0, long month1, long day1, long year1) {
		long daysB = 0;
		if ((false == isValidDate(month0,day0,year0)) || (false == isValidDate(month1,day1,year1))) {	
			System.out.println("error, one of those dates are not valid, try again!");
			System.exit(0);
		}
		else if((month0 == month1) && (day0 == day1) && (year0 == year1)) {
			return daysB;								//check if same date
		}
		else if ((month0 == month1) && (year0 == year1)) {
			return daysB = Math.abs(day0 - day1);		//check if same year and month, then subtract days
		}
		long older, olderm, olderd, younger, youngerm, youngerd; 
		if (year0 > year1) {
		    older = year0;
		    olderm = month0;
		    olderd = day0;
		    younger = year1;
		    youngerm = month1;                  
		    youngerd = day1; 									
		} 							//place later year as older and earlier as younger
		else {
		    older = year1;
		    olderm = month1;
		    olderd = day1;
		    younger = year0;
		    youngerm = month0;
   	      	    youngerd = day0;
		}
		if ((older == younger) && (olderm - youngerm == 1)){
		    return daysB = daysInMonth(youngerm, younger) - youngerd + olderd;
		}
		daysB = daysInMonth(youngerm, younger) - youngerd;     // days from day to end of first month
	        daysB = daysB + olderd;		                       // days into last month 	
		for (long i = (youngerm + 1) ; i < 12.99 ; i++) {
			if (true == isLeapYear(younger)){
			        daysB = daysB + 1;
			}
			        daysB = daysB + daysInMonth(i,younger);   //count days in rest of year of first year
		}
		for (long j = (younger + 1); j < older ; j++) {        //for loop, for each year until reaches first day of second date
			if (isLeapYear(j)) {								
			        daysB = daysB + 366;
			}					       //number of days based on if leap year or not
			else {
				daysB = daysB + 365;
			}		
		}
		for (long k = 1; k < olderm; k++){
			daysB = daysB + daysInMonth(k,older); 	      //add number of days in each month until it reaches last month
		}										
		return daysB;
	}
	
	public static void main(String[] args) {
				
	}
} 
	
