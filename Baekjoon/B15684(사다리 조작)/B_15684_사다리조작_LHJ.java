import java.util.*;
public class Main {
	static int N,M,H;
	static int[][] map;
	static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		M=sc.nextInt();
		H=sc.nextInt();
		N=sc.nextInt();
		map=new int[N][M];
		for(int i=0; i<H; i++) {
			map[sc.nextInt()-1][sc.nextInt()-1]=1;
		}
		solve(0,0);
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
		
	}
	public static boolean check() {
		boolean isPossible=true;
		for(int k=0; k<M; k++) {
			int x=0;
			int y=k;
			while(x<=N-1) {
				if(map[x][y]==0 && y!=0 && map[x][y-1]==1) {
					x+=1;
					y-=1;
				}else if(map[x][y]==1) {
					x+=1;
					y+=1;
				}else if(map[x][y]==0) {
					x+=1;
				}
			}
			if(y!=k) {
				isPossible=false;
				break;
			}
		}
		return isPossible;
	}
	public static void solve(int n, int s) {
		if(n>3) {
			return;
		}else {
			if(check()) {
				ans=Math.min(ans, n);
				return;
			}
		}
		for(int i=s; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0 && j!=M-1 &&
						((j==0 && map[i][j+1]==0)
								||(j==M-2 && map[i][j-1]==0) 
								|| (j!=0 && j!=M-2 && map[i][j+1]==0 && map[i][j-1]==0))) {
					
					map[i][j]=1;
					solve(n+1,i);
					map[i][j]=0;
				}
			}
		}
	}

}
