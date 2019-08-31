package baekjoon;

import java.util.Scanner;

public class B17070_파이프옮기기1_HSJ {
	static int N;
	static int[][] map;
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		dfs(0,1,0);
		System.out.println(cnt);
	}
	
	public static void dfs(int x, int y, int status) {
		if(x == N-1 && y == N-1) {
			cnt++;
			return;
		}
		switch(status) {
		case 0:
			if(isOk(x,y,0)) dfs(x,y+1,0);
			if(isOk(x,y,2)) dfs(x+1,y+1,2);	
			break;
		case 1:
			if(isOk(x,y,1)) dfs(x+1,y,1);
			if(isOk(x,y,2)) dfs(x+1,y+1,2);	
			break;
		case 2: 
			if(isOk(x,y,0)) dfs(x,y+1,0);
			if(isOk(x,y,1)) dfs(x+1,y,1);	
			if(isOk(x,y,2)) dfs(x+1,y+1,2);	
			break;
		}
	}
	
	public static boolean isOk(int x, int y, int s) {
		switch(s) {
		case 0:
			if(y+1 >= N) return false;
			else if(map[x][y+1] == 1) return false;
			else return true;
		case 1:
			if(x+1 >= N) return false;
			else if(map[x+1][y] == 1) return false;
			else return true;
		case 2: 
			if(y+1 >= N || x+1 >= N) return false;
			else if(map[x][y+1]==1 || map[x+1][y]==1 || map[x+1][y+1]==1) return false;
			else return true;
		default:
			return false;
		}

	}
}
