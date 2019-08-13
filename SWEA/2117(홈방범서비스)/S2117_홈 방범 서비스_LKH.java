import java.util.*;
public class Solution {
	public static class Pair{
		int x, y, area;
		Pair(int x, int y, int area){
			this.x = x;
			this.y = y;
			this.area = area;
		}
	}
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int maxHouse = 0;
	public static void printMap(int map[][], int sx, int sy, int m, int n) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx, sy, 1));
		int visited[][] = new int[n][n];
		int vCnt = 1;
		int hCnt = 0;
		if(map[sy][sx] == 1)hCnt = 1;
		int check = 0;
		visited[sy][sx] = 1;
		int area = 0;
		while(!q.isEmpty()) {
			if(vCnt == n*n) break;
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			area = p.area;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1 || visited[ny][nx] == 1) continue;
				else {
					if(map[ny][nx] == 1) {
						hCnt++;
						if(hCnt*m>=(area*area)+((area+1)*(area+1))) {
							check = hCnt;
						}
					}
					q.add(new Pair(nx, ny, area+1));
					visited[ny][nx] = 1;
					vCnt++;
				}
			}
		}
		
		if(maxHouse<check) maxHouse = check;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int t=1; t<=tc; t++) {
			int n = scn.nextInt();
			int m = scn.nextInt();
			int map[][] = new int[n][n];
			LinkedList<Pair> list = new LinkedList<>();
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					map[i][j] = scn.nextInt();
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					printMap(map, j, i, m, n);
				}
			}
            if(maxHouse == 0) maxHouse = 1;
			System.out.println("#"+t+" "+maxHouse);
			maxHouse = 0;
		}
	}
}