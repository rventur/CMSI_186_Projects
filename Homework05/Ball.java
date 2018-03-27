/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Field.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Bob
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the BallSim Class 
 *                   such as:
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-15  Bob           Create Ball Class
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;
public class Ball extends Timer {

    public double x = 0.0;
    public double vx = 0.0;
    public double y = 0.0;
    public double vy = 0.0;
    public double[] velocity = {0.0,0.0};
    public double[] position = {0.0,0.0};
    DecimalFormat pf = new DecimalFormat("#0.00");
    DecimalFormat vf = new DecimalFormat("#0.0000");
	
    public Ball() {
	
    }
	
    public double validateXCoordinate (String arg) throws NumberFormatException {
  	  try {
  		  x = Double.parseDouble(arg);
  	  }
  	  catch (NumberFormatException e) {
  		  System.out.println("\nError! au contraire mon frère, use numbers!");        //checks validity of arguments (must be a number)
  	  }
	  position[0]=x;
          return position[0];
    }
	
    public double validateXVelocity (String arg) throws NumberFormatException {
  	  try {
  		  vx = Double.parseDouble(arg);
  	  }
  	  catch (NumberFormatException e) {
  		  System.out.println("\nError! au contraire mon frère, use numbers!");        //checks validity of arguments (must be a number)
  	  }
	  velocity[0]=vx;
          return velocity[0];
    }
	
    public double validateYCoordinate (String arg) throws NumberFormatException {
  	  try {
  		  y = Double.parseDouble(arg);
  	  }
  	  catch (NumberFormatException e) {
  		  System.out.println("\nError! au contraire mon frère, use numbers!");        //checks validity of arguments (must be a number)
  	  }
	  position[1]=y;
          return position[1];
    }
	
    public double validateYVelocity (String arg) throws NumberFormatException {
  	  try {
  		  vy = Double.parseDouble(arg);
  	  }
  	  catch (NumberFormatException e) {
  		  System.out.println("\nError! au contraire mon frère, use numbers!");        //checks validity of arguments (must be a number)
  	  }
	  velocity[1]=vy;
          return velocity[1];
    }
	
    public void roll () {
      position[0] = position[0] + velocity[0]*timeSlice;                             //finds new position from velocity by component
      position[1] = position[1] + velocity[1]*timeSlice;
      velocity[0] = velocity[0] - ((velocity[0]*.01)*timeSlice);                     //formula to calculate new velocity from friction by component
      velocity[1] = velocity[1] - ((velocity[1]*.01)*timeSlice);
      tick();                                                                        //uses tick to keep track of elapsed time for ball(ball is a timer)
    } 
	
    public String toString() {
	String s = "";
	if ( (0 == velocity[0]) && (0 == velocity[1]) ){
		s = "position: <" + pf.format(position[0])+ ", " + pf.format(position[1]) + "> " + "          velocity: <at rest>";
	}
	else {
		s = "position: <" + pf.format(position[0])+ ", " + pf.format(position[1]) + "> " + "          velocity: <" + vf.format(velocity[0]) + ", " + vf.format(velocity[1]) + "> ft/sec";
	}
	return s;
    }
	
