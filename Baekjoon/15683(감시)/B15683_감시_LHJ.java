import java.util.*;
public class Main {
	static int N,M;
	static int[][] map;
	static Pair[] cc;
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	static int ans=Integer.MAX_VALUE;
	public static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		map=new int[N][M];
		int num=0;
		LinkedList<Pair> cctv=new LinkedList<Pair>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]>=1 && map[i][j]<=5) {
					cctv.add(new Pair(i,j));
					num+=1;
				}
			}
		}
		cc=cctv.toArray(new Pair[num]);
		solve(0, new int[num]);
		System.out.println(ans);
	}
	public static void go(int[][] copy, int x, int y, int d) {
		int nx=x;
		int ny=y;
		while(true) {
			nx=nx+dx[d];
			ny=ny+dy[d];
			if(nx>=0 && ny>=0 && nx<N && ny<M) {
				if(copy[nx][ny]==0) {
					copy[nx][ny]=7;
				}else if(copy[nx][ny]==6) {
					break;
				}
			}else {
				break;
			}
		}
	}
	public static void draw(int[][] copy, int[] pick) {
		for(int k=0; k<pick.length; k++) {
			if(map[cc[k].x][cc[k].y]==1) {
				if(pick[k]==0) {
					go(copy, cc[k].x, cc[k].y, 1);
				}else if(pick[k]==1) {
					go(copy, cc[k].x, cc[k].y, 2);
				}else if(pick[k]==2) {
					go(copy, cc[k].x, cc[k].y, 3);
				}else if(pick[k]==3) {
					go(copy, cc[k].x, cc[k].y, 0);
				}
			}else if(map[cc[k].x][cc[k].y]==2) {
				if(pick[k]==0) {
					go(copy, cc[k].x, cc[k].y, 1);
					go(copy, cc[k].x, cc[k].y, 3);
				}else if(pick[k]==1) {
					go(copy, cc[k].x, cc[k].y, 2);
					go(copy, cc[k].x, cc[k].y, 0);
				}	
			}else if(map[cc[k].x][cc[k].y]==3) {
				if(pick[k]==0) {
					go(copy, cc[k].x, cc[k].y, 0);
					go(copy, cc[k].x, cc[k].y, 1);
				}else if(pick[k]==1) {
					go(copy, cc[k].x, cc[k].y, 1);
					go(copy, cc[k].x, cc[k].y, 2);
				}else if(pick[k]==2) {
					go(copy, cc[k].x, cc[k].y, 2);
					go(copy, cc[k].x, cc[k].y, 3);
				}else if(pick[k]==3) {
					go(copy, cc[k].x, cc[k].y, 3);
					go(copy, cc[k].x, cc[k].y, 0);
				}
			}else if(map[cc[k].x][cc[k].y]==4) {
				if(pick[k]==0) {
					go(copy, cc[k].x, cc[k].y, 3);
					go(copy, cc[k].x, cc[k].y, 0);
					go(copy, cc[k].x, cc[k].y, 1);
				}else if(pick[k]==1) {
					go(copy, cc[k].x, cc[k].y, 0);
					go(copy, cc[k].x, cc[k].y, 1);
					go(copy, cc[k].x, cc[k].y, 2);
				}else if(pick[k]==2) {
					go(copy, cc[k].x, cc[k].y, 1);
					go(copy, cc[k].x, cc[k].y, 2);
					go(copy, cc[k].x, cc[k].y, 3);
				}else if(pick[k]==3) {
					go(copy, cc[k].x, cc[k].y, 2);
					go(copy, cc[k].x, cc[k].y, 3);
					go(copy, cc[k].x, cc[k].y, 0);
				}
			}else if(map[cc[k].x][cc[k].y]==5) {
				go(copy, cc[k].x, cc[k].y, 1);
				go(copy, cc[k].x, cc[k].y, 2);
				go(copy, cc[k].x, cc[k].y, 3);
				go(copy, cc[k].x, cc[k].y, 0);
			}
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(copy[i][j]);
//				}
//				System.out.println("");
//			}
//			System.out.println("");
		}
	}
	public static void solve(int n, int[] pick) {
		if(n==cc.length) {
			int[][] copy=new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					copy[i][j]=map[i][j];
				}
			}
			draw(copy,pick);
			int count=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(copy[i][j]==0) {
						count+=1;
					}
				}
			}
			ans=Math.min(count, ans);
			return;
		}
		for(int d=0; d<4; d++) {
			pick[n]=d;
			solve(n+1, pick);
		}
	}

}
