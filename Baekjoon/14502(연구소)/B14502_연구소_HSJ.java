package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B14502_연구소_HSJ {
	static int N,M;
	static int[][] map, v;
	static int max;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static LinkedList<Pair> virus = new LinkedList<>();
    static LinkedList<Pair> copy = new LinkedList<>();
    static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		v = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				v[i][j] = map[i][j];
				if(map[i][j] == 2) {
					virus.add(new Pair(i,j));
					copy.add(new Pair(i,j));
				}
			}
		}
		max = Integer.MIN_VALUE;
		dfs(map, 0);
		System.out.println(max);
	}
	
	public static void dfs(int[][] m, int cnt) {
		if(cnt == 3) {
			for(int i=0; i< N; i++) {
		        for(int j=0; j < M; j++) {
		            v[i][j] = m[i][j];
		        }
			}
			virus(v);
			return;
		}else {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(m[i][j] == 0) {
						m[i][j] = 1;
						dfs(m, cnt+1);
						m[i][j] = 0;
					}
				}
			}
		}
	}
	
	public static void virus(int[][] v) {
		while(!virus.isEmpty()) {
			Pair curV = virus.remove();
			int curX, curY;
			for(int j = 0; j < 4; j++) {
				curX = curV.x + dx[j];
				curY = curV.y + dy[j];
			
				//벽
				if(curX < 0 || curX >= N || curY < 0 || curY >= M || v[curX][curY] == 1) {
					continue;
				}
				if(v[curX][curY] == 0) {
					v[curX][curY] = 2;
					virus.add(new Pair(curX,curY));
				}
			}
		}
		getSafe(v);
		//초기화
		for(Pair p : copy) {
			virus.add(new Pair(p.x, p.y));
		}
		//virus = (LinkedList<Pair>) copy.clone();
		//v = map.clone();
		
	}
	public static void getSafe(int[][] v) {
		int safeArea = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(v[i][j] == 0) {
					safeArea++;
				}
			}
		}
		if(max < safeArea) max = safeArea;
		
	}

}
