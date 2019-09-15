package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B3055_≈ª√‚_HSJ {
	static int R, C;
	static char[][] map;
	static LinkedList<Pair> water = new LinkedList<>();
	static LinkedList<Pair> dochi = new LinkedList<>();
	static boolean[][] visited; 
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int max = 0;
	static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String s = sc.next();
			for(int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == '*') water.add(new Pair(i,j));
				else if(map[i][j]== 'S') {
					dochi.add(new Pair(i,j));
					visited[i][j] = true;
				} 
			}
		}
		go();
		if(max == 0) System.out.println("KAKTUS");
		else System.out.println(max);
	}
	
	public static void go() {
		int cnt = 0;
		while(!dochi.isEmpty()) {
			cnt++;
			int size = water.size();
			for(int i = 0; i < size; i++) {
				Pair p = water.remove();
				for(int j = 0; j < 4; j++) {
					int w_nx = p.x;
					int w_ny = p.y;
					w_nx += dx[j];
					w_ny += dy[j];
					if(w_nx < 0 || w_ny < 0 || w_nx >= R || w_ny >= C) continue;
					else if(map[w_nx][w_ny] == '.') {
						map[w_nx][w_ny] = '*';
						water.add(new Pair(w_nx,w_ny));
					}
				}
			}
			
			int dochi_size = dochi.size();
			for(int i = 0; i < dochi_size; i++) {
				Pair p = dochi.remove();
				for(int j = 0; j <4; j++) {
					int d_nx = p.x;
					int d_ny = p.y;
					d_nx += dx[j];
					d_ny += dy[j];
					if(d_nx < 0 || d_ny < 0 || d_nx >= R || d_ny >= C || 
						map[d_nx][d_ny]=='X' || visited[d_nx][d_ny]) continue;
					else if(map[d_nx][d_ny] == '.') {
						dochi.add(new Pair(d_nx,d_ny));
					}else if(map[d_nx][d_ny] == 'D') {
						max = Math.max(max, cnt);
					}
					visited[d_nx][d_ny] = true;
				}
			}
		}
	}
}
