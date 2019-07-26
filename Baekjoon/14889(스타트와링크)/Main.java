import java.util.*;
public class Main {
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int MIN=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N=sc.nextInt();
		visited=new boolean[N];
		map=new int[N][N];
		
		for(int i=0; i<N; i++) {
			visited[i]=false;
			for(int j=0; j<N; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		solve(0,0);
		System.out.print(MIN);
	}
	public static void solve(int befo, int depth) {
		if(depth==N/2) {
			int team1=0,team2=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i]==visited[j] && visited[i]==true) {
						team1+=map[i][j];
					}else if(visited[i]==visited[j] && visited[i]==false) {
						team2+=map[i][j];
					}
				}
			}
			MIN=Math.min(Math.abs(team1-team2),MIN);
//			for(int i=0; i<N; i++) {
//				System.out.print(visited[i]+" ");
//			}
//			System.out.print(Math.abs(team1-team2)+"\n");
			return;
		}
		for(int i=befo; i<N; i++) {
			if(visited[i]==false) {
				visited[i]=true;
				solve(i,depth+1);
				visited[i]=false;
			}
		}
	}
}
