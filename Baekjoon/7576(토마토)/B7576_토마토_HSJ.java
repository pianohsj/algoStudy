package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B7576_토마토_HSJ {
	static int M,N;
	static int[][] map;
	static Queue<Pair> q = new LinkedList<>();
	static int[] dx = {1,-1,0,0};//동서
	static int[] dy = {0,0,1,-1};//남북
	static int cnt = 0;
	public static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					q.add(new Pair(i,j));
				}
			}
		}
		tomato();
		//익지않은 토마토있는지 확인
		check();
		System.out.println(cnt);
		
	}
	
	public static void tomato(){
		int x, y, nx, ny;
		while(!q.isEmpty()) {
			//cnt = cnt+1; //일자 세기
			Pair nq = q.poll();
			x = nq.x;
			y = nq.y;
			for(int i = 0; i < 4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == -1) {
					continue;
				}
				if(map[nx][ny] == 0) {
					cnt = map[x][y];
					map[nx][ny] = map[x][y] +1;
					q.add(new Pair(nx,ny));
				}
			}
		}
	}
	
	public static void check() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					cnt = -1;
				}
			}
		}
	}

}
