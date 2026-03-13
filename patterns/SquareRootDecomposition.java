import java.util.*;
class SquareRootDecomposition{
  public static int[] blocking(int[] arr){
    int sqrt = (int)Math.sqrt(arr.length);
    int[] blocks = new int[sqrt+1];
    for(int i = 0; i < arr.length; i++){
      blocks[i/sqrt] += arr[i];
    }
    //System.out.println(Arrays.toString(blocks));
    return blocks;
  }
  public static int query(int left, int right, int[] arr, int[] blocks){
    int sum = 0;
    int sqrt = (int)Math.sqrt(arr.length);
    while(left <= right && left != 0 && left % sqrt != 0){
      sum += arr[left];
      left++;
    }
    while(left + sqrt <= right){
      sum += blocks[left / sqrt];
      left += sqrt;
    }
    while(left <= right){
      sum += arr[left];
      left++;
    }
    return sum;
  }
  public static void main(String[] args){
    int[] arr = {2,1,4,7,8,6,5,1,9,3};
    int[] blocks = blocking(arr);
    int queryStartRange = 2;
    int queryEndRange = 7;
    System.out.println(query(queryStartRange,queryEndRange,arr,blocks));
  }

}
