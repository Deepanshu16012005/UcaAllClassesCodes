import java.util.Scanner;
class TowerOfHanoi{
  public static void towerOfHanoi(int n,char source, char helper, char destination,int []moves){
    if(n==1){
      moves[0]++;
      System.out.println("move "+source+" to "+destination);
      return;
    }
    towerOfHanoi(n-1,source,destination,helper,moves);
    moves[0]++;
    System.out.println("move "+source+" to "+destination);

    towerOfHanoi(n-1,helper,source,destination,moves);
  }
  public static void main (String[] args){
    int []moves=new int[1];
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter no of disks: ");
    int n = sc.nextInt();
    towerOfHanoi(n,'S','H','D',moves);
    System.out.println("no of moves: "+moves[0]);
  }
}
