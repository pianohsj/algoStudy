package baekjoon;

import java.util.Scanner;

public class B14500_테트로미노_HSJ {
	static int N, M;
	static int[][] map;
	static int sum = 0;
	static int max = 0;
	static int[][][] shape = 
		{
				{{0,0},{0,1},{0,2},{0,3}},
				{{0,0},{1,0},{2,0},{3,0}},
				{{0,0},{0,1},{1,0},{1,1}},
				{{0,0},{1,0},{2,0},{2,1}},
				{{2,0},{0,1},{1,1},{2,1}},
				{{1,0},{0,0},{0,1},{0,2}},
				{{0,0},{0,1},{0,2},{1,2}},
				{{0,0},{0,1},{1,1},{2,1}},
				{{0,1},{0,0},{1,0},{2,0}},
				{{0,2},{1,0},{1,1},{1,2}},
				{{0,0},{1,0},{1,1},{1,2}},
				{{0,0},{1,0},{1,1},{2,1}},
				{{0,1},{1,0},{1,1},{2,0}},
				{{1,0},{0,1},{1,1},{0,2}},
				{{0,0},{0,1},{1,1},{1,2}},
				{{0,0},{0,1},{0,2},{1,1}},
				{{1,0},{0,1},{1,1},{2,1}},
				{{0,1},{1,0},{1,1},{1,2}},
				{{0,0},{1,0},{2,0},{1,1}}
		};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for(int i = 0; i < 19; i++) {
			sum = 0;
			go(i);
		}
		System.out.println(max);
		
	}
	
	public static void go(int idx) {
		int x = 0;
		int y = 0;
		a: for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < 4; k++) {
					x = shape[idx][k][0]+i;
					y = shape[idx][k][1]+j;
					if(y >= M) {
						sum = 0;
						continue a;
					} 
					if(x >= N) {
						sum = 0;
						break;
					} 
					sum += map[x][y];
				}
				max = Math.max(sum, max);
				sum = 0;
			}
		}
	}

}
