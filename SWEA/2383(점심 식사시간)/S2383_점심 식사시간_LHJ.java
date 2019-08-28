import java.util.*;
public class Solution {
	public static int N;
	public static int[][] map;
	
	public static Pair stair1,stair2;
	
	public static int ans;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for(int t=1; t<=T; t++) {
			N=sc.nextInt();
			map=new int[N][N];
			
			int npeople=0;
			Pair[] pp=new Pair[10];
			
			stair1=null;stair2=null;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]=sc.nextInt();
					if(map[i][j]==1) {
						pp[npeople]=new Pair(i,j);
						npeople+=1;
					}else if(map[i][j]>=2){
						if(stair1==null) {
							stair1=new Pair(i,j);
						}else {
							stair2=new Pair(i,j);
						}
					}
				}
			}
			ans=Integer.MAX_VALUE;
			solve(new int[npeople],0,npeople,pp);
			System.out.println("#"+t+" "+ans);
		}
	}
	public static void solve(int[] people, int n, int nPeople,Pair[] pp) {
		if(n==nPeople) {
			LinkedList<Integer> list1=new LinkedList<Integer>();
			LinkedList<Integer> list2=new LinkedList<Integer>();
			for(int i=0; i<nPeople; i++) {
				if(people[i]==0) {
					list1.add(Math.abs(pp[i].x-stair1.x)+Math.abs(pp[i].y-stair1.y));
				}else {
					list2.add(Math.abs(pp[i].x-stair2.x)+Math.abs(pp[i].y-stair2.y));
				}
			}
			Collections.sort(list1);Collections.sort(list2);
			
			LinkedList<Integer> s1=new LinkedList<Integer>();
			LinkedList<Integer> s2=new LinkedList<Integer>();
			
			int time=0;
			while(!(list1.isEmpty() && list2.isEmpty() && s1.isEmpty() && s2.isEmpty())) {
				time+=1;
				
				while(!s1.isEmpty() && s1.get(0)==0) {
					s1.remove(0);
				}
				for(int i=0; i<s1.size(); i++) {
					s1.set(i, s1.get(i)-1);
				}
				while(!list1.isEmpty() && list1.get(0)<time && s1.size()<3) {
					list1.remove(0);
					s1.add(map[stair1.x][stair1.y]-1);
				}
				
				while(!s2.isEmpty() && s2.get(0)==0) {
					s2.remove(0);
				}
				for(int i=0; i<s2.size(); i++) {
					s2.set(i, s2.get(i)-1);
				}
				while(!list2.isEmpty() && list2.get(0)<time && s2.size()<3) {
					list2.remove(0);
					s2.add(map[stair2.x][stair2.y]-1);
				}
			}
			ans=Math.min(ans, time);
			return;
		}
		for(int i=0; i<2; i++) {
			people[n]=i;
			solve(people,n+1,nPeople,pp);
		}
	}
	public static class Pair{
		public int x,y;
		Pair(int x, int y){
			this.x=x; this.y=y;
		}
	}

}
