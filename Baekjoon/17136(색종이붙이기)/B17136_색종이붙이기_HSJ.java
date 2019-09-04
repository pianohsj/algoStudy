package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B17136_색종이붙이기_HSJ {
	static int[][] map = new int[10][10];
	static boolean[][] visited = new boolean[10][10];
	static LinkedList<Integer> list = new LinkedList<>();
	static int[] size = {5,5,5,5,5};
	static int num = 0;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) num++;
			}
		}
		if(num == 0) System.out.println(0);
		else {
			go(0, 0);
			if(min == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(min);
		} 
	}
	
	public static void go(int count, int x) {
		if(num == 0) {
			min = Math.min(min, count);
			return;
		}
		if(count == -1) return;
		for(int i = x; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(map[i][j] == 0 || visited[i][j]) continue;
				for(int k = 0; k < 5; k++) {
					if(size[k] == 0) continue;
					if(i+k >=10 || j+k >=10) continue;
					if(!isPossible(i,j, k+1)) break;
					check(i,j, k+1, true);
					size[k]--;
					if(j+k+1 >= 10) {
						if(i+1 < 10) go(count+1, i+1);
						else {
							if(num == 0) {
								go(count+1, i);
							}else {
								go(-1,i);
							}
						}
					} else go(count+1, i);
					check(i,j, k+1, false);
					size[k]++;
				}
				return;
			}
		}
		
	}
	public static boolean isPossible(int x, int y, int size) {
		for(int i = x; i < x+size; i++) {
			for(int j = y; j < y+size; j++) {
				if(map[i][j] == 0 || visited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	public static void check(int x, int y, int size, boolean t) {
		for(int i = x; i < x+size; i++) {
			for(int j = y; j < y+size; j++) {
				visited[i][j] = t;
				if(t) num--;
				if(!t) num++;
			}
		}
	}
}
