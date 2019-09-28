import java.util.*;
public class Main {
	static int dx[] = {0,0,0,1,-1};
	static int dy[] = {0,-1,1,0,0};
	static int ans = 0;
	public static class Shark{
		int s, d, z;
		Shark(int s, int d, int z){
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	public static void next(Shark newMap[][], int sx, int sy, int s, int d, int z, int r, int c) {
		int moving = 0;
		int nx = sx;
		int ny = sy;
		int nd = d;
		while(true) {
			if(moving == s) {
				break;
			}
			nx = nx+dx[nd];
			ny = ny+dy[nd];
			if(nx<1 || ny<1 || nx>c || ny>r) {
				nx = nx-dx[nd];
				ny = ny-dy[nd];
				if(nd == 1) nd = 2;
				else if(nd == 2) nd = 1;
				else if(nd == 3) nd = 4;
				else if(nd == 4) nd = 3;
				continue;
			}

			moving++;
		}
		if(newMap[ny][nx]!=null) {
			if(newMap[ny][nx].z<z) newMap[ny][nx] = new Shark(s, nd, z);
		}
		else {
			newMap[ny][nx] = new Shark(s, nd, z);
		}
	}
	public static Shark[][] move(Shark map[][], int r, int c) {
		Shark[][] newMap = new Shark[r+1][c+1];
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				if(map[i][j]!=null) {
					next(newMap, j, i, map[i][j].s, map[i][j].d, map[i][j].z, r, c);
				}
			}
		}
		return newMap;
	}
	public static void simulation(Shark map[][], int r, int c) {
		int man = 1;
		int result = 0;
		while(man<c+1) {
			for(int i=0; i<r+1; i++) {
				if(map[i][man] != null) {
					result += map[i][man].z;
					map[i][man] = null;
					break;
				}
			}
			map = move(map, r, c);
			man++;
		}
		ans = result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int r = scn.nextInt();
		int c = scn.nextInt();
		int m = scn.nextInt();
		Shark map[][] = new Shark[r+1][c+1];
		for(int i=0; i<m; i++) {
			int y = scn.nextInt();
			int x = scn.nextInt();
			int s = scn.nextInt();
			int d = scn.nextInt();
			int z = scn.nextInt();
			map[y][x] = new Shark(s,d,z);
		}
		
		simulation(map, r, c);
		System.out.println(ans);
	}
}