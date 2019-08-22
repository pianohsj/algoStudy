package samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S2117_홈방범서비스_HSJ {
	static int T, N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dirX = {1,0,-1,0};
	static int[] dirY = {0,1,0,-1};
	static int max;
	static class Pair{
		int x, y, l;
		Pair(int x, int y, int l){
			this.x = x;
			this.y = y;
			this.l = l;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			max = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					bfs(i,j);
				}
			}
			sb.append("#").append(t+1).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void bfs(int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		int k = 1;
		visited = new boolean[N][N];
		int fee = 0;
		int cnt = 0;
		int oper = k * k + (k-1) * (k-1);
		q.add(new Pair(r,c,1));
		
		while(!q.isEmpty()) {
			
			Pair p = q.remove();
			int px = p.x;
			int py = p.y;
			int pl = p.l;
			visited[px][py] = true;
			if(map[px][py] == 1) {
				fee += M;
				cnt++;
				k = pl;
				oper = k * k + (k-1) * (k-1);
				if(fee-oper >= 0) {
					if(max < cnt) max = cnt;
				}
			}
			for(int i = 0; i < 4; i++) {
				int nx = px + dirX[i];
				int ny = py + dirY[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
					continue;
				}else {
					q.add(new Pair(nx,ny,pl+1));
					visited[nx][ny] = true;
				}
			}
		}
	}

}
