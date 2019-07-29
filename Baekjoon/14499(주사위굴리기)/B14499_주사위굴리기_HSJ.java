package baekjoon;

import java.util.Scanner;

public class B14499_주사위굴리기_HSJ {
	static int N, M; //지도 세로, 가로
	static int r, c; //시작위치
	static int K; //명령개수
	static int[][] map;
	static int[] dice; //지도
	static int[] dir; //방향
	static int[] dx = { 1, -1, 0, 0 };//동서
    static int[] dy = { 0, 0, -1, 1 };//북남
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[N][M];
		dir = new int[K];
		dice = new int[7];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for(int i = 0; i < K; i++) {
			dir[i] = sc.nextInt();
		}
		
		for(int i = 0; i < dir.length; i++) {
			simulation(dir[i]);
		}
	}
	
	public static void simulation(int d) {
		//방향이동
		r = r + dy[d-1];
		c = c + dx[d-1];
		if(r < 0 || r >= N || c < 0 || c >= M) {//벽일때 
			r = r - dy[d-1];
			c = c - dx[d-1];
			return;
		}else {
			changeDice(d);//주사위 map 이동
			//주사위 밑면 값이 0인지 아닌지 확인하기
			if(map[r][c] == 0) {
				map[r][c] = dice[6];
			}else {
				dice[6] = map[r][c];
				map[r][c] = 0;
			}
			System.out.println(dice[1]);
		}
	}
	
	public static void changeDice(int d) {
		int[] clone = dice.clone();//dice 복사
		
		if(d == 1) {//동
			dice[1] = clone[4];
			dice[3] = clone[1];
			dice[4] = clone[6];
			dice[6] = clone[3];
		}else if(d == 2) {//서
			dice[1] = clone[3];
			dice[3] = clone[6];
			dice[4] = clone[1];
			dice[6] = clone[4];
		}else if(d == 3) {//북
			dice[1] = clone[5];
			dice[2] = clone[1];
			dice[5] = clone[6];
			dice[6] = clone[2];
		}else if(d == 4) {//남
			dice[1] = clone[2];
			dice[2] = clone[6];
			dice[5] = clone[1];
			dice[6] = clone[5];
		}
	}

}
