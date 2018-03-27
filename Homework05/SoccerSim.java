/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Provides a class defining methods for a soccer simulator
 *  @author       :  Bob
 *  Date written  :  2018-03-15
 *  Description   :  This class provides a bunch of methods which may be useful for the BallSim Class 
 *                   such as:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-15  Bob           Create SoccerSim Class
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;
public class SoccerSim extends Ball {
	
   public final double width = 1000.0;
   public final double height = 1000.0; 
   public double x = 0.0;
   public double y = 0.0;
   public double px = 0.0;
   public double py = 0.0;
   public int scene = 0;
   public int argLength = 0;
   public int iterations = 0;
   public String[] newArgs = null;
   public Ball[] balls = null;
   public final double epsilon = 1.0;
   public final double dballs = (4.45*2)/12 + epsilon;
   public final double stopVelocity = 1/12.0;
   public boolean r;
   public String out = "";
   DecimalFormat p = new DecimalFormat("#0.00");
	
   public SoccerSim() {
			
   }
	
   public void createPole() {
	px = Math.random()*width;                                  //creates pole in positive x axis somewhere along width
	py = Math.random()*height;                                 //creates pole in positive y axis somewhere along height
   }
	
   public void handleArguments(String[] args) throws IllegalArgumentException {
	int j = 0;
	argLength = args.length;
	if (0 == argLength){
		try{ throw new IllegalArgumentException(); }                  
		catch (IllegalArgumentException iae) {
			System.out.println("That's not gonna fly, input arguments to use this program! Program will end now!");
			System.exit(0);
		}			
	}
	iterations = (int)Math.floor(argLength/4);                  //determine how many balls need to be made 
	balls = new Ball[iterations];
	if (0.0 == argLength%4) {
		for (int i = 0; i < argLength; i+=4) {
			balls[j] = new Ball();
			balls[j].validateXCoordinate(args[i]);              //takes arguments passed in, creates ball object with attributes 
			balls[j].validateYCoordinate(args[i+1]);
			balls[j].validateXVelocity(args[i+2]);
			balls[j].validateYVelocity(args[i+3]);
			j++;
			if (iterations == j) {                             //stops for loop once it has created enough balls
				break;
			}
		}	
	}
	else if (1.0 == argLength%4) {
		double x = validateTimeSliceArg(args[argLength-1]);    //validatesTimeSLice if passed in
		String y = "" + x;
		for (int i = 0; i < argLength; i+=4) {
			balls[j] = new Ball(); 
			balls[j].validateTimeSliceArg(y);                  //passes in value for timeSlice for each ball since each ball has own timer
			balls[j].validateXCoordinate(args[i]);
			balls[j].validateYCoordinate(args[i+1]);           //same as above for-loop
			balls[j].validateXVelocity(args[i+2]);
			balls[j].validateYVelocity(args[i+3]);
			j++;
			if (iterations == j) {
				break;
			}
	        }			
	}
	else {
	       System.out.println("Invalid Arguments!, program will end now! Byeee!");  
	       System.exit(0);
        }	
	try {	
	    if (0 == timeSlice) {
		     throw new NumberFormatException("\nError! au contraire mon frère, use numbers! Are you trying to send me in an inifinite loop?");
	    }
	}
	catch (NumberFormatException nfe){
		System.out.println("\nError! au contraire mon frère, use nonzero number! Are you trying to send me in an inifinite loop?");
		System.exit(0);
	}
   }
	
   public int scenarios() {
	for (int j = 0; j < balls.length; j++) {
		if ( (Math.abs(balls[j].position[0] - px) <= epsilon) && (Math.abs(balls[j].position[1] - py) <= epsilon) )  {
			return scene = 1;                                                                              //scenario 1 ball crashes into pole
		}
		if ( (Math.abs(balls[j].position[0]) > width) | (Math.abs(balls[j].position[1]) > height) ) {
			return scene = 2;                                                                              //scenario 2 ball falls off field
		}
	}

        for (int j = 0; j < balls.length-1; j++) {
		for (int k = 0; k < balls.length; k++) {
			if (j == k) {
				k++;                                          //uses for loop to check if any balls have crashed with one another (scenario 3)
			}
		        if ( (Math.abs(balls[j].position[0] - balls[k].position[0]) <= dballs ) && (Math.abs(balls[j].position[1] - balls[k].position[1]) <= dballs) ) {
					return scene = 3;			                                                                              
				}
			}			                     
                }
		if (true == atRest()) {                                  //scenario 4 all balls have stopped moving
			scene = 4;
		}
        return scene;
	}
	
	public String occurence() {
		switch (scene) {
		    case 0: out = "Nothing of Interest has happened";
			    break;
		    case 1: out = "It Seems I have crashed into the pole!";                //switch statement for each possible scenario to output message
			    break;
		    case 2: out = "It Seems I have fallen off the field!";
			    break;	
		    case 3: out = "It Seems I have crashed into a ball!";
		            break;
		    case 4: out = "It Seems all balls are at rest, Collision not Possible";
			    break;
		}
		return out;
	}
	
	public String report() {
		String report = "";
		scenarios();                                                       //uses scenarios to find out if any occurred then occurence to begin writing report
		occurence();
		report += "\nProgress report at: " + balls[0].clockString() + "\n";   //adds time to report
		report = report + out;
		for (int i = 0; i < balls.length; i++) {
			if (balls.length == i) {
				break;
			} 
			report += "\n ball:" + i + " " + balls[i].toString();          //adds information of each ball's velocity and position to report
		}
		report += "\n";
		return report;
	}
	
	public boolean atRest() {
		int count = 0;
		for (int i = 0; i < balls.length; i++) {
			if ( ((stopVelocity) >= Math.abs(balls[i].velocity[0])) && ((stopVelocity) >= Math.abs(balls[i].velocity[1])) ) {
			    balls[i].velocity[0] = 0;
 			    balls[i].velocity[1] = 0;
			    count++;	                                              //determines if ball is at rest or not
			}                                                             //at rest defined if moving less than 1 inch per second
		}
		if (count == balls.length) {
			r = true;
		}
		else {
			r = false;                                                    //only true if all balls are at rest
		}
		return r;
	}
	
	public static void main (String[] args) {
		SoccerSim simulator = new SoccerSim();
		simulator.createPole();
		simulator.handleArguments(args);
		boolean x = simulator.atRest();
		System.out.println("\nThe pole is at <" + simulator.p.format(simulator.px) + ", " + simulator.p.format(simulator.py) + ">");
		System.out.println("\nThe field is " + simulator.width + " by " + simulator.width);
		System.out.println("\nThe time slice value is " + simulator.timeSlice + " seconds");
		System.out.println(simulator.report());
		if (simulator.scene > 0) {
			x = true;
		}
		while ((false == x)) {
			for (int i = 0; i < simulator.balls.length; i++) {
				simulator.balls[i].roll();
			}
			x = simulator.atRest();
			System.out.println(simulator.report());
			if (simulator.scene > 0) {
				break;
			}
		}			    
	}
}
