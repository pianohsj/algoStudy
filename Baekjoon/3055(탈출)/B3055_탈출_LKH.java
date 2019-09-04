import java.util.*;
public class Main {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static class Pair{
		int x, y, t;
		Pair(int x, int y, int t){
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
	static int min = Integer.MAX_VALUE;
	static boolean isFind = false;
	public static void move(String map[][], int timeTable[][], int sx, int sy, int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx, sy, 0));
		int visited[][] = new int[r][c];
		visited[sy][sx] = 1;
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			int time = p.t;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>c-1 || ny>r-1 || visited[ny][nx] == 1) continue;
				else if(map[ny][nx].equals("X") || map[ny][nx].equals("*")) continue;
				else {
					if(map[ny][nx].equals("D")) {
						isFind = true;
						if(min>time+1) min = time+1;
						break;
					}
					if(timeTable[ny][nx]<=time+1 && timeTable[ny][nx]>0) continue;
					visited[ny][nx] = 1;
					q.add(new Pair(nx, ny, time+1));
				}
			}
		}
	}
	public static void spreadWater(String map[][], int tTable[][], int sx, int sy, int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx, sy, 0));
		int visited[][] = new int[r][c];
		visited[sy][sx] = 1;
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			int time = p.t;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>c-1 || ny>r-1 || visited[ny][nx] == 1) continue;
				else if(map[ny][nx].equals("X") || map[ny][nx].equals("D") || map[ny][nx].equals("S")) continue;
				else if(tTable[ny][nx] == -1) continue;
				else {
					if(tTable[ny][nx]>0) {
						if(tTable[ny][nx]>time+1) tTable[ny][nx] = time+1; 
					}
					else tTable[ny][nx] = time+1;
					visited[ny][nx] = 1;
					q.add(new Pair(nx, ny, time+1));
				}
				
			}
		}
		// 고슴도치 출발
		
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int r = scn.nextInt();
		int c = scn.nextInt();
		String map[][] = new String[r][c];
		int gx = 0;
		int gy = 0;
		int wx = 0;
		int wy = 0;
		for(int i=0; i<r; i++) {
			String str = scn.next();
			map[i] = str.split("");
			for(int j=0; j<c; j++) {
				if(map[i][j].equals("S")) {
					gx = j;
					gy = i;
				}
			}
		}
		int tTable[][] = new int[r][c];
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j].equals("*")) {
					tTable[i][j] = -1;
					spreadWater(map, tTable, j, i, r, c);
				}
			}
		}
		move(map, tTable, gx, gy, r, c);
		if(isFind) System.out.println(min);
		else System.out.println("KAKTUS");	
	}	
}