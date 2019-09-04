package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B3055_≈ª√‚_HSJ {
	static int R, C;
	static char[][] map;
	static LinkedList<Pair> water = new LinkedList<>();
	static Pair dochi;
	static LinkedList<Pair> ll = new LinkedList<>();
	static int[] dx = {1,-1,0,0};
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
		for(int i = 0; i < R; i++) {
			String s = sc.next();
			for(int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == '*') water.add(new Pair(i,j));
				else if(map[i][j]== 'S') {
					dochi = new Pair(i,j);
				} 
			}
			
		}
		flood();
		dochigo(dochi.x, dochi.y, 0);
		if(max == 0) System.out.println("KAKTUS");
		else System.out.println(max);
	}
	
	public static void flood() {
		while(!water.isEmpty()) {
			Pair p = water.remove();
			System.out.println(p.x+" "+ p.y);
			int w_nx = p.x;
			int w_ny = p.y;
			for(int i = 0; i < 4; i++) {
				w_nx += dx[i];
				w_ny += dy[i];
				if(w_nx < 0 || w_ny < 0 || w_nx >= R || w_ny >= C || map[w_nx][w_ny]=='*') continue;
				else if(map[w_nx][w_ny] == '.') {
					map[w_nx][w_ny] = '*';
					ll.add(new Pair(w_nx,w_ny));
				}
			}
		}
	}
	public static void dochigo(int x, int y, int time) {
		int d_nx = x;
		int d_ny = y;
		for(int i = 0; i < 4; i++) {
			d_nx += dx[i];
			d_ny += dy[i];
			if(d_nx < 0 || d_ny < 0 || d_nx >= R || d_ny >= C || 
					map[d_nx][d_ny] == 'X') continue;
			else if(map[d_nx][d_ny] == 'D') {
				max = Math.max(max, time);
				return;
			}
			else if(map[d_nx][d_ny] == '*') continue;
			else {
				for(int j = 0; j < ll.size(); j++) {
					water.add(ll.get(j));
				}
				ll = new LinkedList<>();
				flood();
				dochigo(d_nx,d_ny, time+1);
			}
		}
	}
}
