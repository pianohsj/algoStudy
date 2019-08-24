package samsung;

import java.util.LinkedList;
import java.util.Scanner;

public class S2105_디저트카페_HSJ {
	static int T;
	static int N;
	static int[][] map;
	static int MAX;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	static int firstX;
	static int firstY;
	static boolean isFirst = true;
	static boolean wall = false;
	static boolean isFinish = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			N = sc.nextInt();
			map = new int[N][N];
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					map[j][k] = sc.nextInt();
				}
			}
			MAX = -1;
			for(int j = 0; j < N-2; j++) {
				for(int k = 1; k < N-1; k++) {
					LinkedList<Integer> list = new LinkedList<>();
					firstX = j;
					firstY = k;
					simulation(j,k,0,list,isFirst,1);
				}
			}
			System.out.println("#"+(i+1)+" "+MAX);
		}
	}
	
	public static void simulation(int x, int y, int dir, LinkedList<Integer> list, boolean f, int cnt) {
		if(f==true) {
			
			list.add(map[x][y]);
		}
		int nX = x + dx[dir];
		int nY = y + dy[dir];
		if(nX < 0 || nX >=N || nY < 0 || nY >= N) {
			if(f==false) {
				list.removeLast();
			}
			return;
		} 
		if(firstX == nX && firstY == nY) {
			MAX = Math.max(MAX, cnt);
			isFinish = true;
			return;
		}else {
			if(list.contains(map[nX][nY])) {
				return;
			}else {
				simulation(nX, nY, dir, list , isFirst, cnt+1);
				if(dir!=3) {
					
					if(isFinish) {
						isFinish = false;
						for(int i = cnt; i < list.size(); i++) {
							list.removeLast();
						}
					}
					simulation(nX, nY, dir+1, list, !isFirst, cnt+1);
				}
				
			}
		}
	}

}
