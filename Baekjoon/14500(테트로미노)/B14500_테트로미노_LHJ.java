import java.util.*;
public class Main {
	static int N,M;
	static int[][] map;
	static int[][] visited;
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {-0,1,0,-1};
	
	static int ans=Integer.MIN_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		map=new int[N][M];
		visited=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dfs(new Pair(i,j),0,0,new LinkedList<Pair>());
			}
		}
		System.out.println(ans);
	}
	public static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void dfs(Pair p,int n,int sum,LinkedList<Pair> list){
		if(n==3) {
			for(int i=0; i<list.size(); i++) {
				Pair pp=list.get(i);
				for(int d=0; d<4; d++) {
					if(pp.x+dx[d]>=0 && pp.x+dx[d]<N && pp.y+dy[d]>=0 && pp.y+dy[d]<M && visited[pp.x+dx[d]][pp.y+dy[d]]!=1) {
						ans=Math.max(ans, sum+map[pp.x+dx[d]][pp.y+dy[d]]);
					}
				}	
			}
			return;
		}
		for(int d=0; d<4; d++) {
			if(p.x+dx[d]>=0 && p.x+dx[d]<N && p.y+dy[d]>=0 && p.y+dy[d]<M && visited[p.x+dx[d]][p.y+dy[d]]!=1) {
				visited[p.x+dx[d]][p.y+dy[d]]=1;
				list.add(new Pair(p.x+dx[d],p.y+dy[d]));
				dfs(new Pair(p.x+dx[d],p.y+dy[d]),n+1,sum+map[p.x+dx[d]][p.y+dy[d]],list);
				list.pollLast();
				visited[p.x+dx[d]][p.y+dy[d]]=0;
			}
		}
	}
}
