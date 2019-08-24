import java.util.*;
public class Solution {
	static int N;
	static int[][] map;
	
	static int dx[]= {1,1,-1,-1};
	static int dy[]= {-1,1,1,-1};
	
	static int[] check;
	
	static int ans;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int t=1; t<=T; t++) {
			check=new int[101];
			map=new int[N][N];
			ans=-1;
			
			int distance[]=new int[2];
			N=sc.nextInt();
			map=new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]=sc.nextInt();
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					check[map[i][j]]=1;
					solve(new Pair(i,j),distance,0,0);
					check[map[i][j]]=0;
				}
			}
			System.out.println("#"+t+" "+ans);
		}
	}
	public static boolean isPossible(int x,int y,int[] distance) {
		int[] tempC=new int[101];
		for(int i=0; i<distance[0]; i++) {
			x=x+dx[2];y=y+dy[2];
			if( (x>=0 && y>=0 && x<N && y<N) &&
					check[map[x][y]]!=1 && tempC[map[x][y]]!=1) {
				
				tempC[map[x][y]]=1;
			}else {
				return false;
			}
			
		}
		for(int i=0; i<distance[1]-1; i++) {
			x=x+dx[3];y=y+dy[3];
			if( (x>=0 && y>=0 && x<N && y<N) &&
					check[map[x][y]]!=1 && tempC[map[x][y]]!=1) {
				
				tempC[map[x][y]]=1;
			}else {
				return false;
			}
			
		}
		x=x+dx[3];y=y+dy[3];
		return true;
	}
	public static void solve(Pair p,int[] distance, int d,int n) {
		if(distance[0]!=0 && distance[1]!=0) {
			if(isPossible(p.x,p.y,distance)) {
				ans=Math.max(ans, n*2);
			}
		}
		for(int i=d; i<2;i++) {	
			if(p.x+dx[i]>=0 && p.y+dy[i]>=0 && p.x+dx[i]<N && p.y+dy[i]<N
					&& check[map[p.x+dx[i]][p.y+dy[i]]]!=1) {
				
				distance[i]+=1;
				check[map[p.x+dx[i]][p.y+dy[i]]]=1;
				
				solve(new Pair(p.x+dx[i], p.y+dy[i]),distance,i,n+1);
				
				distance[i]-=1;
				check[map[p.x+dx[i]][p.y+dy[i]]]=0;
			}
		}
		
	}
	public static class Pair{
		public int x,y;
		Pair(int x,int y){
			this.x=x;this.y=y;
		}
	}
}
