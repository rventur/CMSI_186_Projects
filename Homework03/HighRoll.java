/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Highroll.java
 *  Purpose       :  Demonstrates the use of input from a command line for use with Yahtzee
 *  Author        :  Roberto Ventura
 *  Date          :  2018-02-22
 *  Description   :  
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-14  Bob           Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

   public static void main( String args[] ) {
       // This line uses the two classes to assemble an "input stream" for the user to type
       // text into the program
	  BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      System.out.println( "\n   Welcome to the DiceGame!!  \n" );
      System.out.println( "     Press the 'q' key to quit the program at any time." );
	  System.out.println("To start off, let's create a DiceSet! Type the number of dice you want to create: ");
	  int dice = 0;
	  try{
		  dice = Integer.parseInt(input.readLine()); 
	  }
      catch( IOException ioe ) {
         System.out.println( "Caught IOException" );
      }
      
	  System.out.println("How many sides do you want on each side of these dice?: ");
	  int nSides = 0;
	  try{
		  nSides = Integer.parseInt(input.readLine());
	  }
      catch (IOException ioe) {
         System.out.println( "Caught IOException" );
      }
	  DiceSet ds = new DiceSet (dice,nSides);
	  int highscore = 0;
	  int score = -1;
      while (true) {
		 System.out.println("What would you like to do?");
		 System.out.println("Option1 : Enter 'R' to roll all dice");
         System.out.println("Option2 : Enter 'I' to roll individual dice");
		 System.out.println("Option3 : Enter 'S' Calculate score for this set");
		 System.out.println("Option4 : Enter 'H' Save this score as highscore");
		 System.out.println("Option5 : Enter 'D' Display highschore");
		 System.out.println("Option6 : Enter 'Q' to quit the program ");
		 System.out.println( ">>" );
         String inputLine = null;
		 int individ = -1;
         try {
            inputLine = input.readLine();
		 }
         catch (IOException ioe) {
           System.out.println( "Caught IOException" );
         }
		 switch (inputLine){
			 case "": System.out.println("enter some text!:");
			          break;
			 case "r": ds.roll();
			           break;
			 case "i": System.out.println("Which Dice is it? (starting from 1)");
			           try{
						   individ = Integer.parseInt(input.readLine());
			           }  
					   catch (Exception e) {
						   System.out.println("Exception found, wrong index or IOException");
					   }
			
			           ds.rollIndividual(individ-1);
			           break;
			 case "s": score = ds.sum();
			           break;
			 case "h": highscore = score;
			           break;
			 case "d": System.out.println("The current high score is: " + highscore);
			           break;
			 case "q": System.out.println("Quitting Now!");
			           System.exit(0);
		 }
            

      }
   }
}
