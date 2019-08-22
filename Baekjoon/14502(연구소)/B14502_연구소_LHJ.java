import java.util.*;
public class Main {
	static int N,M;
	static int[][] map;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static int ans=Integer.MIN_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		solve(0);
		System.out.println(ans);

	}
	public static class Pair{
		public int x,y;
		Pair(int x,int y){
			this.x=x;this.y=y;
		}
	}
	public static void solve(int n) {
		if(n==3) {
			bfs();
			return;
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					map[i][j]=1;
					solve(n+1);
					map[i][j]=0;
				}
			}
		}
	}
	public static void bfs() {
		Queue<Pair> que=new LinkedList<Pair>();
		int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j]=map[i][j];
				if(temp[i][j]==2) {
					que.add(new Pair(i,j));
				}
			}
		}
		while(!que.isEmpty()) {
			Pair p=que.poll();
			for(int i=0; i<4; i++) {
				if(p.x+dx[i]>=0 && p.x+dx[i]<N && p.y+dy[i]>=0 && p.y+dy[i]<M && temp[p.x+dx[i]][p.y+dy[i]]==0) {
					temp[p.x+dx[i]][p.y+dy[i]]=2;
					que.add(new Pair(p.x+dx[i],p.y+dy[i]));
				}
			}
		}
		int count=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(temp[i][j]==0) {
					count+=1;
				}
			}
		}
		ans=Math.max(ans, count);
		
	}

}
