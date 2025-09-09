//sign function – return 1 if positive, 0 if zero, and –1 if negative.  Examples: sign(130) = 1, sign(-23) = -1
//You are allowed to use only one of these : ! ~ & ^ | + << >>
//Max ops : 10

import java.util.*;
class SignFunction {
  public static int sign(int n){
    int neg = n>>31;
    int pos = ((~n+1)>>31);
    pos = (~pos+1);
    return neg | pos;
  }
  public static void main(String []args){
    System.out.println(sign(100));
    System.out.println(sign(-100));
    System.out.println(sign(0));
  }
}
