class MergeArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // complete the code.
        // testcase 1,3,5  2,4,6
        //for(int i=m;i<nums1.length;i++){
        //    nums1[i] = nums2[i-m];
        //}
        //int left1 = 0;
        //int left2 = m;
        //while(left1<left2 && left2 < nums1.length){
        //    if(nums1[left1] > nums1[left2]){
        //        swap(nums1,left1,left2);
        //        left2++;
        //    }
        //    left1++;
        //}
        // not failing
        //
        int i = m-1;
        int j = n-1;
        int lastIndex = m + n - 1;
        while(i>=0 && j>=0){
            if(nums1[i]>nums2[j]){
                nums1[lastIndex--] = nums1[i--];
            }else{
                nums1[lastIndex--] = nums2[j--];
            }
        }
        while(j>=0){
            nums1[lastIndex--] = nums2[j--];
        }
        while(i>=0){
            nums1[lastIndex--] = nums1[i--];
        }
    }
    public void swap(int []arr,int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
}

