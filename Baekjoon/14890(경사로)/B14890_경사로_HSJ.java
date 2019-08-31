package baekjoon;

import java.util.Scanner;

public class B14890_경사로_HSJ {
	static int N, L;
	static int[][] map, map2;
	static boolean[][] isCheck;
	static int pass = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		map = new int[N][N];
		map2 = new int[N][N];
		isCheck = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				map2[j][i] = map[i][j];
			}
		}
		for(int i = 0; i < N; i++) {
			go(map,i);
		}
		isCheck = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			go(map2,i);
		}
		System.out.println(pass);
	}
	
	public static void go(int[][] m, int idx) {
		int status = -1;//0:작을때 1: 클 때
		int same = 1;
		a: for(int i = 0; i < N-1; i++) {
			if(m[idx][i] == m[idx][i+1]) {
				same++;
				if(status == 0) {
					if(i== N-2) {
						if(same != L) return;
					} 
					if(same == L) {
						same = 0;
						status = -1;
					}
				}
			}else if(m[idx][i] > m[idx][i+1]) {
				if(status == 0) return;
				if(m[idx][i]-m[idx][i+1] > 1) return;
				isCheck[idx][i] = true;
				if(L == 1) {
					same = 0;
					continue;
				} 
				if(i== N-2) {
					if(L!=1) return;
				}
				same = 1;
				status = 0;
			}else {
				if(same < L) return;
				if(m[idx][i+1]-m[idx][i] > 1) return;
				for(int j = i; j >= i-L+1; j--) {
					if(isCheck[idx][j]) return;
					isCheck[idx][j] = true;
				}
				same = 1;
			}
		}
		pass++;
	}

}
