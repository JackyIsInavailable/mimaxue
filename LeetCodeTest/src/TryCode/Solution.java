package TryCode;

import java.util.*;

class Solution {
    int[] dx={1,0,0,1};
    int[] dy={0,1,-1,0};
    Queue<int[]> quene;
    List<List<Integer>> edges;
    int[] visited;
    boolean valid=true;
    public int maxAreaOfIsland(int[][] grid) {
        // int count=0;

        int m=grid.length;
        int n=grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(grid[i][j]+",");
            }
            System.out.println();
        }
        System.out.println("--------------");
        int maxCount=0;
        quene=new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    maxCount=Math.max(maxCount,getCount(grid,i,j,m,n));
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(grid[i][j]+",");
            }
            System.out.println();
        }
        return maxCount;
    }
    public int getCount(int[][] grid,int i,int j,int m,int n){
        int count=1;
        quene.offer(new int[]{i,j});
        grid[i][j]=2;
        while(!quene.isEmpty()){
            int[] axis=quene.poll();
            for(int index=0;index<4;index++){
                int x=axis[0]+dx[index];
                int y=axis[1]+dy[index];
                if(x>=0&&x<m&&y>=0&&y<n&&grid[x][y]==1){
                    // System.out.println("1");
                    quene.offer(new int[]{x,y});
                    grid[x][y]=2;
                    count++;
                }
            }
        }
        return count;
    }

    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")){
            return "0";
        }
        int len1=num1.length()-1;
        int len2=num2.length()-1;
        int[] ansArr = new int[len1+2+len2];
        StringBuilder sb=new StringBuilder();
        for(int i=len1;i>=0;i--){
            int x=num1.charAt(i)-'0';
            for(int j=len2;j>=0;j--){
                int y=num2.charAt(j)-'0';
                ansArr[i+j+1]+=x*y;
            }
        }
        for(int i=len1+len2+1;i>0;i--){
            ansArr[i-1]+=ansArr[i]/10;
            ansArr[i]=ansArr[i]%10;
        }
//        for(int num:ansArr){
//            System.out.print(num+",");
//        }
        int index=(ansArr[0]==0)?1:0;
        while(index<len1+len2+2){
            System.out.println(ansArr[index]);
            sb.append(ansArr[index]);
            index++;
        }
        return sb.toString();
        // return String.valueOf(realSum);
    }
    public int minNumber(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int min=Integer.MAX_VALUE;
        for(int i=0;i<nums1.length;i++){
            for(int j=0;j<nums2.length;j++){
                System.out.print(nums1[i]+",");
                System.out.print(nums2[j]+",");
                System.out.println(nums1[i] == nums2[j]);
                if(nums1[i]==nums2[j]&&nums1[i]<min){
                    min=nums1[i];
//                    System.out.println(min);

                }
            }
        }
        int ans=nums1[0]>nums2[0]?nums2[0]*10+nums1[0]:nums1[0]*10+nums2[0];
        if(min!=Integer.MAX_VALUE){
            return min;
        }
        return ans;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges=new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            edges.add(new ArrayList<Integer>());
        }
        visited=new int[numCourses];
        for(int[] info:prerequisites){
            edges.get(info[1]).add(info[0]);
        }
        Iterator<List<Integer>> iterator = edges.iterator();
        while (iterator.hasNext()){
            List<Integer> next = iterator.next();
            Iterator<Integer> iterator1 = next.iterator();
            while (iterator1.hasNext()){
                Integer next1 = iterator1.next();
                System.out.println(next1);
            }
        }
        return true;
    }

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int sLen=s.length();
        int[] ansVal=new int[sLen];
        boolean contains;
        for(int i=0;i<sLen;i++){
            contains=false;
            for(int j=0;j<chars.length();j++){
                if(s.charAt(i)==chars.charAt(j)){
                    contains=true;
                    ansVal[i]=vals[chars.indexOf(s.charAt(i))];
                    break;
                }
            }
            if(!contains){
                ansVal[i]=s.charAt(i)-'a';
            }
        }
        for(int num:ansVal){
            System.out.println(num);
        }
        Arrays.sort(ansVal);
        return ansVal[ansVal.length-1];
        // for(int i=0;i;)
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[][] grid= {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
//        System.out.println(solution."d",new int[]{-1000}));
        solution.canFinish(2,new int[][]{{1,0},{0,1}});
    }
}