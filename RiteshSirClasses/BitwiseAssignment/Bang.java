//bang – Compute !x without using !

//Examples: bang(3)=0, bang(0)=1

//Legal ops: ~ & ^ | + << >>



import java.util.*;
class Bang {
  public static int bang(int n){
    return ((n>>31) | ((~n+1)>>31)) + 1;
  }
  public static void main(String []args){
    System.out.println(bang(19));
    System.out.println(bang(-19));
    System.out.println(bang(0));

  }
}
