class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);
        int l = 0,r = n - 1;

        while(l <= r){
            int mid = (l + r) / 2;
            int h = n - mid;
            if(citations[mid] >= h){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return  n - l;
    }
}