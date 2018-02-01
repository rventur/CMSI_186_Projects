/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson
 *  Author        :  Roberto Ventura
 *  Date          :  2017-02-08
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]. Example methods: reverse, contains vowel, if palindrome
 *                   even/odd letters in string and not returning duplicate odd/even letters
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 *. @version 1.2.0  2018-01-27  Bob.          Write the methods for StringStuff
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static final String vowels = "aeiouy";                         //declare list of vowels as a string, y will be included
   public static final String alphabet = "0abcdefghijklmnopqrstuvwxyz";  //declare alphabet(uppercase and lowercase) as a string
   public static final String ALPHABET = "oABCDEFGHIJKLMNOPQRSTUVWXYZ";
	  
   public static boolean containsVowel (String s) {
	  s = s.toLowerCase();
	  int n = s.length();
	  for (int i = 0; i < n; i++) {                                    //for loop to carry value of individual letters in string S
		  for (int j = 0; j < vowels.length(); j++) {              //second for loop to compare each letter to a vowel
		      if (s.charAt(i) == vowels.charAt(j)) {
			    return true;
		      }
	          }
	   }
       return false;
   }
   
   /**
    * Method to return the reverse of a string passed as an argument
    *
    * @param s String containing the data to be reversed
    * @return  String containing the reverse of the input string
    */
   public static String reverse (String s) {
 	  String reverse = "";                                             //declare reverse as an empty string
 	  int num = s.length() - 1;                            
 	  for (int i = num; i > -.5; i--) {                                //for loop going from the last letter of the input string 
 		  reverse += s.charAt(i);                                  //and puts each letter into variable reverse
 	  }
       return reverse;
    }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome (String s) {
	  s = s.toLowerCase();                                              //changes input string to lowercase
	  return (s.contentEquals(reverse(s)));                             //takes reverse of input string using method reverse 
   }                                                                        //and compares it to original string

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly (String s) {
	  String evens = "";
	  for (int p = 0; p < s.length(); p++ ) {
		  for (int e = 2; e<27; e+=2) {                                 //for loop that goes checks alphabet for even letters
			  if (s.charAt(p) == ALPHABET.charAt(e)) {              //if even letter added to empty variable evens
				  evens += s.charAt(p);
			  }
			  else if (s.charAt(p) == alphabet.charAt(e)) { 
				  evens += s.charAt(p);
			  }
		  }
	  }
      return evens;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly (String s) {
	  String odds = "";
 	  for (int l = 0; l < s.length(); l++) {
		  for (int o = 1; o < 27; o +=2) {                               //for loop that goes checks alphabet for odd letters
			  if (s.charAt(l) == ALPHABET.charAt(o)) {               //if odd letter added to empty variable odds
				  odds += s.charAt(l);
			  }
			  else if (s.charAt(l) == alphabet.charAt(o)) { 
				  odds += s.charAt(l);
			  }
		  }
 	  }
      return odds;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes (String s) {
	  String check = evensOnly(s);
	  String nDupe = "";
	  for (int d = 0; d < check.length(); d++) {
		  String f = "" + check.charAt(d);                             //uses evensonly method to get evens then uses for loop that
		  f = f.toLowerCase();                                         //uses if statements to determine if there are duplicates
		  String F = f.toUpperCase();       
		  if (nDupe.contains(f) || nDupe.contains(F)) {
			  continue;
		  }
		  else {
		  	  nDupe += check.charAt(d);
		  }	 
	  }
      return nDupe;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes (String s) {
	  String checks = oddsOnly(s);
	  String noDupe = "";
	  for (int w = 0; w < checks.length(); w++) {
		  String y = "" + checks.charAt(w);
		  y = y.toLowerCase();                                        //uses oddsonly method to get evens then uses for loop that
		  String Y = y.toUpperCase();                                 //uses if statements to determine if there are duplicates
		  if (noDupe.contains(y)|| noDupe.contains(Y)) {
			  continue;
		  }
		  else {
		  	  noDupe += checks.charAt(w);
		  }	 
	  }
      return noDupe;
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main (String args[]) {
	   
	  System.out.println("Initializing Testing in 3 2 1 ...");
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
	  
   }
}
