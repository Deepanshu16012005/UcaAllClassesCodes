class countDivisor{
  public static int noDivisor(int a){
    int count = 0;
    for(int i = 1; i * i <= a; i++){
      if( a % i == 0){
        count++;
	if(a / i != i){
	  count++;
	}
      }
    }
   return count; 
  }
  public static void main(String[] args){
    System.out.println(noDivisor(14));
    System.out.println(noDivisor(16));
  }
}
