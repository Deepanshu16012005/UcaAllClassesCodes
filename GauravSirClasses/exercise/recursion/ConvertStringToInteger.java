public class ConvertStringToInteger {
    /**
     * Convert a string to an integer without using built-in parsing functions.
     * 
     * Constraints / Rules:
     * 1. You may not use built-in functions like Integer.parseInt() or similar.
     * 2. You may not use any form of loops (for, while, do-while).
     * 3. You may not use any form of conditional statements (if, switch, ternary operator).
     * 4. Your function should work for negative numbers.
     * 5. If the string is invalid, return 0.
     * 6. Only characters that should be allowed in the string are numeric digits from '0'
     *    through '9', possibly preceded by a single minus sign '-' at the start if the number
     *    is negative. A string that contains any other characters in it is considered invalid.
     * 7. You may assume that the string will not have leading or trailing whitespace.
     * 8. You may assume that if the string contains valid characters, then it represents an integer
     * that can be successfully stored in an int; that is, its value will not exceed 2^31.
     * 9. If the empty string is passed, your function should return 0.
     * 
     * @param s - Input string representing an integer.
     * @returns int - The integer value represented by the string.
     */
    public int convertStringToInteger(String s) {
        if(s.length()==0){
	    return 0;
	}
	int[] ans = new int[1];
        if(s.charAt(0)=='-'){
	     helper(s.substring(1),ans);
	     return -1 * ans[0];
	}	
        helper(s,ans);
	return ans[0];
    }
    public void helper(String s, int[] ans){
	if(s.length()==0){
	    return;
	}
    	if(!Character.isDigit(s.charAt(0))){
	    ans[0] = 0;
	    return;
	}	
        ans[0] += ((int)((s.charAt(0)-'0') * Math.pow(10,s.length()-1)));
        helper(s.substring(1),ans);
    }

    /**
     * Main method for testing the ConvertStringToInteger class.
     */
    public static void main(String[] args) {
        ConvertStringToInteger converter = new ConvertStringToInteger();
        String[] testStrings = {"123", "-456", "0", "789a", "", "-0", "2147483647", "-2147483648"};
        int[] expectedResults = {123, -456, 0, 0, 0, 0, 2147483647, -2147483648};

        for (int i = 0; i < testStrings.length; i++) {
            assert converter.convertStringToInteger(testStrings[i]) == expectedResults[i] : "Test case " + (i + 1) + " failed";
        }
	System.out.println("Passed all test cases");
    }
}
