package TryCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    int[] dx={1,0,0,1};
    int[] dy={0,1,-1,0};
    Queue<int[]> quene;
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
        for(int num:ansArr){
            System.out.print(num+",");
        }
        int index=ansArr[0]=='0'?1:0;
        while(index<len1+len2+2){
//            System.out.println(ansArr[index]);
            sb.append(ansArr[index]);
            index++;
        }
        return sb.toString();
        // return String.valueOf(realSum);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid= {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        solution.multiply("999","10");
    }
}