/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *                   Tick(), get minute, hour, hands angle, toString
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2018-03-13  Bob           Create Clock Class
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;
public class Clock {
  /**
   *  Class field definintions go here
   */
   private double angle = 0.0;
   private double hangle = 0.0;
   private double mangle = 0.0;
   private String clock = "";
   private double hours = 0.0;
   private double minutes = 0.0;
   private double seconds = 0.0;
   public double totalSeconds = 0.0;
   private double tempSeconds = 0.0;
   private double totalMinutes = 0.0;
   private double handAngle = 0.0;
   public double TimeSlice = 60.0;
   DecimalFormat df = new DecimalFormat("#0.00");
   

  /**
   *  Constructor goes here
   */
   public Clock() {
	   //potentially initialize clock values
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {	  
	  return totalSeconds += TimeSlice;                                           //to add timeSlice to elapsed time
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg (String argValue) throws NumberFormatException {
 	  try {
 		  angle = Double.parseDouble(argValue);
		  if (angle < 0 || angle > 360) {
			  throw new NumberFormatException("Can't input negative numbers");    //check if arg is valid can only be number
		  }                                                                       //and if between 0 and 360 degrees
 	  }
 	  catch (NumberFormatException e) {
 		  System.out.println("\n     Error, input a non-negative number.\n     Pobrecito!");
 	  }
      return angle;
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
		  return TimeSlice;
	  }                                                                          //check if arg is valid can only be number
	  try {                                                                      //and if between 0 and 360 degrees
		  TimeSlice = Double.parseDouble(argValue);       									
		  if (TimeSlice < 0 || TimeSlice > 1800) {                                          
			  throw new NumberFormatException("Can't input negative numbers or numbers greater than 1800");
		  }
	  }
	  catch (NumberFormatException e) {
		  System.out.println("\n     Error, input a non-negative number.\n     Pobrecito!");
	  }
      return TimeSlice;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
	  hangle = (.5/60)*totalSeconds;                                            //moves (.5 degrees per minute) times number of seconds
	  while (hangle >= 360.0) {
		  hangle = hangle - 360.0;                                              //reduce if greater than 360
	  }
      return hangle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
	  mangle = .1*totalSeconds;                                                 //moves (.1 degrees per second) times number of seconds
	  while (mangle >= 360.0) {
 		  mangle = mangle - 360.0;                                              //reduce if greater than 360
 	  }
      return mangle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
	  if ( (getMinuteHandAngle() < 180) || getMinuteHandAngle() > 0) {
	       handAngle = Math.abs( getHourHandAngle() - getMinuteHandAngle() );    //uses if statements to make sure angles are valid
      }
	  else if (getMinuteHandAngle() > 180) {
	       handAngle = Math.abs( getMinuteHandAngle() - getHourHandAngle() );
	  }
	  else {
		  System.out.println("Error! angles are invalid\n Pobrecito!");		  
	  }
	  //if hand angle greater than 180 degrees subtract the other way
      return handAngle;
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
   public String toString() {
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
	  s = hours + " hours, " + minutes + " minutes, " + df.format(seconds) + " seconds";
      return s;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( " \nCreating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "  \nNew clock created: " + clock.toString() );
	  
      System.out.println( "    \nTesting validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - na son!" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  360 degrees', expecting double value   360.0" );
      try { System.out.println( (360.0 == clock.validateAngleArg( "360.0" )) ? " - got 360.0" : " - na son!" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  -10 degrees', expecting error" );
      try { System.out.println( (-10.0 == clock.validateAngleArg( "-10.0" )) ? " - got -10.0" : " - na son!" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  System.out.print( "      sending '  380 degrees', expecting error" );
      try { System.out.println( (380.0 == clock.validateAngleArg( "380.0" )) ? " - got 380.0" : " - na son!" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  System.out.print( "      sending '  ABC degrees', expecting error" );
      try { System.out.println( clock.validateAngleArg( "ABC" )); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
	  System.out.println( "    \nTesting validateTimeSliceArg()....");
	  System.out.print( "      sending '  0.0 seconds', expecting double value   0.0" );
	  try { System.out.println( (0.0 == clock.validateTimeSliceArg( "0.0")) ? " - got 0.0" : " - na son!"); }
	  catch ( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );}
	  System.out.print( "      sending '  1800.0 seconds', expecting double value   1800.0" );
	  try { System.out.println( (1800.0 == clock.validateTimeSliceArg( "1800.0")) ? " - got 1800.0" : " - na son!"); }
	  catch ( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );}
	  System.out.print( "      sending '  -1800.0 seconds', expecting double value   -1800.0" );
	  try { System.out.println( (1800.0 == clock.validateTimeSliceArg( "1800.0")) ? " - got -1800.0" : " - na son!"); }
	  catch ( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );}
      System.out.print( "      sending '  ABC seconds', expecting double value   ABC" );
	  try { System.out.println( clock.validateTimeSliceArg( "ABC" )); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
	  System.out.println( "      \nOutputting clock as string" );
	  System.out.println(clock.toString());
	  
	  System.out.println( "    \nTesting Tick()....");
	  System.out.println( "    Tick of 45 seconds");
	  clock.validateTimeSliceArg("45.0");
	  System.out.println(clock.tick());
	  System.out.println(clock.toString());
	  System.out.println( "    Tick of 50 seconds");
	  clock.validateTimeSliceArg("50.0");
	  System.out.println(clock.tick());
	  System.out.println(clock.toString());
	  System.out.println( "    Tick of 1800 seconds");
	  clock.validateTimeSliceArg("1800.0");
	  System.out.println(clock.tick());
	  System.out.println(clock.toString());
	  System.out.println( "    Tick of 1800 seconds");
	  clock.validateTimeSliceArg("1800.0");
	  System.out.println(clock.tick());
	  System.out.println(clock.toString());
	  System.out.println( "    Tick of 1800 seconds");
	  clock.validateTimeSliceArg("1800.0");
	  System.out.println(clock.tick());
	  System.out.println(clock.toString());
	  
	  Clock test = new Clock();
	  test.totalSeconds = 21957.10170;
	  System.out.println(test.toString());
	  System.out.println( "    \nTesting getHourHandAngle()....");
	  System.out.println( test.getHourHandAngle() ) ;
	  
	  System.out.println( "    \nTesting getMinuteHandAngle()....");
	  System.out.println( test.getMinuteHandAngle() );
	  
	  System.out.println( "    \nTesting getHandAngle()....");
	  System.out.println( test.getHandAngle() );
	  System.out.println( "    \nTick of 1801 seconds");
	  test.validateTimeSliceArg("1800.0");
	  test.tick();
	  test.validateTimeSliceArg("1.0");
	  test.tick();
	  System.out.println(test.toString());
	  System.out.println( "    \nTesting getHourHandAngle()....");
	  System.out.println( test.getHourHandAngle() ) ;
	  
	  System.out.println( "    \nTesting getMinuteHandAngle()....");
	  System.out.println( test.getMinuteHandAngle() );
	  
	  System.out.println( "    \nTesting getHandAngle()....");
	  System.out.println( test.getHandAngle() );
   }
}