    public static void main(String[] args) {
		
      System.out.println( "\nTimer CLASS TESTER PROGRAM\n" +
                            "--------------------------\n" );
      System.out.println( " \nCreando una nueva pelota (new ball): " );
      Ball pelota = new Ball();
	  
      System.out.println( "    \nTesting validateXCoordinateArg()....");
	  
      System.out.print( "      sending '  0.0 in x direction', expecting double value   0.0" );
      try { System.out.println( (0.0 == pelota.validateXCoordinate( "0.0")) ? " - got 0.0" : " - na son!"); }
      catch (Exception e) { System.out.println( " - Exception thrown: " + e.toString()); }
	  
      System.out.print( "      sending '  40.0 in x direction', expecting double value   40.0" );
      try { System.out.println( (40.0 == pelota.validateXCoordinate( "40.0")) ? " - got 40.0" : " - na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
      System.out.print( "      sending '  -300.0 in x direction', expecting double value   -300.0" );
      try { System.out.println( (-300.0 == pelota.validateXCoordinate( "-300.0")) ? " - got -300.0" : " - na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() );}
	  
      System.out.print( "      sending '  ABC in x direction', expecting ERROR " );
      try { System.out.println( "ABC" == String.valueOf(pelota.validateXCoordinate( "ABC" )) ? "- got ABC": "- na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(pelota.toString());
	  
      System.out.println( "    \nTesting validateYCoordinateArg()....");
	  
      System.out.print( "      sending '  0.0 in y direction', expecting double value   0.0" );
      try { System.out.println( (0.0 == pelota.validateYCoordinate( "0.0")) ? " - got 0.0" : " - na son!"); }
      catch (Exception e) { System.out.println( " - Exception thrown: " + e.toString()); }
	  
      System.out.print( "      sending '  20.0 in y direction', expecting double value   20.0" );
      try { System.out.println( (20.0 == pelota.validateYCoordinate( "20.0")) ? " - got 20.0" : " - na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
      System.out.print( "      sending '  -170.0 in y direction', expecting double value   -170.0" );
      try { System.out.println( (-170.0 == pelota.validateYCoordinate( "-170.0")) ? " - got -170.0" : " - na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() );}
	  
      System.out.print( "      sending '  XYZ in y direction', expecting ERROR " );
      try { System.out.println( "XYZ" == String.valueOf(pelota.validateYCoordinate( "XYZ" )) ? "- got XYZ": "- na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(pelota.toString());
	  
      System.out.println( "    \nTesting validateXVelocity()....");
	  
      System.out.print( "      sending '  0.0 in y direction', expecting double value   0.0" );
      try { System.out.println( (0.0 == pelota.validateXVelocity( "0.0")) ? " - got 0.0" : " - na son!"); }
      catch (Exception e) { System.out.println( " - Exception thrown: " + e.toString()); }
	  
      System.out.print( "      sending '  20.0 in y direction', expecting double value   20.0" );
      try { System.out.println( (20.0 == pelota.validateXVelocity( "20.0")) ? " - got 20.0" : " - na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
      System.out.print( "      sending '  -170.0 in y direction', expecting double value   -170.0" );
      try { System.out.println( (-170.0 == pelota.validateXVelocity( "-170.0")) ? " - got -170.0" : " - na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() );}
	  
      System.out.print( "      sending '  XYZ in y direction', expecting ERROR " );
      try { System.out.println( "XYZ" == String.valueOf(pelota.validateXVelocity( "XYZ" )) ? "- got XYZ": "- na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(pelota.toString());
	  
      System.out.println( "    \nTesting validateYVelocity()....");
	  
      System.out.print( "      sending '  0.0 in y direction', expecting double value   0.0" );
      try { System.out.println( (0.0 == pelota.validateYVelocity( "0.0")) ? " - got 0.0" : " - na son!"); }
      catch (Exception e) { System.out.println( " - Exception thrown: " + e.toString()); }
	  
      System.out.print( "      sending '  40.0 in y direction', expecting double value   40.0" );
      try { System.out.println( (40.0 == pelota.validateYVelocity( "40.0")) ? " - got 40.0" : " - na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
      System.out.print( "      sending '  -170.0 in y direction', expecting double value   -300.0" );
      try { System.out.println( (-300.0 == pelota.validateYVelocity( "-300.0")) ? " - got -300.0" : " - na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() );}
	  
      System.out.print( "      sending '  ABC in y direction', expecting ERROR " );
      try { System.out.println( "ABC" == String.valueOf(pelota.validateYVelocity( "ABC" )) ? "- got ABC": "- na son!"); }
      catch (Exception e) { System.out.println ( " - Exception thrown: " + e.toString() ); }
	  
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(pelota.toString());
	  
      System.out.println("\nCreating a new ball at (100,200) going (-2,1)ft/sec and time slice of 1 second");
	  
      Ball ball1 = new Ball();
      ball1.validateTimeSliceArg("1");
      ball1.validateXCoordinate("100");
      ball1.validateYCoordinate("200");
      ball1.validateXVelocity("-2");
      ball1.validateYVelocity("1");	  
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(ball1.toString());
      System.out.println("\nNow Rolling");
      ball1.roll();
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(ball1.toString());
      System.out.println("\nNow Rolling");
      ball1.roll();
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(ball1.toString());
      System.out.println("\nNow Rolling");
      ball1.roll();
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(ball1.toString());
		 	
      System.out.println("\nCreating a new ball at (50,70) going (4,-6)ft/sec and time slice of 5 seconds");
	  
      Ball ball2 = new Ball();
      ball2.validateTimeSliceArg("5");
      ball2.validateXCoordinate("50");
      ball2.validateYCoordinate("70");
      ball2.validateXVelocity("4");
      ball2.validateYVelocity("-6");	  
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(ball2.toString());
      System.out.println("\nNow Rolling");
      ball2.roll();
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(ball2.toString());
      System.out.println("\nNow Rolling");
      ball2.roll();
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(ball2.toString());
      System.out.println("\nNow Rolling");
      ball2.roll();
      System.out.println("\nWhere am I? and how fast am I going?");
      System.out.println(ball2.toString());  		
     }
}
