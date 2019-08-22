import java.util.*;
public class Solution {
	static int N,K;
	//static int[][] map;
	
	static int dx[]= {-1,0,1,0};
	static int dy[]= {0,1,0,-1};
	
	static int ans;
	public static class Pair{
		public int x,y;
		Pair(int x, int y){
			this.x =x;
			this.y=y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t<T; t++) {
			N=sc.nextInt();
			K=sc.nextInt();
			
			Queue<Pair> que=new LinkedList<Pair>();
			int high=0;
			int[][] map=new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]=sc.nextInt();
					if(map[i][j]==high) {
						que.add(new Pair(i,j));
					}
					else if(map[i][j]>high) {
						high=map[i][j];
						que.clear();
						que.add(new Pair(i,j));
					}
				}
			}
			ans=Integer.MIN_VALUE;
			while(!que.isEmpty()) {
				dfs(que.poll(),0,K,map,new int[N][N]);
			}
			System.out.println(ans);
			System.out.println(Integer.MIN_VALUE);
		}

	}
	public static void dfs(Pair p, int n, int k, int[][] m, int[][] visited) {
		if(k==0) {
			ans=Math.max(ans, n);
			return;
		}
		int[][] copy=new int[N][N];
		int[][] check=new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j]=m[i][j];
				check[i][j]=visited[i][j];
			}
		}

		check[p.x][p.y]=1;
		for(int d=0; d<4; d++) {
			if(p.x+dx[d]>=0 && p.x+dx[d]<N && p.y+dy[d]>=0 && p.y+dy[d]<N && check[p.x+dx[d]][p.x+dx[d]]!=1) {
				if(copy[p.x][p.y]<=copy[p.x+dx[d]][p.y+dy[d]]
						&& k>=copy[p.x+dx[d]][p.y+dy[d]]-copy[p.x][p.y]+1) {
					
					copy[p.x+dx[d]][p.y+dy[d]]=copy[p.x][p.y]-1;
					k=k-(copy[p.x+dx[d]][p.y+dy[d]]-copy[p.x][p.y]+1);
					dfs(new Pair(p.x+dx[d],p.y+dy[d]),n+1,k,copy,check);
				
				}else if(copy[p.x][p.y]>copy[p.x+dx[d]][p.y+dy[d]]){
					dfs(new Pair(p.x+dx[d],p.y+dy[d]),n+1,k,copy,check);
				}
			}
		}
	}
}
