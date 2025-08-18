//https://open.kattis.com/problems/jugglingpatterns?tab=metadata
import java.util.*;
class JugglingProblem{
    public static void juggle(String pattern){
        int sum = 0 ;
	int [] arr = new int [pattern.length()];
	for(int i=0;i<pattern.length();i++){
	    arr[i]=Character.getNumericValue(pattern.charAt(i));
	    sum+=arr[i];
	}
	if(sum%pattern.length()!=0){
            System.out.println("Invalid with # no of balls");
	    return;
	}
	int balls = sum/pattern.length();
	HashSet<Integer>set= new HashSet<>();
	int beat=0;
	for(int num:arr){
	    if(set.contains((num+beat)%pattern.length())){
       	        System.out.println("Invalid with "+balls+" no of balls");
		return ;
	     }
	    set.add((num+beat)%pattern.length());
	    beat++;
	}
	System.out.println("Valid with "+balls+" no of balls");
    }

    public static void main(String[] args) {
        // Use a Scanner to read from standard input (the console)
        Scanner scanner = new Scanner(System.in);

        // Loop as long as there is another line of input to read
        while (scanner.hasNextLine()) {
            String pattern = scanner.nextLine();

            // Skip any empty lines that might be in the input
            if (pattern == null || pattern.trim().isEmpty()) {
                continue;
            }

            // Call the method that contains the core logic to validate the pattern
            juggle(pattern);
        }

        // It's good practice to close the scanner when you're done with it
        scanner.close();
    }
}
