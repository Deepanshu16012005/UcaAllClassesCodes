import java.util.*;
public class GenerateParenthesis {
    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * 
     * Constraints:
     * 1. 1 <= n <= 8
     * 2. The solution set must not contain duplicate combinations.
     * 
     * Example:
     * Input: n = 3
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     * 
     * @param n - Number of pairs of parentheses.
     * @returns List<String> - A list of all combinations of well-formed parentheses.
     */
    public List<String> generateParenthesis(int n) {
      List<String> list = new ArrayList<>();
      helper(list,n,new StringBuilder(),0,0);
      return list;
    }
    public void helper(List<String> list, int n, StringBuilder sb, int oB , int cB){
      if(sb.length() == n*2){
        list.add(sb.toString());
	return;
      }
      if(oB < n){
	sb.append("(");
        helper(list , n , sb , oB+1 , cB);
	sb.deleteCharAt(sb.length() - 1);
      }
      if(cB < oB){
        sb.append(")");
        helper(list , n , sb , oB , cB+1);
        sb.deleteCharAt(sb.length() - 1);
      }
    }

    /**
     * Main method for testing the GenerateParenthesis class.
     */
    public static void main(String[] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        int n = 3;
        List<String> result = gp.generateParenthesis(n);
        List<String> expected = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        
        assert result.size() == expected.size() && result.containsAll(expected) : "Test case failed";
    }
}
