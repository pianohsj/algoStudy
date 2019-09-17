import java.util.*;
public class Main {
	static int N,M;
	static int[][] map;
	//static int[][] pickCheck;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	static int nSpace=0;
	static int ans=Integer.MAX_VALUE;
	static Pair[] virus;
	static int nV=0;
	public static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x=x;this.y=y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		map=new int[N][N];
		//pickCheck=new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]==0) {
					nSpace+=1;
				}else if(map[i][j]==2) {
					nV+=1;
				}
			}
		}
		virus=new Pair[nV];
		int idx=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==2) {
					virus[idx]=new Pair(i,j);
					idx+=1;
				}
			}
		}
		if(nSpace==0) {
			System.out.println(0);
		}else {
			solve(0,0,new int[M]);
			if(ans==Integer.MAX_VALUE) {
				System.out.println(-1);
			}else {
				System.out.println(ans);
			}
		}
	}
	public static void solve(int s,int n,int[] pick) {
		if(n==M) {
			Queue<Pair> que=new LinkedList<Pair>();
			int time=0;
			
			int[][] countmap=new int[N][N];
			int[][] visited=new int[N][N];
			for(int i=0; i<M; i++) {
				visited[virus[pick[i]].x][virus[pick[i]].y]=1;
				que.add(virus[pick[i]]);
			}
			
			int countS=0;
			a:while(!que.isEmpty()) {
				Pair p=que.poll();
				for(int d=0; d<4; d++) {
					int nextX=p.x+dx[d];
					int nextY=p.y+dy[d];
					if(nextX>=0 && nextY>=0 && nextX<N && nextY<N
							&& map[nextX][nextY]!=1 && visited[nextX][nextY]==0) {
						
						if(map[nextX][nextY]==0) {
							countS+=1;
						}
						visited[nextX][nextY]=1;
						countmap[nextX][nextY]=countmap[p.x][p.y]+1;
						time=Math.max(time, countmap[nextX][nextY]);
						que.add(new Pair(nextX,nextY));
						if(countS==nSpace) {//빈칸이 없음에도 계속 bfs 도는 거 예외처리
							break a;
						}
					}
				}
			}
			if(countS==nSpace) {//bfs가 끝나도 빈칸이 있는경우 예외처리
				ans=Math.min(ans, time);
			}
			return;
		}
		for(int i=s; i<nV; i++) {
			pick[n]=i;
			solve(i+1,n+1,pick);
		}
		//시간초과 남
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				if(map[i][j]==2 && pickCheck[i][j]==0) {
//					pickCheck[i][j]=1;
//					pick[n]=new Pair(i,j);
//					solve(n+1,pick);
//					pickCheck[i][j]=0;
//				}
//			}
//		}
	}

}
