/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                   validateDigits, two reversers, and valueOf methods; revamped equals
 *                                   and compareTo methods to use the Java String methods; ready to
 *                                   start work on subtractByte and subtractInt methods
 *  1.2.0 2018-04-19  Bob            Write BrobInt class
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );        /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );        /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );        /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );        /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );        /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );        /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );        /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );        /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );        /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );        /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );        /// Constant for "ten"

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private String sign          = "";        // "" if no sign(positive), "+" is positive, "-" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte   bsign         = 0;         // "0" is positive, "1" is negative
   private int[] intVersion     = null;      // int array for storing the string values; uses the reversed string
   private String noSign        = "";        // string representation without negative sign
   private boolean vd           = false;     // boolean to keep value of validate digits

  /**
   *  Constructor takes a string and assigns it to the internal storage, then use ValidateDigits() to check for a sign character
   *   and handle that accordingly;  
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt(String value) {
	   internalValue = value;
	   sign = sign();
	   vd = validateDigits();  
	   if (vd == true) {                                            //validates digits before splitting up number into int[]
		   chunkyMonkey(value);
		   noSign();                                             //create same string but with no negative sign
           }
	   else {
		   System.out.print("Hey you need to pass in a number!");
	   }
   }
   /**
    *  Method to determine what the sign of the brobInt is
	*  no parameter or return because this method is called in constructor
	*  with the value of the sign stored as variable sign 
	*  positive if no sign or has a + sign, negative if has a - sign
    */
   public String sign () {
	   if (internalValue.contains("+")) {
		   sign = "+";
	   }
	   else if (internalValue.contains("-")) {             //stores classwide variable for sign
		   sign = "-";
	   }
	   return sign;
   }
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to create String representation of BrobInt without the sign
	*  Note: only used for constructor
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void noSign () {
	   if ((sign == "+") || (sign == "-")){
	   	   noSign = internalValue.substring(1,internalValue.length());
	   }
	   else {
		   noSign = internalValue;
	   }
	   chunkyMonkey(noSign);
   }
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
	*  Note: only used for constructor
    *  @param  s         String to be split up into
    *  @return int[] that is the string s with 8 elements in each array
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int[] chunkyMonkey (String s) {
	   int nChars = 8;
	   int length = s.length();
	   int i = 0;
	   int start = length - nChars;
	   int size = (int)(Math.ceil(length/nChars) + 1);
	   intVersion = new int[size];
	   while (length >= nChars) {
		   intVersion[i] = Integer.parseInt(s.substring(start,length));
		   start -= nChars;
		   length -= nChars;
		   i++;	   
	   }
	   if (length > 0) {
		   intVersion[i] = Integer.parseInt(s.substring(0,length));
	   }
	   return intVersion;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
	   char[] charVal = internalValue.toCharArray();
	   if ( ('-' == charVal[0]) || ('+' == charVal[0]) ) {
	       for (int i = 1; i < charVal.length; i++) {
			    if (false == Character.isDigit(charVal[i])) {
					 return false;
		            }   
	       }
	   }
	   else { 
	       for (int i = 0; i < charVal.length; i++) {
			    if (false == Character.isDigit(charVal[i])) {
				    return false;
			    }   
	       } 
	   }
	   return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt (no parameters)
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
  	  String reverse = "";                                               //declare reverse as an empty string
  	  int num = internalValue.length() - 1;                            
  	  for (int i = num; i > -.5; i--) {                                  //for loop going from the last letter of the input string 
  		  reverse += internalValue.charAt(i);                            //and puts each letter into variable reverse
  	  }
	  BrobInt rev = new BrobInt(reverse);
          return rev;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      return gint.reverser();
   }

 

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
	  String ans = "";
	  String s = "";
	  gint.chunkyMonkey(gint.toString());
	  if ( (gint.sign() == "-") && (sign() == "-") ) {
		  s = "-";
	  }
	  else if ( (gint.sign() == "-") || (sign() == "-") ) {
		  return subtract(gint);
	  }
	  int start = 0;
	  int end = 7;
	  int iterations = 0;
	  int rest = 0;
	  BrobInt bigger = ZERO;
	  int[] toCopy = intVersion;
	  if ( (gint.intVersion.length == intVersion.length) ) {
		  iterations = intVersion.length;
	  }
	  else if (gint.intVersion.length > intVersion.length) {
		  iterations = intVersion.length;                                  //iterations based on shortest length
		  bigger = gint;
		  rest = gint.intVersion.length;
		  toCopy = gint.intVersion;
	  }
	  else {
	  	  iterations = gint.intVersion.length;
		  bigger = new BrobInt(internalValue);
		  rest = intVersion.length;
		  toCopy = intVersion;
	  }
	  for (int n = 0; n < iterations; n++) {
		  int carry = 0;                                                   //reset carry to zero
		  int total = intVersion[n] + gint.intVersion[n];                  //initialize to first total value of two arrays of intVersion
		  int adderLen = String.valueOf(gint.intVersion[n]).length();      //number of digits in nth value of gint.intversion
		  int addenedLen = String.valueOf(intVersion[n]).length();         //number of digits in nth value of intversion
		  int totalLen = String.valueOf(total).length();                   //number of digits in nth value of total
		  int ptotal = 0;                                                  //set previous total to zero
		  int gintLen = 100;                                               //number of digits in gint (initial 100 to be > ptotal)
		  int intLen = 100;                                                //number of digits in int
		  if ( (totalLen == adderLen) || (totalLen == addenedLen) ) {      //if digits in total == digits in both numbers being added
		      total = intVersion[n] + gint.intVersion[n];            //find current total
		      totalLen = String.valueOf(total).length();             //number of digits in total		                                                 
		      if (n > 0) {
		          ptotal = (intVersion[n-1] + gint.intVersion[n-1]);       //if statement to create previous total if not first iteration (n=0)
			  gintLen = String.valueOf(gint.intVersion[n-1]).length(); //number of digits in previous int[]
			  intLen = String.valueOf(intVersion[n-1]).length();       //number of digits in previous gint[] 
		      } 
		      if ( (String.valueOf(ptotal).length() >  gintLen) && (String.valueOf(ptotal).length() > intLen) ) {  
				//if previous total has carry add a carry to last char in this total sequence
		          carry = 1;
			  char[] totalc = String.valueOf(total).toCharArray();                                    //characters that are part of answer(ans)
		          totalc[totalLen-1] = (char) (Integer.valueOf(totalc[totalLen-1]) + carry);              //add carry if necessary
		  	  String specific = String.valueOf(totalc).substring(0,(String.valueOf(total).length()));
			  carry = 0;
		  	  ans = specific + ans;                 //add total (includes carry) to answer	
		      }
		      else {
			      ans = String.valueOf(total) + ans;    //if no carry, add this total to answer
		      }
	  	      if (((rest - iterations) > 0) && ((1+n) == iterations)) {
  		          int left = bigger.intVersion.length;
		          int counter = iterations;
  			  for (int i = counter; i < left; i++) {
  		              int toCopi = bigger.intVersion[i];
  			      ans = String.valueOf(toCopi) + ans;
  			  }
	  	      }	
		  }
		  else if ( (totalLen > adderLen) && (totalLen > addenedLen) ) {     
			  total = intVersion[n] + gint.intVersion[n];                    //find current total
			  totalLen = String.valueOf(total).length();                     //number of digits in total
			  char[] totalc = String.valueOf(total).toCharArray();          //char array to choose specific numbers (all except first)        
			  if (n > 0) {  
			      ptotal = (intVersion[n-1] + gint.intVersion[n-1]);       //previous total (found if n>0)
			      gintLen = String.valueOf(gint.intVersion[n-1]).length(); //number of digits in previous int[]
			      intLen = String.valueOf(intVersion[n-1]).length();       //number of digits in previous gint[]
			  }
  			  if ( (String.valueOf(ptotal).length() >  gintLen)         //if previous total is greater than length of previous
  			    && (String.valueOf(ptotal).length() > intLen) ) {       //gint[] and int[], must be carry
				carry = 1;
			     	totalc[totalLen-1] = (char) (Integer.valueOf(totalc[totalLen-1]) + carry); //add carry to last integer
				carry = 0;
  			   } 		         	          					  
			  String specific = String.valueOf(totalc).substring(1,(String.valueOf(total).length()));   //add takes total and puts into string (specific)
			  ans = specific + ans;                                                                     //this is added to answer
			  if ((1+n) == iterations) {
		              ans = String.valueOf(totalc[0]) + ans;                       //if the last addition, puts back character that was taken out as carry
			  }
			  if (((rest - iterations) > 0) && ((1+n) == iterations)) {
  			      int left = bigger.intVersion.length;
			      int counter = iterations;
  			      for (int i = counter; i < left; i++) {
  			          int toCopi = bigger.intVersion[i];
  				  ans = String.valueOf(toCopi) + ans;
  			      }
	                  }
		  }
	  }
	  if ( ans.contains("-") ) {
              ans = ans;
	  }
	  else if (s == "-") {
	      ans = s + ans;
	  }  	  
	  BrobInt added = new BrobInt(ans);
          return added;
   }
 

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt gint ) {
	  BrobInt currentNoSign = new BrobInt(this.noSign);
	  BrobInt gintNoSign = new BrobInt(gint.noSign);
	  String ans = "";
	  String s = "";                                                     //will hold the sign value
	  BrobInt bigger = null;
	  BrobInt smaller = null;
	  BrobInt difference = gint;
	  int checker = currentNoSign.intVersion[intVersion.length-1] - gintNoSign.intVersion[gint.intVersion.length-1]; //will be used to check if int or gint is bigger
	  if (true == equals(gint)) {                                        //check if BrobInts are the same if so difference is 0
              difference = ZERO;
	      return difference;
	  }
	  else if ( 0 < checker ) {                                          //Decide which one is bigger and smaller
	      bigger = new BrobInt(internalValue);                                           
              smaller = gint;
	  }                                                                  //then assign BrobInt bigger and smaller accordingly
	  else if ( 0 > checker  ) {
	      bigger = gint;
              smaller = new BrobInt(internalValue);
	  }
	  if ( (sign == "-") && (gint.sign != "-") ) {                        //if subtracting negative numbers, add values and attach negative sign
	      ans = "-" + String.valueOf(currentNoSign.add(gintNoSign)); 
	      difference = new BrobInt(ans);
	      return difference;   
	  }
	  else if ( (sign != "-") && (gint.sign == "-") ) {                   //if subtracting by negative number, add values and attach positive sign
	      ans = "+" + String.valueOf(currentNoSign.add(gintNoSign)); 
	      difference = new BrobInt(ans);
	      return difference;   
	  }
	  if ( (bigger == gint) &&  ( (gint.sign != "-") && (sign != "-") )  ) {
		  s = "-";
	  }
//	  if ( ((gint.sign == "-") && (sign == "-")) || ("-" != bigger.sign) && ("-" != smaller.sign) && (gint == bigger)) {
///		  s = "-";                                                         //negative sign if bigger value is negative
//	  } 
//	  else if ( (gint.sign != "-") && (sign != "-")) {            //positive sign if bigger value is positive
//		  s = "+";
//	  }
	  int iterations = smaller.intVersion.length;                               //amount of subtractions needed to be done
	  int total = bigger.intVersion[0] - smaller.intVersion[0];                 //find first subtraction
	  if (iterations == 1) {
		  return difference = new BrobInt(String.valueOf(total));
	  }
	  if ((bigger.intVersion.length > 1) && (smaller.intVersion.length > 1)) {
	      for (int n = 1; n < iterations; n++) {
	          total = bigger.intVersion[n] - smaller.intVersion[n];                        //initialize to first total value of two arrays of intVersion
		  int ptotal = bigger.intVersion[n-1] - smaller.intVersion[n-1];             //set previous total 
		  if (ptotal < 0) {
	              int specific = ((int) Math.pow(10,8)) - Math.abs(ptotal);         //will subract previous total by "borrow" 
		      ans = String.valueOf(specific) + ans;                             //add this total to answer string
		      total = total - 1;                                                //will subtract one in this current total as borrow
		      ans = String.valueOf(total) + ans;
		  }
		  else {
		      ans = String.valueOf(ptotal) + ans;
		  } 
		  if ( ((n+1) >= iterations) && ( (bigger.intVersion.length > iterations) || ( smaller.intVersion[iterations-1] == 0)) ) { 
			   //determine if there are terms to bring down
	              int left = bigger.intVersion.length ;
		      int counter = iterations - 1;
		      for (int i = counter; i < left; i++) {
		          int toCopy = bigger.intVersion[i];
			  ans = String.valueOf(toCopy) + ans;
		      }
		  }	 
	      }
		
	  }
	  else {
              ans = String.valueOf(total);
	  }
	  ans = s + ans;
	  if ( (ans.charAt(0) == '-') && (ans.charAt(1) == '-')){
	      ans = ans.substring(2);
	  }
	  difference = new BrobInt(ans);
          return difference;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) { 
	  BrobInt stop = gint;
	  BrobInt adder = add(gint);
	  stop = stop.subtract(ONE);
	  while (!stop.equals(ZERO)) {
		  stop = stop.subtract(ONE);
	  	  adder = add(adder);                                           //repeated addition that goes until i == gint
	  }
	  adder = adder.subtract(gint);
	  String ans = String.valueOf(adder);
          if ( ((gint.sign == "-") && (sign != "-")) || ((gint.sign != "-") && (sign == "-")) ) { //add negative if necessary
	      ans = "-" + ans;
          }
	  BrobInt product = new BrobInt(ans);
	  return product;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
	   if (this.compareTo(gint) == (-1)) {  //if dividing by bigger number equals zero
               return ZERO;
	   }
	   if (this.equals(gint)) {             //if dividing by same number equals one
	       return ONE;
	   }
           if (gint.equals(ZERO)) {             //prevent from dividing by zero
	       throw new IllegalArgumentException("cannot divide by zero");
           }
	   String ans = "";
	   String q = "";
	   BrobInt d1 = gint;                   //divisor
	   BrobInt d2 = this;                   //number being divided
	   int n = d1.toString().length();      //set n == to length of divisor
	   BrobInt d3 = new BrobInt(d2.toString().substring(0,n));  
	   //copy n characters from number being divided for subtraction
	   if (d1.compareTo(d3) == 1 ) {
	       n = n+1;       //if divisor is greater than d3,should take on another digit
	       d3 = new BrobInt(this.toString().substring(0,n));
	   }
	   while (n != d2.toString().length()) { //loop stops once have looked at all numbers
	       while (d3.compareTo(d1) == 1) {  //loop stops when number subtracted is greater than divisor
	           int subtract = Integer.valueOf(String.valueOf(d3)) % Integer.valueOf(String.valueOf(d1));
		   if (subtract > 0) {
		       q = String.valueOf( (Integer.valueOf(String.valueOf(d3)) - subtract)/Integer.valueOf(String.valueOf(d1)));
		   }            
		   else if (subtract == 0) {
		             q = String.valueOf((Integer.valueOf(String.valueOf(d3)) - subtract)/Integer.valueOf(String.valueOf(d1)));
		   }
		   BrobInt multiplier = new BrobInt(q);
		   BrobInt subtractor = d1.multiply(multiplier); 
		   d3 = d3.subtract(subtractor);                      //uses mod to find remainder then subracts to get number that gets integer
	           ans = ans + q;                                     //when divided, then multiplies by this (q) and subtracts for next iteration
		   if ( n == (d2.toString().length()) ) { 
		       break;
		   }
		   String extract = "" + d2.toString().charAt(n);     //to bring down next number
		   d3 = new BrobInt(d3.toString() + extract);
		   n++;
	       } 
	   }
	   if (ans.length() < d1.toString().length()) {
	       int iterations = d1.toString().length() - ans.length();     //determine if stopped prematurely
	       for (int i = 0; i < iterations; i++) {
	           ans = ans + "0";
	       }
	   }
	   if ( ((gint.sign == "-") && (sign != "-")) || ((gint.sign != "-") && (sign == "-")) ) { //add negative if necessary
	       ans = "-" + ans;
	   }
	   BrobInt answer = new BrobInt(ans);
	   return answer;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
	   if (this.compareTo(gint) == (-1)) {
               return ZERO;
	   }
	   return this.subtract(this.divide(gint).multiply(gint));       
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
	   // handle the signs here
	   if ( 1 == bsign && 0 == gint.bsign ) {
	         return -1;
	   } 
	   else if( 0 == bsign && 1 == gint.bsign ) {
	         return 1;
	   }

	        // the signs are the same at this point
	        // check the length and return the appropriate value
	        //   1 means this is longer than gint, hence larger
	        //  -1 means gint is longer than this, hence larger
           if ( internalValue.length() > gint.internalValue.length() ) {
               return 1;
           } 
	   else if( internalValue.length() < gint.internalValue.length() ) {
               return (-1);
           } 
	   else {
               for ( int i = 0; i < internalValue.length(); i++ ) {
                   Character a = Character.valueOf( internalValue.charAt(i) );
                   Character b = Character.valueOf( gint.internalValue.charAt(i) );
                   if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
                       return 1;
                   } 
	           else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
                   return (-1);
             }
          }
       }
       return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.valueOf( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      return internalValue; 
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its int
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );  //more for debugging and see what's going on
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      System.exit( 0 );
   }
}
