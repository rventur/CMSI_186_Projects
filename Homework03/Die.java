/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  B.J. Johnson
 *  Author        :  Roberto Ventura
 *  Date          :  2018-02-22
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-06  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-02-17  B.J. Johnson  Filled in method code
 *  @version 1.2.0  2018-02-22  Bob           Create methods for Die Class
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.*;
public class Die {
  /**
   * private instance data
   */
   private int sides;
   private int pips;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die (int nSides) {
	   if (nSides < 3.9) {
		   throw new IllegalArgumentException();
	   }
	   sides = nSides;
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {
	  return pips = (int) (Math.random() * sides + 1);
   }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
      return pips;
   }
   public int getSides(){
	  return sides;
   }
  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public void setSides (int nSides) {
	   sides = nSides;
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
	  int value = getValue();
	  String s = "≤" + value + "≥";
      return s;
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public static String toString (Die d) {
	  return d.toString();
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      System.out.println( "Hello world from the Die class..." );
	  try {
		 System.out.println("Trying a dice with 2 sides");
	     Die m = new Die(2);
	  }
	  catch (IllegalArgumentException I) {System.out.println("That's not enough sides! \n");}	  
	  
	  try {
		 System.out.println("Trying a dice with 0 sides");
	     Die m = new Die(0);
	  }
	  catch (IllegalArgumentException I) {System.out.println("That's not enough sides! \n");}
	  
	  try {
		 System.out.println("Trying a dice with 4 sides");
	     Die l = new Die(4);
		 System.out.println("it Works! \n");
		 
	  }
	  catch (IllegalArgumentException I) {System.out.println("That's not enough sides! \n");}
	  
	  Die n = new Die(5);
	  System.out.println("Trying a dice with 5 sides");
	  System.out.println("number of sides in die " + n.getSides());
	  n.roll();
	  System.out.println("roll value is " + n.getValue());
	  n.roll();
	  System.out.println("rolled again value is as string " + n.toString());
	  System.out.println("setting sides to 10000");
	  n.setSides(10000);
	  n.roll();
	  System.out.println("roll value is " + n.getValue());
	  System.out.println("value is " + toString(n));
	  System.out.println("number of sides in die " + n.getSides() + "\n");
	  System.out.println();
	  
	  try {
		 System.out.println("Trying a dice with -1 sides");
	     Die p = new Die(-1);
	  }
	  catch (IllegalArgumentException I) {System.out.println("That's not enough sides!\n");}	
	  
	  Die o = new Die(10);
	  System.out.println("Trying a dice with 10 sides");
	  System.out.println("number of sides in die " + o.getSides());
	  o.roll();
	  System.out.println("roll value is " + o.getValue());
	  o.roll();
	  System.out.println("rolled again value is as string " + toString(o));
	  System.out.println("setting sides to 500");
	  o.setSides(500);
	  o.roll();
	  System.out.println("roll value is " + o.getValue());
	  System.out.println("value is " + toString(o));
	  System.out.println("number of sides in die " + o.getSides());	
   }

}