import java.util.*;
public class Solution {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int max = Integer.MIN_VALUE;
	static public class Pair{
		int x, y, h, len;
		Pair(int x, int y, int h, int len){
			this.x = x;
			this.y = y;
			this.h = h;
			this.len = len;
		}
	}
	public static void dfs(int map[][], int sx, int sy, int h, int n) {
		Stack<Pair> s = new Stack<>();
		s.add(new Pair(sx, sy, h, 1));
		int tmp_max = Integer.MIN_VALUE;
		while(!s.isEmpty()) {
			Pair p = s.pop();
			int x = p.x;
			int y = p.y;
			int nH = p.h;
			int len = p.len;
			if(tmp_max<len) tmp_max = len;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1 || nH<=map[ny][nx]) continue;
				else {
					s.add(new Pair(nx, ny, map[ny][nx], len+1));
				}
			}
		}
		if(max<tmp_max) max = tmp_max;
	}
	public static void simulation(int map[][], LinkedList<Pair> list, int maxH, int k, int n) {
		while(!list.isEmpty()) {
			Pair p = list.remove();
			int sx = p.x;
			int sy = p.y;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(i==sy && j == sx) {
						dfs(map, sx, sy, maxH, n);
					}
					else {
						for(int r=0; r<=k; r++) {
							map[i][j] -=r;
							dfs(map, sx, sy, maxH, n);
							map[i][j] +=r;
						}
					}
					
				}
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int t=1; t<=tc; t++) {
			int n = scn.nextInt();
			int map[][] = new int[n][n];
			int k = scn.nextInt();
			LinkedList<Pair> list = new LinkedList<>();
			int maxH = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					map[i][j] = scn.nextInt();
					if(maxH<map[i][j]) {
						maxH = map[i][j];
					}
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(maxH==map[i][j]) {
						list.add(new Pair(j, i, maxH, 0));
					}
				}
			}
			
			simulation(map, list, maxH, k, n);
			System.out.println("#"+t+" "+max);
			max = 0;			
		}
	}
}
