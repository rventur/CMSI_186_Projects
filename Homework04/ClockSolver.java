/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  @author       :  Bob
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
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
 *  @version 1.0.1  2018-03-13  Bob           Create ClockSolverClass
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double EPSILON                    = .001;      // small value for double-precision comparisons
   double tangle = 0;
   double slice = 60;
   Clock clock = new Clock();

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit(1);
      }
  
	  try {
	     tangle = clock.validateAngleArg(args[0]);	
	  }
	  catch (NumberFormatException e) {
	  	  System.out.println(e.toString());                           //checks for errors in arguments
	  }
      if (1 == args.length) {
	       slice = 60.0;
      } 
	  if (2 == args.length) {
	     try {
	         slice = clock.validateTimeSliceArg(args[1]);            //if no time slice in args, set to 60.0
       }
	     catch (NumberFormatException e) {
		     System.out.println(e.toString());
		  
	     }
     }
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      Clock clock = new Clock();
	  cse.handleInitialArguments(args);
      clock.TimeSlice = cse.slice;
	  System.out.println( "      Outputting clock as string" );
	  System.out.println(clock.toString());
	  System.out.println("\nLooking for an angle of " + cse.tangle + "˚");
	  System.out.println(" with a time slice of " + clock.TimeSlice + " seconds\n");
    while (clock.totalSeconds < 43200) {
		 if (Math.abs(clock.getHandAngle() - cse.tangle) <= cse.EPSILON) {
			 System.out.println("Found Target Angle: " + cse.tangle + "˚ at " + clock.toString() );
			 clock.tick();
			 continue;		 
		 }
		 clock.tick();
    }
     System.exit(0);
   }
}
