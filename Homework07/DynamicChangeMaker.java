/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DynamicChangeMaker.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson
 *  Author        :  Roberto Ventura
 *  Date          :  2017-02-08
 *  Description   :  This file is an example of dynamic programming. The approach to this 
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
 *. @version 1.2.0  2018-01-27  Bob           Write the methods for DynamicChangeMaker
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker {
	
    public static int targetValue = 0;
    public static Tuple coins = null;       //coin denominations
    public static int[] demon = null;       //representation of Tuple at int array

    public DynamicChangeMaker () {
  	  super();	
    }
	   
    /**
     *  Method to validate the tuple passed in 
     *  @param   int [] that will be checked for any negative, empty values or repeats and throw exception if occurs
     *  @param   int that will be checked if empty or negative and throw exception if occurs
     *  no return value
     *  @throws  NumberFormatException
     */
     public void badData(int[] denom, int targ) throws NumberFormatException {
	for (int i = 0; i < denom.length; i++) {
            if (0 >= denom[i]) {
		throw new NumberFormatException("Bad data: can't have an empty or negative coin");
            }
	    for (int j = 0; j < denom.length; j++) {
		if (i == j) {
		    continue;
		}
	        else if (denom[i] == denom[j]) { 
		    throw new NumberFormatException("Bad data: can't have repeated coins");
		}
	    }
	 }
         if (targ <= 0) {
		throw new NumberFormatException("Bad data: can't reach negative or empty target");
         }
			
     }
     
  
  
    /**
     *  Method to validate the arguments
     *  @param   argValue  String from the main programs args[0] input
     *  @throws  NumberFormatException
     *	no return, method stores variables classwide and checks for bad data using previous method
     */
     public void validateArg (String args[]) throws NumberFormatException {
	        String[] demons = args[0].split(",");
		if (args.length <= 1) {
			throw new NumberFormatException("must input a target value and coin denominations");
		}
		try {
			for (int i = 0; i < demons.length; i++) {
				demon[i] = Integer.parseInt(demons[i]);
			}
			coins = new Tuple(demon);
		}
		catch (NumberFormatException e) {
			System.out.println("\n     Error, input a non-negative number.\n     Pobrecito!");
		}
 	        try {
		    if (args[1].length() != 1) {
		  	    throw new NumberFormatException("Bad Data: Must enter only one target value");
		    }
 		    targetValue = Integer.parseInt(args[1]);
		    if ( 0 >= targetValue) {
			    throw new NumberFormatException("Can't have a 0 or negative target");    //check if arg is valid can only be number
		    }                                                                            //that is not zero or a negative
 	        }
 	        catch (NumberFormatException e) {
 		    System.out.println("\n     Error, input a non-negative number.\n     Pobrecito!");
 	        }
		badData(demon,targetValue);
     }
	
     /**
     *  Method to optimize amount of change that can be made
     *  @param   int[] with coin denominations to be used
     *  @param   int   specifying what value to be made with coins
     *	@return, tuple with amount of coins needed to reach target value
     */	
     public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
		 DynamicChangeMaker d = new DynamicChangeMaker();
		 d.badData(denominations,target);
		 int rowCount = denominations.length;
		 int columnCount = target + 1;
		 Tuple answer = new Tuple(rowCount);
		 Tuple[][] matrix = new Tuple [rowCount][columnCount];
		 for (int i = 0; i < rowCount; i++) {
		     for (int j = 0; j < columnCount; j++) {
				 if (0 == j) {
					 matrix[i][j] = new Tuple(rowCount);            //first column of zero always tuple of zeros
				 }
				 else {
					 if ( j < denominations[i]) {             //if current target - index less than zero, must be impossible
					     matrix[i][j] = Tuple.IMPOSSIBLE;
					     if (j >= denominations[i]) {
				                 if ( (matrix[i][j-denominations[i]] != Tuple.IMPOSSIBLE)) {   //check to see if solution backwards 
					             matrix[i][j] = matrix[i][j].add(matrix[i][j-denominations[i]]);          //and add this value
				                 } 
					     }
						 if (i != 0) {
						     if ((matrix[i-1][j] == Tuple.IMPOSSIBLE)) {
						         matrix[i][j] = matrix[i][j];
						 }
						 else {
						     matrix[i][j] = matrix[i-1][j];
						 }				 	
					 }
				     }
				     else {                                 //if gets here then means you can go backward
					     Tuple zeroTuple = new Tuple(rowCount);
					     zeroTuple.setElement(i,1);         //able to take one out so add one to tuple
				 	     matrix[i][j] = zeroTuple;          //set this value
					     if ( (j - denominations[i]) >= 0 ) {   //check to see if solution backwards 
							 if (matrix[i][j - denominations[i]] == Tuple.IMPOSSIBLE) {
							 	 matrix[i][j] = Tuple.IMPOSSIBLE;
							 }
						     else {  //if entry backwards is impossible, just impossible
							     matrix[i][j] = matrix[i][j].add(matrix[i][j-denominations[i]]);          //and add this value		 	  
						     }
					     } 
					     if ( i != 0 ) {
							 if ((matrix[i-1][j] == Tuple.IMPOSSIBLE)) {
								 matrix[i][j] = matrix[i][j];
							 }
							 else {
							     matrix[i][j] = matrix[i-1][j];
						         }
					     }
				     }			     
			         }
		     }    
		 }
    	         answer = matrix[rowCount-1][columnCount-1];
	         System.out.println("answer is: " + answer.toString());
  	         return answer;
     }
	 
     public static void main (String args[]) {
		 DynamicChangeMaker dcm = new DynamicChangeMaker();
		 if ((args.length <= 1)) {
			 System.out.println("It seems that you have not entered any denominations or target value, re-run and try again!");
			 System.out.println("To enter denominations separate by commas, then hit spacebar and enter target value!");
			 System.exit(0);
		 }
		 dcm.validateArg(args);
		 dcm.makeChangeWithDynamicProgramming(dcm.demon, dcm.targetValue);
 
     }

}
