package baekjoon;

import java.util.Scanner;

public class B14889_스타트와링크_HSJ {
	static int N;
	static int[][] S;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = new int[N][N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				S[i][j] = sc.nextInt();
			}
		}
		dfs(0,0);
		System.out.println(min);
	}
	
	static void dfs(int x, int depth) {
		if(depth >= N/2) {
			int startSum=0, linkSum=0;
			for(int i=0; i<N; i++) {
	            for(int j=0; j<N; j++) {
	                if(visited[i] && visited [j])//true->start
	                	startSum += S[i][j];
	 
	                if(!visited[i] && !visited[j])//false->link
	                	linkSum += S[i][j];
	            }
	        }
			min = Math.min(min, Math.abs(startSum - linkSum));
			return;
		}
		
		for(int i=x; i<N; i++) {
				if(!visited[i]) {
	                visited[i] = true;
	                dfs(i+1, depth+1);
	                visited[i] = false;
	            }
        }
	}
}
