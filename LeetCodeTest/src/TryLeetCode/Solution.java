package TryLeetCode;


import java.util.*;

public class Solution {
    public int maxProfit(int[] prices){
        int maxProfit=0,temp=0;
        int minPrice=Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            if (price-minPrice>maxProfit){
                maxProfit=price-minPrice;
            }
        }
        return maxProfit;
    }
    public int singleNumber(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            if (map.containsKey(num)){
//                System.out.println(map.get(num));
                map.remove(num);
            }else{
                map.put(num,1);
            }
        }
//        int ans;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if (integerIntegerEntry!=null){
                return integerIntegerEntry.getKey();
            }
//            System.out.println(integerIntegerEntry);
        }
        return -1;
    }
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head!=null){
            if (!set.add(head)){
                return true;
            }
            head=head.next;
        }
        return false;
    }
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode Tmp=head;
        while (Tmp!=null){
            list.add(Tmp.val);
            Tmp=Tmp.next;
        }
        int front=0;
        int back=list.size()-1;
        while (front<back){
            if (!list.get(front).equals(list.get(back))){
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
    public void moveZeroes(int[] nums) {
        int j=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0){
                nums[j++]=nums[i];
            }
        }
//        for (int i=j;i< Arrays.stream(nums).findFirst())
        for (int i = j; i < nums.length; i++) {
            nums[i]=0;
        }

    }
    public int getBinaryCounts(int n){
        int k,cnt=0;
        while (n>0){
            k=n%2;
            n=n/2;
            if (k==1){
                cnt++;
            }
        }
        return cnt;
    }
    public int[] countBits(int n) {
        int temp=n;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(getBinaryCounts(temp--));
        }
        int[] nums = new int[list.size()];
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            nums[i]=list.get(i);
        }
        return nums;
    }
    public void reorderList(ListNode head) {

    }
    public int countNums(int nums[]){
        if (nums.length==1){
            return nums[0];
        }
        int temp;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length-1; j++) {
                if (nums[j]<nums[j+1]){
                    temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                }
            }
        }
        return nums[nums.length/2];
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> ansLists = new ArrayList<>();

        for (int first = 0; first < len; first++) {
            if (first>0&&nums[first]==nums[first+1]){
                continue;
            }
            int third = len -1;
            int target=-nums[first];
            for (int second = first+1; second < len; second++) {
                if (second>first+1&&nums[second]==nums[second+1]){
                    continue;
                }
                while (second<third && nums[second]+nums[third]>target){
                    third--;
                }

                if (second==third){
                    break;
                }
                if (nums[second]+nums[third]==target){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ansLists.add(list);
                }
            }
        }
        return ansLists;
    }
//    public int search(int[] nums, int target) {
//        int len=nums.length;
//        int first=0;
//        int second=len-1;
//        int mid=(first+second)/2;
//        while(first<second){
//            if(nums[mid]>target){
//                second=mid-1;
//            }else if(nums[mid]<target){
//                first=mid+1;
//            }else{
//                break;
//            }
//            mid=(first+second)/2;
//        }
//        return mid;
//    }
    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diff = 0;
                for (int k = 0; i + k < m && j + k < n; k++) {
                    diff += s.charAt(i + k) == t.charAt(j + k) ? 0 : 1;
                    if (diff > 1) {
                        break;
                    } else if (diff == 1) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        //细节，这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
        //例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
        //输入：[3,1,1,0] k = 2时则不会漏掉
        //因为presum[3] - presum[0]表示前面 3 位的和，所以需要map.put(0,1),垫下底
        map.put(0, 1);
        int count = 0;
        int presum = 0;
        for (int x : nums) {
            presum += x;
            //当前前缀和已知，判断是否含有 presum - k的前缀和，那么我们就知道某一区间的和为 k 了。
            if (map.containsKey(presum - k)) {
                count += map.get(presum - k);//获取次数
            }
            //更新
            map.put(presum,map.getOrDefault(presum,0) + 1);
        }
        return count;
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb=new StringBuilder();
        int n1=num1.length();
        int n2=num2.length();
        if(n1>n2){
            for(int i=0;i<n1-n2;i++){
                num2="0"+num2;
            }
        }else{
            for(int i=0;i<n2-n1;i++){
                num1="0"+num1;
            }
        }
        char[] arr1=new char[n1];
        char[] arr2=new char[n2];
        arr1=num1.toCharArray();
        arr2=num2.toCharArray();
        int carry=0;
        int sum=0;
        for(int i = (Math.max(n1, n2))-1; i>=0; i--){
            sum=(arr1[i]-'0')+(arr2[i]-'0')+carry;
            carry=sum/10;
            sum=sum>=10?sum-10:sum;
            System.out.println(carry);
            System.out.println(sum);
            sb.append(sum);
        }
        if(carry!=0){
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public int longestPalindrome(String s) {
        int[] arr=new int[s.length()];
        char[] ch=s.toCharArray();
        for(int i=0;i<ch.length;i++){
            arr[ch[i]-'a']++;
        }
        Arrays.sort(arr);
        int sum=0,max=0;
        for(int i=0;i< arr.length;i++){
            if (arr[i]!=0){
                if (arr[i]%2==0){
                    sum+=arr[i];
                }else {
                    if (max<arr[i]){
                        max=arr[i];
                    }
                }
            }
        }
        return max+sum;
    }

    public int findRepeatNumber(int[] nums) {
        int[] numCount=new int[11];
        int n=nums.length;
        for(int i=0;i<n;i++){
            while(nums[i]>0){
                int k=nums[i]%10;
                nums[i]/=10;
                numCount[k]++;
                System.out.println(k);
            }
        }
        // int
        for(int i=0;i<11;i++){
            if(numCount[i]>1)return i;
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int Index=binary(nums,target);
        if(Index==-1)return 0;
        int leftIndex=Index;
        int RightIndex=Index;
        while(leftIndex>=0){

            if(nums[leftIndex]==target){
                leftIndex--;
            }else{
                break;
            }
        }
        while(RightIndex<=nums.length-1){
            if(nums[RightIndex]==target){
                RightIndex++;
            }else{
                break;
            }
        }
        System.out.println(RightIndex);
        System.out.println(leftIndex);
        return RightIndex-leftIndex+1;
    }

    public int binary(int[] nums,int target){
        int left=0;
        int n=nums.length;
        int right=n-1;
        while(left<=right){
            int mid=(right-left)/2+left;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return -1;
    }

    public int maxWidthOfVerticalArea(int[][] points) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<points.length;i++){
            list.add(points[i][0]);
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int Max=0;
        for(int i=0;i<list.size()-1;i++){
            int temp=list.get(i+1)-list.get(i);
            System.out.println(temp);
            Max=Math.max(Max,temp);
        }
        return Max;
    }
    public boolean wordPattern(String pattern, String s) {
        Map<Character,String> map=new HashMap<>();
        char[] ch=pattern.toCharArray();
        String[] ch2=s.split(" ");
        if(ch.length!=ch2.length){
            return false;
        }
        for(int i=0;i<ch.length;i++){
            if(map.containsKey(ch[i])&&!map.get(ch[i]).equals(ch2[i])){
                return false;
            }else{
                map.put(ch[i],ch2[i]);
            }
        }
        return true;
    }
    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode cur=head;
//        ListNode
        while(cur!=null){
            ListNode node=cur.next;
            cur.next=prev;
            prev=cur;
            cur=node;
        }
        return prev;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr= {{3,1},{9,0},{1,0},{1,4},{5,3},{8,8}};
        System.out.println(solution.wordPattern("abba","dog cat cat dog"));
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}