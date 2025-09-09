class GCD{
  public static int Gcd (int a,int b){
    if(a==b){
      return a;
    }
    if(a>b){
      return Gcd(a-b,b);
    }else{
      return Gcd(b-a,a);
    }
  }
}
