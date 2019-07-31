package baekjoon;

import java.util.Scanner;

public class B14503_로봇청소기_HSJ {

	static int N,M;
    static int[][] map = new int[50][50];
    static int count = 1;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		map[r][c] = 2; 
		dfs(r, c, d);
		
		System.out.println(count);
	}
    
	public static void dfs(int r, int c, int d) { 
		int curR = r, curC = c, curD = d;
		
		for(int i = 0; i < 4; i++) {
			curD = (curD+3)%4; //북동남서(0,1,2,3) -> 북서남동(0,3,2,1)
			curR = r + dx[curD];
			curC = c + dy[curD];
		
			//벽
			if(curR < 0 || curR >= N || curC < 0 || curC >= M || map[curR][curC] == 1) {
				continue;
			}
			if(map[curR][curC] == 0) {
				count++;
				map[curR][curC] = 2;
				dfs(curR,curC,curD);
				return;
			}
		}
		
		//후진
		curD = (curD+2)%4;
		curR = r + dx[curD];
		curC = c + dy[curD];
		
		//종료
		if(map[curR][curC] == 1) {
			return;
		}else {
			dfs(curR,curC,d); 
		}
	}

}
