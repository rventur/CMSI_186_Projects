/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *                   Tick(), getseconds(), validateTimeSliceArg(), clockString()
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-15  Bob           Create Clock Class
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;
public class Timer {
  /**
   *  Class field definintions go here
   */
   private String clock = "";
   private double hours = 0.0;
   private double minutes = 0.0;
   private double seconds = 0.0;
   public double totalSeconds = 0.0;
   private double tempSeconds = 0.0;
   private double totalMinutes = 0.0;
   public double timeSlice = 1.0;
   DecimalFormat df = new DecimalFormat("#00.0#");
   DecimalFormat tf = new DecimalFormat("#00");
   

  /**
   *  Constructor goes here
   */
   public Timer() {
	   
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {	  
	  return totalSeconds += timeSlice;                                           //to add timeSlice to elapsed time
   }
   
  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg (String argValue) {
	  if (argValue == "") {
		  return timeSlice;
	  }                                                                       //check if arg is valid can only be positive number
	  try {                                                                      
		  timeSlice = Double.parseDouble(argValue);       									
		  if (timeSlice < 0) {                                          
			  throw new NumberFormatException();
		  }
	  }
	  catch (NumberFormatException e) {
		  System.out.println("\n"+e.toString());
		  System.out.println("     Error, i'm not configured to go back in time or count by letters!");
	  }
      return timeSlice;
   }
   
  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String clockString() {
	  String s = "";
	  tempSeconds = totalSeconds;   
	  if (totalSeconds < 60) {                                 //temporary seconds to have total of seconds and checks if totalSeconds
		  seconds = tempSeconds;                               //will set tempseconds in string s as seconds if less than 60
	  }
	  if (totalSeconds > 60) {	
		  totalMinutes = totalSeconds/60;  	                   //if greater than 60 sec, divide by 60 to num of minutes
          while (tempSeconds >= 60) {     
			  tempSeconds = tempSeconds - 60;                  //change total number of seconds and subtract minutes 
	      }                                                    //and will set this to be seconds in string s
	  }
	  seconds = tempSeconds;
	  if (totalMinutes < 60) {
		  minutes = totalMinutes;                              //will set minutes in string s to total of min if less than 60
	  }
	  if ((totalMinutes - Math.floor(totalMinutes)) < .99) {   
		  minutes = Math.floor(totalMinutes);
	  }
	  else {
		  minutes = Math.ceil(totalMinutes);                  //to detect if 59 minutes will switch to 0 min,add another hour instead of 59 min
	  }
	  if (totalMinutes > 60) {
		  hours = totalMinutes/60;
	      while (minutes >= 60) {
			  minutes = minutes - 60;
	      }
	  }
	  if ((hours - Math.floor(hours)) < .99) {                //change hours to an integer
		  hours = Math.floor(hours);
	  }
	  else {
		  hours = Math.ceil(hours);
	  }
	  s = tf.format(hours) + ":" + tf.format(minutes) + ":" + df.format(seconds);
      return s;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main(String args[]) {

      System.out.println( "\nTimer CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( " \nCreating a new timer: " );
      Timer clock = new Timer();
      System.out.println( "  \nNew timer created: " + clock.clockString() );
	  
	  System.out.println( "    \nTesting validateTimeSliceArg()....");
	  
	  System.out.print( "      sending '  0.0 seconds', expecting double value   0.0" );
	  try { 
		  System.out.println( (0.0 == clock.validateTimeSliceArg( "0.0")) ? " - got 0.0" : " - na son!"); 
	  }
	  catch (Exception e) { 
		  System.out.println( " - Exception thrown: " + e.toString());
	  }
	  
	  System.out.print( "      sending '  40.0 seconds', expecting double value   40.0" );
	  try { 
		  System.out.println( (40.0 == clock.validateTimeSliceArg( "40.0")) ? " - got 40.0" : " - na son!"); 
	  }
	  catch (Exception e) { 
		  System.out.println ( " - Exception thrown: " + e.toString() );
	  }
	  
	  System.out.print( "      sending '  -300.0 seconds', expecting error" );
	  try { 
		  System.out.println( (-300.0 == clock.validateTimeSliceArg( "-300.0")) ? " - got -300.0" : " - na son!"); 
	  }
	  catch (Exception e) { 
		  System.out.println ( " - Exception thrown: " + e.toString() );
	  }
	  
      System.out.print( "      sending '  ABC seconds', expecting ERROR " );
	  try { 
		  System.out.println( "ABC" == String.valueOf(clock.validateTimeSliceArg( "ABC" )) ? "- got ABC": "- na son!"); 
	  }
      catch (Exception e) { 
		  System.out.println ( " - Exception thrown: " + e.toString() ); 
	  }	  
	  
	  System.out.println( "    \nTesting Tick()....");
	  System.out.println("Tick of 0 seconds");
	  clock.validateTimeSliceArg("0.0");
	  System.out.println(" Total Seconds: " + clock.tick());
	  System.out.println(clock.clockString());
	  System.out.println("Tick of 45 seconds");
	  clock.validateTimeSliceArg("45.0");
	  System.out.println(" Total Seconds: " + clock.tick());
	  System.out.println(clock.clockString());
	  System.out.println("Tick of 50 seconds");
	  clock.validateTimeSliceArg("50.0");
	  System.out.println(" Total Seconds: " + clock.tick());
	  System.out.println(clock.clockString());
	  System.out.println("Tick of 1800 seconds");
	  clock.validateTimeSliceArg("1800.0");
	  System.out.println(" Total Seconds: " + clock.tick());
	  System.out.println(clock.clockString());
	  System.out.println("Tick of 1800 seconds");
	  clock.validateTimeSliceArg("1800.0");
	  System.out.println(" Total Seconds: " + clock.tick());
	  System.out.println(clock.clockString());
	  System.out.println("Tick of 1800 seconds");
	  clock.validateTimeSliceArg("1800.0");
	  System.out.println(" Total Seconds: " + clock.tick());
	  System.out.println(clock.clockString());
	  System.out.println("Tick of 3600 seconds");
	  clock.validateTimeSliceArg("3600.0");
	  System.out.println(" Total Seconds: " + clock.tick());
	  System.out.println(clock.clockString());
   }
}