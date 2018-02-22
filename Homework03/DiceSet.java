/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2018-02-22
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet (int k, int n);                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual(int i);             // Randomly rolls only the ith die in this set
 *                   public int getIndividual (int i);               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString (DiceSet ds);     // Classwide version of the preceding instance method
 *                   public boolean isIdentical (DiceSet ds);        // Returns true iff this set is identical to the set ds
 *                   public static void main (String[] args);        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 *  @version 1.1.0. 2018-02-15  Bob           Create DiceSet Methods
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet (int count, int sides) {
	  this.count = count;
	  this.sides = sides;
	  ds = new Die [count];
	  for (int i = 0; i < count; i++){
		   ds[i] = new Die(sides);
	  } 
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
	  int sum = 0;
	  int value = 0;	  
	  for (Die d : ds) {
	   	  sum += d.getValue();
	  }
      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
	   for (Die d : ds){
	   	   d.roll();
	   }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @throws IllegalArgumentException if the index is out of range
   */
   public int rollIndividual (int dieIndex) {
	  return ds[dieIndex].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual (int dieIndex) {
      return ds[dieIndex].getValue();
   }
   
   public int numOfDice (DiceSet dr){
	   return dr.count;
   }
   
   public int numOfSides (DiceSet dr){
   	   return dr.sides;
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
	  String result = "\n";
	  for (int i = 0; i < count; i++) {
		  result += "\n" + ds[i].toString();
	  }
	  return result + "\n";
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString (DiceSet ds) {
      return ds.toString();
   }

  /**
   * @return  true if this set is identical to the set passed as an argument
   */
   public boolean isIdentical (DiceSet dr) {
	  boolean boop = true;
	  boolean poop = false;
	  int dsum = 0;
	  int isides = 0;
	  if (ds.length != numOfDice(dr)) {       //check if same number of dice
		  System.out.println("the number of dice in one is " + ds.length + " dice and the other has " + numOfDice(dr));
          System.out.println("Not the same number of dice");
		  return poop;
	  }  
	  if (ds[0].getSides() != numOfSides(dr)) {          //check if same number of sides
		  System.out.println("one dice has "+ ds[0].getSides() + " sides and the other has " + numOfSides(dr));
	  	  System.out.println("Not the same number of sides");
		  return poop;
	  }
	  for (int k = 0; k < ds.length; k++ ) {  //find sum of ds because it is die array
		  dsum += ds[k].getValue();
	  }
	  if (dsum != dr.sum()) {                 //check if same sum as ds and this new DiceSet
		  System.out.println("the sum of dice in one set is "+ dsum + " the sum in the other is " + dr.sum());
		  System.out.println("Not the same sum");
		  return poop;
	  }
	  for (int j = 0; j < count; j++) {       //check if have the same values, (any order)
	      if (toString(dr).contains ( ds[j].toString() )) {
			  continue;
	      }
		  else {
			  System.out.println("Not the same values");
		  	  return poop;
		  }
      }
      return boop; 
   }
   /*
   * A little test main to check things out
   */
   public static void main (String[] args) {
       System.out.println( "Hello world from the DiceSet class..." );
	   DiceSet some = new DiceSet(2,6);
	   some.roll();
	   System.out.println(toString(some));
	   System.out.println("sum is " + some.sum() + "\n");
	   System.out.println("In this DiceSet, the second dice is " + some.getIndividual(1));
	   System.out.println("\n Yo! I made this new set by rolling the second dice :)");
	   some.rollIndividual(1);
	   System.out.println(toString(some));
	   System.out.println("sum is " + some.sum()+ "\n");
	   
	   System.out.println("\n Yo! I made this new set :)");
	   DiceSet thing = new DiceSet(2,6);
	   thing.roll();
	   System.out.println(thing.toString());
	   System.out.println("sum is " + thing.sum());
	   System.out.println("In this DiceSet, the first dice is " + thing.getIndividual(0));
	   System.out.println("Do these dice have the same number of dice, sides, sum, and values(in any order) ? : " + some.isIdentical(thing));
	   System.out.println("\n Yo! I made this new set by rolling the first dice :)");
	   thing.rollIndividual(0);
	   System.out.println(toString(thing));
	   System.out.println("sum is " + thing.sum()+ "\n");
	   
	   System.out.println("\n Yo! I made this new set :)");	   
	   DiceSet set = new DiceSet(6,10);
	   set.roll();
	   System.out.println(toString(set));
	   System.out.println("sum is " + set.sum());
	   System.out.println("In this DiceSet, the third dice is " + set.getIndividual(2));
	   
	   System.out.println("\n Yo! I made this new set :)");
	   DiceSet let = new DiceSet(4,5);
	   let.roll();
	   System.out.println(toString(let));
	   System.out.println("sum is " + let.sum());
	   System.out.println("In this DiceSet, the fourth dice is " + let.getIndividual(3));
	   System.out.println("Do these dice have the same number of dice, sides, sum, and values(in any order) ? : " + set.isIdentical(let));
	   
	   DiceSet shei = new DiceSet(3,15);
	   shei.roll();
	   System.out.println("\n Yo! I made this new set");
	   System.out.println(toString(shei));
	   System.out.println("sum is " + shei.sum());
	   System.out.println("In this DiceSet, the first dice is " + shei.getIndividual(0));
	   System.out.println("\n Yo! I made this new set by rolling the second dice :)");
	   shei.rollIndividual(1);
	  
	   DiceSet sse = new DiceSet(3,10);
	   sse.roll();
	   System.out.println("\n Yo! I made this new set");
	   System.out.println(sse.toString());
	   System.out.println("sum is " + sse.sum());
	   System.out.println("In this DiceSet, the first dice is " + sse.getIndividual(0));
	   System.out.println("\n Yo! I made this new set by rolling the second dice :)");
	   sse.rollIndividual(1);
	   System.out.println("Do these dice have the same number of dice, sides, sum, and values(in any order) ? : " + shei.isIdentical(sse));
	   
	   DiceSet rick = new DiceSet(7,7);
	   rick.roll();
	   System.out.println("\n Yo! I made this new set");
	   System.out.println(toString(rick));
	   System.out.println("sum is " + rick.sum());
	   System.out.println("In this DiceSet, the first dice is " + rick.getIndividual(0));
	   System.out.println("\n Yo! I made this new set by rolling the second dice :)");
	   rick.rollIndividual(1);
	  
	   DiceSet james = new DiceSet(7,7);
	   james.roll();
	   System.out.println("\n Yo! I made this new set");
	   System.out.println(james.toString());
	   System.out.println("sum is " + james.sum());
	   System.out.println("In this DiceSet, the first dice is " + james.getIndividual(0));
	   System.out.println("Do these dice have the same number of dice, sides, sum, and values(in any order) ? : " + rick.isIdentical(james));
   }

}
