import java.util.*;
public class Main {
	
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static int clean = 1;
	// 북(0) 동(1) 남(2) 서(3)
	public static int changeDir(int dir, int status) {
		int next_dir = 0;
		if(status == 0) {
			if(dir == 0) {
				next_dir = 3;
			}
			else if(dir == 1) {
				next_dir = 0;
			}
			else if(dir == 2) {
				next_dir = 1;
			}
			else if(dir == 3) {
				next_dir = 2;
			}
		}
		else {
			if(dir == 0) {
				next_dir = 2;
			}
			else if(dir == 1) {
				next_dir = 3;
			}
			else if(dir == 2) {
				next_dir = 0;
			}
			else if(dir == 3) {
				next_dir = 1;
			}
		}
		
		return next_dir;
	}
	public static void simulation(int map[][], int visited[][], int sy, int sx, int dir, int n, int m) {
		
		int tmp_dir = 0;
		int cnt = 0;
		visited[sy][sx] = 1;
		while(true) {
			if(cnt == 4) {
				tmp_dir = changeDir(dir,1);
				int bx = sx+dx[tmp_dir];
				int by = sy+dy[tmp_dir];
				if(map[by][bx] == 1) break;
				else {
					sx = bx;
					sy = by;
					cnt = 0;
				}
			}
			dir = changeDir(dir,0);
			int nx = sx+dx[dir];
			int ny = sy+dy[dir];
			if(map[ny][nx] == 1 || visited[ny][nx] == 1) {
				cnt++;
			}
			else {
				sx = nx;
				sy = ny;
				visited[ny][nx] = 1;
				clean++;
				cnt = 0;
			}
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int map[][] = new int[n][m];
		int visited[][] = new int[n][m];
		int sy = scn.nextInt();
		int sx = scn.nextInt();
		int d = scn.nextInt();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = scn.nextInt();
			}
		}
		// 입력부
		
		simulation(map, visited, sy, sx, d, n, m);
		System.out.println(clean);
	}	
}