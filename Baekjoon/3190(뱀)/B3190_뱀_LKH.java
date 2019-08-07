package test;
import java.util.*;
public class Note {
	static class Pair{
		int x;
		int y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int playTime = 0;
	public static int changeDir(String dir, int d) {
		int nd = 0;
		// 1 快, 2 谅, 3惑, 4 窍
		if(d==1) {
			if(dir.equals("L")) {
				nd = 3;
			}
			else {
				nd = 4;
			}
		}
		else if(d==2) {
			if(dir.equals("L")) {
				nd = 4;
			}
			else {
				nd = 3;
			}
		}
		else if(d==3) {
			if(dir.equals("L")) {
				nd = 2;
			}
			else {
				nd = 1;
			}
		}
		else if(d==4) {
			if(dir.equals("L")) {
				nd = 1;
			}
			else {
				nd = 2;
			}
		}
		return nd;
	}
	public static void play(int map[][], int time[], String dir[], int r, int n) {
		int limit = 0;
		int round = 0;
		int cnt = 0;
		int x = 0;
		int y = 0;
		int d = 1;
		boolean end = false;
		map[y][x] = 2;
		LinkedList<Pair> list = new LinkedList<>();
		list.add(new Pair(x,y));
		while(true) {	
			if(end) break;
			
			int nx = x;
			int ny = y;
			if(d == 1) {
				nx = nx+1;
			} // 坷弗率
			else if(d == 2) {
				nx = nx-1;
			} // 哭率
			else if(d == 3) {
				ny = ny-1;
			} // 困率
				else if(d == 4) {
					ny = ny+1;
				} // 酒贰率
				if(nx<0 || nx> n-1 || ny<0 || ny> n-1) {
					end = true;
					
				} // 寒俊 何碟磨 版快
				else {
					if(map[ny][nx] == 0) {
						Pair p = list.removeFirst(); // 部府甫 力芭
						int ex = p.x;
						int ey = p.y;
						map[ey][ex] = 0;
						list.addLast(new Pair(nx,ny));
						map[ny][nx] = 2;
					} // 后沫老 版快
					else if(map[ny][nx] == 2) {
						end = true;
						//playTime += limit;
						
					}
					else if(map[ny][nx] == 1){
						map[ny][nx] = 2;
						list.add(new Pair(nx, ny));
					}
					x = nx;
					y = ny;	
				}
				
				limit++; // 1檬 刘啊

			if(limit == time[round]) {
				d=changeDir(dir[round], d);
				if(round<time.length-1)round++;
			}
		
		}
		playTime = limit;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int k = scn.nextInt();
		int map[][] = new int[n][n];
		for(int i=0; i<k; i++) {
			int h = scn.nextInt();
			int w = scn.nextInt();
			map[h-1][w-1] = 1;
		}
		
		int r = scn.nextInt();
		int t[] = new int[r];
		String d[] = new String[r];
		for(int i=0; i<r; i++) {
			t[i] = scn.nextInt();
			String str = scn.next();
			str.replace(" ", "");
			d[i] = str;	
		}
		play(map, t, d, r, n);
		System.out.println(playTime);	
	}

}
