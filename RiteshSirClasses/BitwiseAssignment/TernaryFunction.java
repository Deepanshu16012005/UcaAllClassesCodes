class TernaryFunction{
  public static int conditional(int x , int y , int z){
    int isZero = ((~x+1) | x) >> 31;
    return (isZero & y) | (~isZero & z);
  }
  public static void main(String[] args){
    System.out.println(conditional(2,4,5));
    System.out.println(conditional(-2,4,5));
    System.out.println(conditional(0,4,5));
  }
}

