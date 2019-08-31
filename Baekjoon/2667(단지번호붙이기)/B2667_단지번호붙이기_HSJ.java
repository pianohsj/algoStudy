package baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B2667_단지번호붙이기_HSJ {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static ArrayList<Integer> cnt = new  ArrayList<>();
	static int house = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		String Line;
		for(int i = 0; i < N; i++) {
			Line = sc.next();
			for(int j = 0; j < N; j++) {
				map[i][j] = Line.charAt(j)-'0';
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]||map[i][j]==0
						) continue;
				go(i,j);
				cnt.add(house);
				house = 0;
			}
		}
		Collections.sort(cnt);
		System.out.println(cnt.size());
		for(int i = 0; i < cnt.size(); i++) {
			System.out.println(cnt.get(i));
		}
	}
	
	public static void go(int x, int y) {
		int nx = 0;
		int ny = 0;
		visited[x][y] = true;
		house++;
		for(int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny]==0) 
				continue;
			else {
				if(visited[nx][ny]) continue;
				go(nx, ny);
			}
		}
		
	}

}
