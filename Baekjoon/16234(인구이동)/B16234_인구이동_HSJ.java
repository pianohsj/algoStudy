package baekjoon;

import java.util.Scanner;

public class B16234_인구이동_HSJ {
	static int N, L, R;
	static int[][] map;
	static int[][] isOpen;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int cnt = -1;
	static int move = 0;
	static class Pair{
		int num, cnt, divide;
		Pair(int num, int cnt, int divide){
			this.num = num;
			this.cnt = cnt;
			this.divide = divide;
		}
	}
	static Pair[] al;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		map = new int[N][N];
		isOpen = new int[N][N];
		al = new Pair[2000];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		while(cnt != 0) {
			cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(isOpen[i][j] == 0) {
						dfs(i,j);
					}
				}
			}
			move();
		}
		
		
		System.out.println(move);
		
	}
	
	public static void dfs(int x, int y) {
		int nx = 0;
		int ny = 0;
		for(int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}else {
				if(!(Math.abs(map[nx][ny]-map[x][y]) >= L && Math.abs(map[nx][ny]-map[x][y]) <= R)) {
					continue;
				}else {
					if(isOpen[nx][ny] != 0) continue;
					if(isOpen[x][y] == 0 && isOpen[nx][ny] == 0) {
						cnt++;
						isOpen[x][y] = cnt;
						isOpen[nx][ny] = cnt;
						al[cnt] = new Pair(map[x][y], 1, 0);
						al[cnt].num += map[nx][ny];
						al[cnt].cnt += 1;
					}else {
						isOpen[nx][ny] = isOpen[x][y];
						al[isOpen[nx][ny]].num += map[nx][ny];
						al[isOpen[nx][ny]].cnt += 1;
					} 
					dfs(nx,ny);
				}
			}
		}
	}
	public static void move() {
		if(cnt != 0) {
			for(int i = 1; i < cnt+1; i++) {
				al[i].divide = al[i].num/al[i].cnt;
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(isOpen[i][j] != 0) {
						map[i][j] = al[isOpen[i][j]].divide;
					}
				}
			}
			move++;
			isOpen = new int[N][N];
			al = new Pair[2000];
		}
	}

}
