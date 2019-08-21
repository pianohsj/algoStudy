package samsung;

import java.util.LinkedList;
import java.util.Scanner;

public class S1949_등산로조성_HSJ {
	static int T;
	static int N, K;
	static int[][] map;
	static int max;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static LinkedList<Pair> list = new LinkedList<>();
	static int answer;
	static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			N = sc.nextInt();
			K = sc.nextInt();
			max = Integer.MIN_VALUE;
			map = new int[N][N];
			answer = 1;
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					map[j][k] = sc.nextInt();
					max = Math.max(max, map[j][k]);
				}
			}
			
			//max 값 저장하기
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(map[j][k] == max) {
						list.add(new Pair(j,k));
					}
				}
			}
			//max아니면 차례대로 깎기
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(map[j][k] != max && map[j][k] != 1) {
						Pair curL;
						if(map[j][k]-K > 0) {
							map[j][k] -= K;
							for(int q = 0; q < list.size(); q++) {
								curL = list.get(q);
								dfs(curL.x,curL.y,1);
							}
							map[j][k] += K;
						}else {
							int tmp = map[j][k];
							map[j][k] = 1;
							for(int q = 0; q < list.size(); q++) {
								curL = list.get(q);
								dfs(curL.x,curL.y,1);
							}
							map[j][k] = tmp;
						}
						
						
					}
				}
			}
			System.out.println(answer);
		}
	}
	
	public static void dfs(int x, int y, int cnt) {
		int curX, curY;
		//Pair curL;
		if(map[x][y] == 1) {
			answer = Math.max(answer, cnt);
			return;
		}else {
			//for(int i = 0; i < list.size(); i ++) {
			//curL = list.get(i);
			for(int j = 0; j < 4; j++) {
				curX = x + dx[j];
				curY = y + dy[j];
				if(curX < 0 || curX >= N || curY < 0 || curY >=N) {
					continue;
				}
				if(map[curX][curY] < map[x][y]) {
					dfs(curX, curY, cnt+1);
				}
			}
		//}
		
		}
		
		
	}

}
	