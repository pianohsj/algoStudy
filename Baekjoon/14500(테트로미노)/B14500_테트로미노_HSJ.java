package baekjoon;

import java.util.Scanner;

public class B14500_테트로미노_HSJ {
	static int N;
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
				{{0,0},{1,0},{1,1},{1,2}},
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
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for(int i = 0; i < 19; i++) {
			go(i);
		}
		System.out.println(max);
		
	}
	
	public static void go(int idx) {
		int x = 0;
		int y = 0;
		a: for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < 4; k++) {
					x = shape[idx][k][0]+i;
					y = shape[idx][k][1]+j;
					if(y >= N) continue a;
					if(x >= N) break;
					sum += map[x][y];
				}
				max = Math.max(sum, max);
				sum = 0;
			}
		}
	}

}
