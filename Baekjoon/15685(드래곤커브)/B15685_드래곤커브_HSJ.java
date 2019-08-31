package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B15685_µå·¡°ïÄ¿ºê_HSJ {
	static int N;
	static Pair[] dragon;
	static LinkedList<Integer> curb = new LinkedList<>();
	static int[][] map = new int[101][101];
	static Pair end;
	static int cnt = 0;
	static class Pair{
		int x, y, d, g;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		Pair(int x, int y, int d, int g){
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dragon = new Pair[N];
		for(int i = 0; i < N; i++) {
			dragon[i] = new Pair(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());
		}
		for(int i = 0; i < N; i++) {
			if(isPossible(dragon[i].x,dragon[i].y)) {
				end = new Pair(dragon[i].x, dragon[i].y);
				map[dragon[i].y][dragon[i].x] = 1;
			}
			curb.addLast(dragon[i].d);
			switch(dragon[i].d) {
			case 0:
				if(isPossible(dragon[i].x+1,dragon[i].y)) {
					end = new Pair(dragon[i].x+1, dragon[i].y);
					map[dragon[i].y][dragon[i].x+1] = 1;
				}
				break;
			case 1:
				if(isPossible(dragon[i].x,dragon[i].y-1)) {
					end = new Pair(dragon[i].x, dragon[i].y-1);
					map[dragon[i].y-1][dragon[i].x] = 1;
				}
				break;
			case 2:
				if(isPossible(dragon[i].x-1,dragon[i].y)) {
					end = new Pair(dragon[i].x-1, dragon[i].y);
					map[dragon[i].y][dragon[i].x-1] = 1;
				}
				break;
			case 3:
				if(isPossible(dragon[i].x,dragon[i].y+1)) {
					end = new Pair(dragon[i].x, dragon[i].y+1);
					map[dragon[i].y+1][dragon[i].x] = 1;
				}
				break;
			}
			go(dragon[i].g, 1);
			//move(0,dragon[i],0);
			curb.clear();
		}
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j]==1) {
					if(map[i+1][j]==1&&map[i][j+1]==1&&map[i+1][j+1]==1)
						cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
	
	public static void go(int max, int g) {
		if(g > max) return;
		for(int i = curb.size()-1; i >= 0; i--) {
			move(i, end, g);
		}
		go(max, g+1); 
	}
	
	public static void move(int i, Pair e, int g) {
		switch(curb.get(i)) {
		case 0:
			if(isPossible(e.x, e.y-1)) {
				map[e.y-1][e.x] = 1;
			}
			curb.addLast(1);
			end = new Pair(e.x, e.y-1);
			break;
		case 1:
			if(isPossible(e.x-1, e.y)) {
				map[e.y][e.x-1] = 1;
			}
			curb.addLast(2);
			end = new Pair(e.x-1, e.y);
			break;
		case 2:
			if(isPossible(e.x, e.y+1)) {
				map[e.y+1][e.x] = 1;
			}
			curb.addLast(3);
			end = new Pair(e.x, e.y+1);
			break;
		case 3:
			if(isPossible(e.x+1, e.y)) {
				map[e.y][e.x+1] = 1;
			}
			curb.add(0);
			end = new Pair(e.x+1, e.y);
			break;
		}
	}
	
	public static boolean isPossible(int x, int y) {
		if(x>=0 && x <= 100 && y >=0 && y <=100) return true;
		else return false;
	}

}
