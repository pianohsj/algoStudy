import java.util.*;
public class Main {
	static int dx[] = {1,1,0};
	static int dy[] = {0,1,1};
	static int ans = 0;
	public static void dfs(int map[][], int sx, int sy, int nx, int ny, int dir, int n) {
		if(nx == n-1 && ny == n-1) {
			ans++;
			return;
		}
		for(int i=0; i<3; i++) {
			nx = sx+dx[i];
			ny = sy+dy[i];
			if(nx<0 || nx>n-1 || ny<0 || ny>n-1) continue;
			else if(map[ny][nx] == 1) continue;
			else if((dir == 0 && i ==2) || (dir == 2 && i ==0))continue;
			else if(i == 1) {
				boolean check = true;
				for(int j=0; j<3; j++) {
					int x = sx+dx[j];
					int y = sy+dy[j];
					if(x<0 || x>n-1 || y<0 || y>n-1) {
						check = false;
						break;
					}
					else if(map[y][x] == 1) {
						check = false;
						break;
					}
				}
				if(!check) continue;
				else {
					if(map[ny][nx] != 1)
						dfs(map, nx, ny, nx, ny, i, n);
				}
			}
			else {
				dfs(map, nx, ny, nx, ny, i, n);
			}
		}
		
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int map[][] = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = scn.nextInt();
			}
		}
		dfs(map, 1, 0, 1, 0, 0, n);
		System.out.println(ans);		
	}
}