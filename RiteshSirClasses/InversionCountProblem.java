import java.util.Arrays;
class InversionCountProblem{
	public static void prob(int[] arr, int s, int e,int []count) {
		if (s >= e) return;

		int mid = (s + e) / 2;

		prob(arr, s, mid,count);
		prob(arr, mid + 1, e,count);

		int[] left = Arrays.copyOfRange(arr, s, mid + 1);
		int[] right = Arrays.copyOfRange(arr, mid + 1, e + 1);

		int i = 0, j = 0, k = s;

		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				arr[k++] = left[i++];
			} else {
				count[0]+=left.length-i;
				arr[k++] = right[j++];
			}
		}

		while (i < left.length) {
			arr[k++] = left[i++];
		}
		while (j < right.length) {
			arr[k++] = right[j++];
		}
	}
	public static void main(String[] args){
		int []count= new int[1];
		prob(new int[]{4,3,2,1},0,3,count);
		System.out.println(count[0]);
	}

}
